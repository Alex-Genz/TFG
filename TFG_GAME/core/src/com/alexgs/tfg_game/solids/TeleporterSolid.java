package com.alexgs.tfg_game.solids;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class TeleporterSolid extends SolidLow {
    public String tgtMapPath;

    public float tpTgtOffsetX;
    public float tpTgtOffsetY;

    public String tpId;
    public String tgtTpId;

    public boolean noReturn;

    public TeleporterSolid(float xPos, float yPos, Stage stage, float width, float height,
                           float offsetX, float offsetY, String tgtMapPath, String tpId, String tgtTpId, boolean noReturn) {
        super(xPos, yPos, stage, width, height);
        this.tgtMapPath = tgtMapPath;

        this.tpTgtOffsetX = offsetX;
        this.tpTgtOffsetY = offsetY;

        this.tpId = tpId;
        this.tgtTpId = tgtTpId;

        this.noReturn = noReturn;

    }

    public TeleporterSolid(float x, float y, Stage s, Polygon poly, float offsetX,
                           float offsetY, String tgtMapPath, String tpId, String tgtTpId, boolean noReturn) {
        super(x, y, s, poly);

        this.tgtMapPath = tgtMapPath;

        this.tpTgtOffsetX = offsetX;
        this.tpTgtOffsetY = offsetY;

        this.tpId = tpId;
        this.tgtTpId = tgtTpId;

        this.noReturn = noReturn;

    }

}
