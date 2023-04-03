package com.alexgs.tfg_game.managers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
    static Music currentMusic;
    static String currentMusicName;
    static Sound sound;

    static public void playMusic(String path, float volume) {
        if (currentMusicName != path) {
            currentMusic.stop();
            currentMusicName = path;
            currentMusic = ResourceManager.getMusic(path);
            currentMusic.setVolume(volume);
            currentMusic.setLooping(true);
            currentMusic.play();

        }

    }

    static public void playSound(String path, float volume) {

        sound = ResourceManager.getSound(path);
        sound.play(volume);

    }

}
