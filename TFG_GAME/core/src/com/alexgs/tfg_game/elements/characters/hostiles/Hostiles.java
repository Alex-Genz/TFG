package com.alexgs.tfg_game.elements.characters.hostiles;

import com.alexgs.tfg_game.elements.Element;
import com.alexgs.tfg_game.elements.bullets.Bullet;
import com.alexgs.tfg_game.elements.bullets.BulletEnemy;
import com.alexgs.tfg_game.elements.characters.Characters;
import com.alexgs.tfg_game.elements.tools.Weapons;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

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


    public Hostiles(float x, float y, Stage s, MainScreen lvl, int charNum, float pathSizeX, float pathSizeY) {
        super(x, y, s, lvl);

        assignCharacter(charNum);

        setHitbox(s);

        super.pathSizeX = pathSizeX;
        super.pathSizeY = pathSizeY;

        setPolygon(8, this.getWidth() / 3, this.getHeight() / 4, 22, 0);

        super.setPath();

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

        System.out.println(Math.abs(this.velocity.x) + " | " + Math.abs(this.velocity.y));

        if (distanceToTarget(lvl.player.getCenteredX(), lvl.player.getCenteredY()) >= 80) {
            if (distanceToTarget(pathPoints[currTgtPathPoint]) < 2) {
                currTgtPathPoint = (currTgtPathPoint == 0) ? 1 : 0;

            } else {
                super.moveTo(pathPoints[currTgtPathPoint], 60);
                super.animations();

            }

        } else {
            if (distanceToTarget(lvl.player.getCenteredX(), lvl.player.getCenteredY()) > 45) {
                super.moveTo(lvl.player.getCenteredX(), lvl.player.getCenteredY(), 60);
                super.animations();

            } else {
                this.velocity.x = 0;
                this.velocity.y = 0;
                super.animations(lvl.player.getCenteredPos());

            }

        }

        if (distanceToTarget(lvl.player.getCenteredX(),
                lvl.player.getCenteredY()) < 80) {
            System.out.println("WAAAARGHHHH!!!");

        }

    }

    protected void loadPersistenceMag(Stage s, Weapons weapon) {
        this.persistenceMag = new Array<>();
        for (int i = 0; i < weapon.getPersistenceMagSize(); i++) {
            this.persistenceMag.add(new BulletEnemy(0, 0, s, lvl,
                    weapon.getDmg(),
                    weapon.getTimeAllowedToExist()));
            this.persistenceMag.get(i).setEnabled(false);

        }

        this.currPersistenceBullet = 0;
        this.shootDir = new Vector2();

    }



    protected void assignCharacter(int charNum) {
        super.idleDown = loadFullAnimation(pathFiller(CHARACTER_SPRITES[charNum][0]),
                1, 1, 0, true);
        super.idleLeft = loadFullAnimation(pathFiller(CHARACTER_SPRITES[charNum][1]),
                1, 1, 0, true);
        super.idleRight = loadFullAnimation(pathFiller(CHARACTER_SPRITES[charNum][2]),
                1, 1, 0, true);
        super.idleUp = loadFullAnimation(pathFiller(CHARACTER_SPRITES[charNum][3]),
                1, 1, 0, true);

        super.walkDown = loadFullAnimation(pathFiller(CHARACTER_SPRITES[charNum][4]),
                1, 4, 0.15f, true);
        super.walkLeft = loadFullAnimation(pathFiller(CHARACTER_SPRITES[charNum][5]),
                1, 4, 0.15f, true);
        super.walkRight = loadFullAnimation(pathFiller(CHARACTER_SPRITES[charNum][6]),
                1, 4, 0.15f, true);
        super.walkUp = loadFullAnimation(pathFiller(CHARACTER_SPRITES[charNum][7]),
                1, 4, 0.15f, true);

    }

    private String pathFiller(String sprite) {
        return PATHS + sprite + EXTENSION;

    }

}
