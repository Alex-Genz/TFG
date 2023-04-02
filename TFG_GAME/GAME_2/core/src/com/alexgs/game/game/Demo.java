package com.alexgs.game.game;

import com.alexgs.game.managers.ResourceManager;
import com.alexgs.game.screens.LoadScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Demo extends Game {

    SpriteBatch batch;
    public ResourceManager resourceManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        InputMultiplexer im = new InputMultiplexer();
        Gdx.input.setInputProcessor(im);

        setScreen(new LoadScreen(this));

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        this.getScreen().dispose();
    }
}