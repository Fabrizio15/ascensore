package com.fabrizio;

public class Piano implements Cloneable{
    private int numFloor = 1;
    //private int key = 0;


    //costruttore con numeroPiano e chiave
    public Piano(int numFloor){
        this.numFloor = numFloor;
        //this.key = key;
    };

    //metodo clone per piano
    public Piano clone(){
        return new Piano(numFloor);
    }

    //get e set chiave
    /* 
    public int getKey() {
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }
    */

    //get e set numero di piano
    public int getNumFloor() {
        return numFloor;
    }
    public void setNumFloor(int numFloor) {
        this.numFloor = numFloor;
    }
}
