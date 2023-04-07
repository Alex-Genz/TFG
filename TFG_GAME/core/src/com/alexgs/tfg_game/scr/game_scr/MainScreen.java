package com.alexgs.tfg_game.scr.game_scr;

import com.alexgs.tfg_game.MyGdxGame;
import com.alexgs.tfg_game.elements.characters.Player;
import com.alexgs.tfg_game.managers.ResourceManager;
import com.alexgs.tfg_game.params.GameParams;
import com.alexgs.tfg_game.scr.ui_scr.BScreen;

import com.alexgs.tfg_game.scr.util.ActorComparator;
import com.alexgs.tfg_game.solids.HighSolid;
import com.alexgs.tfg_game.solids.LowSolid;
import com.alexgs.tfg_game.solids.Solid;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    public Array<Solid> solids;

//    cam
    OrthographicCamera cam;


//    mouse
    public float mouseX;
    public float mouseY;
    Vector3 mouse3d;

//    Entities
    Player player;

    public MainScreen(MyGdxGame game) {
        super(game);
//        stage
        mainStage = new Stage();
        uiStage = new Stage();

//        actorComparator
        compar = new ActorComparator();

//        map
        map = resourceManager.getMap("world/maps/devmap_1.tmx");
        ren = new OrthogonalTiledMapRenderer(map, mainStage.getBatch());

        MapProperties props = map.getProperties();

        tileWidth = props.get("tilewidth", Integer.class);
        tileHeight = props.get("tileheight", Integer.class);
        mapWidthTiles = props.get("width", Integer.class);
        mapHeightTiles = props.get("height", Integer.class);

        mapWidthRaw = tileWidth * mapWidthTiles;
        mapHeightRaw = tileHeight * mapHeightTiles;

        solids = new Array<>();

        instantiateObjects(props);
        for (MapObject characters :
                getElementList()) {
            props = characters.getProperties();

            switch (props.get("spawn").toString()) {
                case "player":
                    player = new Player((float) props.get("x"), (float) props.get("y"), mainStage, this);

                    break;

                case "character":
                    instantiateCharacters(props);

                    break;

                case "obj_spawn":


                    break;

            }

        }

        instantiateSolids(props);

//        cam
        cam = (OrthographicCamera) mainStage.getCamera();
//        cam.setToOrtho(false, GameParams.SCR_WIDTH * GameParams.ZOOM, GameParams.SCR_HEIGHT * GameParams.ZOOM);
        cam.setToOrtho(false, GameParams.scrWidth * GameParams.ZOOM, GameParams.scrHeight * GameParams.ZOOM);
        ren.setView(cam);

//        mouse
        mouse3d = new Vector3();




//        extras
//        cam.position.x = tileWidth * 2 + (tileWidth / 2);
//        cam.position.y = tileHeight * 2 + (tileHeight / 2);

//        entities
//        player = new Player(tileWidth * 2, tileHeight * 2, mainStage, this);

    }

    private void instantiateCharacters(MapProperties props) {
        switch (props.get("character_type").toString()) {
            case "alpha":
                System.out.println("alpha detected");

                break;

            case "bravo":
                System.out.println("bravo detected");

                break;

            case "charlie":
                System.out.println("charlie detected");

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

        cam.unproject(mouse3d);
        mouseX = mouse3d.x;
        mouseY = mouse3d.y;

        ren.setView(cam);
        ren.render();

        mainStage.draw();

        ren.render(new int[]{1});

        uiStage.draw();

    }

    private void checkCollisions() {
        for (Solid solidObj :
                solids) {
            if (solidObj.getEnabled() && solidObj instanceof HighSolid && solidObj.overlaps(player)) {
                player.preventOverlap(solidObj);
                player.stopMoving();

            }


    }
    }

    private void centerCam() {
        this.cam.position.x = camCollimator(this.player.getCenteredX());
        this.cam.position.y = camCollimator(this.player.getCenteredY());
//        this.cam.position.x = this.player.getX();
//        this.cam.position.y = this.player.getY();

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

    private void instantiateObjects(MapProperties props) {
/*        for (MapObject objects :
                getElementList()) {
            props = objects.getProperties();

            switch (props.get("type").toString()) {
                case "character_spawn":
                    System.out.println("char spawn detected");
                    player = new Player((float) props.get("x"), (float) props.get("y"), mainStage, this);

                    break;

                case "obj_spawn":


                    break;

            }

        }*/

    }

    public void setMap(String map)
    {
        this.map.dispose(); // Dispose your map first
        this.map = ResourceManager.getMap(map);
        this.ren = new OrthogonalTiledMapRenderer(this.map, mainStage.getBatch());

    }

    private void instantiateSolids(MapProperties props) {
        Solid solid;
        for (MapObject solidObj :
                getRectangleList("rec_high_solid", "type")) {
            props = solidObj.getProperties();
            solid = new HighSolid((float) props.get("x"), (float) props.get("y"),
                    mainStage, (float) props.get("width"), (float) props.get("height"));

            solids.add(solid);

        }


        for (MapObject solidObj :
                getRectangleList("rec_low_solid", "type")) {
            props = solidObj.getProperties();
            solid = new LowSolid((float) props.get("x"), (float) props.get("y"),
                    mainStage, (float) props.get("width"), (float) props.get("height"));

            solids.add(solid);

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

}
