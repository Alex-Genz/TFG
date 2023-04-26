package com.alexgs.tfg_game.elements.characters;

import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Player extends Characters {
    protected Animation<TextureRegion> idleUp;
    protected Animation<TextureRegion> idleDown;
    protected Animation<TextureRegion> idleLeft;
    protected Animation<TextureRegion> idleRight;

    protected Animation<TextureRegion> walkUp;
    protected Animation<TextureRegion> walkDown;
    protected Animation<TextureRegion> walkLeft;
    protected Animation<TextureRegion> walkRight;

    boolean moving = false;
    final int SPEED = 80;

    boolean running = false;

    Vector2 moveVec;
    Vector2 lastPosVec;

    public Player(float x, float y, Stage s, MainScreen lvl) {
        super(x, y, s, lvl);

        setAnimations();
        this.setAnimation(walkUp);

        setPolygon(8, this.getWidth() / 2, this.getHeight() / 4, 8, 0);
//        setRectangle();

        moveVec = new Vector2();
        moveVec.x = this.getX();
        moveVec.y = this.getY();

        lastPosVec = new Vector2();

    }

    public void act(float delta) {
        super.act(delta);
        movement();
        animate();

        checkMoving();

//        System.out.println(delta + " | " + animationTime);

        this.applyPhysics(delta);
//        System.out.println(this.getX() + " | " + this.getY() + " ||| target: " + moveVec.x + " | " + moveVec.y + " | " + moving);
//        System.out.println((int) (this.getX() - moveVec.x) + " | " + (int) (this.getY() - moveVec.y) + " ||| " + (this.getX() - moveVec.x) + " | " + (this.getY() - moveVec.y) + " ||| " + this.velocity.x + " | " + this.velocity.y);
//        System.out.println(this.getX() + " | " + this.getY() + " ||| " + lastPosVec.x + " | " + lastPosVec.y + " ||| " + moveVec.x + " | " + moveVec.y);


    }

    public void stopMoving() {
        System.out.println("called");
        this.moveVec.x = lastPosVec.x;
        this.moveVec.y = lastPosVec.y;

    }

    private void checkMoving() {
        moving = (Math.abs(this.velocity.x) > 0 || Math.abs(this.velocity.y) > 0) ? true : false;

    }

    private void animate() {
        if (moving) {
            if (this.velocity.x > 0) {
                this.setAnimation(walkRight);

            } else if (this.velocity.x < 0) {
                this.setAnimation(walkLeft);

            } else if (this.velocity.y > 0) {
                this.setAnimation(walkUp);

            } else if (this.velocity.y < 0) {
                this.setAnimation(walkDown);

            }

        } else {
            if (Math.abs((this.lvl.mouseX - this.getCenteredX())) > Math.abs((this.lvl.mouseY - this.getCenteredY()))) {
                if ((this.lvl.mouseX - this.getCenteredX()) > 0) {
                    this.setAnimation(idleRight);

                } else if ((this.lvl.mouseX - this.getCenteredX()) < 0) {
                    this.setAnimation(idleLeft);

                }

            } else if (Math.abs((this.lvl.mouseX - this.getCenteredX())) < Math.abs((this.lvl.mouseY - this.getCenteredY()))) {
                if ((this.lvl.mouseY - this.getCenteredY()) > 0) {
                    this.setAnimation(idleUp);

                } else if ((this.lvl.mouseY - this.getCenteredY()) < 0) {
                    this.setAnimation(idleDown);

                }

            }

        }

    }

//    DEPRECATED | -> legacy tile/grid-based movement <- | DEPRECATED
    private void movement2() {
        if (!moving) {
            if (Gdx.input.isKeyPressed(Input.Keys.W))
                moveMe(0, lvl.getTileHeight(), 1);

            else if (Gdx.input.isKeyPressed(Input.Keys.S))
                moveMe(0, lvl.getTileHeight(), -1);

            else if (Gdx.input.isKeyPressed(Input.Keys.D))
                moveMe(lvl.getTileWidth(), 0, 1);

            else if (Gdx.input.isKeyPressed(Input.Keys.A))
                moveMe(lvl.getTileWidth(), 0, -1);

//            else
//                moving = false;

        } else {
            if ((int) (this.getX() - moveVec.x) == 0 && (int) (this.getY() - moveVec.y) == 0) {
                stopMov();
                moving = false;

            }

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT))
            running = !running;


    }

    private void movement() {
        int speed = (running) ? SPEED * 2 : SPEED;

        if (Gdx.input.isKeyPressed(Input.Keys.D))
            this.velocity.x = speed * 1;

        else if (Gdx.input.isKeyPressed(Input.Keys.A))
            this.velocity.x = speed * -1;

        else
            this.velocity.x = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W))
            this.velocity.y = speed * 1;

        else if (Gdx.input.isKeyPressed(Input.Keys.S))
            this.velocity.y = speed * -1;

        else
            this.velocity.y = 0;

        if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT))
            running = !running;

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (this.velocity.x == 0 && this.velocity.y == 0)
                System.out.println("BANG BANG!!");

            else
                System.out.println("no bang bang :(");

        }

    }

    private void stopMov() {
        this.velocity.x = 0;
        this.velocity.y = 0;
        this.setX(moveVec.x);
        this.setY(moveVec.y);

    }

    private void moveMe(float tgtX, float tgtY, int dir) {
        lastPosVec.x = this.getX();
        lastPosVec.y = this.getY();

        moving = true;

        this.moveVec.x = this.getX() + (tgtX * dir);
        this.moveVec.y = this.getY() + (tgtY * dir);

        int speed = (running) ? SPEED * 2 : SPEED;

        this.velocity.x = ((tgtX != 0) ? speed : 0) * dir;
        this.velocity.y = ((tgtY != 0) ? speed : 0) * dir;

    }

    private void setAnimations() {
        idleUp = loadFullAnimation("sprites/player/up/hgun_idle_up.png", 1, 1, 0, true);
        idleDown = loadFullAnimation("sprites/player/down/hgun_idle_down.png", 1, 1, 0, true);
        idleLeft = loadFullAnimation("sprites/player/left/hgun_idle_left.png", 1, 1, 0, true);
        idleRight = loadFullAnimation("sprites/player/right/hgun_idle_right.png", 1, 1, 0, true);

        walkUp = loadFullAnimation("sprites/player/up/hgun_walk_up.png", 1, 6, 0.15f, true);
        walkDown = loadFullAnimation("sprites/player/down/hgun_walk_down.png", 1, 6, 0.15f, true);
        walkLeft = loadFullAnimation("sprites/player/left/hgun_walk_left.png", 1, 6, 0.15f, true);
        walkRight = loadFullAnimation("sprites/player/right/hgun_walk_right.png", 1, 6, 0.15f, true);


    }

}
