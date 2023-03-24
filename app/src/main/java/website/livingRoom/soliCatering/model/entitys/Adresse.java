package website.livingRoom.soliCatering.model.entitys;

import androidx.databinding.Bindable;
import androidx.room.Ignore;


import website.livingRoom.soliCatering.BR;
import website.livingRoom.soliCatering.utile.ExpressionValidateur;
import website.livingRoom.soliCatering.utile.FormulaireEtat;

import java.util.Objects;

public class Adresse extends FormulaireEtat {
    //field
    private String rue;
    private int numeroRue;
    private int codePostal;
    private String ville;

    //Constructor
    public Adresse(String rue, int numeroRue, int codePostal, String ville) {
        this.rue = rue;
        this.numeroRue = numeroRue;
        this.codePostal = codePostal;
        this.ville = ville;
    }
    //to instantiate object for binding
    @Ignore
    public Adresse() {
    }

    //PROPRIETY
    //*PROPRIETY FOR BINDING PARAMS AND RETURN STRING*/
    @Bindable
    public String getRue() {
        return rue;
    }
    public void setRue(String rue) {
        if (RUE_OK = ExpressionValidateur.validRue(rue))
            notifyPropertyChanged(BR.rue);
        this.rue = rue;
    }
    @Bindable
    public String getNumeroRueString() {
        if (numeroRue == 0) return "";
        else return String.valueOf(numeroRue);
    }
    public void setNumeroRueString(String numeroRue) {
        //todo: try catch
        if (NUMERO_RUE_OK = ExpressionValidateur.validNumRue(numeroRue)) {
            this.numeroRue = Integer.parseInt(numeroRue);
            notifyPropertyChanged(BR.numeroRueString);
        }
    }
    @Bindable
    public String getCodePostalString() {
        if (codePostal == 0) return "";
        else return String.valueOf(codePostal);
    }
    public void setCodePostalString(String codePostal) {
        if (CODE_POSTAL_OK = ExpressionValidateur.validCodePostal(codePostal)) {
            this.codePostal = Integer.parseInt(codePostal);
            notifyPropertyChanged(BR.codePostalString);
        }
    }
    @Bindable
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        if (VILLE_OK = ExpressionValidateur.validVille(ville))
            notifyPropertyChanged(BR.ville);
        this.ville = ville;
    }
    @Bindable
    public String getCompletAdresse() {
        return rue + " " + numeroRue + ", " + codePostal + " " + ville + ".";
    }
    /*FOR DAO AND NORMAL ACCESS*/
    public int getNumeroRue() {
        return numeroRue;
    }
    public int getCodePostal() {
        return codePostal;
    }

    /*EQUAL ET HASH METHODE*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse = (Adresse) o;
        return numeroRue == adresse.numeroRue && codePostal == adresse.codePostal && Objects.equals(rue, adresse.rue) && Objects.equals(ville, adresse.ville);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rue, numeroRue, codePostal, ville);
    }
}
