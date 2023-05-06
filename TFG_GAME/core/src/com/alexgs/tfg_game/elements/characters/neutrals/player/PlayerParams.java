package com.alexgs.tfg_game.elements.characters.neutrals.player;

import com.alexgs.tfg_game.elements.tools.ToolsOrWeapons;

public class PlayerParams {
    public static final int MAX_PLAYER_HEALTH = 100;
    public static int currPlayerHealth = MAX_PLAYER_HEALTH;

    public static ToolsOrWeapons[] toolInv = new ToolsOrWeapons[2];
    public static ToolsOrWeapons currWeapon;

}
