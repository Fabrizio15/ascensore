package com.fabrizio;

import java.util.Scanner;


public class Controller {
    
    private Mediatore mediatore;
    private View view;
    private Scanner input = new Scanner(System.in);


    public Controller(){
    }

// chiede un input da tastiera di tipo intero
    public int chiediInput(String richiesta, int min, int max){
        System.out.println(richiesta + ":");
        String ans = input.nextLine();
        int res = 0;
        try{
            res = Integer.parseInt(ans);
        }catch (NumberFormatException e){
            System.out.println("il valore inserito non è un numero");
            return chiediInput(richiesta, min, max);
        }
        if(res < min || res > max){
            System.out.println("inserire un numero valido (compreso tra " + min + " e " + max + ")");
            return chiediInput(richiesta, min, max);
        }
        return res;
    }


    public void setMediatore(Mediatore mediatore) {
        this.mediatore = mediatore;
    }

    public void setView(View view) {
        this.view = view;
    }

// eventi possibili
    public boolean handleEvent(int x){
        switch(x){
            case 1:
                if(mediatore.getNumeroDiPiani() == 0){
                    int numeroPiani = chiediInput("inserire numero piani", 1, 100);
                    mediatore.creaPalazzo(numeroPiani);
                }
                else 
                    System.out.println("il palazzo è stato già inizializzato");
                break;
            case 2:
                if(mediatore.getNumeroDiPiani() == 0){
                    System.out.println("il palazzo non è ancora inizializzato");
                    break;
                }
                String nome = chiediStringa("inserisci il nome della persona:");
                int peso = chiediInput("inserire il peso della persona", 1, 300);
                int pianoCorrente = chiediInput("inserisci il piano corrente della persona", 1, mediatore.getNumeroDiPiani());
                if(mediatore.aggiungiPersona(new Persona(nome, peso,pianoCorrente,mediatore)))
                    System.out.println("la persona è stata aggiunta al palazzo");
                else 
                    System.out.println("esiste già una persona con lo stesso nome");
                break;
            case 3:
                String a = chiediStringa("quale persona vuoi eliminare dal palazzo");
                if(mediatore.rimuoviPersona(a))
                    System.out.println("persona rimossa");
                else
                    System.out.println("non è presente la persona " + a + " nel palazzo");
                break;
            case 4:
                view.stampaAscensore(mediatore.getAscensore());
                break;
            //persone nel palazzo
            case 5:
                for(Persona persona : mediatore.getPersoneNelPalazzo())
                    view.stampaPersona(persona);
                break;
            case 6:
                String b = chiediStringa("quale persona vuole chiamare l'ascensore?");
                if(mediatore.richiedi(b))
                    System.out.println("la persona: " + b + " ha chiamato l'ascensore");
                else 
                    System.out.println("la persona non è presente nel palazzo");
                break;
            case 7:
                mediatore.parti();
                break;
            case 8:
                return false;
        }
        return true;
    }
    
    
    public boolean nextOperation(){
        view.stampaMenu();
        int ans = chiediInput("", 1, 8);
        return handleEvent(ans);
    }


    public String chiediStringa(String richiesta){
        System.out.println(richiesta + ":");
        String ans = input.nextLine();
        if(ans == ""){
            System.out.println("inserisci un nome non vuoto per la persona");
            return chiediStringa(richiesta);
        }
        return ans;
    }
}
