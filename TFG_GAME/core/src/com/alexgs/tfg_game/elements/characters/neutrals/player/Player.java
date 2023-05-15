package com.alexgs.tfg_game.elements.characters.neutrals.player;

import com.alexgs.tfg_game.elements.Element;
import com.alexgs.tfg_game.elements.bullets.Bullet;
import com.alexgs.tfg_game.elements.bullets.BulletFriendly;
import com.alexgs.tfg_game.elements.characters.Characters;
import com.alexgs.tfg_game.elements.characters.neutrals.Neutrals;
import com.alexgs.tfg_game.elements.tools.WeaponsParams;
import com.alexgs.tfg_game.params.GameParams;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class Player extends Characters {
    protected Animation<TextureRegion> idleUp;
    protected Animation<TextureRegion> idleDown;
    protected Animation<TextureRegion> idleLeft;
    protected Animation<TextureRegion> idleRight;

    protected Animation<TextureRegion> walkUp;
    protected Animation<TextureRegion> walkDown;
    protected Animation<TextureRegion> walkLeft;
    protected Animation<TextureRegion> walkRight;

// INFO: preparation for extra animations
// TODO: extract animation sprite strips
    protected Animation<TextureRegion> runUp;
    protected Animation<TextureRegion> runDown;
    protected Animation<TextureRegion> runLeft;
    protected Animation<TextureRegion> runRight;


    protected Animation<TextureRegion> idleUpKnife;
    protected Animation<TextureRegion> idleDownKnife;
    protected Animation<TextureRegion> idleLeftKnife;
    protected Animation<TextureRegion> idleRightKnife;

    protected Animation<TextureRegion> walkUpKnife;
    protected Animation<TextureRegion> walkDownKnife;
    protected Animation<TextureRegion> walkLeftKnife;
    protected Animation<TextureRegion> walkRightKnife;


    protected Animation<TextureRegion> idleUpGun;
    protected Animation<TextureRegion> idleDownGun;
    protected Animation<TextureRegion> idleLeftGun;
    protected Animation<TextureRegion> idleRightGun;

    protected Animation<TextureRegion> walkUpGun;
    protected Animation<TextureRegion> walkDownGun;
    protected Animation<TextureRegion> walkLeftGun;
    protected Animation<TextureRegion> walkRightGun;
    
    private Stage s;

    boolean moving = false;
    final int SPEED = 80;

    boolean running = false;

// DEPRECATED - 2 lines
    Vector2 moveVec;
    Vector2 lastPosVec;

    private float lClickActivationTime = 0.30f;

// WARNING! even more experimental code
    private final float ROUNDS_PER_MINUTE = 300;
    private float timeBeforeNextShot = 0;

    public Player(float x, float y, Stage s, MainScreen lvl) {
        super(x, y, s, lvl);

        this.s = s;

        setAnimations();
        this.setAnimation(walkUp);

        setPolygon(8, this.getWidth() / 4, this.getHeight() / 4,
                24, 0);
//        setRectangle();
        setHitbox(this.s);

        loadPersistenceMag(this.s, false);

        moveVec = new Vector2();
        moveVec.x = this.getX();
        moveVec.y = this.getY();

        lastPosVec = new Vector2();

        this.shootDir = new Vector2();

    }

//    TODO: move this method to Characters.java & adapt for refactorization
    protected void loadPersistenceMag(Stage s, boolean changeWeapon) {
        if (changeWeapon) {
            for (Bullet bullet :
                    persistenceMag) {
                bullet.setEnabled(false);

            }

        }

        this.persistenceMag = new Array<>();
        for (int i = 0; i < PlayerParams.currTool.getPersistenceMagSize(); i++) {
            this.persistenceMag.add(new BulletFriendly(0, 0, s, lvl,
                    PlayerParams.currTool.getDmg(),
                    PlayerParams.currTool.getTimeAllowedToExist()));
            this.persistenceMag.get(i).setEnabled(false);

        }

        this.currPersistenceBullet = 0;
        this.shootDir = new Vector2();

    }

    private void setHitbox(Stage s) {
        super.hitbox = new Element(this.getX() + 24, this.getY() + 1, s,
                this.getWidth() / 4, this.getHeight() - 4);
        super.hitbox.setPolygon(8);

    }

    public void act(float delta) {
        super.act(delta);
        updateHitbox();
        movement();
        animate();

        interact();

        checkMoving();

        if (lClickActivationTime >= 0) {
            lClickActivationTime-=delta;

        }

        if (timeBeforeNextShot > 0) {
            timeBeforeNextShot-=delta;

        }

//        System.out.println(delta + " | " + animationTime);

    }

    private void updateHitbox() {
        super.hitbox.setPosition(this.getX() + 24, this.getY() + 1);

    }

    private void interact() {
        for (Neutrals npc :
                lvl.neutralNPCs) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
                System.out.println((super.distanceToTarget(npc.getCenteredX(), 
                        npc.getCenteredY()) < 40) ? npc.message :
                        "not close enough" + super.distanceToTarget(npc.getCenteredX(), 
                        npc.getCenteredY()));

            }

        }

    }

    private void checkMoving() {
        moving = (Math.abs(this.velocity.x) > 0 || 
                Math.abs(this.velocity.y) > 0) ? true : false;

    }

    private void animate() {
        if (moving) {
            if (this.velocity.x > 0 /*&& Math.abs(this.velocity.x) > Math.abs(this.velocity.y)*/) {
                this.setAnimation((running) ? runRight : walkRight);
//                this.setAnimation(walkRight);

            } else if (this.velocity.x < 0 /*&& Math.abs(this.velocity.x) > Math.abs(this.velocity.y)*/) {
                this.setAnimation((running) ? runLeft : walkLeft);
//                this.setAnimation(walkLeft);

            } else if (this.velocity.y > 0 && Math.abs(this.velocity.y) > Math.abs(this.velocity.x)) {
                this.setAnimation((running) ? runUp : walkUp);
//                this.setAnimation(walkUp);

            } else if (this.velocity.y < 0 && Math.abs(this.velocity.y) > Math.abs(this.velocity.x)) {
                this.setAnimation((running) ? runDown : walkDown);
//                this.setAnimation(walkDown);

            }

        } else {
            if (Math.abs((this.lvl.mouseX - this.getCenteredX())) >
                    Math.abs((this.lvl.mouseY - this.getCenteredY()))) {
                if ((this.lvl.mouseX - this.getCenteredX()) > 0) {
                    this.setAnimation(idleRight);

                } else if ((this.lvl.mouseX - this.getCenteredX()) < 0) {
                    this.setAnimation(idleLeft);

                }

            } else if (Math.abs((this.lvl.mouseX - this.getCenteredX())) <
                    Math.abs((this.lvl.mouseY - this.getCenteredY()))) {
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

// TODO: EXPERIMENTAL CODE! test and troubleshoot is prioritized and mandatory
    private void shoot() {
        final int PROJECTILE_OFFSET = (int) persistenceMag.get(0).getWidth() / 2;
        
        this.shootDir.x = lvl.mouseX - this.getCenteredX();
        this.shootDir.y = lvl.mouseY - this.getCenteredY();

//        System.out.println("BANG BANG!!" + " ||| " + this.shootDir.x + " | " + this.shootDir.y);

        shootDir = shootDir.nor();

        this.persistenceMag.get(currPersistenceBullet).fire(this.getCenteredX() -
                PROJECTILE_OFFSET, this.getCenteredY() - PROJECTILE_OFFSET,
                this.shootDir.x * PlayerParams.currTool.getBulletSpeed(),
                this.shootDir.y * PlayerParams.currTool.getBulletSpeed());

//        this.currPersistenceBullet = (this.currPersistenceBullet + 1) % PERSISTENCE_MAG_SIZE;
        this.currPersistenceBullet = (this.currPersistenceBullet + 1) %
                PlayerParams.currTool.getPersistenceMagSize();

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

        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            if (PlayerParams.currTool.isCanSwitchFireMode()) {
                PlayerParams.currTool.isInFullAuto = !PlayerParams.currTool.isInFullAuto;
                System.out.println(PlayerParams.currTool.isInFullAuto);

            } else
                System.out.println("weapon cannot switch fire modes");

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            PlayerParams.chosenWeapon = (PlayerParams.chosenWeapon == 1) ? 0 : 1;
            PlayerParams.currTool = PlayerParams.weaponInv[PlayerParams.chosenWeapon];
            loadPersistenceMag(this.s, true);

        }

        if (this.velocity.x == 0 && this.velocity.y == 0 &&
                lClickActivationTime <= 0) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                    !PlayerParams.currTool.isInFullAuto) {
                shoot();

            }
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) &&
                    PlayerParams.currTool.isInFullAuto) {
                if (timeBeforeNextShot <= 0) {
                    shoot();
                    timeBeforeNextShot = 60 / PlayerParams.currTool.getRoundsPerMinute();

                }

            }

        }

        if (GameParams.TOGGLE_RUN) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT))
                running = !running;

        } else
            running = (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) ? true : false;

    }

