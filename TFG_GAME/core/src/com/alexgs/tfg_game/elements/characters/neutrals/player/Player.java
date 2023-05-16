package com.alexgs.tfg_game.elements.characters.neutrals.player;

import com.alexgs.tfg_game.elements.Element;
import com.alexgs.tfg_game.elements.characters.Characters;
import com.alexgs.tfg_game.elements.characters.neutrals.Neutrals;
import com.alexgs.tfg_game.params.GameParams;
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


    protected Animation<TextureRegion> idleUpPistol;
    protected Animation<TextureRegion> idleDownPistol;
    protected Animation<TextureRegion> idleLeftPistol;
    protected Animation<TextureRegion> idleRightPistol;

    protected Animation<TextureRegion> walkUpPistol;
    protected Animation<TextureRegion> walkDownPistol;
    protected Animation<TextureRegion> walkLeftPistol;
    protected Animation<TextureRegion> walkRightPistol;


    protected Animation<TextureRegion> idleUpAr;
    protected Animation<TextureRegion> idleDownAr;
    protected Animation<TextureRegion> idleLeftAr;
    protected Animation<TextureRegion> idleRightAr;

    protected Animation<TextureRegion> walkUpAr;
    protected Animation<TextureRegion> walkDownAr;
    protected Animation<TextureRegion> walkLeftAr;
    protected Animation<TextureRegion> walkRightAr;

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

        super.loadPersistenceMag(this.s, PlayerParams.currWeapon, 0, false);

        moveVec = new Vector2();
        moveVec.x = this.getX();
        moveVec.y = this.getY();

        lastPosVec = new Vector2();

        this.shootDir = new Vector2();

    }

