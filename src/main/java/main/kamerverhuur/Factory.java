package main.kamerverhuur;

import main.kamerverhuur.Game;

import main.kamerverhuur.subject.Speelbords.Speelbord;

public interface Factory<T> {

    T[][] speelveld(Game game);
    void setborder();

    Speelbord newSpeelbord(int x, int y, Game game, boolean Sides);
    public String getName();
}
