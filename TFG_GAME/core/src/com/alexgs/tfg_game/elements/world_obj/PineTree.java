package com.alexgs.tfg_game.elements.world_obj;

import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class PineTree extends WorldObjects {

    public PineTree(float x, float y, Stage s, MainScreen lvl, String variant) {
        super(x, y, s, lvl, variant + "_pine_tree");

        this.setPolygon(8, 24, 12, this.getWidth() / 3 + 4, 3);

    }

}
