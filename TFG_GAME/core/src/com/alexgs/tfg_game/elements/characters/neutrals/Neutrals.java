package com.alexgs.tfg_game.elements.characters.neutrals;

import com.alexgs.tfg_game.elements.Element;
import com.alexgs.tfg_game.elements.characters.Characters;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Neutrals extends Characters {
    public String message;

    public final String PATHS = "sprites/npcs/";
    public final String EXTENSION = ".png";
    public final String[][] CHARACTER_SPRITES =
            {
                    {
                            "chara01_b/vx_chara01_b_char8_idown",
                            "chara01_b/vx_chara01_b_char8_ileft",
                            "chara01_b/vx_chara01_b_char8_iright",
                            "chara01_b/vx_chara01_b_char8_iup",
                            "chara01_b/vx_chara01_b_char8_wdown",
                            "chara01_b/vx_chara01_b_char8_wleft",
                            "chara01_b/vx_chara01_b_char8_wright",
                            "chara01_b/vx_chara01_b_char8_wup"
                    },
                    {
                            "chara02_c/vx_chara02_c_char7_idown",
                            "chara02_c/vx_chara02_c_char7_ileft",
                            "chara02_c/vx_chara02_c_char7_iright",
                            "chara02_c/vx_chara02_c_char7_iup",
                            "chara02_c/vx_chara02_c_char7_wdown",
                            "chara02_c/vx_chara02_c_char7_wleft",
                            "chara02_c/vx_chara02_c_char7_wright",
                            "chara02_c/vx_chara02_c_char7_wup"
                    },
                    {
                            "chara02_c/vx_chara02_c_char8_idown",
                            "chara02_c/vx_chara02_c_char8_ileft",
                            "chara02_c/vx_chara02_c_char8_iright",
                            "chara02_c/vx_chara02_c_char8_iup",
                            "chara02_c/vx_chara02_c_char8_wdown",
                            "chara02_c/vx_chara02_c_char8_wleft",
                            "chara02_c/vx_chara02_c_char8_wright",
                            "chara02_c/vx_chara02_c_char8_wup"
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

    public Neutrals(float x, float y, Stage s, MainScreen lvl, int charNum, String message) {
        super(x, y, s, lvl);

        assignCharacter(charNum);

        setHitbox(s);

        this.message = message;

        setPolygon(8, this.getWidth() / 3, this.getHeight() / 4, 22, 0);

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

        if (distanceToTarget(super.lvl.player.getCenteredX(), super.lvl.player.getCenteredY()) < 60)
            this.animations(super.lvl.player.getCenteredX(), super.lvl.player.getCenteredY());

    }

    private String pathFiller(String sprite) {
        return PATHS + sprite + EXTENSION;

    }

    protected void animations(float targetX, float targetY) {
        if (Math.abs((targetX - this.getCenteredX())) > Math.abs((targetY - this.getCenteredY())))
            if ((targetX - this.getCenteredX()) > 0)
                this.setAnimation(idleRight);

            else
                this.setAnimation(idleLeft);


        else if (Math.abs((targetX - this.getCenteredX())) < Math.abs((targetY - this.getCenteredY())))
            if ((targetY - this.getCenteredY()) > 0)
                this.setAnimation(idleUp);

            else
                this.setAnimation(idleDown);

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

}
