package com.fabrizio;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.fabrizio.Ascensore.sensoDiMarcia;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private Controller controllore;
    private View view;
    private Mediatore mediatore;

    @Before 
    public void init(){
        controllore = new Controller();
        view = new View();
        mediatore = new Mediatore(view, controllore);
        view.setMediatore(mediatore);
        controllore.setMediatore(mediatore);
        controllore.setView(view);
        mediatore.creaPalazzo(10);
    }
    /**
     * Rigorous Test :-)
     */

    @Test
    public void testaPesoAscensore(){
        Persona persona1 = new Persona("test1",60,1,mediatore);
        Persona persona2 = new Persona("test2",50,1,mediatore);
        mediatore.aggiungiPersona(persona1);
        mediatore.aggiungiPersona(persona2);

        Ascensore ascensore = Ascensore.getAscensore();

        ascensore.faiSalire(persona1);
        ascensore.faiSalire(persona2);

        int pesoAscensore = ascensore.getCurrentpeso();

        ascensore.faiScendere(persona1);
        ascensore.faiScendere(persona2);


        Persona persona3 = new Persona("test1",80,1,mediatore);
        Persona persona4 = new Persona("test2",30,1,mediatore);

        ascensore.faiSalire(persona3);
        ascensore.faiSalire(persona4);
        
        int pesoAscensore2 = ascensore.getCurrentpeso();

        assertTrue(pesoAscensore == pesoAscensore2);
        assertTrue(pesoAscensore == 110);
    }

    @Test
    public void testaViaggioPersona(){
        Persona persona1 = new Persona("test1",60,5,mediatore);
        Ascensore ascensore = Ascensore.getAscensore();

        persona1.setPianoDestinazione(8);

        persona1.chiama();

        mediatore.parti();

        assertTrue(persona1.getPianoCorrente() == 8);
        assertTrue(ascensore.getCurrentFloor() == 8);
        assertTrue(persona1.getPianoDestinazione() == 0);
        assertTrue(mediatore.getNumeroDiPiani() == 10);
    }

    @Test
    public void testaPersoneNelPalazzo(){
        Persona persona1 = new Persona("test1",60,2,mediatore);
        Persona persona2 = new Persona("test2",70,3,mediatore);
        Persona persona3 = new Persona("test3",80,4,mediatore);

        mediatore.aggiungiPersona(persona1);
        mediatore.aggiungiPersona(persona2);
        mediatore.aggiungiPersona(persona3);

        mediatore.rimuoviPersona("test1");

        assertTrue(!mediatore.getPersoneNelPalazzo().contains(persona1));
        assertTrue(mediatore.getPersoneNelPalazzo().contains(persona2));
        assertTrue(mediatore.getPersoneNelPalazzo().contains(persona3));
        assertTrue(mediatore.rimuoviPersona("test2"));
        assertTrue(!mediatore.rimuoviPersona("test2"));
    }

    @Test
    public void testaViaggia(){
        Persona persona1 = new Persona("test1",60,2,mediatore);
        Persona persona2 = new Persona("test2",70,3,mediatore);
        Persona persona3 = new Persona("test3",80,4,mediatore);

        Ascensore ascensore = Ascensore.getAscensore();
        ascensore.setCurrentFloor(1);

        mediatore.aggiungiPersona(persona1);
        mediatore.aggiungiPersona(persona2);
        mediatore.aggiungiPersona(persona3);

        persona1.setPianoDestinazione(8);
        persona2.setPianoDestinazione(2);

        persona1.chiama();
        persona2.chiama();

        mediatore.parti();

        assertTrue(ascensore.getSensoDiMarcia() == sensoDiMarcia.scendendo);
        assertTrue(persona1.getPianoCorrente() == 8);
        assertTrue(persona2.getPianoCorrente() == 2);
        assertTrue(ascensore.getCurrentFloor() == 2);
    }

    @Test
    public void testViaggiaN2(){
        Persona persona1 = new Persona("test1",60,2,mediatore);
        Persona persona2 = new Persona("test2",70,3,mediatore);
        Persona persona3 = new Persona("test3",80,4,mediatore);

        Ascensore ascensore = Ascensore.getAscensore();
        ascensore.setCurrentFloor(1);

        mediatore.aggiungiPersona(persona1);
        mediatore.aggiungiPersona(persona2);
        mediatore.aggiungiPersona(persona3);

        persona1.setPianoDestinazione(8);
        persona2.setPianoDestinazione(2);

        persona1.chiama();
        persona2.chiama();

        mediatore.parti();

        persona1.setPianoDestinazione(1);
        persona2.setPianoDestinazione(3);
        persona3.setPianoDestinazione(7);
        
        
        persona1.chiama();
        persona2.chiama();
        persona3.chiama();


        mediatore.parti();

        assertTrue(ascensore.getSensoDiMarcia() == sensoDiMarcia.scendendo);
        assertTrue(ascensore.getCurrentFloor() == 1);
        assertTrue(ascensore.getPersone().size() == 0);
        assertTrue(mediatore.getInAttesa().size() == 0);
    }

    
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    
}
