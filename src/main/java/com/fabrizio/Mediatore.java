package com.fabrizio;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import javax.lang.model.element.Element;

import com.fabrizio.Ascensore.sensoDiMarcia;
import com.fabrizio.Ascensore.stato;



public class Mediatore {
    private Ascensore ascensore;
    private Piano piano;
    private ArrayList<Piano> palazzo = new ArrayList<>();
    private int numeroDiPiani;

    private ArrayList<Integer> pianiDaVisitare = new ArrayList<>(); 
    private ArrayList<Persona> inAttesa = new ArrayList<>();





    public Mediatore(Ascensore ascensore, Piano piano){
        this.ascensore = Ascensore.getAscensore();
        this.piano = piano;
    }


    private<T> ArrayList<T> listcopy(ArrayList<T> list){
        ArrayList<T> res = new ArrayList<T>();
        for(T element : list)
            res.add(element);
        return res;
    }


    //crea un palazzo di un certo numero di piani
    public void creaPalazzo(int numeropiani){
        setNumeroDiPiani(numeropiani);
        Piano x;
        for(int i = 1; i <= numeropiani; i++){
            x = piano.clone();
            x.setNumFloor(i);
            palazzo.add(x);
        }
    }


    //get ascensore (classe singleton)
    public Ascensore getAscensore() {
        return ascensore;
    }


    public void parti(){
        while(pianiDaVisitare.size()!=0){

            //try {
                viaggia();
            //}catch(Exception e){return;}; 

        //    System.out.println("sono arrivato dopo viaggia");
            ascensore.svuota();
        //    System.out.println("sono arrivato dopo ascensore.svuota");
            gestisciAttese();
        //    System.out.println("sono arrivato dopo gestisci attese");

        }
    }


    //
    public void gestisciAttese(){
    //    System.out.println("sono nel metodo gestisci attese");
        ArrayList<Persona> copia = listcopy(inAttesa); 
        for(Persona x : copia){
    //        System.out.println("sono nel for di gestisci attese");
    //        System.out.println("sto per effettuare l'if con la persona: " + x.getName());
            if (x.getPianoCorrente() == ascensore.getCurrentFloor())
                x.chiama();
        }
    //    System.out.println("sono alla fine di gestisci attese");
    }


    //chiede alla persona di inserire il piano dove vuole andare o se gia lo ha lo aggiunge alla lista dei piani da visitare
    public void chiedi(Persona persona){
        Scanner scanner = new Scanner(System.in);
        int destinazione = 0;
        if(persona.getPianoDestinazione() == 0){
            while(destinazione <= 0 && destinazione > numeroDiPiani){
                //chiedi di inserire un piano
                System.out.println(persona.getName() + "inserisci il piano desiderato:");

                String input = scanner.nextLine();
                destinazione = Integer.parseInt(input);
            }
            persona.setPianoDestinazione(destinazione);
            //devi aggiungere il piano alla lista 
            aggiungiPianoAllaLista(persona.getPianoDestinazione());
        }
        //nel caso in cui il piano è già sbagliato TODO vedi se questo caso esiste
        else if(persona.getPianoDestinazione() <= 0 || persona.getPianoDestinazione() > numeroDiPiani){
            persona.setPianoDestinazione(0);
            chiedi(persona);
        }
        else{
            //devi aggiungere il piano alla lista
            aggiungiPianoAllaLista(persona.getPianoDestinazione());
        } 
    }



// quando una persona chiama l'ascensore da qualsiasi piano
// vede se è nello stesso piano in caso chiama chiama chiedi ed entra
// altrimenti mette la persona in attesa

    public void chiama(Persona persona){
        if(persona.getPianoCorrente() == ascensore.getCurrentFloor()){
            entra(persona);
            chiedi(persona);
        }
        else{
            inAttesa.add(persona);
            //ascensore.vaiAlPiano(persona.getPianoCorrente());
            //entra(persona);

            //devi mettere il piano della persona nella lista dei piano da visitare
            aggiungiPianoAllaLista(persona.getPianoCorrente());
        }
        
        //dovrai vedere il piano della persona che chiama
    }

    public void entra(Persona persona){
        //prima devi vedere il peso della persona
        //e verificare che sia minore del peso max dell'ascensore
        if(ascensore.getCurrentpeso() + persona.getpeso() <= ascensore.getMaxpeso()){
            ascensore.faiSalire(persona);
            if(inAttesa.contains(persona))
                inAttesa.remove(persona);
            //ascensore.stampaAscensore();
             //in questo punto dovrai chiedere per la chiave se il piano la richiede
        }
        else{
            if(!inAttesa.contains(persona))
                inAttesa.add(persona);
        }
        //poi devi chiedere alla persona il piano dove vuole andare
        //e aggiungerlo alla lista di piani da visitare
    }


    //vede se ci sono piani da visitare disponibili
    //in caso ci va oppure cambia direzione e ci va
    public void viaggia(){
        if(pianiDaVisitare.size() == 0){
            return;
        }

        for(int x : pianiDaVisitare){
            System.out.print(x + " ");
        }
        System.out.println();

        Optional<Integer> prossimoPiano;
        
        if(ascensore.getSensoDiMarcia() == Ascensore.sensoDiMarcia.sopra){
            prossimoPiano = pianiDaVisitare.stream()
                            .filter(x -> x > ascensore.getCurrentFloor())
                            .min((i,j)->i.compareTo(j));
                            
        }
        else{
            prossimoPiano = pianiDaVisitare.stream()
                            .filter(x -> x < ascensore.getCurrentFloor())
                            .max((i,j)->i.compareTo(j));
        }

        if(prossimoPiano.isPresent() == false){
            if(ascensore.getSensoDiMarcia() == Ascensore.sensoDiMarcia.sopra)
                ascensore.setSensoDiMarcia(sensoDiMarcia.sotto);
            else
                ascensore.setSensoDiMarcia(sensoDiMarcia.sopra);
            viaggia();
            //throw new Exception("raggiunto top");
            return;
        }

        //fai viaggiare l'ascensore
        ascensore.vaiAlPiano(prossimoPiano.get());
        pianiDaVisitare.remove(Integer.valueOf(prossimoPiano.get()));
    }



// aggiunge un piano alla lista
    public void aggiungiPianoAllaLista(int piano){
        if(piano == 0)
            return;
        if(pianiDaVisitare.contains(piano))
            return;
        pianiDaVisitare.add(piano);
        pianiDaVisitare.sort(Integer::compare);
    }




    public int getNumeroDiPiani() {
        return numeroDiPiani;
    }
    public void setNumeroDiPiani(int numeroDiPiani) {
        this.numeroDiPiani = numeroDiPiani;
    }


    public void stampaPalazzo(){
        for(Piano x : palazzo){
            System.out.println("questo è il piano numero: " + x.getNumFloor());
        }
    }
}
