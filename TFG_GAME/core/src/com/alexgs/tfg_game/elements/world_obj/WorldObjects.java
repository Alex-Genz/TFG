package com.alexgs.tfg_game.elements.world_obj;

import com.alexgs.tfg_game.elements.Element;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class WorldObjects extends Element {
    protected MainScreen lvl;

    public WorldObjects(float x, float y, Stage s, MainScreen lvl, String file) {
        super(x, y, s);

        this.lvl = lvl;

        super.animation = loadFullAnimation("world/world_objects/" + file + ".png",
                1, 1, 0, true);

    }

}
