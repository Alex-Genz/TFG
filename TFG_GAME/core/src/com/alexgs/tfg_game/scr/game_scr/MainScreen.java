package com.alexgs.tfg_game.scr.game_scr;

import com.alexgs.tfg_game.MyGdxGame;
import com.alexgs.tfg_game.elements.characters.hostiles.*;
import com.alexgs.tfg_game.elements.characters.neutrals.player.Player;
import com.alexgs.tfg_game.elements.characters.neutrals.*;
import com.alexgs.tfg_game.elements.world_obj.*;
import com.alexgs.tfg_game.managers.ResourceManager;
import com.alexgs.tfg_game.params.GameParams;
import com.alexgs.tfg_game.scr.ui_scr.BScreen;

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

    public Array<Neutrals> neutralNPCs;
    public Array<Hostiles> hostiles;

    public Array<WorldObjects> worldObjects;

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
        teleporters = new Array<>();

        neutralNPCs = new Array<>();
        hostiles = new Array<>();

        worldObjects = new Array<>();

        for (MapObject characters :
                getElementList()) {
            props = characters.getProperties();

            switch (props.get("spawn").toString()) {
                case "player":
                    if (!playerHasTeleported) {
                        player = new Player((float) props.get("x"),
                                (float) props.get("y"), mainStage, this);
                        this.playerHasSpawn = true;

                    }

                    break;

                case "character":
                    this.neutralNPCs.add(new NeutralOne((float) props.get("x") - this.tileWidth,
                            (float) props.get("y"), mainStage, this, (int) props.get("char_type"),
                            props.get("message").toString(),
                            Float.parseFloat(props.get("path_size_x").toString()),
                            Float.parseFloat(props.get("path_size_y").toString())));

                    break;

                case "enemy":
                    this.hostiles.add(new Shooter((float) props.get("x") - this.tileWidth,
                            (float) props.get("y"), mainStage, this, (int) props.get("skin"),
                            Float.parseFloat(props.get("path_size_x").toString()),
                            Float.parseFloat(props.get("path_size_y").toString())));

                    break;

                case "normal_maple_tree":
                    this.worldObjects.add(new MapleTreeNorm((float) props.get("x") - this.tileWidth,
                            (float) props.get("y"), mainStage, this));

                    break;

                case "snow_maple_tree":
                    this.worldObjects.add(new MapleTreeSnow((float) props.get("x") - this.tileWidth,
                            (float) props.get("y"), mainStage, this));

                    break;

                case "normal_pine_tree":
                    this.worldObjects.add(new PineTreeNorm((float) props.get("x") - this.tileWidth,
                            (float) props.get("y"), mainStage, this));

                    break;

                case "snow_pine_tree":
                    this.worldObjects.add(new PineTreeSnow((float) props.get("x") - this.tileWidth,
                            (float) props.get("y"), mainStage, this));

                    break;

                case "crate":
                    this.worldObjects.add(new Crate((float) props.get("x") - this.tileWidth,
                            (float) props.get("y"), mainStage, this, props.get("variant").toString()));

                    break;

            }

        }

        instantiateSolids(props);

        if (playerHasTeleported) {
            player = new Player(playerTpSpawnX +
                    (GameParams.touchedTeleporter.tpTgtOffsetX * tileWidth),
                    playerTpSpawnY + (GameParams.touchedTeleporter.tpTgtOffsetY * tileHeight),
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
            case "sharpshooter":
//                System.out.println("sharpshooter with skinset number " + props.get("skin").toString());

                break;

            case "biter":
//                System.out.println("biter with skinset number " + props.get("skin").toString());

                break;

        }

    }

    public void render(float delta) {
        super.render(delta);
        mainStage.act();

        centerCam();

        checkCollisions();

        mouse3d.x = Gdx.input.getX();
        mouse3d.y = Gdx.input.getY();
        mouse3d.z = 0;

        setMouseCoord();

        ren.setView(cam);
        ren.render();

        mainStage.getActors().sort(compar);

        mainStage.draw();

        ren.render(new int[]{6});
        ren.render(new int[]{11});

        uiStage.draw();

    }

    private void setMouseCoord() {
        cam.unproject(mouse3d);

        mouseX = mouse3d.x;
        mouseY = mouse3d.y;

    }

    private void checkCollisions() {
        for (SolidLow solidObj :
                loSolids) {
            if (solidObj.getEnabled() && solidObj.overlaps(player)) {
                player.preventOverlap(solidObj);

            }

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

        for (Teleporter tpObj :
                teleporters) {
            if (tpObj.getEnabled() && !tpObj.noReturn && tpObj.overlaps(player)) {
                //        tp target reserve purge
                GameParams.touchedTeleporter = null;

                GameParams.touchedTeleporter = tpObj;

                game.setScreen(new MainScreen(game));

            }

        }

        for (Neutrals neutral :
                neutralNPCs) {
            if (neutral.getEnabled() && neutral.overlaps(player)) {
                player.preventOverlap(neutral);

            }

        }

        for (WorldObjects wObj :
                worldObjects) {
            if (wObj.getEnabled() && wObj.overlaps(player)) {
                player.preventOverlap(wObj);

            }

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

        for (MapObject solidObj :
                getRectangleList("teleporter", "tile_type")) {
            props = solidObj.getProperties();
            Teleporter teleporter = new Teleporter((float) props.get("x"), (float) props.get("y"),
                    mainStage, (float) props.get("width"), (float) props.get("height"),
                    Float.parseFloat(props.get("offset_x").toString()),
                    Float.parseFloat(props.get("offset_y").toString()),
                    props.get("target_map").toString(), props.get("id").toString(),
                    props.get("tgt_id").toString(),
                    Boolean.parseBoolean(props.get("no_return").toString()));

            if (GameParams.touchedTeleporter != null &&
                    teleporter.tpId.equals(GameParams.touchedTeleporter.tgtTpId)) {
                this.playerTpSpawnX = teleporter.getX();
                this.playerTpSpawnY = teleporter.getY();

            }

            teleporters.add(teleporter);

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

    public int getMapWidthRaw() {
        return mapWidthRaw;
    }

    public int getMapHeightRaw() {
        return mapHeightRaw;
    }

}
