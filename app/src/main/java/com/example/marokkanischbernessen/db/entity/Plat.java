package com.example.marokkanischbernessen.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "plats")
public class Plat {
    //FIELD
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    final int point;
    final int degureEpice;
    final int idPic;
    final int tempsEnJour;
    final float prix;
    final String nom;
    final String discription;
    final String type;
    final String vegui;
    final String nomPic;

    //CONSTRUCTOR
    public Plat(String nom, String discription, int point, float prix, String type, String vegui,
                int degureEpice, int tempsEnJour, String nomPic, int idPic) {
        this.point = point;
        this.degureEpice = degureEpice;
        this.idPic = idPic;
        this.nom = nom;
        this.discription = discription;
        this.prix = prix;
        this.type = type;
        this.tempsEnJour = tempsEnJour;
        this.vegui = vegui;
        this.nomPic = nomPic;
    }

    //PROPRIETY
    public int getPoint() {
        return point;
    }

    public int getDegureEpice() {
        return degureEpice;
    }

    public int getIdPic() {
        return idPic;
    }

    public float getPrix() {
        return prix;
    }

    public String getNom() {
        return nom;
    }

    public String getDiscription() {
        return discription;
    }

    public String getType() {
        return type;
    }

    public int tempsEnJour() {
        return tempsEnJour;
    }

    public String getVegui() {
        return vegui;
    }

    public String getNomPic() {
        return nomPic;
    }

    //OVERRIDE METHODE EQUALS TO COMPARE OBJECT

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plat)) return false;
        Plat plat = (Plat) o;
        return point == plat.point && degureEpice == plat.degureEpice && idPic == plat.idPic && tempsEnJour == plat.tempsEnJour && Float.compare(plat.prix, prix) == 0 && Objects.equals(nom, plat.nom) && Objects.equals(discription, plat.discription) && Objects.equals(type, plat.type) && Objects.equals(vegui, plat.vegui) && Objects.equals(nomPic, plat.nomPic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, degureEpice, idPic, tempsEnJour, prix, nom, discription, type, vegui, nomPic);
    }
}
