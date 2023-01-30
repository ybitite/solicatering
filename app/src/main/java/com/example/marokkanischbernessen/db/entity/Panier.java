package com.example.marokkanischbernessen.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "paniers")
public class Panier extends InformationLivraison {

    //FIELD
    @NonNull
    @PrimaryKey
    public int idPanier;
    int idClientOwner;
    int etat;
    int idMenu;
    float prix;
    int idPic;
    private String nomPrenom;

    @Ignore
    private String titreCatering;

    //CONSTRUCTOR

    public Panier(String dateLivr, String heurLivr, String remarque, short nombre, Adresse adresse,
                  int idPanier, int idClientOwner, String nomPrenom, int etat, int idMenu, float prix, int idPic) {
        super(dateLivr, heurLivr, remarque, nombre, adresse);
        this.idPanier = idPanier;
        this.idClientOwner = idClientOwner;
        this.etat = etat;
        this.idMenu = idMenu;
        this.prix = prix;
        this.idPic = idPic;
        this.nomPrenom = nomPrenom;
        titreCatering = "SoliCatering du " + dateLivr;
    }

    @Ignore/*to create panier on valider button click client view model*/
    public Panier(int idPanier, int idClientOwner, String nomPrenom, int etat,
                  int idMenu, float prix, int idPic, InformationLivraison informationLivraison) {
        super(informationLivraison.dateLivr, informationLivraison.heurLivr,
                informationLivraison.remarque, informationLivraison.nombre, informationLivraison.adresse);
        this.idPanier = idPanier;
        this.idClientOwner = idClientOwner;
        this.nomPrenom = nomPrenom;
        this.etat = etat;
        this.idMenu = idMenu;
        this.prix = prix;
        this.idPic = idPic;
        titreCatering = "SoliCatering du " + dateLivr;
    }
    //PROPRIETY

    public int getIdClientOwner() {
        return idClientOwner;
    }

    public void setIdClientOwner(int idClientOwner) {
        this.idClientOwner = idClientOwner;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getIdPic() {
        return idPic;
    }

    public void setIdPic(int idPic) {
        this.idPic = idPic;
    }

    public String getTitreCatering() {
        return titreCatering;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    //Methode to return  the format correct for the ui control
    public String getPrixFormat() {
        return prix + " CHF";
    }

    public String getNombreFormat() {
        return nombre + " P";
    }

    public String getPrixTotalFormat() {
        return prix * nombre + " CHF";
    }

    //override equal and hash methode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Panier)) return false;
        if (!super.equals(o)) return false;
        Panier panier = (Panier) o;
        return idClientOwner == panier.idClientOwner && etat == panier.etat && idMenu == panier.idMenu && Float.compare(panier.prix, prix) == 0 && idPic == panier.idPic && Objects.equals(nomPrenom, panier.nomPrenom) && Objects.equals(titreCatering, panier.titreCatering);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idClientOwner, etat, idMenu, prix, idPic, nomPrenom, titreCatering);
    }
}