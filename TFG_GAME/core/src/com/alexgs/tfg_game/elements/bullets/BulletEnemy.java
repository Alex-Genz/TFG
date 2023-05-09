package com.alexgs.tfg_game.elements.bullets;

import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BulletEnemy extends Bullet {

    public BulletEnemy(float x, float y, Stage s, MainScreen lvl,
                       int dmg, /*int bulletSpeed, */float timeBeforeCulling) {
        super(x, y, s, lvl, dmg, /*bulletSpeed, */timeBeforeCulling);

    }

}
