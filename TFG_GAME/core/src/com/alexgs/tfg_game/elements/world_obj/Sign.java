package com.alexgs.tfg_game.elements.world_obj;

import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Sign extends WorldObjects {

    public Sign(float x, float y, Stage s, MainScreen lvl, String variant) {
        super(x, y, s, lvl, "vx_sign_" + variant);

        if (Integer.valueOf(variant) <= 2) {
            this.setPolygon(8, this.getWidth(), 12, 0, 0);

        } else {
            this.setRectangle(this.getWidth(), this.getHeight() / 4, 0, 0);

        }

    }

}
