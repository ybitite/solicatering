package com.example.marokkanischbernessen.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "articlepaniers")
public class ArticlePanier {
    //FIELD
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    final int idPlats;
    final int idPanier;
    final int nombrePlat;
    final int pointPlat;
    final int idPic;
    final String nomPlat;

    //CONSTRUCTOR
    public ArticlePanier(int idPanier, int idPlats, int nombrePlat, int pointPlat, String nomPlat, int idPic) {
        this.idPanier = idPanier;
        this.idPlats = idPlats;
        this.nombrePlat = nombrePlat;
        this.pointPlat = pointPlat;
        this.nomPlat = nomPlat;
        this.idPic = idPic;
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

    public int getPointPlat() {
        return pointPlat;
    }

    public String getNomPlat() {
        return nomPlat;
    }

    public int getIdPic() {
        return idPic;
    }

    //to return string format for ui control
    public String getPointPlatFormat() {
        return pointPlat + " points";
    }

    public String getNombrePlatFormat() {
        return String.valueOf(nombrePlat);
    }
}