//    DEPRECATED
    private void stopMov() {
        this.velocity.x = 0;
        this.velocity.y = 0;
        this.setX(moveVec.x);
        this.setY(moveVec.y);

    }

//    DEPRECATED
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
//        idleUp = loadFullAnimation("sprites/player/up/hgun_idle_up.png",
//                1, 1, 0, true);
//        idleDown = loadFullAnimation("sprites/player/down/hgun_idle_down.png",
//                1, 1, 0, true);
//        idleLeft = loadFullAnimation("sprites/player/left/hgun_idle_left.png",
//                1, 1, 0, true);
//        idleRight = loadFullAnimation("sprites/player/right/hgun_idle_right.png",
//                1, 1, 0, true);
//
//        walkUp = loadFullAnimation("sprites/player/up/hgun_walk_up.png",
//                1, 6, 0.15f, true);
//        walkDown = loadFullAnimation("sprites/player/down/hgun_walk_down.png",
//                1, 6, 0.15f, true);
//        walkLeft = loadFullAnimation("sprites/player/left/hgun_walk_left.png",
//                1, 6, 0.15f, true);
//        walkRight = loadFullAnimation("sprites/player/right/hgun_walk_right.png",
//                1, 6, 0.15f, true);
//
//        runUp = loadFullAnimation("sprites/player/up/hgun_walk_up.png",
//                1, 6, 0.15f, true);
//        runDown = loadFullAnimation("sprites/player/down/hgun_walk_down.png",
//                1, 6, 0.15f, true);
//        runLeft = loadFullAnimation("sprites/player/left/hgun_walk_left.png",
//                1, 6, 0.15f, true);
//        runRight = loadFullAnimation("sprites/player/right/hgun_walk_right.png",
//                1, 6, 0.15f, true);

        idleDown = loadFullAnimation("sprites/player/down/nothing_idle_down.png", 1, 1, 0, true);
        idleLeft = loadFullAnimation("sprites/player/left/nothing_idle_left.png", 1, 1, 0, true);
        idleRight = loadFullAnimation("sprites/player/right/nothing_idle_right.png", 1, 1, 0, true);
        idleUp = loadFullAnimation("sprites/player/up/nothing_idle_up.png", 1, 1, 0, true);

        walkDown = loadFullAnimation("sprites/player/down/nothing_walk_down.png", 1, 6, 0.15f, true);
        walkLeft = loadFullAnimation("sprites/player/left/nothing_walk_left.png", 1, 6, 0.15f, true);
        walkRight = loadFullAnimation("sprites/player/right/nothing_walk_right.png", 1, 6, 0.15f, true);
        walkUp = loadFullAnimation("sprites/player/up/nothing_walk_up.png", 1, 6, 0.15f, true);

        runDown = loadFullAnimation("sprites/player/down/nothing_run_down.png", 1, 6, 0.15f, true);
        runLeft = loadFullAnimation("sprites/player/left/nothing_run_left.png", 1, 6, 0.15f, true);
        runRight = loadFullAnimation("sprites/player/right/nothing_run_right.png", 1, 6, 0.15f, true);
        runUp = loadFullAnimation("sprites/player/up/nothing_run_up.png", 1, 6, 0.15f, true);


    }

}