//    TODO: move this method to Characters.java & adapt for refactorization

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
            lClickActivationTime -= delta;

        }

        if (timeBeforeNextShot > 0) {
            timeBeforeNextShot -= delta;

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
                this.setAnimation((running) ? runRight : setWeaponAnim(walkRightPistol, walkRightAr));

            } else if (this.velocity.x < 0 /*&& Math.abs(this.velocity.x) > Math.abs(this.velocity.y)*/) {
                this.setAnimation((running) ? runLeft : setWeaponAnim(walkLeftPistol, walkLeftAr));

            } else if (this.velocity.y > 0 && Math.abs(this.velocity.y) > Math.abs(this.velocity.x)) {
                this.setAnimation((running) ? runUp : setWeaponAnim(walkUpPistol, walkUpAr));

            } else if (this.velocity.y < 0 && Math.abs(this.velocity.y) > Math.abs(this.velocity.x)) {
                this.setAnimation((running) ? runDown : setWeaponAnim(walkDownPistol, walkDownAr));

            }

        } else {
            if (Math.abs((this.lvl.mouseX - this.getCenteredX())) >
                    Math.abs((this.lvl.mouseY - this.getCenteredY()))) {
                if ((this.lvl.mouseX - this.getCenteredX()) > 0) {
                    this.setAnimation(setWeaponAnim(idleRightPistol, idleRightAr));

                } else if ((this.lvl.mouseX - this.getCenteredX()) < 0) {
                    this.setAnimation(setWeaponAnim(idleLeftPistol, idleLeftAr));

                }

            } else if (Math.abs((this.lvl.mouseX - this.getCenteredX())) <
                    Math.abs((this.lvl.mouseY - this.getCenteredY()))) {
                if ((this.lvl.mouseY - this.getCenteredY()) > 0) {
                    this.setAnimation(setWeaponAnim(idleUpPistol, idleUpAr));

                } else if ((this.lvl.mouseY - this.getCenteredY()) < 0) {
                    this.setAnimation(setWeaponAnim(idleDownPistol, idleDownAr));

                }

            }

        }

    }

    private Animation<TextureRegion> setWeaponAnim(Animation<TextureRegion> anim1, Animation<TextureRegion> anim2) {
        switch (PlayerParams.chosenWeapon) {
            case 0:
                return anim1;

            case 1:
                return anim2;

        }
        return null;

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

        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            if (PlayerParams.currWeapon.isCanSwitchFireMode()) {
                PlayerParams.currWeapon.isInFullAuto = !PlayerParams.currWeapon.isInFullAuto;
                System.out.println(PlayerParams.currWeapon.isInFullAuto);

            } else
                System.out.println("weapon cannot switch fire modes");

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            PlayerParams.chosenWeapon = (PlayerParams.chosenWeapon == 1) ? 0 : 1;
            PlayerParams.currWeapon = PlayerParams.weaponInv[PlayerParams.chosenWeapon];
            loadPersistenceMag(this.s, PlayerParams.currWeapon, 0, true);

        }

        if (this.velocity.x == 0 && this.velocity.y == 0 &&
                lClickActivationTime <= 0) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                    !PlayerParams.currWeapon.isInFullAuto) {
                super.shoot(PlayerParams.currWeapon, lvl.mouseX, lvl.mouseY);

            }
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) &&
                    PlayerParams.currWeapon.isInFullAuto) {
                if (timeBeforeNextShot <= 0) {
                    super.shoot(PlayerParams.currWeapon, lvl.mouseX, lvl.mouseY);
                    timeBeforeNextShot = 60 / PlayerParams.currWeapon.getRoundsPerMinute();

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
        idleDown = loadFullAnimation("sprites/player/down/nothing_idle_down.png", 1, 1, 0, true);
        idleLeft = loadFullAnimation("sprites/player/left/nothing_idle_left.png", 1, 1, 0, true);
        idleRight = loadFullAnimation("sprites/player/right/nothing_idle_right.png", 1, 1, 0, true);
        idleUp = loadFullAnimation("sprites/player/up/nothing_idle_up.png", 1, 1, 0, true);

//

        walkDown = loadFullAnimation("sprites/player/down/nothing_walk_down.png", 1, 6, 0.15f, true);
        walkLeft = loadFullAnimation("sprites/player/left/nothing_walk_left.png", 1, 6, 0.15f, true);
        walkRight = loadFullAnimation("sprites/player/right/nothing_walk_right.png", 1, 6, 0.15f, true);
        walkUp = loadFullAnimation("sprites/player/up/nothing_walk_up.png", 1, 6, 0.15f, true);

        runDown = loadFullAnimation("sprites/player/down/nothing_run_down.png", 1, 6, 0.15f, true);
        runLeft = loadFullAnimation("sprites/player/left/nothing_run_left.png", 1, 6, 0.15f, true);
        runRight = loadFullAnimation("sprites/player/right/nothing_run_right.png", 1, 6, 0.15f, true);
        runUp = loadFullAnimation("sprites/player/up/nothing_run_up.png", 1, 6, 0.15f, true);

//

        idleDownPistol = loadFullAnimation("sprites/player/down/pistol_idle_down.png", 1, 1, 0, true);
        idleLeftPistol = loadFullAnimation("sprites/player/left/pistol_idle_left.png", 1, 1, 0, true);
        idleRightPistol = loadFullAnimation("sprites/player/right/pistol_idle_right.png", 1, 1, 0, true);
        idleUpPistol = loadFullAnimation("sprites/player/up/pistol_idle_up.png", 1, 1, 0, true);

        walkDownPistol = loadFullAnimation("sprites/player/down/pistol_walk_down.png", 1, 6, 0.15f, true);
        walkLeftPistol = loadFullAnimation("sprites/player/left/pistol_walk_left.png", 1, 6, 0.15f, true);
        walkRightPistol = loadFullAnimation("sprites/player/right/pistol_walk_right.png", 1, 6, 0.15f, true);
        walkUpPistol = loadFullAnimation("sprites/player/up/pistol_walk_up.png", 1, 6, 0.15f, true);

//

        idleDownAr = loadFullAnimation("sprites/player/down/ar_idle_down.png", 1, 1, 0, true);
        idleLeftAr = loadFullAnimation("sprites/player/left/ar_idle_left.png", 1, 1, 0, true);
        idleRightAr = loadFullAnimation("sprites/player/right/ar_idle_right.png", 1, 1, 0, true);
        idleUpAr = loadFullAnimation("sprites/player/up/ar_idle_up.png", 1, 1, 0, true);

        walkDownAr = loadFullAnimation("sprites/player/down/ar_walk_down.png", 1, 6, 0.15f, true);
        walkLeftAr = loadFullAnimation("sprites/player/left/ar_walk_left.png", 1, 6, 0.15f, true);
        walkRightAr = loadFullAnimation("sprites/player/right/ar_walk_right.png", 1, 6, 0.15f, true);
        walkUpAr = loadFullAnimation("sprites/player/up/ar_walk_up.png", 1, 6, 0.15f, true);


    }

}
