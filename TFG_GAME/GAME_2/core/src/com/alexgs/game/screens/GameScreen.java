package com.alexgs.game.screens;

import com.alexgs.game.elements.Solid;
import com.alexgs.game.elements.character.Citizen;
import com.alexgs.game.elements.character.Cow;
import com.alexgs.game.elements.character.Enemy;
import com.alexgs.game.elements.character.Player;
import com.alexgs.game.elements.powerup.Powerup;
import com.alexgs.game.elements.powerup.RegenPowerup;
import com.alexgs.game.elements.powerup.SpeedPowerup;
import com.alexgs.game.elements.world_objects.*;
import com.alexgs.game.game.Demo;
import com.alexgs.game.game.GameParams;
import com.alexgs.game.managers.AudioManager;
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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class GameScreen extends BScreen {
    //    GUI
    Stage mainStage;

    Label lblHealth;
    Label lblPwUP;
    Label lblScore;

    private float tCool = 5;

    //    entities
    public Player player;

    boolean dontCallMe = false;


    //    map
    public Array<Solid> solids;
    public Array<Solid> lowSolids;
    public Array<WorldObjects> worldObj;

    public Array<Enemy> enemies;
    public Array<Powerup> powerups;
    public Array<Cow> cows;
    public Array<Citizen> citizens;

    private float cDown = 5;

    private int tileWidth, tileHeight, mapWidthRaw, mapHeightRaw, mapWidthTiles, mapHeightTiles;

    private ActorComparator comparator;

    //    cam
    OrthographicCamera cam;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private static float CORRECTION_FACTOR = 10;


    // mouse
    public float mouseX;
    public float mouseY;
    Vector3 mouse3D;

//    MISC


    public GameScreen(Demo game) {
        super(game);

        comparator = new ActorComparator();
        mouse3D = new Vector3();
        mainStage = new Stage();

//        map
//        map loading
        switch (GameParams.LEVEL) {
            case 0:
            case 1:
                map = resourceManager.getMap("world/maps/map_1.tmx");
                AudioManager.playMusic("audio/music/hl1_military_precision.mp3");

                break;

            case 2:
                map = resourceManager.getMap("world/maps/map_2.tmx");
                AudioManager.playMusic("audio/music/hl2_cp_violation.mp3");
                GameParams.score = GameParams.scoreRes;

                break;

        }

//        map props and elements
        solids = new Array<>();
        MapProperties props = map.getProperties();

        tileWidth = props.get("tilewidth", Integer.class);
        tileHeight = props.get("tileheight", Integer.class);
        mapWidthTiles = props.get("width", Integer.class);
        mapHeightTiles = props.get("height", Integer.class);

        mapWidthRaw = tileWidth * mapWidthTiles;
        mapHeightRaw = tileHeight * mapHeightTiles;

//        map object extraction

        Solid solid;
        for (MapObject solidObj :
                getRectangleList("solid", "type")) {
            props = solidObj.getProperties();
            solid = new Solid((float) props.get("x"), (float) props.get("y"),
                    mainStage, (float) props.get("width"), (float) props.get("height"));

            solids.add(solid);

        }

        for (Polygon poly : getPolygonList("poly", "type")) {
            solids.add(new Solid(poly.getX(), poly.getY(), mainStage, poly));

        }



        lowSolids = new Array<>();

        for (MapObject solidObj :
                getRectangleList("solidLow", "type")) {
            props = solidObj.getProperties();
            solid = new Solid((float) props.get("x"), (float) props.get("y"),
                    mainStage, (float) props.get("width"), (float) props.get("height"));

            lowSolids.add(solid);

        }

        for (Polygon poly : getPolygonList("polyLow", "type")) {
            lowSolids.add(new Solid(poly.getX(), poly.getY(), mainStage, poly));

        }

        float playerSpawnX = 0;
        float playerSpawnY = 0;

        enemies = new Array<>();
        powerups = new Array<>();
        cows = new Array<>();
        citizens = new Array<>();
        worldObj = new Array<>();
        for (MapObject characters : getElementList()) {
            props = characters.getProperties();

            switch (props.get("spawn").toString()) {
                case "shooter":
//                    loads "shooter" enemies
                    Enemy blob = new Enemy((float) props.get("x"), (float) props.get("y"), mainStage, this);
                    blob.setEnabled(true);
                    enemies.add(blob);

                    break;

                case "cow":
//                    loads cows that just look at a random direction every couple seconds
                    Cow cow = new Cow((float) props.get("x"), (float) props.get("y"), mainStage, this);
                    cows.add(cow);

                    break;

                case "player":
//                    sets player spawn coordinates
                    playerSpawnX = (float) props.get("x");
                    playerSpawnY = (float) props.get("y");

                    break;

                case "powerup":
                    Powerup powup = ((Math.random() > 0.5) ?
                            new SpeedPowerup((float) props.get("x"), (float) props.get("y"), mainStage, this) :
                            new RegenPowerup((float) props.get("x"), (float) props.get("y"), mainStage, this));
                    powerups.add(powup);

                    break;

                case "citizen":
                    Citizen cit = new Citizen((float) props.get("x"), (float) props.get("y"), mainStage, this);
                    citizens.add(cit);

                    break;

//                    END OF SPAWNABLE ENTITIES | START OF SPAWNABLE WORLD OBJECTS
                case "maple":
                    Maple maple = new Maple((float) props.get("x"), (float) props.get("y"), mainStage, this);
                    worldObj.add(maple);

                    break;

                case "pine":
                    Pine pine = new Pine((float) props.get("x"), (float) props.get("y"), mainStage, this);
                    worldObj.add(pine);

                    break;

                case "tree1":
                    ThinTree1 tree = new ThinTree1((float) props.get("x"), (float) props.get("y"), mainStage, this);
                    worldObj.add(tree);

                    break;



                case "well2":
                    WellB well = new WellB((float) props.get("x"), (float) props.get("y"), mainStage, this);
                    worldObj.add(well);

                    break;

                case "sign1":
                    SignA sign = new SignA((float) props.get("x"), (float) props.get("y"), mainStage, this);
                    worldObj.add(sign);

                    break;



                case "streetlight":
                    Streetlight light = new Streetlight((float) props.get("x"), (float) props.get("y"), mainStage, this);
                    worldObj.add(light);

                    break;

                case "scarecrow":
                    Scarecrow sCrow = new Scarecrow((float) props.get("x"), (float) props.get("y"), mainStage, this);
                    worldObj.add(sCrow);

                    break;

            }

        }

//        --

//        cam
        renderer = new OrthogonalTiledMapRenderer(map, mainStage.getBatch());
        cam = (OrthographicCamera) mainStage.getCamera();
        cam.setToOrtho(false, GameParams.getScrWidth() * GameParams.zoom,
                GameParams.getScrHeight() * GameParams.zoom);
        renderer.setView(cam);
//        --

//        spawn
        player = new Player(playerSpawnX, playerSpawnY, mainStage, this);

        uiStage = new Stage();
        lblHealth = new Label("Health: " + (int) GameParams.playerHealth, uiStyle);
        lblHealth.setPosition(10, GameParams.getScrHeight() - 40);

        lblPwUP = new Label("HELLO", uiStyle);
        lblPwUP.setPosition(10, lblHealth.getY() - lblHealth.getHeight());
        lblPwUP.setVisible(false);

        lblScore = new Label("Score: ", uiStyle);
        lblScore.setPosition(GameParams.getScrWidth() - 180, GameParams.getScrHeight() - 40);

        uiStage.addActor(lblHealth);
        uiStage.addActor(lblPwUP);
        uiStage.addActor(lblScore);

    }

    public void render(float delta) {
        super.render(delta);
        mainStage.act();

        colliders();

        if (!dontCallMe)
            checkForChange();

        if (GameParams.playerHealth <= 0) {
            game.setScreen(new DeathScreen(game));

        }

        centerCam();

        // mouse stuff
        mouse3D.x = Gdx.input.getX();
        mouse3D.y = Gdx.input.getY();
        mouse3D.z = 0;

        cam.unproject(mouse3D);
        mouseX = mouse3D.x;
        mouseY = mouse3D.y;
        // end of mouse stuff

        renderer.setView(cam);
        renderer.render();

        mainStage.getActors().sort(comparator);

        mainStage.draw();

        renderer.render(new int[]{4});
        renderer.render(new int[]{7});

        this.lblHealth.setText("Health: " + (int) GameParams.playerHealth);
        this.lblScore.setText("Score: " + (int) GameParams.score);

        this.lblPwUP.setText(this.player.txt);
        this.lblPwUP.setVisible((this.player.regenPwUpDur > 0 || this.player.speedPwUpDur > 0) ? true : false);

        uiStage.draw();

    }

    private void checkForChange() {
        if (checkIfEnemiesDead()) {
            if (GameParams.LEVEL == 2) {
                GameParams.LEVEL = 1;
                game.setScreen(new EndScreen(game));

            } else {
                GameParams.LEVEL++;
                GameParams.scoreRes+=GameParams.score;
                game.setScreen(new GameScreen(game));

            }

        }

    }

    private boolean checkIfEnemiesDead() {
        int count = 0;
        for (Enemy enemy :
                enemies) {
            if (!enemy.getEnabled())
                count++;

        }

        return (count == enemies.size) ? true : false;

    }

    private void colliders() {
        for (Solid s :
                solids) {
            if (s.getEnabled() && s.overlaps(player))
                player.preventOverlap(s);

            for (Enemy red :
                    enemies) {
                if (s.getEnabled() && (red.getEnabled() && s.overlaps(red)))
                    red.preventOverlap(s);

            }

            for (Cow cow :
                    cows) {
                if (s.getEnabled() && (cow.getEnabled() && s.overlaps(cow)))
                    cow.preventOverlap(s);

            }

            for (Citizen cit :
                 citizens) {
                if (s.getEnabled() && (cit.getEnabled() && s.overlaps(cit)))
                    cit.preventOverlap(s);

            }

        }

        for (Solid ls :
                lowSolids) {
            if (ls.getEnabled() && ls.overlaps(player))
                player.preventOverlap(ls);

            for (Enemy red :
                    enemies) {
                if (ls.getEnabled() && (red.getEnabled() && ls.overlaps(red)))
                    red.preventOverlap(ls);

            }

            for (Cow cow :
                    cows) {
                if (ls.getEnabled() && (cow.getEnabled() && ls.overlaps(cow)))
                    cow.preventOverlap(ls);

            }

            for (Citizen cit :
                 citizens) {
                if (ls.getEnabled() && (cit.getEnabled() && ls.overlaps(cit)))
                    cit.preventOverlap(ls);

            }

        }

        for (WorldObjects obj :
                worldObj) {
            if (obj.getEnabled() && obj.collider.overlaps(player))
                player.preventOverlap(obj.collider);

            for (Enemy red :
                    enemies) {
                if (obj.getEnabled() && (red.getEnabled() && obj.collider.overlaps(red)))
                    red.preventOverlap(obj.collider);

            }

            for (Cow cow :
                    cows) {
                if (obj.getEnabled() && (cow.getEnabled() && obj.collider.overlaps(cow)))
                    cow.preventOverlap(obj.collider);

            }

            for (Citizen cit :
                    citizens) {
                if (obj.getEnabled() && (cit.getEnabled() && obj.overlaps(cit)))
                    cit.preventOverlap(obj.collider);

            }

        }

    }

    public void centerCam() {
        this.cam.position.x = camCollimator(this.player.getCenteredX());
        this.cam.position.y = camCollimator(this.player.getCenteredY());

        this.cam.position.x = MathUtils.clamp(this.cam.position.x, this.cam.viewportWidth / 2,
                this.mapWidthRaw - this.cam.viewportWidth / 2);
        this.cam.position.y = MathUtils.clamp(this.cam.position.y, this.cam.viewportHeight / 2,
                this.mapHeightRaw - this.cam.viewportHeight / 2);

        this.cam.update();

    }

    public class ActorComparator implements Comparator<Actor> {

        @Override
        public int compare(Actor actor1, Actor actor2) {
            if (actor1.getY() == actor2.getY()) {
                return 0;

            }

            if (actor1.getY() > actor2.getY()) {
                return -1;

            }

            return 1;
        }

    }

    private float camCollimator(float pos) {
//        simplifies player pos to avoid floating-point errors
        return Math.round(pos * CORRECTION_FACTOR) / CORRECTION_FACTOR;

    }

//
//
//

    public ArrayList<MapObject> getRectangleList(String propertyName, String category) {
        ArrayList<MapObject> list = new ArrayList<MapObject>();
        for (MapLayer layer : map.getLayers()) {
            for (MapObject obj : layer.getObjects()) {
                if (!(obj instanceof RectangleMapObject))
                    continue;
                MapProperties props = obj.getProperties();
                if (props.containsKey(category) && props.get(category).equals(propertyName)) {
                    list.add(obj);
                }

            }

        }

        return list;
    }

    public ArrayList<Polygon> getPolygonList(String propertyName, String category) {

        Polygon poly;
        ArrayList<Polygon> list = new ArrayList<Polygon>();
        for (MapLayer layer : map.getLayers()) {
            for (MapObject obj : layer.getObjects()) {


                if (!(obj instanceof PolygonMapObject))
                    continue;
                MapProperties props = obj.getProperties();
                if (props.containsKey(category) && props.get(category).equals(propertyName)) {

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
