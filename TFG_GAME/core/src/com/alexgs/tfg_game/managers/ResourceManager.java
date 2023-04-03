package com.alexgs.tfg_game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TmxMapLoader.Parameters;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public final class ResourceManager {

    private ResourceManager() {
    }

    private static AssetManager assets = new AssetManager();
    private static LabelStyle buttonStyle;
    public static TextButtonStyle textButtonStyle;

    private static final String SPRITES_PATH = "sprites/citizens/";
    private static final String[] CITIZENS_PATH = {"cit1/", "cit2/", "cit3/", "cit4/"/*, "cit5/"*/};
    private static final String[] CITIZEN_SPRITES = {"walk_down.png", "walk_left.png", "walk_right.png", "walk_up.png", "idle_down.png", "idle_left.png", "idle_right.png", "idle_up.png"};

    private static final String hgun = "hgun_";
    private static final String[] status = {"idle_", "walk_"};
    private static final String[] dir = {"down", "left", "right", "up"};

    public static void loadAllResources() {
//        Texture filtering & MipMaps
        Parameters params = new Parameters();
        params.textureMinFilter = TextureFilter.Nearest;
        params.textureMagFilter = TextureFilter.Nearest;
        params.generateMipMaps = true;

        final String[] PLAYER_PATH_PREFIXES = {"sprites/player/fwd/", "sprites/player/lft/",
                "sprites/player/rgt/", "sprites/player/bck/"};

        /*
         * maps assets.setLoader(TiledMap.class, new TmxMapLoader()); assets.load(<map
         * file path>, TiledMap.class);
         */
        assets.setLoader(TiledMap.class, new TmxMapLoader());

        assets.load("world/maps/devmap_1.tmx", TiledMap.class);

        /*
         * sprites/world_objects/ui_gui images = assets.load(<player file path>, Texture.class);
         */

//        player
        for (String dirStr :
                dir) {
            for (String statusStr :
                    status) {
                assets.load("sprites/player/" + dirStr + "/" + hgun + statusStr + dirStr + ".png", Texture.class);
                System.out.println("sprites/player/" + dirStr + "/" + hgun + statusStr + dirStr + ".png loaded!");

            }

        }


        /*
         * ost = assets.load(<audio file path>, Music.class);
         */


        /*
         * sfx = assets.load(<audio file path>, Sound.class);
         */


        /*
         * UI assets.load(<ui file path>, Texture.class);
         */
        assets.load("ui_gui/button2.png", Texture.class);

        // add more elements


    }

    public static boolean update() {
        return assets.update();
    }

    public static void botones() {

        // estilo para botones
        FreeTypeFontGenerator ftfg = new FreeTypeFontGenerator(Gdx.files.internal("fonts/sans.ttf"));
        FreeTypeFontParameter ftfp = new FreeTypeFontParameter();

        ftfp.size = 36;
        ftfp.color = Color.WHITE;
        ftfp.borderColor = Color.BLACK;
        ftfp.borderWidth = 2;

        BitmapFont fuentePropia = ftfg.generateFont(ftfp);
        buttonStyle = new LabelStyle();
        buttonStyle.font = fuentePropia;
        textButtonStyle = new TextButtonStyle();
//        Texture buttonText = ResourceManager.getTexture("world/world_obj/barrel.png");
        Texture buttonText = ResourceManager.getTexture("ui_gui/button2.png");
        NinePatch buttonPatch = new NinePatch(buttonText);
        textButtonStyle.up = new NinePatchDrawable(buttonPatch);
        textButtonStyle.font = fuentePropia;

    }

    /*
     * public static TextureAtlas getAtlas(String path){ return assets.get(path,
     * TextureAtlas.class);
     *
     * }
     */

    public static Texture getTexture(String path) {
        return assets.get(path, Texture.class);
    }

    static Music getMusic(String path) {
        return assets.get(path, Music.class);
    }

    static Sound getSound(String path) {
        return assets.get(path, Sound.class);
    }

    public static TiledMap getMap(String path) {
        return assets.get(path, TiledMap.class);
    }

    public static void dispose() {
        assets.dispose();
    }
}
