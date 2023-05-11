package com.alexgs.tfg_game.elements.characters.hostiles;

import com.alexgs.tfg_game.elements.characters.neutrals.Neutrals;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class HostileOne extends Hostiles {

    public HostileOne(float x, float y, Stage s, MainScreen lvl, int charNum, float pathSizeX, float pathSizeY) {
        super(x, y, s, lvl, charNum, pathSizeX, pathSizeY);

        this.setAnimation(super.idleDown);

    }

}
