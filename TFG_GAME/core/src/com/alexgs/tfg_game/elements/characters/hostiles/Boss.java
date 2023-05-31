package com.alexgs.tfg_game.elements.characters.hostiles;

import com.alexgs.tfg_game.elements.Element;
import com.alexgs.tfg_game.elements.characters.Characters;
import com.alexgs.tfg_game.elements.characters.neutrals.player.PlayerParams;
import com.alexgs.tfg_game.elements.tools.BossWeapon;
import com.alexgs.tfg_game.elements.tools.Weapons;
import com.alexgs.tfg_game.params.Consts;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Boss extends Characters {
    private Weapons weapon;

    private float timeBeforeNextShot = 0;

    private float health = 2500;

    public Boss(float x, float y, Stage s, MainScreen lvl) {
        super(x, y, s, lvl);

        this.weapon = new BossWeapon();

        assignCharacter(21);

        setPolygon(8, this.getWidth() / 3 + 4,
                this.getHeight() / 4, 40, 0);

        setHitbox(s);

        super.loadPersistenceMag(s, this.weapon, 1, false);

        this.setAnimation(super.idleDown);

    }

    private void setHitbox(Stage s) {
        super.hitbox = new Element(this.getX() + 17, this.getY(), s,
                this.getWidth() / 2f - 3, this.getHeight() - 32);
        super.hitbox.setPolygon(8);

    }

    private void updateHitbox() {
        super.hitbox.setPosition(this.getX() + 34, this.getY());

    }

    public void act(float delta) {
        if (this.getEnabled()) {
            super.act(delta);

            updateHitbox();

            if (this.health <= 0) {
                this.setEnabled(false);
                PlayerParams.scoreCountRes+=3000;

            }

            if (timeBeforeNextShot > 0)
                timeBeforeNextShot -= delta;


            if (distanceToTarget(lvl.player.getCenteredX(), lvl.player.getCenteredY()) < 180) {
                this.velocity.x = 0;
                this.velocity.y = 0;
                super.animations(lvl.player.getCenteredPos());

                if (timeBeforeNextShot <= 0 && this.velocity.x == 0 && this.velocity.y == 0) {
                    super.shoot(this.weapon, lvl.player.getCenteredX(), lvl.player.getCenteredY());
                    timeBeforeNextShot += 60 / weapon.getRoundsPerMinute();

                }

            } else {
                if (lvl.player.getX() > 284 && lvl.player.getY() > 284) {
                    super.moveTo(lvl.player.getCenteredX(), lvl.player.getCenteredY(), 80);
                    super.animations();

                }

            }

        }

    }

    private String pathFiller(int character, int dir) {
        return Consts.PATHS + Consts.NPC_CHARACTER_SPRITE[character] +
                Consts.SPRITE_DIR[dir] + Consts.EXTENSION;

    }

    protected void assignCharacter(int charNum) {
        super.idleDown = loadFullAnimation("sprites/enemies/" + Consts.ENEMY_SPRITES[3] + Consts.SPRITE_DIR[0] + ".png",
                1, 1, 0, true);
        super.idleLeft = loadFullAnimation("sprites/enemies/" + Consts.ENEMY_SPRITES[3] + Consts.SPRITE_DIR[1] + ".png",
                1, 1, 0, true);
        super.idleRight = loadFullAnimation("sprites/enemies/" + Consts.ENEMY_SPRITES[3] + Consts.SPRITE_DIR[2] + ".png",
                1, 1, 0, true);
        super.idleUp = loadFullAnimation("sprites/enemies/" + Consts.ENEMY_SPRITES[3] + Consts.SPRITE_DIR[3] + ".png",
                1, 1, 0, true);

        super.walkDown = loadFullAnimation("sprites/enemies/" + Consts.ENEMY_SPRITES[3] + Consts.SPRITE_DIR[4] + ".png",
                1, 8, 0.125f, true);
        super.walkLeft = loadFullAnimation("sprites/enemies/" + Consts.ENEMY_SPRITES[3] + Consts.SPRITE_DIR[5] + ".png",
                1, 8, 0.125f, true);
        super.walkRight = loadFullAnimation("sprites/enemies/" + Consts.ENEMY_SPRITES[3] + Consts.SPRITE_DIR[6] + ".png",
                1, 8, 0.125f, true);
        super.walkUp = loadFullAnimation("sprites/enemies/" + Consts.ENEMY_SPRITES[3] + Consts.SPRITE_DIR[7] + ".png",
                1, 8, 0.125f, true);

    }

    public void hit(float dmg) {
        this.health-=dmg;
        System.out.println(this.health);

    }

    public float getHealth() {
        return this.health;
    }

}
