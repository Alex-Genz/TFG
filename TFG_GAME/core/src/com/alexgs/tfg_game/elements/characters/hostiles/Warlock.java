package com.alexgs.tfg_game.elements.characters.hostiles;

import com.alexgs.tfg_game.elements.tools.*;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Warlock extends Hostiles {

    public Warlock(float x, float y, Stage s, MainScreen lvl, int charNum,
                   float pathSizeX, float pathSizeY, float speed) {
        super(x, y, s, lvl, charNum, pathSizeX, pathSizeY, speed, 50, 80);

        this.setAnimation(super.idleDown);

        this.weapon = new MagicStaff();

        super.loadPersistenceMag(s, this.weapon, 1, false);


    }

    public void act(float delta) {
        if (this.getEnabled()) {
            super.act(delta);

            if (distanceToTarget(lvl.player.getCenteredX(), lvl.player.getCenteredY()) < super.stopDistance) {
                if (super.timeBeforeNextShot <= 0 && this.velocity.x == 0 && this.velocity.y == 0) {
                    super.shoot(this.weapon, lvl.player.getCenteredX(), lvl.player.getCenteredY());
                    super.timeBeforeNextShot+= 60/ weapon.getRoundsPerMinute();

                }

            }

            if (super.timeBeforeNextShot > 0)
                super.timeBeforeNextShot-=delta;

        }

    }

}
