package com.fabrizio;

import java.util.ArrayList;

public class Mediatore {
    private Persona persona;
    private Ascensore ascensore;
    private Piano piano;
    private ArrayList<Piano> palazzo = new ArrayList<>();

    public Mediatore(Persona persona, Ascensore ascensore, Piano piano){
        this.persona = persona;
        this.ascensore = Ascensore.getAscensore();
        this.piano = piano;
    }

    public void creaPalazzo(){
        Piano x;
        for(int i = 1; i <= 10; i++){
            x = piano.clone();
            x.setNumFloor(i);
            palazzo.add(x);
        }
    }

    //get e set persona
    public Persona getPersona() {
        return persona;
    }
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    //get ascensore (classe singleton)
    public Ascensore getAscensore() {
        return ascensore;
    }

    public void chiama(Persona persona){
        this.persona = persona;
        if(persona.getPianoCorrente() == ascensore.getCurrentFloor()){
            entra(persona);
        }
        else{
            ascensore.vaiAlPiano(persona.getPianoCorrente());
            entra(persona);
        }
        
        //dovrai vedere il piano della persona che chiama
    }

    public void entra(Persona persona){
        //prima devi vedere il peso della persona
        //e verificare che sia minore del peso max dell'ascensore
        if(ascensore.getCurrentWeight() + persona.getWeight() < ascensore.getMaxWeight()){
            ascensore.vaiAlPiano(persona.getPianoDestinazione());
            persona.setPianoCorrente(persona.getPianoDestinazione());
            persona.setPianoDestinazione(0);
        }
        //poi devi chiedere alla persona il piano dove vuole andare
        //e aggiungerlo alla lista di piani da visitare
    }




    public void stampaPalazzo(){
        for(Piano x : palazzo){
            System.out.println("questo è un singolo piano: " + x.getNumFloor());
        }
    }
}
