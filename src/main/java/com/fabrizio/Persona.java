package com.fabrizio;

import java.util.ArrayList;

public class Persona {
    private String name = "";
    private int weight = 0;
    private ArrayList<Integer> keys = new ArrayList<>();
    //private 


    public int getWeight() {
        return weight;
    }
    public Persona setWeight(int weight) {
        this.weight = weight;
        return this;
    }
    public String getName() {
        return name;
    }
    public Persona setName(String name) {
        this.name = name;
        return this;
    }

    //il seguente metodo da a una persona una chiave per un dererminato piano
    public void add(int key){
        if(keys.contains(key)){
            System.out.println("la chiave è già presente");
            break;
        }
        else
            keys.add(key);
    }

}
