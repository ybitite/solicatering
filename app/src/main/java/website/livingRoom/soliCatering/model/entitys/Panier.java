package website.livingRoom.soliCatering.model.entitys;

import android.content.res.Resources;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.util.Objects;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.utile.AppUtile;

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
    final int idClientOwner;
    final String nomPrenom;
    final int idMenu;
    final float prix;
    final int etat;
    @Embedded
    InformationLivraison informationLivraison;

    //CONSTRUCTOR
    //to keep acces from room

    public Panier(int id, String nom, String discription, int idPic, String nomPic, int idClientOwner, String nomPrenom, int idMenu, float prix, int etat, InformationLivraison informationLivraison) {
        super(id, nom, discription, idPic, nomPic);
        this.idClientOwner = idClientOwner;
        this.nomPrenom = nomPrenom;
        this.idMenu = idMenu;
        this.prix = prix;
        this.etat = etat;
        this.informationLivraison = informationLivraison;
    }

    //to create from object from code
    @Ignore
    public Panier(int idPanier, int idClientOwner, String nomPrenom, int etat, int idMenu, float prix, String nomPic, InformationLivraison informationLivraison) {
        super(idPanier,"","",0,nomPic);
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

    public int getEtat() {
        return etat;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public float getPrix() {
        return prix;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public String getPrixFormat() {
        //get resources from AppUtil
        Resources resources = AppUtile.getResource();
        return prix + " " + resources.getString(R.string.text_devise);
    }

    public String getPrixTotalFormat() {
        //get resources from AppUtil
        Resources resources = AppUtile.getResource();
        return prix * informationLivraison.getNombre() + " " + resources.getString(R.string.text_devise);
    }

    public InformationLivraison getInformationLivraison() {
        return informationLivraison;
    }

    //override equal and hash methode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Panier)) return false;
        if (!super.equals(o)) return false;
        Panier panier = (Panier) o;
        return idClientOwner == panier.idClientOwner && idMenu == panier.idMenu && Float.compare(panier.prix, prix) == 0 && etat == panier.etat && Objects.equals(nomPrenom, panier.nomPrenom) && Objects.equals(informationLivraison, panier.informationLivraison);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idClientOwner, nomPrenom, idMenu, prix, etat, informationLivraison);
    }
}