package com.alexgs.tfg_game.scr.game_scr;

import com.alexgs.tfg_game.MyGdxGame;
import com.alexgs.tfg_game.elements.Teleporter;
import com.alexgs.tfg_game.elements.characters.hostiles.*;
import com.alexgs.tfg_game.elements.characters.neutrals.player.Player;
import com.alexgs.tfg_game.elements.characters.neutrals.*;
import com.alexgs.tfg_game.elements.characters.neutrals.player.PlayerParams;
import com.alexgs.tfg_game.elements.world_obj.*;
import com.alexgs.tfg_game.managers.ResourceManager;
import com.alexgs.tfg_game.managers.SoundManager;
import com.alexgs.tfg_game.params.GameParams;
import com.alexgs.tfg_game.scr.ui_scr.BScreen;

import com.alexgs.tfg_game.scr.ui_scr.DeathScreen;
import com.alexgs.tfg_game.scr.ui_scr.EndScreen;
import com.alexgs.tfg_game.scr.util.ActorComparator;
import com.alexgs.tfg_game.solids.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Iterator;

public class MainScreen extends BScreen {
    //    gui
    Stage mainStage;

    //    actorComparator
    private ActorComparator compar;

    //    map / map prop
    private TiledMap map;
    private OrthogonalTiledMapRenderer ren;
    private int tileWidth, tileHeight, mapWidthRaw, mapHeightRaw, mapWidthTiles, mapHeightTiles;

    public Array<SolidHigh> hiSolids;
    public Array<SolidLow> loSolids;
    public Array<Teleporter> teleporters;
    private Teleporter teleporter;

    public Array<Neutrals> neutralNPCs;
    public Array<Hostiles> hostiles;

    public Array<WorldObjects> worldObjects;
    public Array<Sign> signs;

    private float playerTpSpawnX;
    private float playerTpSpawnY;

    //    cam
    OrthographicCamera cam;


    //    mouse
    public float mouseX;
    public float mouseY;
    Vector3 mouse3d;

    //    Entities
    public Player player;
    private boolean playerHasSpawn = false;
    private boolean playerHasTeleported = false;

    public Boss finalBoss;

    //    UI
    Label lblHealth;
    Label lblCurrWeapon;
    Label lblFireMode;
    Label lblDialog;
    Label lblBossHealth;
    Label lblScore;

    final String STR_HEALTH = "HP: ";
    final String STR_WEAPON = "Arma actual: ";

    String mapOstPath;
    float mapOstVol = 0;

    String scoreStr = "";

    private boolean isBossMap = false;
    boolean isWithinBossArena = false;

    private float timeBeforeWinScr = 1.25f;

    public MainScreen(MyGdxGame game) {
        super(game);
//        stage
        mainStage = new Stage();
        uiStage = new Stage();

//        actorComparator
        compar = new ActorComparator();

//        map
        if (GameParams.touchedTeleporter == null) {
            map = resourceManager.getMap("world/maps/spawn_map_b.tmx");
            this.playerHasTeleported = false;

        } else {
            map = resourceManager.getMap(GameParams.touchedTeleporter.tgtMapPath);
            this.playerHasTeleported = true;

        }


        ren = new OrthogonalTiledMapRenderer(map, mainStage.getBatch());

        MapProperties props = map.getProperties();

        tileWidth = props.get("tilewidth", Integer.class);
        tileHeight = props.get("tileheight", Integer.class);
        mapWidthTiles = props.get("width", Integer.class);
        mapHeightTiles = props.get("height", Integer.class);

        mapWidthRaw = tileWidth * mapWidthTiles;
        mapHeightRaw = tileHeight * mapHeightTiles;

        hiSolids = new Array<>();
        loSolids = new Array<>();

        neutralNPCs = new Array<>();
        hostiles = new Array<>();

        teleporters = new Array<>();

        worldObjects = new Array<>();
        signs = new Array<>();

        instantiateElements(props);

        instantiateSolids(props);

        if (playerHasTeleported) {
            player = new Player(playerTpSpawnX +
                    (GameParams.touchedTeleporter.getTpTgtOffsetX() * tileWidth) - tileWidth / 2,
                    playerTpSpawnY + (GameParams.touchedTeleporter.getTpTgtOffsetY() * tileHeight),
                    mainStage, this);

            this.playerHasSpawn = true;

        }

        if (!playerHasSpawn) {
            player = new Player(64, 64, mainStage, this);

        }


//        cam
        cam = (OrthographicCamera) mainStage.getCamera();
        cam.setToOrtho(false, GameParams.scrWidth * GameParams.ZOOM,
                GameParams.scrHeight * GameParams.ZOOM);
        ren.setView(cam);

//        mouse
        mouse3d = new Vector3();

    }

