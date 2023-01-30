package com.example.marokkanischbernessen.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "categories")
public class Categorie {
    //FIELD
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    final int point;
    final int idPic;
    final String nom;
    final String discription;
    final String nomPic;

    //CONTRUCTOR
    public Categorie(String nom, String discription, int point, int idPic, String nomPic) {
        this.nom = nom;
        this.discription = discription;
        this.point = point;
        this.idPic = idPic;
        this.nomPic = nomPic;
    }

    //PROPRIETY
    public int getPoint() {
        return point;
    }

    public int getIdPic() {
        return idPic;
    }

    public String getNom() {
        return nom;
    }

    public String getDiscription() {
        return discription;
    }

    public String getNomPic() {
        return nomPic;
    }

    //OVERRIDE METHODE EQUALS TO COMPARE OBJECT
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categorie)) return false;
        Categorie categorie = (Categorie) o;
        return id == categorie.id && point == categorie.point && idPic == categorie.idPic && Objects.equals(nom, categorie.nom) && Objects.equals(discription, categorie.discription) && Objects.equals(nomPic, categorie.nomPic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, point, idPic, nom, discription, nomPic);
    }
}
