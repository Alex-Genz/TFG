package com.alexgs.game.elements.world_objects;

import com.alexgs.game.elements.Element;
import com.alexgs.game.screens.GameScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class WellB extends WorldObjects {

    public WellB(float x, float y, Stage s, GameScreen lvl) {
        super(x, y, s, lvl);

        super.animation = loadFullAnimation("world/world_obj/other/well_2.png", 1, 1, 0, true);

        collider = new Element(0, 0, s, this.getWidth() - 6, this.getHeight() / 3);
        collider.setPolygon(8);
        collider.setPosition(this.getX() + 3, this.getY());

    }

}
