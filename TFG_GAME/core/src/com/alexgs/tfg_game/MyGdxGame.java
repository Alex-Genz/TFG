package com.alexgs.tfg_game;

import com.alexgs.tfg_game.managers.ResourceManager;
import com.alexgs.tfg_game.scr.ui_scr.LoadScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
    SpriteBatch batch;
    public ResourceManager resMgr;

//    Texture img;

    @Override
    public void create() {
        batch = new SpriteBatch();
        InputMultiplexer inMult = new InputMultiplexer();
        Gdx.input.setInputProcessor(inMult);

        setScreen(new LoadScreen(this));

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    /*    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render () {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }*/

}
