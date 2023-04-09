package com.alexgs.tfg_game;

import com.alexgs.tfg_game.params.GameParams;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfLoader {

    public static void loadConfData() {
        Properties conf = new Properties();

        try {
            conf.load(ConfLoader.class.getResourceAsStream("/parameters.properties"));

            GameParams.scrWidth = Integer.parseInt(conf.getProperty("scr_width"));
            GameParams.scrHeight = (int) (GameParams.scrWidth * GameParams.SCR_RATIO[Integer.valueOf(conf.getProperty("aspect_ratio"))]);
            GameParams.debug = Boolean.parseBoolean(conf.getProperty("debug"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

}
