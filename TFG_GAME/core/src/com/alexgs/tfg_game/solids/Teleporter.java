package com.alexgs.tfg_game.solids;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Teleporter extends Solid {
    public String tgtMapPath;

    public float tpTgtOffsetX;
    public float tpTgtOffsetY;

    public Teleporter(float xPos, float yPos, Stage stage, float width, float height, float offsetX, float offsetY, String tgtMapPath) {
        super(xPos, yPos, stage, width, height);
        this.tgtMapPath = tgtMapPath;

        this.tpTgtOffsetX = offsetX;
        this.tpTgtOffsetY = offsetY;

    }

    public Teleporter(float x, float y, Stage s, Polygon poly, float offsetX, float offsetY, String tgtMapPath) {
        super(x, y, s, poly);

        this.tgtMapPath = tgtMapPath;

        this.tpTgtOffsetX = offsetX;
        this.tpTgtOffsetY = offsetY;

    }

}
