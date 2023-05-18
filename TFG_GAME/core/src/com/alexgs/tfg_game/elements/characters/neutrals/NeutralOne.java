package com.alexgs.tfg_game.elements.characters.neutrals;

import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class NeutralOne extends Neutrals {

    public NeutralOne(float x, float y, Stage s, MainScreen lvl, int charNum, String message, float pathSizeX, float pathSizeY, float speed) {
        super(x, y, s, lvl, charNum, message, pathSizeX, pathSizeY, speed);

        this.setAnimation(super.idleDown);

    }

}
