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

//        TODO delete these 4 maps \/
//        assets.load("world/maps/test.tmx", TiledMap.class, params);
//        assets.load("world/maps/untitled.tmx", TiledMap.class, params);
//        assets.load("world/maps/forest.tmx", TiledMap.class, params);

//        Final maps
        assets.load("world/maps/map_1.tmx", TiledMap.class, params);
        assets.load("world/maps/map_2.tmx", TiledMap.class, params);

        /*
         * map elements assets.load(<element file path>, Texture.class);
         */
//        assets.load("world/world_obj/barrel.png", Texture.class);
        assets.load("world/world_obj/button.png", Texture.class);
        assets.load("world/world_obj/button2.png", Texture.class);
        assets.load("sprites/props/bullet.png", Texture.class);

        /*
         * enemies assets.load(<enemy file path>, Texture.class);
         */

        /*
         * player (player + player elements/objects) assets.load(<player file path>,
         * Texture.class);
         */
//        assets.load("player/testPlayer.png", Texture.class);
        assets.load("sprites/powUP/pwup_sprite.png", Texture.class);
        assets.load("sprites/powUP/pwup2_sprite.png", Texture.class);

        assets.load("sprites/powUP/speed_powup.png", Texture.class);
        assets.load("sprites/powUP/health_regen_powup.png", Texture.class);

        // player / enemy
        assets.load(PLAYER_PATH_PREFIXES[0] + "hgun_idle_fwd.png", Texture.class);
        assets.load(PLAYER_PATH_PREFIXES[1] + "hgun_idle_lft.png", Texture.class);
        assets.load(PLAYER_PATH_PREFIXES[2] + "hgun_idle_rgt.png", Texture.class);
        assets.load(PLAYER_PATH_PREFIXES[3] + "hgun_idle_bck.png", Texture.class);

        assets.load(PLAYER_PATH_PREFIXES[0] + "hgun_walk_fwd.png", Texture.class);
        assets.load(PLAYER_PATH_PREFIXES[0] + "hgun_walk_fwd_inv.png", Texture.class);

        assets.load(PLAYER_PATH_PREFIXES[1] + "hgun_walk_lft.png", Texture.class);
        assets.load(PLAYER_PATH_PREFIXES[1] + "hgun_walk_lft_inv.png", Texture.class);

        assets.load(PLAYER_PATH_PREFIXES[2] + "hgun_walk_rgt.png", Texture.class);
        assets.load(PLAYER_PATH_PREFIXES[2] + "hgun_walk_rgt_inv.png", Texture.class);

        assets.load(PLAYER_PATH_PREFIXES[3] + "hgun_walk_bck.png", Texture.class);

        // shoot
        assets.load(PLAYER_PATH_PREFIXES[0] + "hgun_idle_shoot_fwd.png", Texture.class);
        assets.load(PLAYER_PATH_PREFIXES[1] + "hgun_idle_shoot_lft.png", Texture.class);
        assets.load(PLAYER_PATH_PREFIXES[2] + "hgun_idle_shoot_rgt.png", Texture.class);
        assets.load(PLAYER_PATH_PREFIXES[3] + "hgun_idle_shoot_bck.png", Texture.class);

        assets.load(PLAYER_PATH_PREFIXES[0] + "hgun_walk_shoot_fwd.png", Texture.class);
        assets.load(PLAYER_PATH_PREFIXES[0] + "hgun_walk_shoot_fwd.png", Texture.class);

        assets.load(PLAYER_PATH_PREFIXES[1] + "hgun_walk_shoot_lft.png", Texture.class);
        assets.load(PLAYER_PATH_PREFIXES[1] + "hgun_walk_shoot_lft_inv.png", Texture.class);

        assets.load(PLAYER_PATH_PREFIXES[2] + "hgun_walk_shoot_rgt.png", Texture.class);
        assets.load(PLAYER_PATH_PREFIXES[2] + "hgun_walk_shoot_rgt_inv.png", Texture.class);

        assets.load(PLAYER_PATH_PREFIXES[3] + "hgun_walk_shoot_bck.png", Texture.class);