    private void setMapOst() {


    }

    private void instantiateElements(MapProperties props) {
        for (MapObject characters :
                getElementList()) {
            props = characters.getProperties();

            switch (props.get("spawn").toString()) {
//                Entities
                case "player":
                    if (!playerHasTeleported) {
                        player = new Player((float) props.get("x"),
                                (float) props.get("y"), mainStage, this);
                        this.playerHasSpawn = true;
                        mapOstPath = props.get("ost").toString();
                        mapOstVol = (float) props.get("volume");

                    }

                    break;

                case "character":
                    this.neutralNPCs.add(new NeutralOne((float) props.get("x") - this.tileWidth,
                            (float) props.get("y"), mainStage, this, (int) props.get("char_type"),
                            props.get("message").toString(),
                            ((float) props.get("path_size_x")),
                            ((float) props.get("path_size_y")),
                            ((float) props.get("speed"))));

                    break;

                case "enemy":
                    instantiateEnemies(props);

                    break;

//                Teleporters
                case "teleporter":
                    teleporter = new Teleporter((float) props.get("x"),
                            (float) props.get("y"), mainStage, this,
                            (float) props.get("offset_x"),
                            (float) props.get("offset_y"),
                            props.get("target_map_path").toString() +
                                    props.get("target_map").toString(),
                            props.get("id").toString(),
                            props.get("target_id").toString(),
                            (boolean) props.get("no_return"),
                            (int) props.get("min_score"),
                            (boolean) props.get("stop_ost"));

                    this.teleporters.add(teleporter);

                    if (GameParams.touchedTeleporter != null &&
                            teleporter.tpId.equals(GameParams.touchedTeleporter.tgtTpId)) {
                        this.playerTpSpawnX = teleporter.getX();
                        this.playerTpSpawnY = teleporter.getY();
                        mapOstPath = props.get("ost").toString();
                        mapOstVol = (float) props.get("volume");

                    }

                    this.isBossMap = (props.get("id").toString().equals("boss")) ? true : false;

                    break;

//                    World objects
                case "world_object":
                    instantiateWorldObjects(props);

                    break;

            }

        }

//        UI
//        lblHealth = new Label(STR_HEALTH, uiStyle1);
        initHud();

        if (!mapOstPath.equals("")) {
            SoundManager.playMusic(mapOstPath, mapOstVol);

        }

    }

