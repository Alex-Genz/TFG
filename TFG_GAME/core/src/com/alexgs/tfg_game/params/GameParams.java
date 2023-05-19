package com.alexgs.tfg_game.params;

import com.alexgs.tfg_game.solids.Teleporter;

public class GameParams {
    //    debug
    public static final boolean DEBUG = true;

    //    screen resolution & aspect ratio values (multiply width with appropiate ratio
    public static final float[] SCR_RATIO = {0.5625f, 0.75f};
    public static final int SCR_WIDTH = 1280;
    public static final int SCR_HEIGHT = (int) (SCR_WIDTH * SCR_RATIO[1]);

    public static final float ZOOM = 0.5f;

    //    audio volume
    public static final float MUSIC_VOL = 0.6f;
    public static final float SFX_VOL = 0.2f;

    //    camera
    public static final float CORRECTION_FACTOR = 10;

    //    map
    public static Teleporter touchedTeleporter = null;

    //    test
    public static int scrWidth;
    public static int scrHeight;
    public static boolean debug;
    public static int framerate;

    public static final boolean TOGGLE_RUN = false;

    //    misc
    public static final String[] NPC_CHARACTER_SPRITE = {
//            "chara01_a/vx_chara01_a_char8_",
            "chara01_b/vx_chara01_b_char8_",



            "chara02_a/vx_chara02_a_char8_",

//            "chara01_b/vx_chara02_b_char8_",
            "chara02_c/vx_chara02_c_char7_",
            "chara02_c/vx_chara02_c_char8_",

            "chara02_d/vx_chara02_d_char7_",



            "chara03_a/vx_chara03_a_char1_",
            "chara03_a/vx_chara03_a_char2_",
            "chara03_a/vx_chara03_a_char3_",
            "chara03_a/vx_chara03_a_char4_",
            "chara03_a/vx_chara03_a_char5_",

//            "chara01_b/vx_chara03_b_char8_",

            "chara03_c/vx_chara03_c_char1_",
            "chara03_c/vx_chara03_c_char2_",
            "chara03_c/vx_chara03_c_char4_",

            "chara03_d/vx_chara03_d_char3_",
            "chara03_d/vx_chara03_d_char5_",

            "chara03_e/vx_chara03_e_char1_",
            "chara03_e/vx_chara03_e_char2_",
            "chara03_e/vx_chara03_e_char4_",

            "chara03_f/vx_chara03_f_char1_",
            "chara03_f/vx_chara03_f_char2_",
            "chara03_f/vx_chara03_f_char4_",
            "chara03_f/vx_chara03_f_char5_",

//            "chara03_g/vx_chara03_g_char8_",

    };

    public static final String[] NPC_SPRITE_DIR = {
        "idown",
        "ileft",
        "iright",
        "iup",
        "wdown",
        "wleft",
        "wright",
        "wup",

    };

//    public static final String[] NPC_SPRITES = {
//        "chara01_b/vx_chara01_b_char8_ileft",
//        "chara01_b/vx_chara01_b_char8_iright",
//        "chara01_b/vx_chara01_b_char8_idown",
//        "chara01_b/vx_chara01_b_char8_iup",
//        "chara01_b/vx_chara01_b_char8_wdown",
//        "chara01_b/vx_chara01_b_char8_wleft",
//        "chara01_b/vx_chara01_b_char8_wright",
//        "chara01_b/vx_chara01_b_char8_wup",
//        "chara02_c/vx_chara02_c_char7_idown",
//        "chara02_c/vx_chara02_c_char7_ileft",
//        "chara02_c/vx_chara02_c_char7_iright",
//        "chara02_c/vx_chara02_c_char7_iup",
//        "chara02_c/vx_chara02_c_char7_wdown",
//        "chara02_c/vx_chara02_c_char7_wleft",
//        "chara02_c/vx_chara02_c_char7_wright",
//        "chara02_c/vx_chara02_c_char7_wup",
//        "chara02_c/vx_chara02_c_char8_idown",
//        "chara02_c/vx_chara02_c_char8_ileft",
//        "chara02_c/vx_chara02_c_char8_iright",
//        "chara02_c/vx_chara02_c_char8_iup",
//        "chara02_c/vx_chara02_c_char8_wdown",
//        "chara02_c/vx_chara02_c_char8_wleft",
//        "chara02_c/vx_chara02_c_char8_wright",
//        "chara02_c/vx_chara02_c_char8_wup",
//        "chara03_c/vx_chara03_c_char1_idown",
//        "chara03_c/vx_chara03_c_char1_ileft",
//        "chara03_c/vx_chara03_c_char1_iright",
//        "chara03_c/vx_chara03_c_char1_iup",
//        "chara03_c/vx_chara03_c_char1_wdown",
//        "chara03_c/vx_chara03_c_char1_wleft",
//        "chara03_c/vx_chara03_c_char1_wright",
//        "chara03_c/vx_chara03_c_char1_wup",
//        "chara03_c/vx_chara03_c_char2_idown",
//        "chara03_c/vx_chara03_c_char2_ileft",
//        "chara03_c/vx_chara03_c_char2_iright",
//        "chara03_c/vx_chara03_c_char2_iup",
//        "chara03_c/vx_chara03_c_char2_wdown",
//        "chara03_c/vx_chara03_c_char2_wleft",
//        "chara03_c/vx_chara03_c_char2_wright",
//        "chara03_c/vx_chara03_c_char2_wup",
//        "chara03_c/vx_chara03_c_char4_idown",
//        "chara03_c/vx_chara03_c_char4_ileft",
//        "chara03_c/vx_chara03_c_char4_iright",
//        "chara03_c/vx_chara03_c_char4_iup",
//        "chara03_c/vx_chara03_c_char4_wdown",
//        "chara03_c/vx_chara03_c_char4_wleft",
//        "chara03_c/vx_chara03_c_char4_wright",
//        "chara03_c/vx_chara03_c_char4_wup"
//
//    };

}
