package com.alexgs.game.elements.character;

import com.alexgs.game.elements.bullet.Bullet;
import com.alexgs.game.elements.bullet.BulletPlayer;
import com.alexgs.game.game.GameParams;
import com.alexgs.game.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class Player extends Characters {
    //    PLAYER ATTRIBUTES
    private final int SPEED = -80;

    private float hRegenCooldown = 0;

    float multiplier;
    public float speedPwUpDur;

    public float regenPwUpDur;

    public String txt;

    //    SPRITE PATH PREFIXES
    private final String[] PATH_PREFIXES = {"sprites/player/fwd/", "sprites/player/lft/", "sprites/player/rgt/", "sprites/player/bck/"};

    private boolean sprint = false;

    //    shooting
    public Array<Bullet> mag;
    private int bulletCount = 36;
    private int currBullet;
    private Vector2 shootDir;

    private boolean hasShot = false;

    public Player(float x, float y, Stage s, GameScreen lvl) {
        super(x, y, s, lvl);
        this.lvl = lvl;

        super.hasPhysics = true;

        multiplier = 1;
        speedPwUpDur = 0;

        regenPwUpDur = 0;

//        shooting
        this.mag = new Array<>();
        for (int i = 0; i < bulletCount; i++) {
            this.mag.add(new BulletPlayer(0, 0, s, lvl));
            this.mag.get(i).setEnabled(false);

        }
        this.currBullet = 0;
        this.shootDir = new Vector2();

        // normal
        setAnimations();

        setMainCollider();

    }

    public void act(float delta) {
        super.act(delta);

        controls(delta);
        animations();

        if (regenPwUpDur > 0) {
            regenPwUpDur -= delta;
            this.txt = "Fast º Regeneration";

        }

        if (speedPwUpDur > 0) {
            speedPwUpDur -= delta;
            this.txt = "Speed Boost";

        }

        if (hRegenCooldown <= 0 || regenPwUpDur > 0) {
            if (GameParams.playerHealth < GameParams.MAX_PLAYER_HEALTH)
                GameParams.playerHealth += 0.2f;

        } else
            hRegenCooldown -= delta;

    }

    public float speedUp(float speed) {
        return speed * ((speedPwUpDur > 0) ? 3 : 1);

    }

    public void ouch(int damage) {
        GameParams.playerHealth -= damage;
        hRegenCooldown = 15;

    }

    // TODO poner a 0 un attrib de Element que mide el tiempo de la animacion | llamar a animacion sin loop y que cambie a la animacion por default al acabar

    private void controls(float delta) {
        if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
            sprint = true;

        else
            sprint = false;

        if (Gdx.input.isKeyPressed(Keys.W))
            this.velocity.y = speedUp(Math.abs(SPEED));

        else if (Gdx.input.isKeyPressed(Keys.S))
            this.velocity.y = speedUp(SPEED);

        else
            this.velocity.y = 0;

        if (Gdx.input.isKeyPressed(Keys.D))
            this.velocity.x = speedUp(Math.abs(SPEED));

        else if (Gdx.input.isKeyPressed(Keys.A))
            this.velocity.x = speedUp(SPEED);

        else
            this.velocity.x = 0;

/*        if (*//*Gdx.input.isKeyJustPressed(Keys.SPACE) || *//*Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if (!hasShot) {
                shoot(delta);
                hasShot = true;
                System.out.println("pressed");

            }

        } else {
            hasShot = false;
            System.out.println("not pressed");

        }*/

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            shoot(delta);

        }

    }

    private void shoot(float delta) {
        final int OFFSET = (int) mag.get(0).getWidth() / 2;

        this.shootDir.x = lvl.mouseX - this.getCenteredX();
        this.shootDir.y = lvl.mouseY - this.getCenteredY();

        shootDir = shootDir.nor();

        this.mag.get(currBullet).fire(this.getCenteredX() - OFFSET, this.getCenteredY() - OFFSET,
                this.shootDir.x * GameParams.BULLET_SPEED, this.shootDir.y * GameParams.BULLET_SPEED);
        this.currBullet = (this.currBullet + 1) % bulletCount;

    }

    private void animations() {
        if (Math.abs((this.lvl.mouseX - this.getCenteredX())) > Math.abs((this.lvl.mouseY - this.getCenteredY()))) {
            if ((this.lvl.mouseX - this.getCenteredX()) > 0) {
                this.setAnimation(super.useCorrectAnimation(idleRight, walkRight, walkRightInv, this.velocity.x * -1, this.velocity.y));

            } else if ((this.lvl.mouseX - this.getCenteredX()) < 0) {
                this.setAnimation(super.useCorrectAnimation(idleLeft, walkLeft, walkLeftInv, this.velocity.x, this.velocity.y));

            }

        } else if (Math.abs((this.lvl.mouseX - this.getCenteredX())) < Math.abs((this.lvl.mouseY - this.getCenteredY()))) {
            if ((this.lvl.mouseY - this.getCenteredY()) > 0) {
                this.setAnimation(super.useCorrectAnimation(idleUp, walkUp));

            } else if ((this.lvl.mouseY - this.getCenteredY()) < 0) {
                this.setAnimation(super.useCorrectAnimation(idleDown, walkDown, walkFwdInv, this.velocity.y, this.velocity.x));

            }

        }

    }

    private void setAnimations() {
        super.idleDown = loadFullAnimation(PATH_PREFIXES[0] +
                "hgun_idle_fwd.png", 1, 1, 0, true);
        super.idleLeft = loadFullAnimation(PATH_PREFIXES[1] +
                "hgun_idle_lft.png", 1, 1, 0, true);
        super.idleRight = loadFullAnimation(PATH_PREFIXES[2] +
                "hgun_idle_rgt.png", 1, 1, 0, true);
        super.idleUp = loadFullAnimation(PATH_PREFIXES[3] +
                "hgun_idle_bck.png", 1, 1, 0, true);

        super.walkDown = loadFullAnimation(PATH_PREFIXES[0] +
                "hgun_walk_fwd.png", 1, 6, 0.15f, true);
        super.walkFwdInv = loadFullAnimation(PATH_PREFIXES[0] +
                "hgun_walk_fwd_inv.png", 1, 6, 0.15f, true);

        super.walkLeft = loadFullAnimation(PATH_PREFIXES[1] +
                "hgun_walk_lft.png", 1, 6, 0.15f, true);
        super.walkLeftInv = loadFullAnimation(PATH_PREFIXES[1] +
                "hgun_walk_lft_inv.png", 1, 6, 0.15f, true);

        super.walkRight = loadFullAnimation(PATH_PREFIXES[2] +
                "hgun_walk_rgt.png", 1, 6, 0.15f, true);
        super.walkRightInv = loadFullAnimation(PATH_PREFIXES[2] +
                "hgun_walk_rgt_inv.png", 1, 6, 0.15f, true);

        super.walkUp = loadFullAnimation(PATH_PREFIXES[3] +
                "hgun_walk_bck.png", 1, 6, 0.15f, true);

    }

}
