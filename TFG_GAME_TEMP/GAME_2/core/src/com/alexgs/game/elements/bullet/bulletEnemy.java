package com.alexgs.game.elements.bullet;

import com.alexgs.game.screens.GameScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class bulletEnemy extends Bullet {

    public bulletEnemy(float x, float y, Stage s, GameScreen lvl) {
        super(x, y, s, lvl);
        // TODO Auto-generated constructor stub
    }

    public void act(float delta) {
        super.act(delta);

        collide();

    }

    private void collide() {
        if (this.getEnabled() && this.overlaps(super.lvl.player)) {
            super.lvl.player.ouch(10);
            this.setEnabled(false);

        }

    }

}
