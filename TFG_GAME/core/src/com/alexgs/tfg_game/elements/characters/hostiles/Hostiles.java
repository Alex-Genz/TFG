package com.alexgs.tfg_game.elements.characters.hostiles;

import com.alexgs.tfg_game.elements.Element;
import com.alexgs.tfg_game.elements.characters.Characters;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Hostiles extends Characters {
//    temporal
    public final String PATHS = "sprites/npcs/";
    public final String EXTENSION = ".png";
    public final String[][] CHARACTER_SPRITES =
            {
                    {
                            "chara03_c/vx_chara03_c_char1_idown",
                            "chara03_c/vx_chara03_c_char1_ileft",
                            "chara03_c/vx_chara03_c_char1_iright",
                            "chara03_c/vx_chara03_c_char1_iup",
                            "chara03_c/vx_chara03_c_char1_wdown",
                            "chara03_c/vx_chara03_c_char1_wleft",
                            "chara03_c/vx_chara03_c_char1_wright",
                            "chara03_c/vx_chara03_c_char1_wup"
                    },
                    {
                            "chara03_c/vx_chara03_c_char2_idown",
                            "chara03_c/vx_chara03_c_char2_ileft",
                            "chara03_c/vx_chara03_c_char2_iright",
                            "chara03_c/vx_chara03_c_char2_iup",
                            "chara03_c/vx_chara03_c_char2_wdown",
                            "chara03_c/vx_chara03_c_char2_wleft",
                            "chara03_c/vx_chara03_c_char2_wright",
                            "chara03_c/vx_chara03_c_char2_wup"
                    },
                    {
                            "chara03_c/vx_chara03_c_char4_idown",
                            "chara03_c/vx_chara03_c_char4_ileft",
                            "chara03_c/vx_chara03_c_char4_iright",
                            "chara03_c/vx_chara03_c_char4_iup",
                            "chara03_c/vx_chara03_c_char4_wdown",
                            "chara03_c/vx_chara03_c_char4_wleft",
                            "chara03_c/vx_chara03_c_char4_wright",
                            "chara03_c/vx_chara03_c_char4_wup"
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
//    temporal

    public Hostiles(float x, float y, Stage s, MainScreen lvl, int charNum) {
        super(x, y, s, lvl);

        assignCharacter(charNum);

        setPolygon(8, this.getWidth() / 3, this.getHeight() / 4, 22, 0);

        setHitbox(s);

    }

    private void setHitbox(Stage s) {
        super.hitbox = new Element(this.getX() + 17, this.getY(), s,
                this.getWidth() / 2f - 2, this.getHeight() - 2);
        super.hitbox.setPolygon(8);

    }

    private void updateHitbox() {
        super.hitbox.setPosition(this.getX() + 17, this.getY());

    }

    public void act(float delta) {
        super.act(delta);

        updateHitbox();

        if (distanceToTarget(lvl.player.getCenteredX(),
                lvl.player.getCenteredY()) < 80) {
            System.out.println("WAAAARGHHHH!!!");

        }

    }

    protected void assignCharacter(int charNum) {
        idleDown = loadFullAnimation(pathFiller(CHARACTER_SPRITES[charNum][0]),
                1, 1, 0, true);
        idleLeft = loadFullAnimation(pathFiller(CHARACTER_SPRITES[charNum][1]),
                1, 1, 0, true);
        idleRight = loadFullAnimation(pathFiller(CHARACTER_SPRITES[charNum][2]),
                1, 1, 0, true);
        idleUp = loadFullAnimation(pathFiller(CHARACTER_SPRITES[charNum][3]),
                1, 1, 0, true);

        walkDown = loadFullAnimation(pathFiller(CHARACTER_SPRITES[charNum][4]),
                1, 4, 0.15f, true);
        walkLeft = loadFullAnimation(pathFiller(CHARACTER_SPRITES[charNum][5]),
                1, 4, 0.15f, true);
        walkRight = loadFullAnimation(pathFiller(CHARACTER_SPRITES[charNum][6]),
                1, 4, 0.15f, true);
        walkUp = loadFullAnimation(pathFiller(CHARACTER_SPRITES[charNum][7]),
                1, 4, 0.15f, true);

    }

    private String pathFiller(String sprite) {
        return PATHS + sprite + EXTENSION;

    }

}
