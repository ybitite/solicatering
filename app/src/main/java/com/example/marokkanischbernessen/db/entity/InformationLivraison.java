package com.example.marokkanischbernessen.db.entity;

import androidx.databinding.Bindable;
import androidx.room.Embedded;
import androidx.room.Ignore;

import com.example.marokkanischbernessen.BR;
import com.example.marokkanischbernessen.utile.ExpressionValidateur;
import com.example.marokkanischbernessen.utile.FormulaireEtat;

import java.util.Objects;

public class InformationLivraison extends FormulaireEtat {

    protected String dateLivr;
    protected String heurLivr;
    protected String remarque;
    protected short nombre;

    @Embedded
    protected Adresse adresse;

    @Ignore
    public InformationLivraison() {
        this.dateLivr = "11/08/2023";
        this.heurLivr = "12:00";
        this.remarque = "pas de remarque";
        this.nombre = 50;
        adresse = new Adresse();
    }

    public InformationLivraison(String dateLivr, String heurLivr, String remarque, short nombre, Adresse adresse) {
        this.dateLivr = dateLivr;
        this.heurLivr = heurLivr;
        this.remarque = remarque;
        this.nombre = nombre;
        this.adresse = adresse;
    }

    //PROPRIETY
    @Bindable
    public String getDateLivr() {
        return dateLivr;
    }

    public void setDateLivr(String dateLivr) {
        if (DATE_LVR_OK = ExpressionValidateur.validDate(dateLivr))
            notifyPropertyChanged(BR.dateLivr);
        this.dateLivr = dateLivr.trim();
    }

    @Bindable
    public String getHeurLivr() {
        return heurLivr;
    }

    public void setHeurLivr(String heurLivr) {
        if (HEURE_LVR_OK = ExpressionValidateur.validHeure(heurLivr))
            notifyPropertyChanged(BR.heurLivr);
        this.heurLivr = heurLivr.trim();
    }

    @Bindable
    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        if (REMARQUE_OK = ExpressionValidateur.validRemarque(remarque))
            notifyPropertyChanged(BR.remarque);
        this.remarque = remarque.trim();
    }

    @Bindable/*PROPRIETY FOR BINDING PARAMS AND RETURN STRING*/
    public String getNombreString() {
        if (nombre == 0) return "";
        return String.valueOf(nombre);
    }

    public void setNombreString(String nombre) {
        if (NOMBRE_OK = ExpressionValidateur.validNombre(nombre)) {
            short nbr = Short.parseShort(nombre);
            this.nombre = nbr;
            notifyPropertyChanged(BR.nombreString);
        }
    }

    /*FOR DAO AND NORMAL ACCESS*/
    public short getNombre() {
        return nombre;
    }

    public void setNombre(short nombre) {
        this.nombre = nombre;
    }

    @Bindable
    public String getDateHeure() {
        return "le " + dateLivr + " à : " + heurLivr;
    }

    //to access to address object
    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getNombreFormat() {
        return getNombreString() + " P";
    }

    /*EQUAL ET HASH METHODE*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InformationLivraison)) return false;
        InformationLivraison that = (InformationLivraison) o;
        return nombre == that.nombre && Objects.equals(dateLivr, that.dateLivr) && Objects.equals(heurLivr, that.heurLivr) && Objects.equals(remarque, that.remarque) && Objects.equals(adresse, that.adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateLivr, heurLivr, remarque, nombre, adresse);
    }
}
