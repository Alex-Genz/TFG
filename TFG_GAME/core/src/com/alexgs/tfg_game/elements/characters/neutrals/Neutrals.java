package com.alexgs.tfg_game.elements.characters.neutrals;

import com.alexgs.tfg_game.elements.Element;
import com.alexgs.tfg_game.elements.characters.Characters;
import com.alexgs.tfg_game.params.Consts;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Neutrals extends Characters {
    public String message;

    protected float speed;

    public Neutrals(float x, float y, Stage s, MainScreen lvl, int charNum, String message, float pathSizeX, float pathSizeY, float speed) {
        super(x, y, s, lvl);

        assignCharacter(charNum);

        setHitbox(s);

        this.message = message;
        this.speed = speed;

        super.pathSizeX = pathSizeX;
        super.pathSizeY = pathSizeY;

        setPolygon(8, this.getWidth() / 3, this.getHeight() / 4, 22, 0);

        super.setPath();

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
        super.act(delta);

        this.applyPhysics(delta);

        updateHitbox();

        if (distanceToTarget(lvl.player.getCenteredX(), lvl.player.getCenteredY()) >= 60) {
            if (distanceToTarget(pathPoints[currTgtPathPoint]) < 2) {
                currTgtPathPoint = (currTgtPathPoint == 0) ? 1 : 0;

            } else {
                super.moveTo(pathPoints[currTgtPathPoint], this.speed);
                this.animations();

            }

        } else {
            this.velocity.x = 0;
            this.velocity.y = 0;
            this.animations(super.lvl.player.getCenteredX(), super.lvl.player.getCenteredY());

        }

        if (pathSizeX != 0 && pathSizeY != 0)
            moveNPC();

    }

    private void moveNPC() {


    }

    private String pathFiller(int character, int dir) {
        return Consts.PATHS + Consts.NPC_CHARACTER_SPRITE[character] +
                Consts.SPRITE_DIR[dir] + Consts.EXTENSION;

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

}
