package website.livingRoom.soliCatering.model.entitys;

import androidx.room.PrimaryKey;

import java.util.Objects;

public class Entite {

    //FIELD
    @PrimaryKey(autoGenerate = true)
    private final int id;
    private final String nom;
    private final String discription;
    private int idPic;
    private final String nomPic;

    //CONSTRUCTOR
    public Entite(int id, String nom, String discription, int idPic, String nomPic) {
        this.id = id;
        this.nom = nom;
        this.discription=discription;
        this.idPic = idPic;
        this.nomPic = nomPic;
    }

    //PROPRIETY
    public int getId() {
        return id;
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

    public void setIdPic(int idPic) {
        this.idPic = idPic;
    }

    //OVERRIDE METHODE EQUALS TO COMPARE OBJECT
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entite entite = (Entite) o;
        return id == entite.id && idPic == entite.idPic && Objects.equals(nom, entite.nom) && Objects.equals(discription, entite.discription) && Objects.equals(nomPic, entite.nomPic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, discription, idPic, nomPic);
    }
}