    private void initHud() {
        lblHealth = new Label(STR_HEALTH + String.format("%03d", 000), ResourceManager.healthStyle);
        lblHealth.setPosition(0, GameParams.getScrHeight() - lblHealth.getHeight());
        lblHealth.setAlignment(Align.left);

        lblCurrWeapon = new Label(STR_WEAPON + "Beretta 98", ResourceManager.weaponStyle);
        lblCurrWeapon.setPosition(0, lblHealth.getY() - lblCurrWeapon.getHeight() + 3);
        lblCurrWeapon.setAlignment(Align.left);

        lblFireMode = new Label("Full Auto", ResourceManager.firemodeStyle);
        lblFireMode.setPosition(0, lblCurrWeapon.getY() - lblFireMode.getHeight() + 3);
        lblFireMode.setAlignment(Align.left);

        lblDialog = new Label("DIALOG!", ResourceManager.dialogStyle);
        lblDialog.setPosition(60, 60);
        lblDialog.setAlignment(Align.topLeft);
        lblDialog.setWrap(true);

        lblDialog.setWidth(GameParams.getScrWidth() - 2 * 60);
        lblDialog.pack();
        lblDialog.setWidth(GameParams.getScrWidth() - 2 * 60);
//        lblDialog.setHeight(120);

        lblDialog.setVisible(false);

        lblBossHealth = new Label("SALUD: 9999", ResourceManager.bossStyle);
        lblBossHealth.setPosition(GameParams.getScrWidth() / 2 - lblBossHealth.getWidth() / 2, GameParams.getScrHeight() - 125);
        lblBossHealth.setAlignment(Align.center);

        lblBossHealth.setVisible(false);

        lblScore = new Label("Score: " + String.format("%05d",
                (PlayerParams.scoreCount + PlayerParams.scoreCountRes)),
                ResourceManager.scoreStyle);
        lblScore.setPosition(GameParams.getScrWidth() - lblScore.getWidth(), GameParams.getScrHeight() - lblScore.getHeight());
//        lblScore.setAlignment(Align.center);
//        lblScore.setWidth(200);


        uiStage.addActor(lblHealth);
        uiStage.addActor(lblCurrWeapon);
        uiStage.addActor(lblFireMode);
        uiStage.addActor(lblDialog);
        uiStage.addActor(lblBossHealth);
        uiStage.addActor(lblScore);

    }

    private void instantiateWorldObjects(MapProperties props) {
        switch (props.get("type").toString()) {

            case "maple_tree":
                this.worldObjects.add(new MapleTree((float) props.get("x") - this.tileWidth,
                        (float) props.get("y"), mainStage, this, props.get("variant").toString()));

                break;

            case "pine_tree":
                this.worldObjects.add(new PineTree((float) props.get("x") - this.tileWidth,
                        (float) props.get("y"), mainStage, this, props.get("variant").toString()));

                break;

            case "vx_tree":
                this.worldObjects.add(new VxTree((float) props.get("x"),
                        (float) props.get("y"), mainStage, this, props.get("variant").toString()));

                break;

            case "crate":
                this.worldObjects.add(new Crate((float) props.get("x"),
                        (float) props.get("y"), mainStage, this, props.get("variant").toString()));

                break;

            case "crystal":
                this.worldObjects.add(new Crystal((float) props.get("x"),
                        (float) props.get("y"), mainStage, this, props.get("variant").toString()));

                break;

            case "sign":
                this.worldObjects.add(new Sign((float) props.get("x"),
                        (float) props.get("y"), mainStage, this, props.get("variant").toString(),
                        props.get("text").toString()));
                this.signs.add(new Sign((float) props.get("x"),
                        (float) props.get("y"), mainStage, this, props.get("variant").toString(),
                        props.get("text").toString()));

                break;

            case "streetlight":
                this.worldObjects.add(new Streetlight((float) props.get("x"),
                        (float) props.get("y"), mainStage, this, props.get("variant").toString()));

                break;

        }

    }

    private void instantiateCharacters(MapProperties props) {
        switch (props.get("character_type").toString()) {
            case "alpha":
//                System.out.println("alpha detected: " + props.get("message").toString());

                break;

            case "bravo":
//                System.out.println("bravo detected");

                break;

            case "charlie":
//                System.out.println("charlie detected");

                break;

        }

    }

