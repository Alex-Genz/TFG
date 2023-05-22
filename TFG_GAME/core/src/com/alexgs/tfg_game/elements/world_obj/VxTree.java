package com.alexgs.tfg_game.elements.world_obj;

import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class VxTree extends WorldObjects {
    public VxTree(float x, float y, Stage s, MainScreen lvl, String file) {
        super(x, y, s, lvl, "vx_" + file + "_tree");

        if (this.getWidth() == 1 * lvl.getTileWidth())
            this.setPolygon(8, 24, 16, 4, 4);

        else
            if (this.getHeight() == 2 * lvl.getTileHeight())
                this.setPolygon(8, 40, 16, 12, 0);

            else
                this.setPolygon(8, 17, 16, 24, 0);

    }

}
