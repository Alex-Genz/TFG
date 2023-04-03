package com.alexgs.game.elements;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class LowSolid extends Element {

    public LowSolid(float xPos, float yPos, Stage stage, float width, float height) {
        super(xPos, yPos, stage, width, height);
        // TODO Auto-generated constructor stub

        float[] vertices = {0, 0, width, 0, width, height, 0, height};
        colision = new Polygon(vertices);
        this.setSize(width, height);

    }

}
