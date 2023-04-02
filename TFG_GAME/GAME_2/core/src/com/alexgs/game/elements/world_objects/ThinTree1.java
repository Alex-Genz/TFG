package com.alexgs.game.elements.world_objects;

import com.alexgs.game.elements.Element;
import com.alexgs.game.screens.GameScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ThinTree1 extends WorldObjects {

    public ThinTree1(float x, float y, Stage s, GameScreen lvl) {
        super(x, y, s, lvl);

        super.animation = loadFullAnimation("world/world_obj/trees/tree_1.png", 1, 1, 0, true);

        collider = new Element(0, 0, s, this.getWidth() - 18, this.getHeight() / 8);
        collider.setPolygon(8);
        collider.setPosition(this.getX() + 9, this.getY());

    }

}
