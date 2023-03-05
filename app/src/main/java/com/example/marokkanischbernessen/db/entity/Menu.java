package com.example.marokkanischbernessen.db.entity;

import java.util.Objects;

@androidx.room.Entity(tableName = "menus")
public class Menu extends Entite {
    //FIELD
    private final int point;
    private final float prix;
    private final String info;

    //CONSTRUCTOR
    public Menu(int id, String nom, String discription, int idPic, String nomPic, int point, float prix, String info) {
        super(id, nom, discription, idPic, nomPic);
        this.point = point;
        this.prix = prix;
        this.info = info;
    }

    //PROPRIETY
    public float getPrix() {
        return prix;
    }

    public int getPoint() {
        return point;
    }

    public String getInfo() {
        return info;
    }

    //OVERRIDE METHODE EQUALS TO COMPARE OBJECT
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        if (!super.equals(o)) return false;
        Menu menu = (Menu) o;
        return point == menu.point && Float.compare(menu.prix, prix) == 0 && Objects.equals(info, menu.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), point, prix, info);
    }
}
