package com.example.marokkanischbernessen.db.entity;


import androidx.room.Entity;


import java.util.Objects;

@Entity(tableName = "categories")
public class Categorie extends Entite {
    //FIELDs
    final int point;

    //CONTRUCTOR
    public Categorie(String nom, String discription, int point, int idPic, String nomPic) {
        super(nom,discription,idPic,nomPic);
        this.point = point;
    }

    //PROPRIETY
    public int getPoint() {
        return point;
    }

    //OVERRIDE METHODE EQUALS TO COMPARE OBJECT
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categorie categorie = (Categorie) o;
        return point == categorie.point;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}
