package com.alexgs.tfg_game.elements.characters;

import com.alexgs.tfg_game.elements.Element;
import com.alexgs.tfg_game.elements.bullets.Bullet;
import com.alexgs.tfg_game.elements.bullets.BulletEnemy;
import com.alexgs.tfg_game.elements.bullets.BulletFriendly;
import com.alexgs.tfg_game.elements.tools.Weapons;
import com.alexgs.tfg_game.managers.SoundManager;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.awt.geom.Line2D;

public class Characters extends Element {
    public MainScreen lvl;

    protected Element hitbox;

    protected Vector2[] pathPoints;
    protected int currTgtPathPoint = 0;

    protected float pathSizeX;
    protected float pathSizeY;

    private Vector2 moveToVec;

    protected Array<Bullet> persistenceMag;
    protected int currPersistenceBullet;
    protected Vector2 shootDir;

    protected Animation<TextureRegion> idleUp;
    protected Animation<TextureRegion> idleDown;
    protected Animation<TextureRegion> idleLeft;
    protected Animation<TextureRegion> idleRight;

    protected Animation<TextureRegion> walkUp;
    protected Animation<TextureRegion> walkDown;
    protected Animation<TextureRegion> walkLeft;
    protected Animation<TextureRegion> walkRight;


    public Characters(float x, float y, Stage s, MainScreen lvl) {
        super(x, y, s);
        this.lvl = lvl;

        this.moveToVec = new Vector2();

    }

    public void act(float delta) {
        super.act(delta);

        this.applyPhysics(delta);

    }



    protected void loadPersistenceMag(Stage s, Weapons weapon, int team, boolean changeWeapon) {
        if (changeWeapon) {
            for (Bullet bullet :
                    persistenceMag) {
                bullet.setEnabled(false);

            }

        }

        this.persistenceMag = new Array<>();
        for (int i = 0; i < weapon.getPersistenceMagSize(); i++) {
            this.persistenceMag.add((team == 0) ? new BulletFriendly(0, 0, s, lvl,
                    weapon.getDmg(),
                    weapon.getTimeAllowedToExist()) :
                    new BulletEnemy(0, 0, s, lvl,
                    weapon.getDmg(),
                    weapon.getTimeAllowedToExist()));
            this.persistenceMag.get(i).setEnabled(false);

        }

        this.currPersistenceBullet = 0;
        this.shootDir = new Vector2();

    }

    protected void shoot(Weapons weapon, Vector2 tgtCentered) {
        final int PROJECTILE_OFFSET = (int) persistenceMag.get(0).getWidth() / 2;

        this.shootDir.x = tgtCentered.x - this.getCenteredX();
        this.shootDir.y = tgtCentered.y - this.getCenteredY();

//        System.out.println("BANG BANG!!" + " ||| " + this.shootDir.x + " | " + this.shootDir.y);

        shootDir = shootDir.nor();

        this.persistenceMag.get(currPersistenceBullet).fire(this.getCenteredX() -
                        PROJECTILE_OFFSET, this.getCenteredY() - PROJECTILE_OFFSET,
                this.shootDir.x * weapon.getBulletSpeed(),
                this.shootDir.y * weapon.getBulletSpeed());

//        this.currPersistenceBullet = (this.currPersistenceBullet + 1) % PERSISTENCE_MAG_SIZE;
        this.currPersistenceBullet = (this.currPersistenceBullet + 1) %
                weapon.getPersistenceMagSize();

    }

    protected void shoot(Weapons weapon, float tgtCenteredX, float tgtCenteredY) {
        final int PROJECTILE_OFFSET = (int) persistenceMag.get(0).getWidth() / 2;

        this.shootDir.x = tgtCenteredX - this.getCenteredX();
        this.shootDir.y = tgtCenteredY - this.getCenteredY();

//        System.out.println("BANG BANG!!" + " ||| " + this.shootDir.x + " | " + this.shootDir.y);

        shootDir = shootDir.nor();

        this.persistenceMag.get(currPersistenceBullet).fire(this.getCenteredX() -
                        PROJECTILE_OFFSET, this.getCenteredY() - PROJECTILE_OFFSET,
                this.shootDir.x * weapon.getBulletSpeed(),
                this.shootDir.y * weapon.getBulletSpeed());

        SoundManager.playSound(weapon.getShootSfxPath(), 0.5f);

//        this.currPersistenceBullet = (this.currPersistenceBullet + 1) % PERSISTENCE_MAG_SIZE;
        this.currPersistenceBullet = (this.currPersistenceBullet + 1) %
                weapon.getPersistenceMagSize();

    }

    public Element getHitbox() {
        return hitbox;

    }

    protected void moveTo(float targetX, float targetY, float speed) {
        this.moveToVec.x = targetX - this.getCenteredX();
        this.moveToVec.y = targetY - this.getCenteredY();

        moveToVec = moveToVec.nor();

        this.velocity.x = this.moveToVec.x * speed;
        this.velocity.y = this.moveToVec.y * speed;

    }

    protected void moveTo(Vector2 target, float speed) {
        this.moveToVec.x = target.x - this.getCenteredX();
        this.moveToVec.y = target.y - this.getCenteredY();

        moveToVec = moveToVec.nor();

        this.velocity.x = this.moveToVec.x * speed;
        this.velocity.y = this.moveToVec.y * speed;

    }

    protected void setPath() {
        pathPoints = new Vector2[]{new Vector2(), new Vector2()};


        pathPoints[0].x = this.getCenteredX() + (pathSizeX * this.lvl.getTileWidth());
        pathPoints[0].y = this.getCenteredY() + (pathSizeY * this.lvl.getTileHeight());

        pathPoints[1].x = this.getCenteredX() - (pathSizeX * this.lvl.getTileWidth());
        pathPoints[1].y = this.getCenteredY() - (pathSizeY * this.lvl.getTileHeight());

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

    protected void animations(Vector2 target) {
        if (Math.abs((target.x - this.getCenteredX())) > Math.abs((target.y - this.getCenteredY())))
            if ((target.x - this.getCenteredX()) > 0)
                this.setAnimation(idleRight);

            else
                this.setAnimation(idleLeft);


        else if (Math.abs((target.x - this.getCenteredX())) < Math.abs((target.y - this.getCenteredY())))
            if ((target.y - this.getCenteredY()) > 0)
                this.setAnimation(idleUp);

            else
                this.setAnimation(idleDown);

    }

    protected void animations() {
        if (this.velocity.x > 0 && Math.abs(this.velocity.x) > Math.abs(this.velocity.y)) {
            this.setAnimation(walkRight);

        } else if (this.velocity.x < 0 && Math.abs(this.velocity.x) > Math.abs(this.velocity.y)) {
            this.setAnimation(walkLeft);

        } else if (this.velocity.y > 0 && Math.abs(this.velocity.y) > Math.abs(this.velocity.x)) {
            this.setAnimation(walkUp);

        } else if (this.velocity.y < 0 && Math.abs(this.velocity.y) > Math.abs(this.velocity.x)) {
            this.setAnimation(walkDown);

        }

    }

}
