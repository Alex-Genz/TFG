package com.alexgs.tfg_game.managers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
    static Music currentMusic;
    static String currentMusicName;
    static Sound sound;

    static public void playMusic(String path, float volume) {
/*        if (currentMusic.isPlaying()) {
            currentMusic.stop();
            currentMusicName = path;
            currentMusic = ResourceManager.getMusic(path);
            currentMusic.setVolume(volume);
            currentMusic.setLooping(true);
            currentMusic.play();

        } else {
            currentMusicName = path;
            currentMusic = ResourceManager.getMusic(path);
            currentMusic.setVolume(volume);
            currentMusic.setLooping(true);
            currentMusic.play();

        }*/
        currentMusic = ResourceManager.getMusic(path);
        currentMusic.setVolume(volume);
        currentMusic.setLooping(true);
        currentMusic.play();

    }

    public static void stopMusic() {
        currentMusic.stop();
    }

    static public void playSound(String path, float volume) {

        sound = ResourceManager.getSound(path);
        sound.play(volume);

    }

    public static boolean isMusicPlaying() {
        return currentMusic.isPlaying();

    }

}
