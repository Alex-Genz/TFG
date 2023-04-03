package com.alexgs.game.elements.powerup;

import com.alexgs.game.elements.Element;
import com.alexgs.game.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Powerup extends Element {
    protected GameScreen lvl;

    protected Animation<TextureRegion> anim;

    protected float respawnTime;
    private float tRespawnTime;

    public Powerup(float x, float y, Stage s, GameScreen lvl) {
        super(x, y, s);
        this.lvl = lvl;

        respawnTime = 30;
        tRespawnTime = 0;

        this.setPolygon(7, 32, 32, 0, 0);

    }

    public void act(float delta) {
        this.setAnimation(anim);

        touch();

        if (!this.getEnabled() && tRespawnTime <= 0) {
            this.setEnabled(true);

        } else {
            tRespawnTime-=delta;

        }

    }

    protected void setRespawnTime() {
        tRespawnTime = respawnTime;

    }

    private void touch() {
        if (this.getEnabled() && this.overlaps(this.lvl.player)) {
            this.setEnabled(false);

        }

    }

}
