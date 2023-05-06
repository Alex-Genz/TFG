package com.alexgs.tfg_game.elements.characters.hostiles;

import com.alexgs.tfg_game.elements.characters.Characters;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Hostiles extends Characters {
//    temporal
    public final String PATHS = "sprites/npcs/";
    public final String EXTENSION = ".png";
    public final String[][] CHARACTER_SPRITES =
            {
                    {
                            "chara01_b/vx_chara03_c_char1_idown",
                            "chara01_b/vx_chara03_c_char1_ileft",
                            "chara01_b/vx_chara03_c_char1_iright",
                            "chara01_b/vx_chara03_c_char1_iup",
                            "chara01_b/vx_chara03_c_char1_wdown",
                            "chara01_b/vx_chara03_c_char1_wleft",
                            "chara01_b/vx_chara03_c_char1_wright",
                            "chara01_b/vx_chara03_c_char1_wup"
                    },
                    {
                            "chara02_c/vx_chara03_c_char2_idown",
                            "chara02_c/vx_chara03_c_char2_ileft",
                            "chara02_c/vx_chara03_c_char2_iright",
                            "chara02_c/vx_chara03_c_char2_iup",
                            "chara02_c/vx_chara03_c_char2_wdown",
                            "chara02_c/vx_chara03_c_char2_wleft",
                            "chara02_c/vx_chara03_c_char2_wright",
                            "chara02_c/vx_chara03_c_char2_wup"
                    },
                    {
                            "chara02_c/vx_chara03_c_char4_idown",
                            "chara02_c/vx_chara03_c_char4_ileft",
                            "chara02_c/vx_chara03_c_char4_iright",
                            "chara02_c/vx_chara03_c_char4_iup",
                            "chara02_c/vx_chara03_c_char4_wdown",
                            "chara02_c/vx_chara03_c_char4_wleft",
                            "chara02_c/vx_chara03_c_char4_wright",
                            "chara02_c/vx_chara03_c_char4_wup"
                    }
            };
//    temporal

    public Hostiles(float x, float y, Stage s, MainScreen lvl) {
        super(x, y, s, lvl);

    }

    public void act(float delta) {
        super.act(delta);

    }

}
