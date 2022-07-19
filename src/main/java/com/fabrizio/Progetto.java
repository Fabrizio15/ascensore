package com.fabrizio;

/**
 * Hello world!
 */
public class Progetto   
{
    public static void main( String[] args )
    {
       // System.out.println( "Hello World!" );
        Persona p = new Persona();
        p.setName("Giovanni").setWeight(70);

        Ascensore a1 = Ascensore.getAscensore();

        //System.out.println("nome: " + p.getName() + "\n peso: " + p.getWeight());

        Piano pi1 = new Piano(1, 0);
      //  Piano pi2 = pi1.clone();
      //  pi2.setNumFloor(2);
      //  Piano pi3 = new Piano(3, 4);
        
      //  System.out.println("piano p1 :" + pi1.getNumFloor() + "\npiano pi2 :" + pi2.getNumFloor());
        
        Mediatore costruzione = new Mediatore(p, a1, pi1);
        p.setMediatore(costruzione);
        
        costruzione.creaPalazzo();

        costruzione.stampaPalazzo();

        p.setPianoDestinazione(7);
        System.out.println("nome: " + p.getName() + "\n peso: " + p.getWeight());
        
        System.out.println("sto chiamando l'ascensore");
        a1.stampaAscensore();
        p.chiama();

        a1.stampaAscensore();

    }
    
}