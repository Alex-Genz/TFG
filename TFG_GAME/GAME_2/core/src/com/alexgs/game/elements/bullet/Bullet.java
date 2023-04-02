package com.alexgs.game.elements.bullet;

import com.alexgs.game.elements.Element;
import com.alexgs.game.elements.Solid;
import com.alexgs.game.elements.character.Cow;
import com.alexgs.game.elements.world_objects.WorldObjects;
import com.alexgs.game.managers.SoundManager;
import com.alexgs.game.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Bullet extends Element {
    GameScreen lvl;

    public float bulletDur;
    public float activTime;

    private Animation<TextureRegion> bullet;

    public Bullet(float x, float y, Stage s, GameScreen lvl) {
        super(x, y, s);
        this.lvl = lvl;

        bullet = loadFullAnimation("sprites/props/bullet.png", 1, 1, 0, false);

        this.setPolygon(8);

        this.bulletDur = 5;

        // TODO Auto-generated constructor stub
    }

    public void act(float delta) {
        if (this.getEnabled()) {
            super.act(delta);
            this.applyPhysics(delta);
            collide();

            if (this.activTime > this.bulletDur)
                this.setEnabled(false);
            else
                this.activTime += delta;

        }

    }

    private void collide() {
        for (Solid solid :
                lvl.solids) {
            if (this.getEnabled() && this.overlaps(solid))
                this.setEnabled(false);

        }

        for (Cow cow :
                lvl.cows) {

            if (this.getEnabled() && this.overlaps(cow)) {
                cow.hit();
                this.setEnabled(false);

            }

        }

        for (WorldObjects obj :
                lvl.worldObj) {
            if (this.getEnabled() && this.overlaps(obj.collider))
                this.setEnabled(false);

        }

    }

    public void fire(float startX, float startY, float speedX, float speedY) {
        this.setEnabled(true);

        this.activTime = 0;

        this.setPosition(startX, startY);
        this.velocity.x = speedX;
        this.velocity.y = speedY;
        SoundManager.playSound("audio/sfx/csgo_usp-s_sfx.mp3");

    }

}
