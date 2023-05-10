package com.alexgs.tfg_game.elements.tools;

public class Weapons {
    private int persistenceMagSize;
    private boolean canSwitchFireMode;
    private int roundsPerMinute;
    private int dmg;
    private float timeAllowedToExist;

    private float bulletSpeed;

    public boolean isInFullAuto;

    public Weapons(int persistenceMagSize, boolean canSwitchFireMode, int
                   roundsPerMinute, int dmg, float timeAllowedToExist,
                   float bulletSpeed, boolean isInFullAuto) {
        this.persistenceMagSize = persistenceMagSize;
        this.canSwitchFireMode = canSwitchFireMode;
        this.roundsPerMinute = roundsPerMinute;
        this.dmg = dmg;
        this.timeAllowedToExist = timeAllowedToExist;
        this.bulletSpeed = bulletSpeed;

        this.isInFullAuto = isInFullAuto;

    }

    public int getPersistenceMagSize() {
        return persistenceMagSize;
    }

    public boolean isCanSwitchFireMode() {
        return canSwitchFireMode;
    }

    public int getRoundsPerMinute() {
        return roundsPerMinute;
    }

    public int getDmg() {
        return dmg;
    }

    public float getTimeAllowedToExist() {
        return timeAllowedToExist;
    }

    public float getBulletSpeed() {
        return bulletSpeed;

    }

}
