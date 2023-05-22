package com.alexgs.tfg_game.params;

import com.alexgs.tfg_game.elements.Teleporter;

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
    public static final float CORRECTION_FACTOR = 15;

    //    map
    public static Teleporter touchedTeleporter = null;

    //    test
    public static int scrWidth;
    public static int scrHeight;
    public static boolean debug;
    public static int framerate;

    public static final boolean TOGGLE_RUN = false;

}
