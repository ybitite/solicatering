package com.example.marokkanischbernessen.db.entity;

public class Conteur {
    //FIELD
    final String name;
    final int ptDepart;
    final int ptReste;

    //CONSTRUCTOR
    public Conteur(String name, int ptDepart, int ptReste) {
        this.name = name;
        this.ptDepart = ptDepart;
        this.ptReste = ptReste;
    }

    //PROPRIETY
    public String getName() {
        return name;
    }

    public int getpDepart() {
        return ptDepart;
    }

    public int getpReste() {
        return ptReste;
    }

    public String getNameFormat() {
        return name + " (" + ptDepart + ")P";
    }

    public String getPtRestFormat() {
        return ptReste + " P";

    }
}
