package com.alexgs.game.game;

import com.alexgs.game.GameParamsCore;

public class GameParams {

//Screen
    // private static int anchoPantalla=1200;
    // private static int altoPantalla=900;

    private static int SCREEN_WIDTH = /*800*/ GameParamsCore.SCR_WIDTH;
    private static int SCREEN_HEIGHT = /*600*/ GameParamsCore.SCR_HEIGHT;

    public static boolean debug = false;

    // Audio;
    public static float musicVolume = 0.6f;
    public static float soundVolume = 0.1f;

    // public static float zoom=0.24f;
    public static float zoom = 0.5f;

    // variables de juegow

    public static int LEVEL = 1;

    public static int GRAVITY = -130;
    public static float PLAYER_X = 0;
    public static float PLAYER_Y = 0;
    public static float playerHealth = 100;
    public static final int MAX_PLAYER_HEALTH = 100;

    public static int score = 0;
    public static int scoreRes = 0;

    public static final int BULLET_SPEED = 200;

    public static int getScrWidth() {
        return SCREEN_WIDTH;
    }

    public static void setScrWidth(int scrWidth) {
        GameParams.SCREEN_WIDTH = scrWidth;
    }

    public static int getScrHeight() {
        return SCREEN_HEIGHT;
    }

    public static void setScrHeight(int scrHeight) {
        GameParams.SCREEN_HEIGHT = scrHeight;
    }

}
