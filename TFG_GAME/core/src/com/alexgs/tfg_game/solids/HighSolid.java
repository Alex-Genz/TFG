package com.alexgs.tfg_game.solids;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class HighSolid extends Solid {
    public HighSolid(float xPos, float yPos, Stage stage, float width, float height) {
        super(xPos, yPos, stage, width, height);

    }

    public HighSolid(float x, float y, Stage s, Polygon poly) {
        super(x, y, s, poly);

    }

}
