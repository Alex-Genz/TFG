package com.alexgs.tfg_game.scr.ui_scr;

import com.alexgs.tfg_game.MyGdxGame;
import com.alexgs.tfg_game.managers.ResourceManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;


public class BScreen implements Screen, InputProcessor {
    public final MyGdxGame game;
    public ResourceManager resourceManager;
    public Stage actualStage;
    public LabelStyle uiStyle;

    InputMultiplexer im;

    public Stage uiStage;

    public BScreen(MyGdxGame game) {

        this.uiStage = new Stage();

        BitmapFont font = new BitmapFont();
        font.getData().setScale(2);
        uiStyle = new LabelStyle(font, Color.LIGHT_GRAY);

        this.game = game;
        this.resourceManager = game.resMgr;

    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        im = (InputMultiplexer) Gdx.input.getInputProcessor();
        im.addProcessor(this);
        im.addProcessor(this.uiStage);

    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        // actualStage.act(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // actualStage.draw();
        // handleKeyboard();

    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
        im.removeProcessor(this);
        im.removeProcessor(this.uiStage);
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean keyDown(int arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    public boolean scrolled(int arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        return false;
    }

}
