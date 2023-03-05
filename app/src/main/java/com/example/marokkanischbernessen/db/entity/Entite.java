package com.example.marokkanischbernessen.db.entity;

import androidx.room.PrimaryKey;

import java.util.Objects;

public class Entite {

    //FIELDs
    @PrimaryKey(autoGenerate = true)
    public int id;
    String nom;
    String discription;
    int idPic;
    String nomPic;

    //CONSTRUCTOR
    public Entite(){

    }

    public Entite(String nom, String discription, int idPic, String nomPic) {
        this.nom = nom;
        this.discription=discription;
        this.idPic = idPic;
        this.nomPic = nomPic;
    }

    //PROPRIETY
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNomPic() {
        return nomPic;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setIdPic(int idPic) {
        this.idPic = idPic;
    }

    public void setNomPic(String nomPic) {
        this.nomPic = nomPic;
    }

    //OVERRIDE METHODE EQUALS TO COMPARE OBJECT
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entite entity = (Entite) o;
        return id == entity.id && idPic == entity.idPic && Objects.equals(nom, entity.nom) && Objects.equals(nomPic, entity.nomPic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, idPic, nomPic);
    }
}
