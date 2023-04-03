package com.alexgs.game.elements.world_objects;

import com.alexgs.game.elements.Element;
import com.alexgs.game.screens.GameScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Streetlight extends WorldObjects {

    public Streetlight(float x, float y, Stage s, GameScreen lvl) {
        super(x, y, s, lvl);

        super.animation = loadFullAnimation("world/world_obj/other/streetlight.png", 1, 1, 0, true);

        collider = new Element(0, 0, s, this.getWidth() - 10, this.getHeight() / 6);
        collider.setRectangle();
        collider.setPosition(this.getX() + 5, this.getY());

    }

}
