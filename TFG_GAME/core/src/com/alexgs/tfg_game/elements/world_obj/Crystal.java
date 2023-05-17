package com.alexgs.tfg_game.elements.world_obj;

import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Crystal extends WorldObjects {
    public Crystal(float x, float y, Stage s, MainScreen lvl, String variant) {
        super(x, y, s, lvl, "vx_crystal_shard_" + variant);

//        TODO: set collbox height with -> ((this.getHeight() == 2 * lvl.getTileHeight()) ? lvl.getTileHeight() : 0)
        this.setPolygon(8, this.getWidth() - 4,
                (this.getHeight() -
                        ((this.getHeight() == 2 * lvl.getTileHeight()) ? lvl.getTileHeight() : 0)) / 2,
                2, 2);

    }

}
