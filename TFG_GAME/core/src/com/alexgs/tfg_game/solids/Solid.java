package com.alexgs.tfg_game.solids;

import com.alexgs.tfg_game.elements.Element;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Solid extends Element {
    public Solid(float xPos, float yPos, Stage stage, float width, float height) {
        super(xPos, yPos, stage, width, height);

        float[] vertices = {0, 0, width, 0, width, height, 0, height};
        colision = new Polygon(vertices);
        this.setSize(width, height);

    }

    public Solid(float x, float y, Stage s, Polygon poly) {
        super(x, y, s);
        this.colision = poly;

    }

}