    private void instantiateEnemies(MapProperties props) {
        switch (props.get("enemy_type").toString()) {
            case "archer":
                this.hostiles.add(new Archer((float) props.get("x") - this.tileWidth,
                        (float) props.get("y"), mainStage, this, (int) props.get("skin"),
                        (float) props.get("path_size_x"),
                        (float) props.get("path_size_y"),
                        (float) props.get("persecution_speed")));

                break;

            case "warlock":
                this.hostiles.add(new Warlock((float) props.get("x") - this.tileWidth,
                        (float) props.get("y"), mainStage, this, (int) props.get("skin"),
                        (float) props.get("path_size_x"),
                        (float) props.get("path_size_y"),
                        (float) props.get("persecution_speed")));

                break;

            case "assasin":
            case "assassin":
                this.hostiles.add(new Assasin((float) props.get("x") - this.tileWidth,
                        (float) props.get("y"), mainStage, this, (int) props.get("skin"),
                        (float) props.get("path_size_x"),
                        (float) props.get("path_size_y"),
                        (float) props.get("persecution_speed")));

                break;

            case "boss":
                this.finalBoss = new Boss((float) props.get("x") - this.tileWidth,
                        (float) props.get("y"), mainStage, this);

        }

    }

    public void render(float delta) {
        super.render(delta);
        mainStage.act();

        centerCam();

        checkCollisions();

        checkPlayerHealth();

        setMouseCoord();

        ren.setView(cam);
        ren.render();

        mainStage.getActors().sort(compar);

        mainStage.draw();

        ren.render(new int[]{7});
        ren.render(new int[]{12});
        ren.render(new int[]{13});

        updateUI();

        bossFight();

        if (timeBeforeWinScr < 0)
            swapToEndScr();

        else if (isBossMap && !finalBoss.getEnabled())
            timeBeforeWinScr -= delta;

    }

    //    TODO: replace boolean test with boss.getenabled
    private void bossFight() {
        if (isBossMap) {
            if (player.getX() > 288 && player.getY() > 288)
                isWithinBossArena = true;
//            SoundManager.stopMusic();
//            SoundManager.playMusic("audio/ost/zelda_wild_calamity_ganon.mp3", 0.35f);

            if (isWithinBossArena && finalBoss.getEnabled())
                if (player.getX() < 287 || player.getY() < 287) {
                    player.setX(player.getX() + 64);
                    player.setY(player.getY() + 32);

                }

        }

    }

    private void updateUI() {
        uiStage.draw();

        this.lblHealth.setText(STR_HEALTH + String.format("%03d", (int) this.player.getPlayerHP()));
        this.lblCurrWeapon.setText(STR_WEAPON + this.player.getPlayerCurrWeapon().getType());
        this.lblFireMode.setText((!this.player.getPlayerCurrWeapon().isInFullAuto) ? "Semi" : "Full Auto");

        this.lblDialog.setVisible(player.isInDialog());
        this.lblDialog.setText(player.getDialog());

        this.lblScore.setText("Score: " + String.format("%05d", (PlayerParams.scoreCount + PlayerParams.scoreCountRes)));

        if (this.isBossMap) {
            if (isWithinBossArena && finalBoss.getEnabled()) {
                this.lblBossHealth.setText("SALUD: " + String.format("%04d", (int) finalBoss.getHealth()));
                this.lblBossHealth.setVisible(true);

            } else
                this.lblBossHealth.setVisible(false);

        }

    }

    private void swapToEndScr() {
        if (timeBeforeWinScr < 0) {
            SoundManager.stopMusic();

            GameParams.touchedTeleporter = null;

            PlayerParams.hp = PlayerParams.MAX_PLAYER_HEALTH;
            PlayerParams.scoreCount = 0;
            PlayerParams.chosenWeapon = 0;

            game.setScreen(new EndScreen(game));

        }

    }

    private void setMouseCoord() {
        mouse3d.x = Gdx.input.getX();
        mouse3d.y = Gdx.input.getY();
        mouse3d.z = 0;

        cam.unproject(mouse3d);

        mouseX = mouse3d.x;
        mouseY = mouse3d.y;

    }

    private void checkPlayerHealth() {
        if (player.getPlayerHP() <= 0) {
            SoundManager.stopMusic();
            game.setScreen(new DeathScreen(game));

        }

    }

