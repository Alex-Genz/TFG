package com.alexgs.game.elements.world_objects;

import com.alexgs.game.elements.Element;
import com.alexgs.game.screens.GameScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Pine extends WorldObjects {

    public Pine(float x, float y, Stage s, GameScreen lvl) {
        super(x, y, s, lvl);

        super.animation = loadFullAnimation("world/world_obj/trees/pine.png", 1, 1, 0, true);

        collider = new Element(0, 0, s, this.getWidth() / 4, this.getHeight() / 6);
        collider.setPolygon(8);
        collider.setPosition(this.getX() + this.getWidth() / 1.6f - this.getWidth() / 4, this.getY());

    }

}
