package com.alexgs.tfg_game.elements.characters;

import com.alexgs.tfg_game.elements.Element;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Characters extends Element {
    public MainScreen lvl;

    protected Element hitbox;

    protected Vector2[] pathPoints;
    protected int currTgtPathPoint = 0;

    protected float pathSizeX;
    protected float pathSizeY;

    private Vector2 moveToVec;

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

    }

    public Element getHitbox() {
        return hitbox;

    }

    protected void moveTo(float targetX, float targetY, int speed) {
        this.moveToVec.x = targetX - this.getCenteredX();
        this.moveToVec.y = targetY - this.getCenteredY();

        moveToVec = moveToVec.nor();

        this.velocity.x = this.moveToVec.x * speed;
        this.velocity.y = this.moveToVec.y * speed;

    }

    protected void moveTo(Vector2 target, int speed) {
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

        System.out.println(pathPoints[0].x + ", " + pathPoints[0].y + " ||| " + pathPoints[1].x + ", " + pathPoints[1].y);

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
        if (this.velocity.x > 0) {
            this.setAnimation(walkRight);

        } else if (this.velocity.x < 0) {
            this.setAnimation(walkLeft);

        } else if (this.velocity.y > 0) {
            this.setAnimation(walkUp);

        } else if (this.velocity.y < 0) {
            this.setAnimation(walkDown);

        }

    }

}
