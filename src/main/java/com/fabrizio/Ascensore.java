package com.fabrizio;

import java.util.ArrayList;

public class Ascensore {
   
    private static Ascensore ascensore = null;
    private int maxpeso = 0;
    private int currentFloor = 10;
    private ArrayList<Persona> persone = new ArrayList<>();
    private stato stato;
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
        //System.out.println("sono in fai salire, sto inserendo una persona");
        //ascensore.stampaAscensore();
        persone.add(persona);
        System.out.println("sono in fai salire, ho inserito una persona: " + persona.getName());
        //ascensore.stampaAscensore();
    }
    public void faiScendere(Persona persona){
        //System.out.println("sono in fai scendere, sto eliminando una persona");
        //ascensore.stampaAscensore();
        persone.remove(persona);
        System.out.println("sono in fai scendere, ho eliminato una persona:" + persona.getName());
        //ascensore.stampaAscensore();
    }

    //butta fuori dall'ascensore tutte le persone che sono arrivate
    public void svuota(){
        ArrayList<Persona> copia = listcopy(persone);
        for(Persona x : copia){
            if(x.getPianoDestinazione() == getCurrentFloor()){

                //System.out.println("SONO NEL FOR CHE CI INTERESSA IN SVUOTA");

                faiScendere(x);
                x.setPianoCorrente(getCurrentFloor());
                x.setPianoDestinazione(0);
               // System.out.println("la persona dopo svuota: ");
               // x.stampaPersona();
            }
        }
    }


    //vai al piano destinazione
    public void vaiAlPiano(int destinazione){
        setCurrentFloor(destinazione);
        spostaPersone();
    }

    public enum sensoDiMarcia{
        sopra,sotto
    }
    // get e set sensodimarcia
    public sensoDiMarcia getSensoDiMarcia() {
        return sensoDiMarcia;
    }

    public void setSensoDiMarcia(sensoDiMarcia sensoDiMarcia) {
        this.sensoDiMarcia = sensoDiMarcia;
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

    void spostaPersone(){
        for(Persona x : persone){
            x.setPianoCorrente(getCurrentFloor());
        }
    }

    public void stampaAscensore(){
        System.out.println("*************************************************");
        System.out.println("piano corrente dell'ascensore:" + getCurrentFloor() + "\t stato corrente dell'asccensore: " + getStato());
        if(persone.size() != 0){
            System.out.println("le persone dentro sono:\n");
            for(Persona x : persone){
                x.stampaPersona();
            }

        }
        else
            System.out.println("l'ascensore non contiene alcuna persona");
        System.out.println("*************************************************");
    }
}
