package com.alexgs.game.elements.character;

import com.alexgs.game.elements.Element;
import com.alexgs.game.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Characters extends Element {
    GameScreen lvl;

    private Vector2 moveToVec;

    public Element feet;

    protected boolean hasPhysics = false;

    // walk
    // idle
    protected Animation<TextureRegion> idleDown;
    protected Animation<TextureRegion> idleUp;
    protected Animation<TextureRegion> idleLeft;
    protected Animation<TextureRegion> idleRight;

    // fwd
    protected Animation<TextureRegion> walkDown;
    protected Animation<TextureRegion> walkFwdInv;

    // left
    protected Animation<TextureRegion> walkLeft;
    protected Animation<TextureRegion> walkLeftInv;

    // right
    protected Animation<TextureRegion> walkRight;
    protected Animation<TextureRegion> walkRightInv;

    // back
    protected Animation<TextureRegion> walkUp;


    public Characters(float x, float y, Stage s, GameScreen lvl) {
        super(x, y, s);
        this.lvl = lvl;

        this.moveToVec = new Vector2();

    }

    protected void setFeet(Stage s) {
        this.feet = new Element(0, 0, s, this.getWidth() / 2, this.getHeight() / 6);
        this.feet.setPolygon(8);

        adjustFeet();

    }

    protected void adjustFeet() {
        this.feet.setPosition(this.getX() + this.getWidth() / 2 - this.getWidth() / 4, this.getY());

    }

    protected void setMainCollider() {
        setPolygon(8, 18, this.getHeight() - 3, 7, 0);

    }

    public void act(float delta) {
        super.act(delta);

        if (hasPhysics && this.getEnabled())
            this.applyPhysics(delta);

    }

    protected void checkOverlap() {
        if (lvl.player.overlaps(this.feet))
            lvl.player.preventOverlap(this.feet);

    }

    protected float distanceToTarget(float tgtX, float tgtY) {
        return (float) Math.sqrt(Math.pow((tgtX - this.getCenteredX()), 2) +
                Math.pow((tgtY - this.getCenteredY()), 2));


    }

    protected void moveTo(float targetX, float targetY, int speed) {
        this.moveToVec.x = targetX - this.getCenteredX();
        this.moveToVec.y = targetY - this.getCenteredY();

        moveToVec = moveToVec.nor();

        this.velocity.x = this.moveToVec.x * speed;
        this.velocity.y = this.moveToVec.y * speed;

    }

    protected float coordinateRandomizer(float entityCurrPos, int distanceMultiplier) {
        return entityCurrPos + (((float) Math.random() * distanceMultiplier) * ((Math.random() > 0.5) ? 1 : -1));

    }

    protected void animations(float targetX, float targetY) {
        if (Math.abs((targetX - this.getCenteredX())) > Math.abs((targetY - this.getCenteredY())))
            if ((targetX - this.getCenteredX()) > 0)
                this.setAnimation(useCorrectAnimation(idleRight, walkRight));

            else
                this.setAnimation(useCorrectAnimation(idleLeft, walkLeft));


        else if (Math.abs((targetX - this.getCenteredX())) < Math.abs((targetY - this.getCenteredY())))
            if ((targetY - this.getCenteredY()) > 0)
                this.setAnimation(useCorrectAnimation(idleUp, walkUp));

            else
                this.setAnimation(useCorrectAnimation(idleDown, walkDown));

    }

//    OG
    protected Animation<TextureRegion> useCorrectAnimation(Animation<TextureRegion> idleAnim,
                                                           Animation<TextureRegion> moveAnim) {

        if (this.velocity.x == 0 && this.velocity.y == 0) {
            return idleAnim;

        } else {
            return moveAnim;

        }

    }

    protected Animation<TextureRegion> useCorrectAnimation(Animation<TextureRegion> idleAnim,
                                                           Animation<TextureRegion> moveAnimA,
                                                           Animation<TextureRegion> moveAnimB,
                                                           float velocityA, float velocityB) {
        if (this.velocity.x != 0 || this.velocity.y != 0) {
            if (velocityA > 0) {
                return moveAnimB;

            } else if (velocityA < 0 || Math.abs(velocityB) >= 0) {
                return moveAnimA;

            }

        }
        return idleAnim;

    }
//    --OG

}
