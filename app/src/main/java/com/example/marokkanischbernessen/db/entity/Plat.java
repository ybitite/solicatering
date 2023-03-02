package com.example.marokkanischbernessen.db.entity;

import androidx.room.Entity;

import java.util.Objects;

@Entity(tableName = "plats")
public class Plat extends Entite {

    //FIELDs
    final int point;
    final int degureEpice;
    final int tempsEnJour;
    final float prix;
    final String type;
    final String vegui;

    //CONSTRUCTOR
    public Plat(String nom, String discription, int point, float prix, String type, String vegui,
                int degureEpice, int tempsEnJour, String nomPic, int idPic) {
        super(nom,discription,idPic,nomPic);
        this.point = point;
        this.degureEpice = degureEpice;
        this.prix = prix;
        this.type = type;
        this.tempsEnJour = tempsEnJour;
        this.vegui = vegui;
    }

    //PROPRIETY
    public int getPoint() {
        return point;
    }

    public int getDegureEpice() {
        return degureEpice;
    }

    public float getPrix() {
        return prix;
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

    //OVERRIDE METHODE EQUALS TO COMPARE OBJECT
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plat plat = (Plat) o;
        return point == plat.point && degureEpice == plat.degureEpice && tempsEnJour == plat.tempsEnJour && Float.compare(plat.prix, prix) == 0 && Objects.equals(type, plat.type) && Objects.equals(vegui, plat.vegui);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, degureEpice, tempsEnJour, prix, type, vegui);
    }
}
