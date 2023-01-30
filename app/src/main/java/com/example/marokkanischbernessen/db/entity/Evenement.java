package com.example.marokkanischbernessen.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "evenements")
public class Evenement {
    //FIELD
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    final int nb;
    final int idPic;
    final String nom;
    final String date;
    final String nomPic;

    //CONSTRUCTOR
    public Evenement(String nom, String date, int nb, int idPic, String nomPic) {
        this.nom = nom;
        this.date = date;
        this.nb = nb;
        this.idPic = idPic;
        this.nomPic = nomPic;
    }

    //PROPRIETY
    public int getIdPic() {

        return idPic;
    }

    public int getNb() {
        return nb;
    }

    public String getNom() {
        return nom;
    }

    public String getDate() {
        return date;
    }

    public String getNomPic() {
        return nomPic;
    }

    //OVERRIDE METHODE EQUALS TO COMPARE OBJECT

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evenement)) return false;
        Evenement evenement = (Evenement) o;
        return nb == evenement.nb && idPic == evenement.idPic && Objects.equals(nom, evenement.nom) && Objects.equals(date, evenement.date) && Objects.equals(nomPic, evenement.nomPic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nb, idPic, nom, date, nomPic);
    }
}


