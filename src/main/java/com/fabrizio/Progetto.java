package com.fabrizio;

public class Progetto   
{
    public static void main( String[] args ){

        Ascensore a1 = Ascensore.getAscensore();
        Piano pi1 = new Piano(1, 0);

        Mediatore costruzione = new Mediatore(a1, pi1);
        
        costruzione.creaPalazzo(10);
        costruzione.stampaPalazzo();

        Persona a = new Persona("Giacomo",60,3);
        Persona b = new Persona("Giulio",50,7);
        Persona c = new Persona("Fabrizio",90,1);
        Persona d = new Persona("Giovanni",60,1);

        a.setMediatore(costruzione);
        b.setMediatore(costruzione);
        c.setMediatore(costruzione);
     //   d.setMediatore(costruzione);

        a.setPianoDestinazione(2);
        b.setPianoDestinazione(2);
        c.setPianoDestinazione(2);
      //  d.setPianoDestinazione(5);

        a.chiama();
        b.chiama();
        c.chiama();
      //  d.chiama();

        System.out.println("stato iniziale:");
        a1.stampaAscensore();
        costruzione.parti();

        System.out.println("stato finale: ");
        a1.stampaAscensore();

        System.out.println("persone:");

        a.stampaPersona();
        b.stampaPersona();
        c.stampaPersona();
    //    d.stampaPersona();


    }
    
}
