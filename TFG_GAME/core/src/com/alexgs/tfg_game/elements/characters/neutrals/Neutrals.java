package com.alexgs.tfg_game.elements.characters.neutrals;

import com.alexgs.tfg_game.elements.characters.Characters;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

public class Neutrals extends Characters {
    public String[][] characterPaths =
            {
                    {
                            "temp/sprites/npc chars/chara02_c/vx_chara02_c_char7_idown.png",
                            "temp/sprites/npc chars/chara02_c/vx_chara02_c_char7_ileft.png",
                            "temp/sprites/npc chars/chara02_c/vx_chara02_c_char7_iright.png",
                            "temp/sprites/npc chars/chara02_c/vx_chara02_c_char7_iup.png",
                            "temp/sprites/npc chars/chara02_c/vx_chara02_c_char7_wdown.png",
                            "temp/sprites/npc chars/chara02_c/vx_chara02_c_char7_wleft.png",
                            "temp/sprites/npc chars/chara02_c/vx_chara02_c_char7_wright.png",
                            "temp/sprites/npc chars/chara02_c/vx_chara02_c_char7_wup.png"
                    },
                    {
                            "temp/sprites/npc chars/chara02_c/vx_chara02_c_char8_idown.png",
                            "temp/sprites/npc chars/chara02_c/vx_chara02_c_char8_ileft.png",
                            "temp/sprites/npc chars/chara02_c/vx_chara02_c_char8_iright.png",
                            "temp/sprites/npc chars/chara02_c/vx_chara02_c_char8_iup.png",
                            "temp/sprites/npc chars/chara02_c/vx_chara02_c_char8_wdown.png",
                            "temp/sprites/npc chars/chara02_c/vx_chara02_c_char8_wleft.png",
                            "temp/sprites/npc chars/chara02_c/vx_chara02_c_char8_wright.png",
                            "temp/sprites/npc chars/chara02_c/vx_chara02_c_char8_wup.png"
                    }
            };

    protected Animation<TextureRegion> idleUp;
    protected Animation<TextureRegion> idleDown;
    protected Animation<TextureRegion> idleLeft;
    protected Animation<TextureRegion> idleRight;

    protected Animation<TextureRegion> walkUp;
    protected Animation<TextureRegion> walkDown;
    protected Animation<TextureRegion> walkLeft;
    protected Animation<TextureRegion> walkRight;

    public Neutrals(float x, float y, Stage s, MainScreen lvl, int charNum) {
        super(x, y, s, lvl);

        assignCharacter(charNum);

        setPolygon(8, this.getWidth() / 3, this.getHeight() / 4, 22, 0);

    }

    public void act(float delta) {
        super.act(delta);

    }

    protected void assignCharacter(int charNum) {
        idleDown = loadFullAnimation(characterPaths[charNum][0], 1, 1, 0, true);
        idleLeft = loadFullAnimation(characterPaths[charNum][1], 1, 1, 0, true);
        idleRight = loadFullAnimation(characterPaths[charNum][2], 1, 1, 0, true);
        idleUp = loadFullAnimation(characterPaths[charNum][3], 1, 1, 0, true);

        walkDown = loadFullAnimation(characterPaths[charNum][4], 1, 4, 0.15f, true);
        walkLeft = loadFullAnimation(characterPaths[charNum][5], 1, 4, 0.15f, true);
        walkRight = loadFullAnimation(characterPaths[charNum][6], 1, 4, 0.15f, true);
        walkUp = loadFullAnimation(characterPaths[charNum][7], 1, 4, 0.15f, true);

    }

}
