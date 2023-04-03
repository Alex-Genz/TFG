package com.alexgs.tfg_game.scr.util;

import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Comparator;

public class ActorComparator implements Comparator<Actor> {

    @Override
    public int compare(Actor actor1, Actor actor2) {
        if (actor1.getY() == actor2.getY()) {
            return 0;

        }

        if (actor1.getY() > actor2.getY()) {
            return -1;

        }

        return 1;
    }

}