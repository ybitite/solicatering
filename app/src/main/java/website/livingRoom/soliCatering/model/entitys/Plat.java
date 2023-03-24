package website.livingRoom.soliCatering.model.entitys;

import android.content.res.Resources;

import androidx.room.Entity;

import java.util.Objects;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.utile.AppUtile;

@Entity(tableName = "plats")
public class Plat extends Entite {

    //FIELD
    private final int point;
    private final int degureEpice;
    private final int tempsEnJour;
    private final float prix;
    private final String type;
    private final String vegui;

    //CONSTRUCTOR

    public Plat(int id, String nom, String discription, int idPic, String nomPic, int point, int degureEpice, int tempsEnJour, float prix, String type, String vegui) {
        super(id, nom, discription, idPic, nomPic);
        this.point = point;
        this.degureEpice = degureEpice;
        this.tempsEnJour = tempsEnJour;
        this.prix = prix;
        this.type = type;
        this.vegui = vegui;
    }

    //Propriety for binding
    public String getPointFormat(){
        //get resources from AppUtil
        Resources resources = AppUtile.getResource();
        return point + resources.getString(R.string.text_point);
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

    public int getTempsEnJour() {
        return tempsEnJour;
    }

    public String getVegui() {
        return vegui;
    }



    //OVERRIDE METHODE EQUALS TO COMPARE OBJECT
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plat)) return false;
        if (!super.equals(o)) return false;
        Plat plat = (Plat) o;
        return point == plat.point && degureEpice == plat.degureEpice && tempsEnJour == plat.tempsEnJour && Float.compare(plat.prix, prix) == 0 && Objects.equals(type, plat.type) && Objects.equals(vegui, plat.vegui);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), point, degureEpice, tempsEnJour, prix, type, vegui);
    }
}
