package com.alexgs.tfg_game.managers;

import com.alexgs.tfg_game.params.Consts;
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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public final class ResourceManager {

    private ResourceManager() {
    }

    private static AssetManager assets = new AssetManager();
    private static LabelStyle buttonStyle;
    public static TextButtonStyle textButtonStyle;

    public static LabelStyle clearStyle;
    public static LabelStyle dialogStyle;
    public static LabelStyle scoreStyle;
    public static LabelStyle healthStyle;
    public static LabelStyle weaponStyle;
    public static LabelStyle firemodeStyle;
    public static LabelStyle bossStyle;

    private static final String NPCS_PATH = "sprites/npcs/";

    private static final String[] PLAYER_PREFIXES = {"nothing_", "pistol_", "ar_"};
    private static final String[] PLAYER_STATUS = {"idle_", "walk_", "run_"};
    private static final String[] PLAYER_DIR = {"down", "left", "right", "up"};

    private static final String[] CRATE_TYPES = {"apples", "closed_1", "closed_2", "fish", "gold",
            "open_1", "open_2", "pebbles", "veggies", "iron", "tnt"};

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

//        assets.load("world/worlds/test.world", Box2D.class);
        assets.load("world/maps/devmap_1.tmx", TiledMap.class);
        assets.load("world/maps/devmap_2.tmx", TiledMap.class);

        assets.load("world/maps/spawn_map.tmx", TiledMap.class);
        assets.load("world/maps/spawn_map_b.tmx", TiledMap.class);
        assets.load("world/maps/town_map.tmx", TiledMap.class);
        assets.load("world/maps/mountain_map.tmx", TiledMap.class);
        assets.load("world/maps/river_map.tmx", TiledMap.class);
        assets.load("world/maps/boss_map.tmx", TiledMap.class);

        /*
         * sprites/world_objects/ui_gui images = assets.load(<player file path>, Texture.class);
         */

//        player
        for (String prefix :
                PLAYER_PREFIXES) {
            for (String dirStr :
                    PLAYER_DIR) {
                for (String statusStr :
                        PLAYER_STATUS) {
                    if (statusStr.equals("run_") && !prefix.equals("nothing_"))
                        continue;

                    assets.load("sprites/player/" + dirStr + "/" +
                                    prefix + statusStr + dirStr + ".png",
                            Texture.class);

                }

            }

        }

//        NPCs
        for (String sprite :
                Consts.NPC_CHARACTER_SPRITE) {
            for (String dir :
                    Consts.NPC_SPRITE_DIR) {
                assets.load(NPCS_PATH + sprite + dir + ".png", Texture.class);

            }

        }

//        enemies

        assets.load("sprites/other/bullet.png", Texture.class);
        assets.load("sprites/other/pwup_sprite.png", Texture.class);
        assets.load("sprites/other/exit_tp.png", Texture.class);

        /*
         * world_objects
         */
        assets.load("world/world_objects/normal_maple_tree.png", Texture.class);
        assets.load("world/world_objects/snow_maple_tree.png", Texture.class);
        assets.load("world/world_objects/normal_pine_tree.png", Texture.class);
        assets.load("world/world_objects/snow_pine_tree.png", Texture.class);

        for (String vxTrees :
                Consts.VX_TREE_TYPES) {
            assets.load("world/world_objects/vx_" + vxTrees + "_tree.png", Texture.class);

        }

        for (String crateTypes :
                CRATE_TYPES) {
            assets.load("world/world_objects/vx_crate_" + crateTypes + ".png", Texture.class);

        }

        for (int i = 1; i <= 3; i++) {
            assets.load("world/world_objects/vx_crystal_shard_" + i + ".png", Texture.class);

        }

        for (int i = 1; i <= 4; i++) {
            assets.load("world/world_objects/vx_sign_" + i + ".png", Texture.class);

        }

        for (int i = 1; i <= 8; i++) {
            assets.load("world/world_objects/vx_column_" + i + ".png", Texture.class);

        }

        assets.load("world/world_objects/street_light_1.png", Texture.class);


        /*
         * ost = assets.load(<audio file path>, Music.class);
         */
        assets.load("audio/ost/pkmn_hgss_ice_cave.mp3", Music.class);
        assets.load("audio/ost/pkmn_dppt_oreburgh_mine.mp3", Music.class);
        assets.load("audio/ost/pkmn_hgss_cianwood_city.mp3", Music.class);

        /*
         * sfx = assets.load(<audio file path>, Sound.class);
         */
        assets.load("audio/sfx/mc_bow_sfx.mp3", Sound.class);
        assets.load("audio/sfx/csgo_knife_sfx_1.mp3", Sound.class);
        assets.load("audio/sfx/magic_staff_sfx.mp3", Sound.class);
        assets.load("audio/sfx/csgo_beretta_sfx.mp3", Sound.class);
        assets.load("audio/sfx/csgo_aug_a3_sfx.mp3", Sound.class);


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
        FreeTypeFontGenerator sansFtfg = new FreeTypeFontGenerator(Gdx.files.internal("fonts/sans.ttf"));
        FreeTypeFontParameter sansFtfp = new FreeTypeFontParameter();

        sansFtfp.size = 36;
        sansFtfp.color = Color.WHITE;
        sansFtfp.borderColor = Color.BLACK;
        sansFtfp.borderWidth = 2;

        BitmapFont fuentePropia = sansFtfg.generateFont(sansFtfp);
        buttonStyle = new LabelStyle();
        buttonStyle.font = fuentePropia;
        textButtonStyle = new TextButtonStyle();
//        Texture buttonText = ResourceManager.getTexture("world/world_obj/barrel.png");
        Texture buttonText = ResourceManager.getTexture("ui_gui/button2.png");
        NinePatch buttonPatch = new NinePatch(buttonText);
        textButtonStyle.up = new NinePatchDrawable(buttonPatch);
        textButtonStyle.font = fuentePropia;

    }

    public static void hudFont() {
        FreeTypeFontGenerator atariFtfg = new FreeTypeFontGenerator(Gdx.files.internal("fonts/AtariST8x16SystemFont.ttf"));
        FreeTypeFontParameter atariFtfp = new FreeTypeFontParameter();

        atariFtfp.size = 32;
        atariFtfp.color = Color.BLACK;
//        atariFtfp.borderColor = Color.BLACK;
//        atariFtfp.borderWidth = 2;

        BitmapFont hudFont = atariFtfg.generateFont(atariFtfp);
        clearStyle = new LabelStyle();
        clearStyle.font = hudFont;

        setupDialogStyle(hudFont);
        setupScoreStyle(hudFont);
        setupHealtStyle(hudFont);
        setupCurrWeaponStyle(hudFont);
        setupFiremodeStyle(hudFont);
        setupBossStyle(hudFont);


    }

    private static void setupDialogStyle(BitmapFont font) {
        dialogStyle = new LabelStyle();
        dialogStyle.font = font;
        dialogStyle.background = new Image(new Texture(Gdx.files.internal("ui_gui/dialog_box_4-3.png"))).getDrawable();

        dialogStyle.background.setTopHeight(dialogStyle.background.getTopHeight() + 30);
        dialogStyle.background.setBottomHeight(dialogStyle.background.getBottomHeight() + 30);
        dialogStyle.background.setLeftWidth(dialogStyle.background.getLeftWidth() + 30);
        dialogStyle.background.setRightWidth(dialogStyle.background.getRightWidth() + 30);

    }

    private static void setupScoreStyle(BitmapFont font) {
        scoreStyle = new LabelStyle();
        scoreStyle.font = font;
        scoreStyle.background = new Image(new Texture(Gdx.files.internal("ui_gui/score_box.png"))).getDrawable();

        scoreStyle.background.setTopHeight(scoreStyle.background.getTopHeight() + 10);
        scoreStyle.background.setBottomHeight(scoreStyle.background.getBottomHeight() + 10);
        scoreStyle.background.setLeftWidth(scoreStyle.background.getLeftWidth() + 10);
        scoreStyle.background.setRightWidth(scoreStyle.background.getRightWidth() + 10);

    }

    private static void setupHealtStyle(BitmapFont font) {
        healthStyle = new LabelStyle();
        healthStyle.font = font;
        healthStyle.background = new Image(new Texture(Gdx.files.internal("ui_gui/health_box.png"))).getDrawable();

        healthStyle.background.setTopHeight(healthStyle.background.getTopHeight() + 10);
        healthStyle.background.setBottomHeight(healthStyle.background.getBottomHeight() + 10);
        healthStyle.background.setLeftWidth(healthStyle.background.getLeftWidth() + 10);
        healthStyle.background.setRightWidth(healthStyle.background.getRightWidth() + 10);

    }

    private static void setupCurrWeaponStyle(BitmapFont font) {
        weaponStyle = new LabelStyle();
        weaponStyle.font = font;
        weaponStyle.background = new Image(new Texture(Gdx.files.internal("ui_gui/curr_weapon_box.png"))).getDrawable();

        weaponStyle.background.setTopHeight(weaponStyle.background.getTopHeight() + 10);
        weaponStyle.background.setBottomHeight(weaponStyle.background.getBottomHeight() + 10);
        weaponStyle.background.setLeftWidth(weaponStyle.background.getLeftWidth() + 10);
        weaponStyle.background.setRightWidth(weaponStyle.background.getRightWidth() + 10);

    }

    private static void setupFiremodeStyle(BitmapFont font) {
        firemodeStyle = new LabelStyle();
        firemodeStyle.font = font;
        firemodeStyle.background = new Image(new Texture(Gdx.files.internal("ui_gui/firemode_box.png"))).getDrawable();

        firemodeStyle.background.setTopHeight(firemodeStyle.background.getTopHeight() + 10);
        firemodeStyle.background.setBottomHeight(firemodeStyle.background.getBottomHeight() + 10);
        firemodeStyle.background.setLeftWidth(firemodeStyle.background.getLeftWidth() + 10);
        firemodeStyle.background.setRightWidth(firemodeStyle.background.getRightWidth() + 10);

    }

    private static void setupBossStyle(BitmapFont font) {
        bossStyle = new LabelStyle();
        bossStyle.font = font;
        bossStyle.background = new Image(new Texture(Gdx.files.internal("ui_gui/boss_health_box.png"))).getDrawable();

        bossStyle.background.setTopHeight(bossStyle.background.getTopHeight() + 10);
        bossStyle.background.setBottomHeight(bossStyle.background.getBottomHeight() + 10);
        bossStyle.background.setLeftWidth(bossStyle.background.getLeftWidth() + 30);
        bossStyle.background.setRightWidth(bossStyle.background.getRightWidth() + 30);

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
