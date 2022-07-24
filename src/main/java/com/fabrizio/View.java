package com.fabrizio;


public class View{
 
    private Mediatore mediatore;

    public void setMediatore(Mediatore mediatore) {
        this.mediatore = mediatore;
    }


    public View(){
    }


    public void stampaMenu(){
        System.out.println();
        System.out.println("------- Operazioni possibili -------");
        System.out.println("Premi 1 per creare il palazzo");
        System.out.println("Premi 2 per creare una persona da aggiungere al palazzo");
        System.out.println("Premi 3 per rimuovere una persona dal palazzo");
        System.out.println("Premi 4 per stampare lo stato dell'ascensore");
        System.out.println("Premi 5 per stampare le persone nel palazzo");
        System.out.println("Premi 6 per chiamare l'ascensore");
        System.out.println("Premi 7 per eseguire la simulazione");
        System.out.println("Premi 8 per terminare il programma");
        System.out.println();
    }


    //questo metodo stampa i dati di una persona
    public void stampaPersona(Persona persona){
        System.out.println("nome persona: " + persona.getName() + "\t con peso: " + persona.getpeso() + 
                            "\nsi trova nel piano numero: " + persona.getPianoCorrente());
    }


    //questo metodo stampa lo stato attuale dell'ascensore
    public void stampaAscensore(Ascensore ascensore){
        System.out.println("---------- Ascensore ----------");
        System.out.println("piano corrente dell'ascensore:" + ascensore.getCurrentFloor() +
                             "\nstato corrente dell'asccensore: " + ascensore.getSensoDiMarcia());
        if(ascensore.getPersone().size() != 0){
            System.out.println("le persone dentro l'ascensore sono:\n");
            for(Persona x : ascensore.getPersone()){
                stampaPersona(x);
            }

        }
        else
            System.out.println("l'ascensore non contiene alcuna persona");
        System.out.println("-------------------------------");
    }
}
