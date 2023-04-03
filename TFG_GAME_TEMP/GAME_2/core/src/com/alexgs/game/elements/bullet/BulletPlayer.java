package com.alexgs.game.elements.bullet;

import com.alexgs.game.elements.character.Enemy;
import com.alexgs.game.screens.GameScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class BulletPlayer extends Bullet {

    public BulletPlayer(float x, float y, Stage s, GameScreen lvl) {
        super(x, y, s, lvl);
        // TODO Auto-generated constructor stub
    }

    public void act(float delta) {
        super.act(delta);

        collide();

    }

    private void collide() {
        for (Enemy enemy :
                this.lvl.enemies) {
            if ((this.getEnabled() && enemy.getEnabled()) && this.overlaps(enemy)) {
                this.setEnabled(false);
                enemy.damage(20);

            }
            
        }

    }

}
