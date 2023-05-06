package com.alexgs.tfg_game.solids;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Teleporter extends Solid {
    public String tgtMapPath;

    public float tpTgtOffsetX;
    public float tpTgtOffsetY;

    public String tpId;
    public String tgtTpId;

    public Teleporter(float xPos, float yPos, Stage stage, float width, float height,
                      float offsetX, float offsetY, String tgtMapPath, String tpId, String tgtTpId) {
        super(xPos, yPos, stage, width, height, false);
        this.tgtMapPath = tgtMapPath;

        this.tpTgtOffsetX = offsetX;
        this.tpTgtOffsetY = offsetY;

        this.tpId = tpId;
        this.tgtTpId = tgtTpId;

    }

    public Teleporter(float x, float y, Stage s, Polygon poly, float offsetX,
                      float offsetY, String tgtMapPath, String tpId, String tgtTpId) {
        super(x, y, s, poly, false);

        this.tgtMapPath = tgtMapPath;

        this.tpTgtOffsetX = offsetX;
        this.tpTgtOffsetY = offsetY;

        this.tpId = tpId;
        this.tgtTpId = tgtTpId;

    }

}
