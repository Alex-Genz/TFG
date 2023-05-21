package com.alexgs.tfg_game.elements;

import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Teleporter extends Element {
    private MainScreen lvl;

    private Animation<TextureRegion> anim;

    public String tgtMapPath;

    public float tpTgtOffsetX;
    public float tpTgtOffsetY;

    public String tpId;
    public String tgtTpId;

    public boolean noReturn;

    public Teleporter(float x, float y, Stage s, MainScreen lvl, float offsetX, float offsetY,
                      String tgtMapPath, String tpId, String tgtTpId, boolean noReturn) {
        super(x, y, s);

        this.lvl = lvl;

        this.tgtMapPath = tgtMapPath + ".tmx";

        this.tpTgtOffsetX = offsetX;
        this.tpTgtOffsetY = offsetY;

        this.tpId = tpId;
        this.tgtTpId = tgtTpId;

        this.noReturn = noReturn;

        anim = loadFullAnimation("sprites/other/pwup_sprite.png", 1, 1, 0, true);

    }

}
