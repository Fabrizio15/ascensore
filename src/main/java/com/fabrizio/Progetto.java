package com.fabrizio;

public class Progetto   
{
    public static void main( String[] args){
       
       
        Controller controllore = new Controller();
        View view = new View();
        Mediatore mediatore = new Mediatore(view, controllore);
        view.setMediatore(mediatore);
        controllore.setMediatore(mediatore);
        controllore.setView(view);

        while(controllore.nextOperation());
        /* 
        Ascensore a1 = Ascensore.getAscensore();
        Piano pi1 = new Piano(1, 0);

        Mediatore costruzione = new Mediatore(a1, pi1);
        
        costruzione.creaPalazzo(10);
        costruzione.stampaPalazzo();

        Persona a = new Persona("Giacomo",60,7);
        Persona b = new Persona("Giulio",50,3);
        Persona c = new Persona("Fabrizio",30,1);
        Persona d = new Persona("Giovanni",60,9);
        Persona e = new Persona("Bruno",50,1);

        a.setMediatore(costruzione);
        b.setMediatore(costruzione);
        c.setMediatore(costruzione);
        d.setMediatore(costruzione);
        e.setMediatore(costruzione);

        a.setPianoDestinazione(1);
        b.setPianoDestinazione(10);
        c.setPianoDestinazione(2);
        d.setPianoDestinazione(6);
        e.setPianoDestinazione(40);

        a.chiama();
        b.chiama();
        c.chiama();
        d.chiama();
        e.chiama();

        System.out.println("stato iniziale:");
        a1.stampaAscensore();

        costruzione.parti();

        System.out.println("stato finale: ");
        a1.stampaAscensore();

        System.out.println("persone:");

        a.stampaPersona();
        b.stampaPersona();
        c.stampaPersona();
        d.stampaPersona();
        e.stampaPersona();

*/
    }
    
}
