package com.example.marokkanischbernessen.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "menus")
public class Menu {
    //FIELD
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    final int point;
    final int idPic;
    final float prix;
    final String nom;
    final String discription;
    final String info;
    final String nomPic;

    //CONSTRUCTOR
    @Ignore
    public Menu() {
        this.id = 0;
        this.point = 0;
        this.idPic = 0;
        this.prix = 0f;
        this.nom = "";
        this.discription = "";
        this.info = "";
        this.nomPic = "";
    }

    public Menu(String nom, String discription, float prix, int point, int idPic, String info, String nomPic) {
        this.nom = nom;
        this.discription = discription;
        this.prix = prix;
        this.point = point;
        this.idPic = idPic;
        this.info = info;
        this.nomPic = nomPic;
    }

    //PROPRIETY
    public float getPrix() {
        return prix;
    }

    public int getPoint() {
        return point;
    }

    public String getNom() {
        return nom;
    }

    public String getDiscription() {
        return discription;
    }

    public int getIdPic() {
        return idPic;
    }

    public String getInfo() {
        return info;
    }

    public String getNomPic() {
        return nomPic;
    }

    //OVERRIDE METHODE EQUALS TO COMPARE OBJECT

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        Menu menu = (Menu) o;
        return point == menu.point && idPic == menu.idPic && Float.compare(menu.prix, prix) == 0 && Objects.equals(nom, menu.nom) && Objects.equals(discription, menu.discription) && Objects.equals(info, menu.info) && Objects.equals(nomPic, menu.nomPic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, idPic, prix, nom, discription, info, nomPic);
    }
}
