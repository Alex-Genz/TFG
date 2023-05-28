package com.alexgs.tfg_game.elements.characters.neutrals.player;

import com.alexgs.tfg_game.elements.tools.*;

public class PlayerParams {
    public static final int MAX_PLAYER_HEALTH = 100;
    public static float hp = MAX_PLAYER_HEALTH;

    public static final float HP_REGEN_DELAY = 5f;
    public static float timeToRegen = 0;

    public static int killCount = 0;
    public static int killCountRes = 0;


    public static Weapons[] weaponInv = {new Pistol(), new AssaultRifle()};
//    public static Weapons currWeapon = new Knife();
    public static Weapons currWeapon = weaponInv[0];

    public static int chosenWeapon = 0;

}
