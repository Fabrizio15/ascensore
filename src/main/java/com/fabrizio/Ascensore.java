package com.fabrizio;

import java.util.ArrayList;

public class Ascensore {
   
    private static Ascensore ascensore = null;
    private int maxpeso = 0;
    private int currentFloor = 1;
    private ArrayList<Persona> persone = new ArrayList<>();
    private sensoDiMarcia sensoDiMarcia;



    private<T> ArrayList<T> listcopy(ArrayList<T> list){
        ArrayList<T> res = new ArrayList<T>();
        for(T element : list)
            res.add(element);
        return res;
    }



    //costruttore privato di ascensore
    private Ascensore(int maxpeso){
        this.maxpeso = maxpeso;
        this.sensoDiMarcia = sensoDiMarcia.salendo;
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
        //System.out.println("sono in fai salire, sto inserendo una persona");
        persone.add(persona);
        //System.out.println("sono in fai salire, ho inserito una persona: " + persona.getName());
    }
    public void faiScendere(Persona persona){
        //System.out.println("sono in fai scendere, sto eliminando una persona");
        persone.remove(persona);
        //System.out.println("sono in fai scendere, ho eliminato una persona:" + persona.getName());
    }

    //butta fuori dall'ascensore tutte le persone che sono arrivate
    public void svuota(){
        ArrayList<Persona> copia = listcopy(persone);
        for(Persona x : copia){
            if(x.getPianoDestinazione() == getCurrentFloor()){

                faiScendere(x);
                x.setPianoCorrente(getCurrentFloor());
                x.setPianoDestinazione(0);
            }
        }
    }


    //vai al piano destinazione
    public void vaiAlPiano(int destinazione){
        setCurrentFloor(destinazione);
        spostaPersone();
    }

    public enum sensoDiMarcia{
        salendo,scendendo
    }
    // get e set sensodimarcia
    public sensoDiMarcia getSensoDiMarcia() {
        return sensoDiMarcia;
    }

    public void setSensoDiMarcia(sensoDiMarcia sensoDiMarcia) {
        this.sensoDiMarcia = sensoDiMarcia;
    }

    void spostaPersone(){
        for(Persona x : persone){
            x.setPianoCorrente(getCurrentFloor());
        }
    }

    public ArrayList<Persona> getPersone() {
        return persone;
    }
}
