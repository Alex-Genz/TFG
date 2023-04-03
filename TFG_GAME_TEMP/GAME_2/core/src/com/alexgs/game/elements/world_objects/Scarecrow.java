package com.alexgs.game.elements.world_objects;

import com.alexgs.game.elements.Element;
import com.alexgs.game.screens.GameScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Scarecrow extends WorldObjects {

    public Scarecrow(float x, float y, Stage s, GameScreen lvl) {
        super(x, y, s, lvl);

        super.animation = loadFullAnimation("world/world_obj/other/scarecrow.png", 1, 1, 0, true);

        collider = new Element(0, 0, s, 12, 12);
        collider.setPolygon(8);
        collider.setPosition(this.getX() + 6, this.getY());

    }

}
