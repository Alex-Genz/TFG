package com.alexgs.tfg_game.elements.world_obj;

import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class PineTreeNorm extends WorldObjects {

    public PineTreeNorm(float x, float y, Stage s, MainScreen lvl) {
        super(x, y, s, lvl, "normal_pine_tree_1");

        this.setPolygon(8, 24, 12, this.getWidth() / 3 + 4, 3);

    }

}
