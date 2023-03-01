package com.example.marokkanischbernessen.db.entity;

import java.util.Objects;

@androidx.room.Entity(tableName = "menus")
public class Menu extends Entite {
    //FIELD
    final int point;
    final float prix;
    final String info;

    //CONSTRUCTOR
    public Menu(String nom, String discription, float prix, int point, int idPic, String info, String nomPic) {
        super(nom,discription,idPic,nomPic);

        this.prix = prix;
        this.point = point;
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
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Menu menu = (Menu) o;
        return point == menu.point && Float.compare(menu.prix, prix) == 0 && Objects.equals(info, menu.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), point, prix, info);
    }
}
