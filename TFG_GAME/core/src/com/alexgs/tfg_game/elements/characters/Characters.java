package com.alexgs.tfg_game.elements.characters;

import com.alexgs.tfg_game.elements.Element;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Characters extends Element {
    public MainScreen lvl;

    protected Element hitbox;

    private Vector2 moveToVec;

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

}
