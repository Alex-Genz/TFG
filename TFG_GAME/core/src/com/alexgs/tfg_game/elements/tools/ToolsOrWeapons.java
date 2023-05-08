package com.alexgs.tfg_game.elements.tools;

import com.alexgs.tfg_game.elements.bullets.Bullet;
import com.badlogic.gdx.utils.Array;

public class ToolsOrWeapons {
    public Array<Bullet> persistenceMag;

    public boolean hasFullAuto;

// TODO: EXPERIMENTAL CODE! test and troubleshoot is prioritized and mandatory
    private boolean currPersistenceBullet;

    private Vector2 shootDir;

    private final int PERSISTENCE_MAG_SIZE = 24;
    public final int MAG_SIZE = 12;
    
    public final boolean CAN_SWITCH_FIRE_MODE = false;
    public final int BULLET_DMG = 10;
    public final int BULLET_SPEED = 80;

    public Animation<TextureRegion> bulletSprite;

    public ToolsOrWeapons() {
// TODO: EXPERIMENTAL CODE! test and troubleshoot is prioritized and mandatory

        this.persistenceMag = new Array<>();
        for (int i = 0; i < PERSISTENCE_MAG_SIZE; i++) {
            this.persistenceMag.add(new BulletFriendly(0, 0, s, lvl, BULLET_DMG, BULLET_SPEED,3f))
            this.persistenceMag.get(i).setEnabled(false);
            
        }

        this.currPersistenceBullet = 0;
        this.shootDir = new Vector2;

    }

// TODO: EXPERIMENTAL CODE! test and troubleshoot is prioritized and mandatory
    public void shoot(float delta) {
        final int PROJECTILE_OFFSET = (int) mag.get(0).getWidth() / 2;

        this.shootDir.x = lvl.mouseX - lvl.player.getCenteredX();
        this.shootDir.y = lvl.mouseY - lvl.player.getCenteredY();

        shootDir = shootDir.nor();

        this.mag.get(currBullet).fire(lvl.player.getCenteredX() - PROJECTILE_OFFSET, 
                lvl.player.getCenteredY - PROJECTILE_OFFSET, 
                this.shootDir.x * BULLET_SPEED, this.shootDir.y * BULLET_SPEED);

        this.currBullet = (this.currBullet + 1) % bulletCount;

    }

}
