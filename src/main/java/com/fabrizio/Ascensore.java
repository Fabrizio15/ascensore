package com.fabrizio;

import java.util.ArrayList;

public class Ascensore {
   
    private static Ascensore ascensore = null;
    private int maxWeight = 0;
    private int currentFloor = 0;
    private ArrayList<Persona> persone = new ArrayList<>();

    //costruttore privato di ascensore
    private Ascensore(int maxWeight){
        this.maxWeight = maxWeight;
    }

    //get pesoCorrente
    public int getCurrentWeight() {
        int peso = 0;
        for(Persona x : persone){
            peso += x.getWeight();
        }
        return peso;
    }

    //get e set pianoCorrente
    public int getCurrentFloor() {
        return currentFloor;
    }
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
    //get e set pesoMax
    public int getMaxWeight() {
        return maxWeight;
    }
    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    //metodo getInstance del dp Singleton per Ascensore
    //di default il peso massimo dell'ascensore è 300
    public static Ascensore getAscensore() {
        if(ascensore == null){
            ascensore = new Ascensore(300);
        }
        return ascensore;
    }


    //vai al piano destinazione
    public void vaiAlPiano(int destinazione){
        if(getCurrentFloor() < destinazione){
            //deve salire
            setCurrentFloor(destinazione);
        }
        else{
            //deve scendere
            setCurrentFloor(destinazione);
        }
    }

    public void stampaAscensore(){
        System.out.println("piano corrente dell'ascensore:" + getCurrentFloor());
        if(persone != null){
            System.out.println("le persone dentro sono:");
            for(Persona x : persone){
                System.out.println(x.getName());
            }
        }
        else
            System.out.println("l'ascensore non contiene alcuna persona");
    }
}
