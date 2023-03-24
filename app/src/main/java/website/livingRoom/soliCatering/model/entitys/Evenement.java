package website.livingRoom.soliCatering.model.entitys;

import java.util.Objects;

@androidx.room.Entity(tableName = "evenements")
public class Evenement extends Entite {
    //FIELD
    private final int nb;
    private final String date;

    //CONSTRUCTOR
    public Evenement(int id, String nom, String discription, int idPic, String nomPic, int nb, String date) {
        super(id, nom, discription, idPic, nomPic);
        this.nb = nb;
        this.date = date;
    }

    //PROPRIETY
    public int getNb() {

        return nb;

    }
    public String getDate() {
        return date;
    }

    //OVERRIDE METHODE EQUALS TO COMPARE OBJECT
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evenement)) return false;
        if (!super.equals(o)) return false;
        Evenement evenement = (Evenement) o;
        return nb == evenement.nb && Objects.equals(date, evenement.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nb, date);
    }
}


