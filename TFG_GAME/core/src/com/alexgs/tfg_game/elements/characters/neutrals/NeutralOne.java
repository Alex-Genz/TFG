package com.alexgs.tfg_game.elements.characters.neutrals;

import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class NeutralOne extends Neutrals {

    public NeutralOne(float x, float y, Stage s, MainScreen lvl, int charNum, String message) {
        super(x, y, s, lvl, charNum, message);

        this.setAnimation(super.idleDown);

    }

}
