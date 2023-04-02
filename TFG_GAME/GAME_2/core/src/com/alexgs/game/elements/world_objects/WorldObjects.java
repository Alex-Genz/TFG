package com.alexgs.game.elements.world_objects;


import com.alexgs.game.elements.Element;
import com.alexgs.game.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class WorldObjects extends Element {
    GameScreen lvl;

    protected Animation<TextureRegion> texture;

    public Element collider;


    public WorldObjects(float x, float y, Stage s, GameScreen lvl) {
        super(x, y, s);
        this.lvl = lvl;

    }

}
