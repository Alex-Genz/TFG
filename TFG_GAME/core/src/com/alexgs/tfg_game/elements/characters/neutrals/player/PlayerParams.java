package com.alexgs.tfg_game.elements.characters.neutrals.player;

import com.alexgs.tfg_game.elements.tools.*;

public class PlayerParams {
    public static final int MAX_PLAYER_HEALTH = 100;
    public static int currPlayerHealth = MAX_PLAYER_HEALTH;


    public static Weapons[] weaponInv = {new Pistol(), new AssaultRifle()};
    public static Weapons currTool = new Knife();

    public static int chosenWeapon = 0;

}
