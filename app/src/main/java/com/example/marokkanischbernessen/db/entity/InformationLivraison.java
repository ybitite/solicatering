package com.example.marokkanischbernessen.db.entity;

import androidx.databinding.Bindable;
import androidx.room.Embedded;

import com.example.marokkanischbernessen.BR;
import com.example.marokkanischbernessen.utile.ExpressionValidateur;
import com.example.marokkanischbernessen.utile.FormulaireEtat;

import java.util.Objects;

public class InformationLivraison extends FormulaireEtat {
    //field
    protected String dateLivr;
    protected String heurLivr;
    protected String remarque;
    protected short nombre;
    @Embedded
    protected Adresse adresse;

    //constructor
    public InformationLivraison() {
        adresse = new Adresse();
    }

    //PROPRIETY
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

    @Bindable/*PROPRIETY FOR BINDING PARAMS AND RETURN STRING*/
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

    /*FOR DAO AND NORMAL ACCESS*/
    public short getNombre() {
        return nombre;
    }

    public void setNombre(short nombre) {
        this.nombre = nombre;
    }
    @Bindable
    public String getDateHeure() {
        return dateLivr + " - " + heurLivr;
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
