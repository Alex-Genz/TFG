package com.alexgs.tfg_game.elements.tools;

import com.alexgs.tfg_game.elements.bullets.Bullet;
import com.badlogic.gdx.utils.Array;

public class ToolsOrWeapons {
    public Array<Bullet> persistenceMag;

    public boolean hasFullAuto;

// TODO: EXPERIMENTAL CODE! test and troubleshoot is prioritized and mandatory
    private final int PERSISTENCE_MAG_SIZE = 24;
    public final int MAG_SIZE = 12;
    
    public final boolean CAN_SWITCH_FIRE_MODE = false;
    public final int BULLET_DMG = 10;
    public final int BULLET_SPEED = 80;

    public ToolsOrWeapons() {
// TODO: EXPERIMENTAL CODE! test and troubleshoot is prioritized and mandatory
        this.persistenceMag = new Array<>();

        for (int i = 0; i < PERSISTENCE_MAG_SIZE; i++) {
            this.persistenceMag.add(new)
            
        }

    }

}
