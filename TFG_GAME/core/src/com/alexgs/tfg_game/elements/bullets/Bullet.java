package com.alexgs.tfg_game.elements.bullets;

import com.alexgs.tfg_game.elements.Element;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Bullet extends Element {
    MainScreen lvl;

    private float timeBeforeCulling;
    private float timeExisting;

    protected int dmg;

    protected Animation<TextureRegion> projectileTexture;

    public Bullet(float x, float y, Stage s, MainScreen lvl) {
        super(x, y, s);

        this.lvl = lvl;

    }

    public void act(float delta) {
        if (this.getEnabled()) {
            this.applyPhysics(delta);

        }

    }

}
