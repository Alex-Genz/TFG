package com.alexgs.game.managers;

import com.alexgs.game.game.GameParams;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
    static Music currentMusic;
    static String currentMusicName;
    static Sound sound;

    static public void playMusic(String path) {
        if (currentMusicName != path) {
            currentMusic.stop();
            currentMusicName = path;
            currentMusic = ResourceManager.getMusic(path);
            currentMusic.setVolume(GameParams.musicVolume);
            currentMusic.setLooping(true);
            currentMusic.play();

        }

    }

    static public void playSound(String path) {

        sound = ResourceManager.getSound(path);
        sound.play(GameParams.soundVolume);

    }

    static public void playSound(String path, float volume) {

        sound = ResourceManager.getSound(path);
        sound.play(volume);

    }

}
