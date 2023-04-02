package com.alexgs.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;

    TiledMap map;
    OrthogonalTiledMapRenderer ren;
    OrthographicCamera cam;
    Stage stg;

    private int tileWidth, tileHeight, mapWidthRaw, mapHeightRaw, mapWidthTiles, mapHeightTiles;

    int x = 500;
    int y = 500;

    @Override
    public void create () {
        map = new TmxMapLoader().load("world/maps/forest.tmx");
        ren = new OrthogonalTiledMapRenderer(map);

        stg = new Stage();

        cam = (OrthographicCamera) stg.getCamera();
        cam.setToOrtho(false, GameParamsCore.SCR_WIDTH * 0.75f, GameParamsCore.SCR_HEIGHT * 0.75f);
        ren.setView(cam);

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        MapProperties props = map.getProperties();

        tileWidth = props.get("tilewidth", Integer.class);
        tileHeight = props.get("tileheight", Integer.class);
        mapWidthTiles = props.get("width", Integer.class);
        mapHeightTiles = props.get("height", Integer.class);

        mapWidthRaw = tileWidth * mapWidthTiles;
        mapHeightRaw = tileHeight * mapHeightTiles;

    }

    @Override
    public void render () {
/*        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();*/
        ren.setView(cam);
        centerCam();
        controls();

        ren.render();

    }

    private void centerCam() {
        cam.position.x = x;
        cam.position.y = y;

        this.cam.position.x = MathUtils.clamp(this.cam.position.x, this.cam.viewportWidth / 2,
                this.mapWidthRaw - this.cam.viewportWidth / 2);
        this.cam.position.y = MathUtils.clamp(this.cam.position.y, this.cam.viewportHeight / 2,
                this.mapHeightRaw - this.cam.viewportHeight / 2);

        cam.update();

    }

    private void controls() {
/*        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            x++;

        }*/

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y+=10;

        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y-=10;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x-=10;

        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x+=10;

        }

    }

    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}
