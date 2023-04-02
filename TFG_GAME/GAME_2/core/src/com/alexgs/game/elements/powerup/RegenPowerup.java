package com.alexgs.game.elements.powerup;

import com.alexgs.game.managers.SoundManager;
import com.alexgs.game.screens.GameScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class RegenPowerup extends Powerup {

    public RegenPowerup(float x, float y, Stage s, GameScreen lvl) {
        super(x, y, s, lvl);

        super.anim = loadFullAnimation("sprites/powUP/health_regen_powup.png", 1, 8, 0.1f, true);

        this.setAnimation(anim);

    }

    public void act(float delta) {
        super.act(delta);

        touch();

    }

    private void touch() {
        if (this.getEnabled() && this.overlaps(super.lvl.player)) {
            super.lvl.player.regenPwUpDur = 5;
            playSound();
            super.setRespawnTime();

        }
    }


    private void playSound() {
        SoundManager.playSound("audio/sfx/hl_medkit.mp3");

    }

}