//        cow
        assets.load("sprites/cow/cow_idle_up.png", Texture.class);
        assets.load("sprites/cow/cow_idle_down.png", Texture.class);
        assets.load("sprites/cow/cow_idle_left.png", Texture.class);
        assets.load("sprites/cow/cow_idle_right.png", Texture.class);

        assets.load("sprites/cow/cow_walk_up.png", Texture.class);
        assets.load("sprites/cow/cow_walk_down.png", Texture.class);
        assets.load("sprites/cow/cow_walk_left.png", Texture.class);
        assets.load("sprites/cow/cow_walk_right.png", Texture.class);

//        citizens
        for (String citizenPath :
                CITIZENS_PATH) {
            for (String citizenSprite :
                    CITIZEN_SPRITES) {
                assets.load(SPRITES_PATH + citizenPath + citizenSprite, Texture.class);

            }

        }

//        world objects
//        trees
        assets.load("world/world_obj/trees/maple.png", Texture.class);
        assets.load("world/world_obj/trees/pine.png", Texture.class);
        assets.load("world/world_obj/trees/tree_1.png", Texture.class);
        assets.load("world/world_obj/trees/tree_2.png", Texture.class);

//        stones


//        other
        assets.load("world/world_obj/other/well_2.png", Texture.class);
        assets.load("world/world_obj/other/scarecrow.png", Texture.class);
        assets.load("world/world_obj/other/basket_1.png", Texture.class);
        assets.load("world/world_obj/other/basket_2.png", Texture.class);
        assets.load("world/world_obj/other/sign_1.png", Texture.class);
        assets.load("world/world_obj/other/sign_2.png", Texture.class);
        assets.load("world/world_obj/other/sign_3.png", Texture.class);
        assets.load("world/world_obj/other/sign_4.png", Texture.class);
        assets.load("world/world_obj/other/streetlight.png", Texture.class);


        /*
         * ost = assets.load(<audio file path>, Music.class);
         */

//        MUSIC
        assets.load("audio/music/M.O.O.N. - Hydrogen.mp3", Music.class);
        assets.load("audio/music/hl1_military_precision.mp3", Music.class);
        assets.load("audio/music/hl2e1_combine_advisory.mp3", Music.class);
        assets.load("audio/music/hl2_cp_violation.mp3", Music.class);

        /*
         * sfx = assets.load(<audio file path>, Sound.class);
         */
//        SFX
        assets.load("audio/sfx/csgo_usp-s_sfx.mp3", Sound.class);
//        placeholder powerup sfx
        assets.load("audio/sfx/sm_powerup.mp3", Sound.class);
        assets.load("audio/sfx/tf2_speed_boost_up.mp3", Sound.class);
        assets.load("audio/sfx/hl_medkit.mp3", Sound.class);

//        cow
        assets.load("audio/sfx/mc_cow1.mp3", Sound.class);
        assets.load("audio/sfx/mc_cow2.mp3", Sound.class);
        assets.load("audio/sfx/mc_cow3.mp3", Sound.class);

        assets.load("audio/sfx/mc_ouch_cow1.mp3", Sound.class);
        assets.load("audio/sfx/mc_ouch_cow2.mp3", Sound.class);
        assets.load("audio/sfx/mc_ouch_cow3.mp3", Sound.class);

        /*
         * UI assets.load(<ui file path>, Texture.class);
         */

        // add more elements


    }

    public static boolean update() {
        return assets.update();
    }

    public static void botones() {

        // estilo para botones
        FreeTypeFontGenerator ftfg = new FreeTypeFontGenerator(Gdx.files.internal("sans.ttf"));
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
        Texture buttonText = ResourceManager.getTexture("world/world_obj/button2.png");
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
