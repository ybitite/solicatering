package com.example.marokkanischbernessen.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

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

    //Override equal and hash methode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticlePanier)) return false;
        ArticlePanier that = (ArticlePanier) o;
        return id == that.id && idPlats == that.idPlats && idPanier == that.idPanier && nombrePlat == that.nombrePlat && pointPlat == that.pointPlat && idPic == that.idPic && Objects.equals(nomPlat, that.nomPlat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPlats, idPanier, nombrePlat, pointPlat, idPic, nomPlat);
    }
}
