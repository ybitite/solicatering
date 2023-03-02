package com.example.marokkanischbernessen.db.entity;

import java.util.Objects;

@androidx.room.Entity(tableName = "evenements")
public class Evenement extends Entite {
    //FIELDs
    final int nb;
    final String date;

    //CONSTRUCTOR
    public Evenement(String nom, String discription , String date, int nb, int idPic, String nomPic) {
        super(nom,discription,idPic,nomPic);
        this.date = date;
        this.nb = nb;
    }

    //PROPRIETY
    public int getNb() {
        return nb;
    }

    public String getDate() {
        return date;
    }

    //OVERRIDE METHODE EQUALS TO COMPARE OBJECT
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evenement evenement = (Evenement) o;
        return nb == evenement.nb && Objects.equals(date, evenement.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nb, date);
    }
}


