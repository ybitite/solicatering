package com.example.marokkanischbernessen.db.entity;

import androidx.room.Embedded;
import androidx.room.Entity;

import java.util.Objects;

/**
*  nom prenom et prix sont enregistré directement sur cette table d'historique
* 1 pour iviter une 4eme et 5eme relation lors de la création de liste d 'object pour
* remplir la rv de panier
* 2 a fin de ne pas créer de dépendence dans la table de menu lors de son update
* la minorité de la tail des donnée genéerer
*
* */

@Entity(tableName = "paniers")
public class Panier extends Entite{
    int idClientOwner;
    String nomPrenom;
    int idMenu;
    float prix;
    int etat;
    @Embedded
    InformationLivraison informationLivraison;

    //CONSTRUCTOR
    public Panier() {
        super();
    }
    public Panier(int idPanier, int idClientOwner, String nomPrenom, int etat,
                  int idMenu, float prix, String nomPic, InformationLivraison informationLivraison) {
        super("","",0,nomPic);
        this.id = idPanier;
        this.idClientOwner = idClientOwner;
        this.nomPrenom = nomPrenom;
        this.etat = etat;
        this.idMenu = idMenu;
        this.prix = prix;
        this.informationLivraison=informationLivraison;
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

    public String getTitreCatering() {
        return "SoliCatering du " + informationLivraison.getDateLivr();
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
        return informationLivraison.nombre + " P";
    }

    public String getPrixTotalFormat() {
        return prix * informationLivraison.nombre + " CHF";
    }

    public InformationLivraison getInformationLivraison() {
        return informationLivraison;
    }

    public void setInformationLivraison(InformationLivraison informationLivraison) {
        this.informationLivraison = informationLivraison;
    }

    //override equal and hash methode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Panier)) return false;
        if (!super.equals(o)) return false;
        Panier panier = (Panier) o;
        return idClientOwner == panier.idClientOwner && etat == panier.etat && idMenu == panier.idMenu && Float.compare(panier.prix, prix) == 0 && Objects.equals(nomPrenom, panier.nomPrenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idClientOwner, etat, idMenu, prix, nomPrenom);
    }
}