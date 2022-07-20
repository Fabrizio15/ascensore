package com.fabrizio;

import java.util.ArrayList;

public class Ascensore {
   
    private static Ascensore ascensore = null;
    private int maxpeso = 0;
    private int currentFloor = 0;
    private ArrayList<Persona> persone = new ArrayList<>();
    private stato stato;

    //costruttore privato di ascensore
    private Ascensore(int maxpeso){
        this.maxpeso = maxpeso;
        this.stato = stato.Fermo;
    }

    //get pesoCorrente
    public int getCurrentpeso() {
        int peso = 0;
        for(Persona x : persone){
            peso += x.getpeso();
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
    public int getMaxpeso() {
        return maxpeso;
    }
    public void setMaxpeso(int maxpeso) {
        this.maxpeso = maxpeso;
    }

    //metodo getInstance del dp Singleton per Ascensore
    //di default il peso massimo dell'ascensore è 300
    public static Ascensore getAscensore() {
        if(ascensore == null){
            ascensore = new Ascensore(300);
        }
        return ascensore;
    }

    //metodi che fanno salire e scendere gente nell'ascensore
    public void faiSalire(Persona persona){
        persone.add(persona);
    }
    public void faiScendere(Persona persona){
        persone.remove(persona);
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

    public enum stato{
        Salendo,Scendendo,Fermo
    }

    public stato getStato() {
        return stato;
    }
    public void setStato(stato stato) {
        this.stato = stato;
    }


    public void stampaAscensore(){
        System.out.println("piano corrente dell'ascensore:" + getCurrentFloor() + "\t stato corrente dell'asccensore: " + getStato());
        if(persone.size() != 0){
            System.out.println("le persone dentro sono:");
            for(Persona x : persone){
                x.stampaPersona();
            }
        }
        else
            System.out.println("l'ascensore non contiene alcuna persona");
    }
}
