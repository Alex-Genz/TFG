package com.alexgs.game.elements.character;

import com.alexgs.game.screens.GameScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Citizen extends Characters {

    public final String SPRITES_PATH = "sprites/citizens/";
    public final String[] CITIZENS_PATH = {"cit1/", "cit2/", "cit3/", "cit4/"/*, "cit5/"*/};
    public final String[] CITIZEN_SPRITES = {"walk_down.png", "walk_left.png", "walk_right.png", "walk_up.png", "idle_down.png", "idle_left.png", "idle_right.png", "idle_up.png"};

    private final int DISTANCE_MULTIPLIER = 140;
    private final int SPEED = 60;

    private float goX = super.coordinateRandomizer(this.getCenteredX(), DISTANCE_MULTIPLIER);
    private float goY = super.coordinateRandomizer(this.getCenteredY(), DISTANCE_MULTIPLIER);

    private float timeToMove = (float) Math.random() * 12;


    public Citizen(float x, float y, Stage s, GameScreen lvl) {
        super(x, y, s, lvl);

        super.hasPhysics = true;

        setRandomSprites();
        super.setMainCollider();

        setFeet(s);

    }

    public void act(float delta) {
        super.act(delta);

        adjustFeet();
        checkOverlap();

        if (distanceToTarget(super.lvl.player.getCenteredX(), super.lvl.player.getCenteredY()) > 100) {
            if (timeToMove <= 0)
                if (distanceToTarget(goX, goY) > 5)
                    super.moveTo(goX, goY, SPEED);

                else {
                    this.velocity.x = 0;
                    this.velocity.y = 0;

                    timeToMove = (float) Math.random() * 12;

                    goX = super.coordinateRandomizer(this.getCenteredX(), DISTANCE_MULTIPLIER);
                    goY = super.coordinateRandomizer(this.getCenteredY(), DISTANCE_MULTIPLIER);

                }

            else
                timeToMove-=delta;


            super.animations(goX, goY);

        } else {
            this.velocity.x = 0;
            this.velocity.y = 0;

            super.animations(super.lvl.player.getCenteredX(), super.lvl.player.getCenteredY());

        }




    }

//    gives the citizen a random look
    private void setRandomSprites() {
        int i = (int) (Math.random() * 10);
//        System.out.println(i);
        switch (i) {
            case 0:
            case 5:
                setSprites(0);

                break;

            case 1:
            case 6:
                setSprites(1);

                break;

            case 2:
            case 7:
                setSprites(2);

                break;

            case 3:
            case 8:
                setSprites(3);

                break;

            case 4:
            case 9:
//                note from programmer/student: im pretty sure this is very dirty :/
                setRandomSprites();

                break;

        }

    }

    private void setSprites(int citizenType) {
        super.idleDown = loadFullAnimation(SPRITES_PATH + CITIZENS_PATH[citizenType] + CITIZEN_SPRITES[4], 1, 1, 0, true);
        super.idleLeft = loadFullAnimation(SPRITES_PATH + CITIZENS_PATH[citizenType] + CITIZEN_SPRITES[5], 1, 1, 0, true);
        super.idleRight = loadFullAnimation(SPRITES_PATH + CITIZENS_PATH[citizenType] + CITIZEN_SPRITES[6], 1, 1, 0, true);
        super.idleUp = loadFullAnimation(SPRITES_PATH + CITIZENS_PATH[citizenType] + CITIZEN_SPRITES[7], 1, 1, 0, true);

        super.walkDown = loadFullAnimation(SPRITES_PATH + CITIZENS_PATH[citizenType] + CITIZEN_SPRITES[0], 1, 4, 0.225f, true);
        super.walkLeft = loadFullAnimation(SPRITES_PATH + CITIZENS_PATH[citizenType] + CITIZEN_SPRITES[1], 1, 4, 0.225f, true);
        super.walkRight = loadFullAnimation(SPRITES_PATH + CITIZENS_PATH[citizenType] + CITIZEN_SPRITES[2], 1, 4, 0.225f, true);
        super.walkUp = loadFullAnimation(SPRITES_PATH + CITIZENS_PATH[citizenType] + CITIZEN_SPRITES[3], 1, 4, 0.225f, true);


    }


}