    private void checkCollisions() {
        for (SolidLow solidObj :
                loSolids) {
            if (solidObj.getEnabled() && solidObj.overlaps(player))
                player.preventOverlap(solidObj);

            if (finalBoss != null)
                if (solidObj.getEnabled() && finalBoss.getEnabled() && solidObj.overlaps(finalBoss))
                    finalBoss.preventOverlap(solidObj);

            for (Neutrals npc :
                    neutralNPCs) {
                if (solidObj.getEnabled() && npc.getEnabled() && solidObj.overlaps(npc)) {
                    npc.preventOverlap(solidObj);

                }

            }

            for (Hostiles enemy :
                    hostiles) {
                if (solidObj.getEnabled() && enemy.getEnabled() && solidObj.overlaps(enemy)) {
                    enemy.preventOverlap(solidObj);

                }

            }

        }

        for (Teleporter tp :
                teleporters) {
            if (tp.getEnabled() && !tp.noReturn && tp.overlaps(player) &&
                    PlayerParams.scoreCount + PlayerParams.scoreCountRes >= tp.getScoreQuota()) {
                System.out.println(tp.tpId + " | " + tp.isStopOst());
                //        tp target reserve purge
                if (SoundManager.isMusicPlaying() && teleporter.isStopOst()) {
                    SoundManager.stopMusic();
                    System.out.println("stop!");

                }

                PlayerParams.scoreCountRes += PlayerParams.scoreCount;
                PlayerParams.scoreCount = 0;

                GameParams.touchedTeleporter = null;

                GameParams.touchedTeleporter = tp;

                game.setScreen(new MainScreen(game));

            }

        }

        for (Neutrals neutral :
                neutralNPCs) {
            if (neutral.getEnabled() && neutral.overlaps(player)) {
                player.preventOverlap(neutral);

            }

        }

        for (Hostiles hostile :
                hostiles) {
            if (hostile.getEnabled() && hostile.overlaps(player)) {
                player.preventOverlap(hostile);

            }

        }

        if (finalBoss != null)
            if (finalBoss.getEnabled() && finalBoss.overlaps(player))
                player.preventOverlap(finalBoss);

        for (WorldObjects wObj :
                worldObjects) {
            if (wObj.getEnabled() && wObj.overlaps(player)) {
                player.preventOverlap(wObj);

            }

            for (Neutrals npc :
                    neutralNPCs) {
                if (wObj.getEnabled() && npc.getEnabled() && wObj.overlaps(npc)) {
                    npc.preventOverlap(wObj);

                }

            }

            for (Hostiles enemy :
                    hostiles) {
                if (wObj.getEnabled() && enemy.getEnabled() && wObj.overlaps(enemy)) {
                    enemy.preventOverlap(wObj);

                }

            }

            if (finalBoss != null)
                if (wObj.getEnabled() && finalBoss.getEnabled() && wObj.overlaps(finalBoss))
                    finalBoss.preventOverlap(wObj);

        }

    }

    private void centerCam() {
        this.cam.position.x = camCollimator(this.player.getCenteredX());
        this.cam.position.y = camCollimator(this.player.getCenteredY());

        this.cam.position.x = MathUtils.clamp(this.cam.position.x, this.cam.viewportWidth / 2,
                this.mapWidthRaw - this.cam.viewportWidth / 2);
        this.cam.position.y = MathUtils.clamp(this.cam.position.y, this.cam.viewportHeight / 2,
                this.mapHeightRaw - this.cam.viewportHeight / 2);

        this.cam.update();

    }

    private float camCollimator(float pos) {
//        simplifies player pos to avoid floating-point errors
//        correction factor varies on zoom level
        return Math.round(pos * GameParams.CORRECTION_FACTOR) / GameParams.CORRECTION_FACTOR;

    }

    public int getTileWidth() {
        return this.tileWidth;

    }

    public int getTileHeight() {
        return this.tileHeight;

    }

