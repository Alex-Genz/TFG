package com.alexgs.tfg_game.elements;

import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Teleporter extends Element {
    private MainScreen lvl;

    private Animation<TextureRegion> anim;

    public String tgtMapPath;

    private float tpTgtOffsetX;
    private float tpTgtOffsetY;

    public String tpId;
    public String tgtTpId;

    public boolean noReturn;

    private int scoreQuota;

    private boolean stopOst;

    public Teleporter(float x, float y, Stage s, MainScreen lvl, float offsetX, float offsetY,
                      String tgtMapPath, String tpId, String tgtTpId, boolean noReturn, int scoreQuota, boolean stopOst) {
        super(x, y, s);

        this.lvl = lvl;

        this.tgtMapPath = tgtMapPath + ".tmx";

        this.tpTgtOffsetX = offsetX;
        this.tpTgtOffsetY = offsetY;

        this.tpId = tpId;
        this.tgtTpId = tgtTpId;

        this.noReturn = noReturn;

        this.scoreQuota = scoreQuota;

        this.stopOst = stopOst;

        anim = loadFullAnimation("sprites/other/exit_tp.png", 1, 8, 0.125f, true);

    }

    public float getTpTgtOffsetX() {
        return tpTgtOffsetX;
    }

    public float getTpTgtOffsetY() {
        return tpTgtOffsetY;
    }

    public int getScoreQuota() {
        return scoreQuota;
    }

    public boolean isStopOst() {
        return stopOst;
    }

}
