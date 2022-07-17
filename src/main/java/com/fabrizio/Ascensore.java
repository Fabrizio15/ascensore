package com.fabrizio;

import java.util.ArrayList;

public class Ascensore {
   
    private static Ascensore ascensore = null;
    private int maxWeight = 0;
    private int currentWeight = 0;
    private int currentFloor = 0;
    //private ArrayList<Person> persons = new ArrayList<>()...

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    //metodo getInstance del dp Singleton per Ascensore
    public static Ascensore getAscensore() {
        if(ascensore == null){
            ascensore = new Ascensore();
        }
        return ascensore;
    }
}
