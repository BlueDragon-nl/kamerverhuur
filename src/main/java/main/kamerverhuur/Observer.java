package main.kamerverhuur;

import java.util.ArrayList;

public abstract class Observer<T> {
    protected ArrayList<T> ingeschrijven = new ArrayList<>();

    public void Inschrijven(T item){
        ingeschrijven.add(item);
    }

    public void Uitschrijven(T item){
        ingeschrijven.remove(item);
    }

    public abstract void update();

}