package com.alexgs.tfg_game;

import com.alexgs.tfg_game.params.GameParams;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
    public static void main(String[] arg) {
        int chosenRatio = 1;

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("Project Wasteland");
        config.setWindowedMode(GameParams.SCR_WIDTH, GameParams.SCR_HEIGHT);
        System.out.println(GameParams.SCR_WIDTH + "x" + GameParams.SCR_HEIGHT);

        new Lwjgl3Application(new MyGdxGame(), config);

    }

}