package main.kamerverhuur.subject;

import java.util.ArrayList;


public abstract class Subject<T> {
    protected ArrayList<T> ingeschrijven = new ArrayList<>();

    public void Inschrijven(T item){
        ingeschrijven.add(item);
    }

    public void Uitschrijven(T item){
        ingeschrijven.remove(item);
    }

    public  void update(){

    };

}