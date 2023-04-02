package com.alexgs.game.elements.character;

import com.alexgs.game.managers.SoundManager;
import com.alexgs.game.screens.GameScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

// TODO make cow run away from player when it gets close (or just run away when it gets hit)
public class Cow extends Characters {

    float randomTimeToTurn;
    float randomTimeToMoo;

    private final int DISTANCE_MULTIPLIER = 90;
    private final int SPEED = 40;

    private float goX = super.coordinateRandomizer(this.getCenteredX(), DISTANCE_MULTIPLIER);
    private float goY = super.coordinateRandomizer(this.getCenteredY(), DISTANCE_MULTIPLIER);

    private float timeToMove = (float) Math.random() * 12;

    private final String[] MOO_SFX = {"audio/sfx/mc_cow1.mp3", "audio/sfx/mc_cow2.mp3", "audio/sfx/mc_cow3.mp3"};
    private final String[] OUCH_MOO_SFX = {"audio/sfx/mc_ouch_cow1.mp3", "audio/sfx/mc_ouch_cow2.mp3", "audio/sfx/mc_ouch_cow3.mp3"};

    public boolean gotHit;

    public Cow(float x, float y, Stage s, GameScreen lvl) {
        super(x, y, s, lvl);

        super.hasPhysics = true;

        gotHit = false;

        super.idleDown = loadFullAnimation("sprites/cow/cow_idle_down.png", 1, 1, 0, true);
        super.idleUp = loadFullAnimation("sprites/cow/cow_idle_up.png", 1, 1, 0, true);
        super.idleLeft = loadFullAnimation("sprites/cow/cow_idle_left.png", 1, 1, 0, true);
        super.idleRight = loadFullAnimation("sprites/cow/cow_idle_right.png", 1, 1, 0, true);

        super.walkDown = loadFullAnimation("sprites/cow/cow_walk_down.png", 1, 4, 0.225f, true);
        super.walkUp = loadFullAnimation("sprites/cow/cow_walk_up.png", 1, 4, 0.225f, true);
        super.walkLeft = loadFullAnimation("sprites/cow/cow_walk_left.png", 1, 4, 0.225f, true);
        super.walkRight = loadFullAnimation("sprites/cow/cow_walk_right.png", 1, 4, 0.225f, true);

        randomTimeToTurn = 2;
        randomTimeToMoo = 2;

        setPolygon(8, 18, this.getHeight() - 10, 7, 0);

        setFeet(s);

    }

    public void act(float delta) {
        super.act(delta);

        adjustFeet();
        checkOverlap();

        if (timeToMove <= 0)
            if (distanceToTarget(goX, goY) > 5)
                super.moveTo(goX, goY, (gotHit) ? SPEED * 3 : SPEED);

            else {
                gotHit = false;

                this.velocity.x = 0;
                this.velocity.y = 0;

                timeToMove = (float) Math.random() * 12;

                goX = super.coordinateRandomizer(this.getCenteredX(), DISTANCE_MULTIPLIER);
                goY = super.coordinateRandomizer(this.getCenteredY(), DISTANCE_MULTIPLIER);

            }

        else
            timeToMove -= delta;

        super.animations(goX, goY);

        if (randomTimeToMoo <= 0)
            playMoo();

        else
            randomTimeToMoo -= delta;

    }

    public void hit() {
        playOuchMoo();

        goX = super.coordinateRandomizer(this.getCenteredX(), (DISTANCE_MULTIPLIER * 3));
        goY = super.coordinateRandomizer(this.getCenteredY(), (DISTANCE_MULTIPLIER * 3));

        timeToMove = 0;

        gotHit = true;

    }


    private void rotateCow() {
        int num = (int) (Math.random() * 10);
        switch (num) {
            case 0:
            case 4:
                this.setAnimation(idleDown);

                break;

            case 1:
            case 5:
                this.setAnimation(idleLeft);

                break;

            case 2:
            case 6:
                this.setAnimation(idleUp);

                break;

            case 3:
            case 7:
                this.setAnimation(idleRight);

                break;

        }

        randomTimeToTurn = (float) (Math.random() * 12);

    }

    private void playMoo() {
        int num = (int) (Math.random() * 10);
        switch (num) {
            case 0:
            case 3:
            case 6:
                playMooTheSecond(0);

                break;

            case 1:
            case 4:
            case 7:
                playMooTheSecond(1);

                break;

            case 2:
            case 5:
            case 8:
                playMooTheSecond(2);

                break;

        }

        randomTimeToMoo = (float) (Math.random() * 20);

    }

    private void playMooTheSecond(int opt) {
//        checks the distance of the player to the cow. if the player is near, plays the sound (made to avoid cow noises coming from the other side of the map)
        if (super.distanceToTarget(this.lvl.player.getCenteredX(), this.lvl.player.getCenteredY()) < 265)
            SoundManager.playSound(MOO_SFX[opt], 0.25f);

    }

    public void playOuchMoo() {
        int num = (int) (Math.random() * 10);
        switch (num) {
            case 0:
            case 3:
            case 6:
                playOuchTheSecond(0);

                break;

            case 1:
            case 4:
            case 7:
            case 9:
                playOuchTheSecond(1);

                break;

            case 2:
            case 5:
            case 8:
                playOuchTheSecond(2);

                break;

        }

    }

    private void playOuchTheSecond(int opt) {
//        checks the distance of the player to the cow. if the player is near, plays the sound (made to avoid cow noises coming from the other side of the map)
        if (super.distanceToTarget(this.lvl.player.getCenteredX(), this.lvl.player.getCenteredY()) < 265)
            SoundManager.playSound(OUCH_MOO_SFX[opt], 0.25f);

    }

}
