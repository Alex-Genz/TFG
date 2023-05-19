package com.alexgs.tfg_game;

import com.alexgs.tfg_game.params.GameParams;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
    public static void main(String[] arg) {
//        try {
//            testArea();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        TODO: remember to uncomment the lines below and comment the method call above after finishing testing
        ConfLoader.loadConfData();

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(GameParams.framerate);
        config.setTitle("Project Wasteland");

        config.setWindowedMode(GameParams.scrWidth, GameParams.scrHeight);
        System.out.println(GameParams.scrWidth + "x" + GameParams.scrHeight);

        new Lwjgl3Application(new MyGdxGame(), config);

    }

    private static void testArea() throws InterruptedException {
//        test01();

    }



    private static void test01() throws InterruptedException {
        String text = "Occaecat Lorem in eiusmod irure reprehenderit labore minim fugiat laboris anim consequat duis tempor esse. " +
                "Minim proident adipisicing labore adipisicing in nisi ea nisi esse do proident. " +
                "Labore non Lorem occaecat id ullamco est cillum non elit adipisicing dolor est voluptate est. " +
                "Elit quis irure labore laborum eiusmod. Pariatur ut in consectetur aliquip amet non aliqua tempor " +
                "officia nostrud enim. Magna duis consequat Lorem nostrud ut anim ex sint labore non aliquip sint ea.";

        String add = "";
        ArrayList<String> arr = new ArrayList<>();
        boolean breakLineNow = false;

        for (int i = 0, length = 1; i < text.length(); i++, length++) {
            add+=text.charAt(i);

            if ((length >= 30 && text.charAt(i) == ' ') || breakLineNow) {
                arr.add(add);
                add = "";
                length = 0;
                breakLineNow = false;

            }

            if (text.charAt(i) == '.')
                breakLineNow = true;

        }

        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));

            if (i % 2 == 1)
                Thread.sleep(1000);

        }

    }

}