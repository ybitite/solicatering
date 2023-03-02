package com.example.marokkanischbernessen.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "articlepaniers")
public class ArticlePanier {
    //FIELD
    @PrimaryKey(autoGenerate = true)
    public int id;
    final int idPanier;
    final int idPlats;
    final int nombrePlat;


    //CONSTRUCTOR
    public ArticlePanier(int idPanier, int idPlats, int nombrePlat) {
        this.idPanier = idPanier;
        this.idPlats = idPlats;
        this.nombrePlat = nombrePlat;
    }

    //PROPRIETY
    public int getIdPanier() {
        return idPanier;
    }

    public int getIdPlats() {
        return idPlats;
    }

    public int getNombrePlat() {
        return nombrePlat;
    }

    //to return string format for ui control
    public String getNombrePlatFormat() {
        return String.valueOf(nombrePlat);
    }

    //Override equal and hash methode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticlePanier)) return false;
        ArticlePanier that = (ArticlePanier) o;
        return id == that.id && idPlats == that.idPlats && idPanier == that.idPanier && nombrePlat == that.nombrePlat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPlats, idPanier, nombrePlat);
    }
}
