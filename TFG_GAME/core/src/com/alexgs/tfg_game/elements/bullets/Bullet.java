package com.alexgs.tfg_game.elements.bullets;

import com.alexgs.tfg_game.elements.Element;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.alexgs.tfg_game.solids.Solid;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Bullet extends Element {
    MainScreen lvl;

    private float timeBeforeCulling;
    private float timeExisting;

// TODO: EXPERIMENTAL CODE! test and troubleshoot is prioritized and mandatory
    protected int dmg;
    protected int bulletSpeed;

    protected Animation<TextureRegion> projectileTexture;

    public Bullet(float x, float y, Stage s, MainScreen lvl, 
    int dmg, int bulletSpeed, float timeBeforeCulling) {
        super(x, y, s);

        this.lvl = lvl;

// TODO: EXPERIMENTAL CODE! test and troubleshoot is prioritized and mandatory
        this.dmg = dmg;
        this.bulletSpeed = bulletSpeed;
        this.timeBeforeCulling = timeBeforeCulling;
        this.timeExisting = timeExisting;

    }

    public void act(float delta) {
        if (this.getEnabled()) {
            this.applyPhysics(delta);

            collide();

        }

    }

// TODO: EXPERIMENTAL CODE! test and troubleshoot is prioritized and mandatory
    public void fire(float startX, float startY, float speedX, float speedY) {
        this.setEnabled(true);

        this.timeExisting = 0;

        this.setPosition(startX, startY);

        this.velocity.x = speedX;
        this.velocity.y = speedY;

    }

// TODO: EXPERIMENTAL CODE! test and troubleshoot is prioritized and mandatory
    private void collide() {
        for (Solid solid :
                this.lvl.solids) {
            if (this.getEnabled() && this.overlaps(solid)) {
                this.setEnabled(false);

            }

        }

    }

}
