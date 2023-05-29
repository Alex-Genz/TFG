package com.alexgs.tfg_game.elements.characters.hostiles;

import com.alexgs.tfg_game.elements.Element;
import com.alexgs.tfg_game.elements.characters.Characters;
import com.alexgs.tfg_game.elements.tools.BossWeapon;
import com.alexgs.tfg_game.elements.tools.Weapons;
import com.alexgs.tfg_game.params.Consts;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Boss extends Characters {
    private Weapons weapon;

    private float timeBeforeNextShot = 0;

    private float health = 100;

    public Boss(float x, float y, Stage s, MainScreen lvl) {
        super(x, y, s, lvl);

        this.weapon = new BossWeapon();

        assignCharacter(21);

        setPolygon(8, this.getWidth() / 3,
                this.getHeight() / 4, 22, 0);

        setHitbox(s);

        super.loadPersistenceMag(s, this.weapon, 1, false);

        this.setAnimation(super.idleDown);

    }

    private void setHitbox(Stage s) {
        super.hitbox = new Element(this.getX() + 17, this.getY(), s,
                this.getWidth() / 2f - 2, this.getHeight() - 2);
        super.hitbox.setPolygon(8);

    }

    private void updateHitbox() {
        super.hitbox.setPosition(this.getX() + 17, this.getY());

    }

    public void act(float delta) {
        if (this.getEnabled()) {
            super.act(delta);

            updateHitbox();

            if (this.health <= 0)
                this.setEnabled(false);

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
                Consts.NPC_SPRITE_DIR[dir] + Consts.EXTENSION;

    }

    protected void assignCharacter(int charNum) {
        super.idleDown = loadFullAnimation(pathFiller(charNum, 0),
                1, 1, 0, true);
        super.idleLeft = loadFullAnimation(pathFiller(charNum, 1),
                1, 1, 0, true);
        super.idleRight = loadFullAnimation(pathFiller(charNum, 2),
                1, 1, 0, true);
        super.idleUp = loadFullAnimation(pathFiller(charNum, 3),
                1, 1, 0, true);

        super.walkDown = loadFullAnimation(pathFiller(charNum, 4),
                1, 4, 0.15f, true);
        super.walkLeft = loadFullAnimation(pathFiller(charNum, 5),
                1, 4, 0.15f, true);
        super.walkRight = loadFullAnimation(pathFiller(charNum, 6),
                1, 4, 0.15f, true);
        super.walkUp = loadFullAnimation(pathFiller(charNum, 7),
                1, 4, 0.15f, true);

    }

    public void hit(float dmg) {
        this.health-=dmg;
        System.out.println(this.health);

    }

    public float getHealth() {
        return this.health;
    }

}
