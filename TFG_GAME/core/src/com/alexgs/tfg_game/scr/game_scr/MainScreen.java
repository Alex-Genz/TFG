package com.alexgs.tfg_game.scr.game_scr;

import com.alexgs.tfg_game.MyGdxGame;
import com.alexgs.tfg_game.params.GameParams;
import com.alexgs.tfg_game.scr.ui_scr.BScreen;

import com.alexgs.tfg_game.scr.util.ActorComparator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainScreen extends BScreen {
//    gui
    Stage mainStage;

//    actorComparator
    private ActorComparator compar;

//    map / map prop
    private TiledMap map;
    private OrthogonalTiledMapRenderer ren;
    private int tileWidth, tileHeight, mapWidthRaw, mapHeightRaw, mapWidthTiles, mapHeightTiles;

//    cam
    OrthographicCamera cam;
    private static final float CORRECTION_FACTOR = 10;

//    mouse
    private float mouseX;
    private float mouseY;
    Vector3 mouse3d;

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

//        cam
        cam = (OrthographicCamera) mainStage.getCamera();
        cam.setToOrtho(false, GameParams.SCR_WIDTH * GameParams.ZOOM, GameParams.SCR_HEIGHT * GameParams.ZOOM);
        ren.setView(cam);

//        mouse
        mouse3d = new Vector3();




//        extras
        cam.position.x = tileWidth * 2 + (tileWidth / 2);
        cam.position.y = tileHeight * 2 + (tileHeight / 2);


    }

    public void render(float delta) {
        super.render(delta);
        mainStage.act();



        ren.setView(cam);
        ren.render();

        moveCam();

        mainStage.draw();

        uiStage.draw();

    }

    private void moveCam() {
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            cam.position.x++;

        else if (Gdx.input.isKeyPressed(Input.Keys.A))
            cam.position.x--;

        if (Gdx.input.isKeyPressed(Input.Keys.W))
            cam.position.y++;

        else if (Gdx.input.isKeyPressed(Input.Keys.S))
            cam.position.y--;

    }

}
