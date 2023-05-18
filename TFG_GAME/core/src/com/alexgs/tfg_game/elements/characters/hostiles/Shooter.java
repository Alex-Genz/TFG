package com.alexgs.tfg_game.elements.characters.hostiles;

import com.alexgs.tfg_game.elements.tools.Pistol;
import com.alexgs.tfg_game.elements.tools.Weapons;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Shooter extends Hostiles {
    private Weapons weapon;

    private final float ROUNDS_PER_MINUTE = 30;
    private float timeBeforeNextShot = 0;

    public Shooter(float x, float y, Stage s, MainScreen lvl,
                   int charNum, float pathSizeX, float pathSizeY, float speed) {
        super(x, y, s, lvl, charNum, pathSizeX, pathSizeY, speed);

        this.setAnimation(super.idleDown);

        this.weapon = new Pistol();

        super.loadPersistenceMag(s, this.weapon, 1, false);

    }

    public void act(float delta) {
        if (this.getEnabled()) {
            super.act(delta);

            if (distanceToTarget(lvl.player.getCenteredX(), lvl.player.getCenteredY()) < 80) {
                if (timeBeforeNextShot <= 0 && this.velocity.x == 0 && this.velocity.y == 0) {
                    super.shoot(this.weapon, lvl.player.getCenteredX(), lvl.player.getCenteredY());
                    timeBeforeNextShot+= 60/ ROUNDS_PER_MINUTE;

                }

            }

            if (timeBeforeNextShot > 0)
                timeBeforeNextShot-=delta;

        }

    }

}
