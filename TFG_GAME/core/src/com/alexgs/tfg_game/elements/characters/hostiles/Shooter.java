package com.alexgs.tfg_game.elements.characters.hostiles;

import com.alexgs.tfg_game.elements.tools.Pistol;
import com.alexgs.tfg_game.elements.tools.Weapons;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Shooter extends Hostiles {
    private Weapons weapon;

    public Shooter(float x, float y, Stage s, MainScreen lvl, int charNum, float pathSizeX, float pathSizeY) {
        super(x, y, s, lvl, charNum, pathSizeX, pathSizeY);

        this.setAnimation(super.idleDown);

        this.weapon = new Pistol();

        super.loadPersistenceMag(s, this.weapon);

    }

    public void act(float delta) {
        super.act(delta);

        if (distanceToTarget(lvl.player.getCenteredX(), lvl.player.getCenteredY()) < 80) {


        }

    }

}
