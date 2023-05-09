package com.alexgs.tfg_game.elements.bullets;

import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BulletFriendly extends Bullet {

    public BulletFriendly(float x, float y, Stage s, MainScreen lvl, 
    int dmg, /*int bulletSpeed, */float timeBeforeCulling) {
// TODO: EXPERIMENTAL CODE! test and troubleshoot is prioritized and mandatory
        super(x, y, s, lvl, dmg, /*bulletSpeed, */timeBeforeCulling);

    }

    public void act(float delta) {
        super.act(delta);

    }

// TODO: EXPERIMENTAL CODE! test and troubleshoot is prioritized and mandatory
    private void collide() {


    }

}
