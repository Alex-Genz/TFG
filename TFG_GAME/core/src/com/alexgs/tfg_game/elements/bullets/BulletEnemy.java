package com.alexgs.tfg_game.elements.bullets;

import com.alexgs.tfg_game.elements.characters.neutrals.Neutrals;
import com.alexgs.tfg_game.scr.game_scr.MainScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BulletEnemy extends Bullet {

    public BulletEnemy(float x, float y, Stage s, MainScreen lvl,
                       int dmg, /*int bulletSpeed, */float timeBeforeCulling) {
        super(x, y, s, lvl, dmg, /*bulletSpeed, */timeBeforeCulling);

    }

    public void act(float delta) {
        super.act(delta);

        collide();

    }

    private void collide() {
        for (Neutrals npc :
                lvl.neutralNPCs) {
            if (this.getEnabled() && npc.getEnabled() && this.overlaps(npc.getHitbox())) {
                this.setEnabled(false);

            }

        }

        if (this.getEnabled() && this.overlaps(lvl.player.getHitbox())) {
            this.setEnabled(false);
//            System.out.println("OUCH! was it something i said?!?!");
            lvl.player.hit(super.dmg);

        }

    }

}
