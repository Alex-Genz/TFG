package com.alexgs.tfg_game.elements.characters.hostiles;

import com.alexgs.tfg_game.elements.Element;
import com.alexgs.tfg_game.elements.characters.Characters;
import com.alexgs.tfg_game.elements.characters.neutrals.player.PlayerParams;
import com.alexgs.tfg_game.elements.tools.Weapons;
import com.alexgs.tfg_game.params.Consts;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Hostiles extends Characters {
    protected Weapons weapon;

    protected float timeBeforeNextShot = 0;

    protected float speed;

    private float health;

    protected float stopDistance;

    private boolean doesPatrol;

    //    temporal
    public final String PATHS = "sprites/enemies/";
    public final String EXTENSION = ".png";


    public Hostiles(float x, float y, Stage s, MainScreen lvl,
                    int charNum, float pathSizeX, float pathSizeY, float speed, float stopDistance, float health, Weapons weapon) {
        super(x, y, s, lvl);

        assignCharacter(charNum);

        setHitbox(s);

        this.speed = speed;

        this.doesPatrol = (pathSizeX == 0 && pathSizeY == 0) ? false : true;

        super.pathSizeX = pathSizeX;
        super.pathSizeY = pathSizeY;

        this.stopDistance = stopDistance;

        this.health = health;

        this.weapon = weapon;

        setPolygon(8, this.getWidth() / 3 + 4,
                this.getHeight() / 4, 20, 0);

        super.setPath();

    }

    private void setHitbox(Stage s) {
        super.hitbox = new Element(this.getX() + 17, this.getY(), s,
                this.getWidth() / 2f - 3, this.getHeight() - 16);
        super.hitbox.setPolygon(8);

    }

    private void updateHitbox() {
        super.hitbox.setPosition(this.getX() + 17, this.getY());

    }

    public void act(float delta) {
        if (this.getEnabled()) {
            super.act(delta);

            updateHitbox();

            if (distanceToTarget(lvl.player.getCenteredX(), lvl.player.getCenteredY()) >= 200) {
                if (distanceToTarget(pathPoints[currTgtPathPoint]) < 2) {
                    if (doesPatrol)
                        currTgtPathPoint = (currTgtPathPoint == 0) ? 1 : 0;
                    else {
                        this.velocity.x = 0;
                        this.velocity.y = 0;
                        super.setAnimation(idleDown);

                    }

                } else {
                    super.moveTo(pathPoints[currTgtPathPoint], 40);
                    super.animations();

                }

            } else {
                if (distanceToTarget(lvl.player.getCenteredX(), lvl.player.getCenteredY()) > stopDistance) {
                    super.moveTo(lvl.player.getCenteredX(), lvl.player.getCenteredY(), this.speed);
                    super.animations();

                } else {
                    this.velocity.x = 0;
                    this.velocity.y = 0;
                    super.animations(lvl.player.getCenteredPos());

                }

            }

            if (this.health <= 0) {
                this.setEnabled(false);

            }

        }

    }

    private Animation<TextureRegion> setRandomIdleDir() {
        switch ((int) (Math.random() * 4)) {
            case 1:
                return idleDown;

            case 2:
                return idleLeft;

            case 3:
                return idleRight;

            case 4:
                return idleUp;

        }

        return null;

    }


    protected void assignCharacter(int charNum) {
        super.idleDown = loadFullAnimation(pathFiller(Consts.ENEMY_SPRITES[charNum] + Consts.SPRITE_DIR[0]),
                1, 1, 0, true);
        super.idleLeft = loadFullAnimation(pathFiller(Consts.ENEMY_SPRITES[charNum] + Consts.SPRITE_DIR[1]),
                1, 1, 0, true);
        super.idleRight = loadFullAnimation(pathFiller(Consts.ENEMY_SPRITES[charNum] + Consts.SPRITE_DIR[2]),
                1, 1, 0, true);
        super.idleUp = loadFullAnimation(pathFiller(Consts.ENEMY_SPRITES[charNum] + Consts.SPRITE_DIR[3]),
                1, 1, 0, true);

        super.walkDown = loadFullAnimation(pathFiller(Consts.ENEMY_SPRITES[charNum] + Consts.SPRITE_DIR[4]),
                1, 8, 0.125f, true);
        super.walkLeft = loadFullAnimation(pathFiller(Consts.ENEMY_SPRITES[charNum] + Consts.SPRITE_DIR[5]),
                1, 8, 0.125f, true);
        super.walkRight = loadFullAnimation(pathFiller(Consts.ENEMY_SPRITES[charNum] + Consts.SPRITE_DIR[6]),
                1, 8, 0.125f, true);
        super.walkUp = loadFullAnimation(pathFiller(Consts.ENEMY_SPRITES[charNum] + Consts.SPRITE_DIR[7]),
                1, 8, 0.125f, true);

    }

    public void hit(float dmg) {
        this.health-=dmg;
//        System.out.println(this.health);

    }

    private String pathFiller(String sprite) {
        return PATHS + sprite + EXTENSION;

    }

    public float getHealth() {
        return health;
    }

}