    //    DEPRECATED
    public void setMap(String map) {
        this.map.dispose(); // Dispose your map first
        this.map = ResourceManager.getMap(map);
        this.ren = new OrthogonalTiledMapRenderer(this.map, mainStage.getBatch());

    }

    private void instantiateSolids(MapProperties props) {
        for (MapObject solidObj :
                getRectangleList("sRecHi", "type")) {
            props = solidObj.getProperties();

            hiSolids.add(new SolidHigh((float) props.get("x"), (float) props.get("y"),
                    mainStage, (float) props.get("width"), (float) props.get("height")));

            loSolids.add(new SolidLow((float) props.get("x"), (float) props.get("y"),
                    mainStage, (float) props.get("width"), (float) props.get("height")));

        }

        for (MapObject solidObj :
                getRectangleList("sRecLo", "type")) {
            props = solidObj.getProperties();

            loSolids.add(new SolidLow((float) props.get("x"), (float) props.get("y"),
                    mainStage, (float) props.get("width"), (float) props.get("height")));

        }

        for (Polygon poly :
                getPolygonList("sPolyHi", "type")) {
            hiSolids.add(new SolidHigh(poly.getX(), poly.getY(), mainStage, poly));
            loSolids.add(new SolidLow(poly.getX(), poly.getY(), mainStage, poly));

        }

        for (Polygon poly :
                getPolygonList("sPolyLo", "type")) {
            loSolids.add(new SolidLow(poly.getX(), poly.getY(), mainStage, poly));

        }

    }

    public ArrayList<MapObject> getRectangleList(String propertyAttrib, String property) {
        ArrayList<MapObject> list = new ArrayList<MapObject>();
        for (MapLayer layer : map.getLayers()) {
            for (MapObject obj : layer.getObjects()) {
                if (!(obj instanceof RectangleMapObject))
                    continue;

                MapProperties props = obj.getProperties();
                if (props.containsKey(property) && props.get(property).equals(propertyAttrib)) {
                    list.add(obj);

                }

            }

        }

        return list;

    }

    public ArrayList<Polygon> getPolygonList(String propertyAttrib, String property) {

        Polygon poly;
        ArrayList<Polygon> list = new ArrayList<Polygon>();
        for (MapLayer layer : map.getLayers()) {
            for (MapObject obj : layer.getObjects()) {


                if (!(obj instanceof PolygonMapObject))
                    continue;

                MapProperties props = obj.getProperties();
                if (props.containsKey(property) && props.get(property).equals(propertyAttrib)) {

                    poly = ((PolygonMapObject) obj).getPolygon();
                    list.add(poly);

                }

            }

        }

        return list;

    }


    public ArrayList<MapObject> getElementList() {
        ArrayList<MapObject> list = new ArrayList<MapObject>();
        for (MapLayer layer : map.getLayers()) {
            for (MapObject obj : layer.getObjects()) {
                if (!(obj instanceof TiledMapTileMapObject))
                    continue;
                MapProperties props = obj.getProperties();

                TiledMapTileMapObject tmtmo = (TiledMapTileMapObject) obj;
                TiledMapTile t = tmtmo.getTile();
                MapProperties defaultProps = t.getProperties();
                if (defaultProps.containsKey("spawn")) {
                    list.add(obj);

                }

                Iterator<String> propertyKeys = defaultProps.getKeys();
                while (propertyKeys.hasNext()) {
                    String key = propertyKeys.next();

                    if (props.containsKey(key))
                        continue;

                    else {
                        Object value = defaultProps.get(key);
                        props.put(key, value);

                    }

                }

            }

        }

        return list;

    }

    public void masterReload() {
        if (GameParams.debug)
            this.game.setScreen(new MainScreen(game));

    }

    public int getMapWidthRaw() {
        return mapWidthRaw;
    }

    public int getMapHeightRaw() {
        return mapHeightRaw;
    }

    public boolean isBossMap() {
        return isBossMap;
    }

}
