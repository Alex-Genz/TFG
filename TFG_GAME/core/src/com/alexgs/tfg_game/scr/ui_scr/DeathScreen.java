package com.alexgs.tfg_game.scr.ui_scr;

import com.alexgs.tfg_game.MyGdxGame;
import com.alexgs.tfg_game.elements.characters.neutrals.player.PlayerParams;
import com.alexgs.tfg_game.managers.ResourceManager;
import com.alexgs.tfg_game.params.GameParams;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
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

    public DeathScreen(MyGdxGame game) {
        super(game);
        // TODO Auto-generated constructor stub

//        AudioManager.playMusic("audio/music/hl2e1_combine_advisory.mp3");

        tabla = new Table();
        tabla.setFillParent(true);

        BitmapFont font = new BitmapFont();
        font.getData().setScale(2f);
        lblInfo = new Label("You died", new Label.LabelStyle(font, Color.LIGHT_GRAY));
        lblInfo.setPosition(GameParams.scrWidth / 2 - lblInfo.getWidth() / 2, GameParams.scrHeight / 2 + lblInfo.getHeight());
        uiStage.addActor(lblInfo);

        this.uiStage.addActor(tabla);

        TextButton boton = new TextButton("Retry", ResourceManager.textButtonStyle);
        boton.addListener((Event e) -> {
            if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
                return false;

            PlayerParams.hp = PlayerParams.MAX_PLAYER_HEALTH;
            this.dispose();
            game.setScreen(new MainScreen(game));
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
