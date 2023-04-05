package com.alexgs.tfg_game.elements.characters;

import com.alexgs.tfg_game.elements.Element;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Characters extends Element {
    MainScreen lvl;

    public Characters(float x, float y, Stage s, MainScreen lvl) {
        super(x, y, s);
        this.lvl = lvl;

    }

    public void act(float delta) {
        super.act(delta);

    }

}
