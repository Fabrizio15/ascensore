package com.fabrizio;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import com.fabrizio.Ascensore.sensoDiMarcia;



public class Mediatore {
    private Ascensore ascensore;
    private Piano piano;

    private Controller controller;
    private View view;

    private ArrayList<Piano> palazzo = new ArrayList<>();
    private ArrayList<Persona> personeNelPalazzo = new ArrayList<>();
    private int numeroDiPiani = 0;

    private ArrayList<Integer> pianiDaVisitare = new ArrayList<>(); 
    private ArrayList<Persona> inAttesa = new ArrayList<>();



    public Mediatore(View view, Controller controller){
        ascensore = Ascensore.getAscensore();
        this.piano = new Piano(1, 0);
        this.view = view;
        this.controller = controller;
    }


    public ArrayList<Persona> getPersoneNelPalazzo(){
        return personeNelPalazzo;
    }


    public boolean richiedi(String x){
        for(Persona p : personeNelPalazzo){
            if(p.getName().equals(x)){
                chiama(p);
                return true;
            }
        }
        return false;
    }


    public boolean aggiungiPersona(Persona persona){
        if(!personeNelPalazzo.stream()
                            .map(Persona::getName)
                            .collect(Collectors.toList())
                            .contains(persona.getName())){
            personeNelPalazzo.add(persona);
            return true;
        }
        return false;
    }



    public boolean rimuoviPersona(String nome){
        ArrayList<Persona> copia = listcopy(personeNelPalazzo);
        for(Persona p : copia){
            if(p.getName().equals(nome)){
                personeNelPalazzo.remove(p);
                return true;
            }
        }
        return false;
    }



    //il seguente metodo crea una copia della lista

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
        while(pianiDaVisitare.size()!=0 || inAttesa.size()!=0){
            viaggia();
            ascensore.svuota();
            gestisciAttese();
        }
    }


    public void gestisciAttese(){
        ArrayList<Persona> copia = listcopy(inAttesa); 
        for(Persona x : copia){
                x.chiama();
        }
    }


    //chiede alla persona di inserire il piano dove vuole andare o se gia lo ha lo aggiunge alla lista dei piani da visitare
    public void chiedi(Persona persona){
        Scanner scanner = new Scanner(System.in);
        int destinazione = 0;

        if(persona.getPianoDestinazione() == 0){

            while(destinazione <= 0 || destinazione > numeroDiPiani || destinazione == persona.getPianoCorrente()){
                //chiedi di inserire un piano
                System.out.println(persona.getName() + " inserisci un piano compreso tra 1 e " + numeroDiPiani);

                String input = scanner.nextLine();
                try{
                    destinazione = Integer.parseInt(input);
                }catch(NumberFormatException e){continue;}
            }
            persona.setPianoDestinazione(destinazione);
            //devi aggiungere il piano alla lista 
            aggiungiPianoAllaLista(persona.getPianoDestinazione());
        }
        //nel caso in cui il piano è già sbagliato
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
        if(ascensore.getPersone().contains(persona))
        return;
        if(persona.getPianoCorrente() == ascensore.getCurrentFloor()){
            entra(persona);
            chiedi(persona);
        }
        else{
            if(!inAttesa.contains(persona)){
            //    System.out.println("sto aggiungendo " + persona.getName() + " in lista di attesa");
                inAttesa.add(persona);

            //devi mettere il piano della persona nella lista dei piano da visitare
            }
            aggiungiPianoAllaLista(persona.getPianoCorrente());
        }
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

        Optional<Integer> prossimoPiano;
        
        if(ascensore.getSensoDiMarcia() == Ascensore.sensoDiMarcia.salendo){
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
            if(ascensore.getSensoDiMarcia() == Ascensore.sensoDiMarcia.salendo)
                ascensore.setSensoDiMarcia(sensoDiMarcia.scendendo);
            else
                ascensore.setSensoDiMarcia(sensoDiMarcia.salendo);
            viaggia();
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

/* 
    public void stampaPalazzo(){
        for(Piano x : palazzo){
            System.out.println("questo è il piano numero: " + x.getNumFloor());
        }
    }
*/
}
