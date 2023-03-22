package website.livingRoom.soliCatering.db.entitys;

import android.content.res.Resources;

import androidx.databinding.Bindable;
import androidx.room.Embedded;
import androidx.room.Ignore;

import website.livingRoom.soliCatering.BR;
import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.utile.AppUtile;
import website.livingRoom.soliCatering.utile.ExpressionValidateur;
import website.livingRoom.soliCatering.utile.FormulaireEtat;

import java.util.Objects;

public class InformationLivraison extends FormulaireEtat {
    //field
    private String dateLivr;
    private String heurLivr;
    private String remarque;
    private short nombre;
    @Embedded
    protected Adresse adresse;

    //constructor
    public InformationLivraison(String dateLivr, String heurLivr, String remarque, short nombre, Adresse adresse) {
        this.dateLivr = dateLivr;
        this.heurLivr = heurLivr;
        this.remarque = remarque;
        this.nombre = nombre;
        this.adresse = adresse;
    }

    //to instantiate object for binding
    @Ignore
    public InformationLivraison() {
        adresse = new Adresse();
    }

    //PROPRIETY
    /*PROPRIETY FOR BINDING PARAMS AND RETURN STRING*/
    @Bindable
    public String getDateLivr() {
        return dateLivr;
    }

    public void setDateLivr(String dateLivr) {
        if (DATE_LVR_OK = ExpressionValidateur.validDate(dateLivr))
            notifyPropertyChanged(BR.dateLivr);
        this.dateLivr = dateLivr;
    }

    @Bindable
    public String getHeurLivr() {
        return heurLivr;
    }

    public void setHeurLivr(String heurLivr) {
        if (HEURE_LVR_OK = ExpressionValidateur.validHeure(heurLivr))
            notifyPropertyChanged(BR.heurLivr);
        this.heurLivr = heurLivr;
    }

    @Bindable
    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        if (REMARQUE_OK = ExpressionValidateur.validRemarque(remarque))
            notifyPropertyChanged(BR.remarque);
        this.remarque = remarque;
    }

    @Bindable
    public String getNombreString() {
        if (nombre == 0) return "";
        return String.valueOf(nombre);
    }

    public void setNombreString(String nombre) {
        if (NOMBRE_OK = ExpressionValidateur.validNombre(nombre)) {
            this.nombre = Short.parseShort(nombre);
            notifyPropertyChanged(BR.nombreString);
        }
    }
    @Bindable
    public String getDateHeure() {
        return dateLivr + " - " + heurLivr;
    }

    /*FOR DAO AND NORMAL ACCESS*/
    public short getNombre() {
        return nombre;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public String getNombreFormat() {
        //get resources from AppUtil
        Resources resources = AppUtile.getResource();
        return nombre + " " + resources.getString(R.string.text_point);
    }

    public String getTitreCatering() {
        //get resources from AppUtil
        Resources resources = AppUtile.getResource();
        return resources.getString(R.string.text_titre_catering) + " " + dateLivr;
    }

    /*EQUAL ET HASH METHODE*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformationLivraison that = (InformationLivraison) o;
        return nombre == that.nombre && Objects.equals(dateLivr, that.dateLivr) && Objects.equals(heurLivr, that.heurLivr) && Objects.equals(remarque, that.remarque) && Objects.equals(adresse, that.adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateLivr, heurLivr, remarque, nombre, adresse);
    }
}
