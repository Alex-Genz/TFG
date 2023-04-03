package com.alexgs.tfg_game.elements.characters;

import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    public Player(float x, float y, Stage s, MainScreen lvl) {
        super(x, y, s, lvl);

        setAnimations();

    }

    public void act(float delta) {
        super.act(delta);

    }

    private void setAnimations() {
        idleUp = loadFullAnimation("sprites/player/up/hgun_idle_up.png", 1, 1, 0, true);
        idleDown = loadFullAnimation("sprites/player/up/hgun_idle_down.png", 1, 1, 0, true);
        idleLeft = loadFullAnimation("sprites/player/up/hgun_idle_left.png", 1, 1, 0, true);
        idleRight = loadFullAnimation("sprites/player/up/hgun_idle_right.png", 1, 1, 0, true);

        walkUp = loadFullAnimation("sprites/player/up/hgun_walk_up.png", 1, 6, 150, true);
        walkDown = loadFullAnimation("sprites/player/up/hgun_walk_down.png", 1, 6, 150, true);
        walkLeft = loadFullAnimation("sprites/player/up/hgun_walk_left.png", 1, 6, 150, true);
        walkRight = loadFullAnimation("sprites/player/up/hgun_walk_right.png", 1, 6, 150, true);


    }

}
