package website.livingRoom.soliCatering.model.entitys;


import androidx.room.Entity;

import java.util.Objects;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.utile.Helper;

@Entity(tableName = "categories")
public class Categorie extends Entite {
    //FIELD
    private final int point;

    //CONTRUCTOR
    public Categorie(int id, String nom, String discription, int idPic, String nomPic, int point) {
        super(id, nom, discription, idPic, nomPic);
        this.point = point;
    }

    //PROPRIETY
    public int getPoint() {
        return point;
    }

    public String getPointFormat(){

        return point + Helper.getString(R.string.text_point);
    }

    //OVERRIDE METHODE EQUALS TO COMPARE OBJECT
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categorie)) return false;
        if (!super.equals(o)) return false;
        Categorie categorie = (Categorie) o;
        return point == categorie.point;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), point);
    }
}
