package com.alexgs.tfg_game.elements.world_obj;

import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Streetlight extends WorldObjects {

    public Streetlight(float x, float y, Stage s, MainScreen lvl, String variant) {
        super(x, y, s, lvl, "street_light_" + variant);

//        TODO: adjust width and x-padding
        this.setRectangle(26, 16, 3, 0);

    }

}
