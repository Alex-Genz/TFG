package com.alexgs.tfg_game.elements.world_obj;

import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Sign extends WorldObjects {
    private String text;

    public Sign(float x, float y, Stage s, MainScreen lvl, String variant, String text) {
        super(x, y, s, lvl, "vx_sign_" + variant);

        this.text = text;

        if (Integer.valueOf(variant) <= 2) {
            this.setPolygon(8, this.getWidth(), 12, 0, 0);

        } else {
            this.setRectangle(this.getWidth(), this.getHeight() / 4, 0, 0);

        }

    }

    public String getText() {
        return text;
    }

}
