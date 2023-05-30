package com.alexgs.tfg_game.managers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
    static Music currentMusic;
    static Sound sound;

    static public void playMusic(String path, float volume) {
        currentMusic = ResourceManager.getMusic(path);
        currentMusic.setVolume(volume);
        currentMusic.setLooping(true);
        currentMusic.play();

    }

    static public void playMusicNoLoop(String path, float volume) {
        currentMusic = ResourceManager.getMusic(path);
        currentMusic.setVolume(volume);
        currentMusic.setLooping(false);
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
