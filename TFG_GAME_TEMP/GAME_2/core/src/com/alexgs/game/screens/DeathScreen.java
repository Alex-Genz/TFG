package com.alexgs.game.screens;

import com.alexgs.game.game.Demo;
import com.alexgs.game.game.GameParams;
import com.alexgs.game.managers.AudioManager;
import com.alexgs.game.managers.ResourceManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class DeathScreen extends BScreen {
    private Table tabla;

    private Label lblInfo;

    public DeathScreen(Demo game) {
        super(game);
        // TODO Auto-generated constructor stub

        AudioManager.playMusic("audio/music/hl2e1_combine_advisory.mp3");

        tabla = new Table();
        tabla.setFillParent(true);

        BitmapFont font = new BitmapFont();
        font.getData().setScale(2f);
        lblInfo = new Label("You died in level: " + GameParams.LEVEL + "\n" + "Score: " + GameParams.score, new Label.LabelStyle(font, Color.LIGHT_GRAY));
        lblInfo.setPosition(GameParams.getScrWidth() / 2 - lblInfo.getWidth() / 2, GameParams.getScrHeight() / 2 + lblInfo.getHeight());
        uiStage.addActor(lblInfo);

        this.uiStage.addActor(tabla);

        TextButton boton = new TextButton("Retry", ResourceManager.textButtonStyle);
        boton.addListener((Event e) -> {
            if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
                return false;

            GameParams.playerHealth = GameParams.MAX_PLAYER_HEALTH;
            GameParams.score = 0;
            this.dispose();
            game.setScreen(new GameScreen(game));
            return false;
        });
        tabla.add(boton);

/*        TextButton botonOpciones = new TextButton("Opciones", ResourceManager.textButtonStyle);
        tabla.add(botonOpciones);
        tabla.row();*/
        TextButton botonSalir = new TextButton("Exit", ResourceManager.textButtonStyle);
        botonSalir.addListener((Event e) -> {
            if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
                return false;
            this.dispose();
            Gdx.app.exit();
            return false;
        });
        tabla.add(botonSalir);
    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        super.render(delta);

        uiStage.act();
        uiStage.draw();

    }

}
