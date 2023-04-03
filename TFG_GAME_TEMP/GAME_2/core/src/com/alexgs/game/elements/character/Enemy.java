package com.alexgs.game.elements.character;

import com.alexgs.game.elements.bullet.Bullet;
import com.alexgs.game.elements.bullet.bulletEnemy;
import com.alexgs.game.game.GameParams;
import com.alexgs.game.screens.GameScreen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class Enemy extends Characters {
    //    TODO deprecated
    public final int TEAM = 0xB;

    //    sprite location paths
    private final String[] PATH_PREFIXES = {"sprites/player/fwd/", "sprites/player/lft/", "sprites/player/rgt/", "sprites/player/bck/"};

    //    bullet stuff
    public Array<Bullet> mag;
    private int bulletCount = 20;
    private int currBullet;
    private Vector2 shootDir;
    private float fireRateLong;
    private float fireRateShort;
    private float fireRate;
    private float tFireRate;
    private boolean toggleShoot;

    private float tRemEngage;

    private float health = GameParams.playerHealth;

    private final int SPEED = 50;


    public Enemy(float x, float y, Stage s, GameScreen lvl) {
        super(x, y, s, lvl);

        this.lvl = lvl;

        super.hasPhysics = true;

        toggleShoot = false;

//        shooting
        this.mag = new Array<>();
        for (int i = 0; i < bulletCount; i++) {
            this.mag.add(new bulletEnemy(0, 0, s, lvl));
            this.mag.get(i).setEnabled(false);

        }
        this.currBullet = 0;
        this.shootDir = new Vector2();
        this.fireRateLong = 1f;
        this.fireRateShort = 0.12f;

//        persecuting
        this.tRemEngage = 0;

        // normal
        setAnimations();
        this.setAnimation(super.idleDown);

//        engage(s);


        super.setMainCollider();

        setFeet(s);

    }

    public void act(float delta) {
        if (this.getEnabled()) {
            super.act(delta);

            if (health <= 0) {
                if (GameParams.playerHealth <= 10)
                    GameParams.score+=200;

                else if (GameParams.playerHealth <= 50 && GameParams.playerHealth > 10)
                    GameParams.score+=150;

                else
                    GameParams.score+=100;

                this.setEnabled(false);

            }

            adjustFeet();
            checkOverlap();

            if (super.distanceToTarget(this.lvl.player.getX(), this.lvl.player.getY()) < 250 || tRemEngage > 0) {
                super.animations(this.lvl.player.getCenteredX(), this.lvl.player.getCenteredY());
                super.moveTo(this.lvl.player.getCenteredX(), this.lvl.player.getCenteredY(), SPEED);

                if (super.distanceToTarget(this.lvl.player.getX(), this.lvl.player.getY()) < 250)
                    this.tRemEngage = 5;

                else
                    this.tRemEngage -= delta;

            } else {
                this.setAnimation(super.idleDown);

                this.velocity.x = 0;
                this.velocity.y = 0;

            }

            if (super.distanceToTarget(this.lvl.player.getX(), this.lvl.player.getY()) < 235) {
                toggleShoot = true;

                if (super.distanceToTarget(this.lvl.player.getX(), this.lvl.player.getY()) < 100)
                    fireRate = fireRateShort;

                else
                    fireRate = fireRateLong;

            } else
                toggleShoot = false;


            if (this.tFireRate <= 0)
                shoot();

            else
                tFireRate -= delta;

        }

    }

    private void shoot() {
        if (toggleShoot) {
            final int OFFSET = (int) mag.get(0).getWidth() / 2;

            this.shootDir.x = this.lvl.player.getCenteredX() - this.getCenteredX();
            this.shootDir.y = this.lvl.player.getCenteredY() - this.getCenteredY();

            tFireRate = fireRate;

            shootDir = shootDir.nor();

            this.mag.get(currBullet).fire(this.getCenteredX() - OFFSET, this.getCenteredY() - OFFSET,
                    this.shootDir.x * GameParams.BULLET_SPEED, this.shootDir.y * GameParams.BULLET_SPEED);
            this.currBullet = (this.currBullet + 1) % bulletCount;

        }

    }

    public void damage(float damage) {
        this.health-=damage;

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
