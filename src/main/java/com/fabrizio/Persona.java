package com.fabrizio;

//import java.util.ArrayList;

public class Persona {
    private Mediatore mediatore;
    private String name = "";
    private int peso = 0;
    //private ArrayList<Integer> keys = new ArrayList<>();
    private int pianoCorrente = 1;
    private int pianoDestinazione = 0;

    public Persona(){
    }

    public Persona(String name, int peso, int pianoCorrente, Mediatore mediatore) {
        this.name = name;
        this.peso = peso;
        this.pianoCorrente = pianoCorrente;
        this.mediatore = mediatore;
    }

    //get e set peso
    public int getpeso() {
        return peso;
    }
    public Persona setpeso(int peso) {
        this.peso = peso;
        return this;
    }

    //get e set nome
    public String getName() {
        return name;
    }
    public Persona setName(String name) {
        this.name = name;
        return this;
    }

    //get e set pianoDestinazione
    public int getPianoDestinazione() {
        return pianoDestinazione;
    }
    public void setPianoDestinazione(int pianoDestinazione) {
        this.pianoDestinazione = pianoDestinazione;
    }

    //get e set pianoCorrente
    public int getPianoCorrente() {
        return pianoCorrente;
    }
    public void setPianoCorrente(int pianoCorrente) {
        this.pianoCorrente = pianoCorrente;
    }

    //get e set mediatore
    public Mediatore getMediatore() {
        return mediatore;
    }
    public void setMediatore(Mediatore mediatore) {
        this.mediatore = mediatore;
    }

    //il seguente metodo da a una persona una chiave per un dererminato piano
    /* 
    public void add(int key){
        if(keys.contains(key)){
            System.out.println("la chiave è già presente");
        }
        else
            keys.add(key);
    }
    */
    //metodo che chiama l'ascensore
    public void chiama(){
        mediatore.chiama(this);
    }
    public void entra(){
        mediatore.entra(this);
    }

}
