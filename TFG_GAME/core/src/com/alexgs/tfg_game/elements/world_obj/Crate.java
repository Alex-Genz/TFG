package com.alexgs.tfg_game.elements.world_obj;

import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Crate extends WorldObjects {

    public Crate(float x, float y, Stage s, MainScreen lvl, String variant) {
            super(x, y, s, lvl, "vx_crate_" + variant);

            this.setRectangle(this.getWidth(), 19, 0, 0);

    }

}
