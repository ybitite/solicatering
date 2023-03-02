package com.example.marokkanischbernessen.db.entity;

import androidx.databinding.Bindable;


import com.example.marokkanischbernessen.BR;
import com.example.marokkanischbernessen.utile.ExpressionValidateur;
import com.example.marokkanischbernessen.utile.FormulaireEtat;

public class Adresse extends FormulaireEtat {
    //field
    private String rue;
    private int numeroRue;
    private int codePostal;
    private String ville;

    //PROPRIETY
    @Bindable
    public String getRue() {
        return rue;
    }
    public void setRue(String rue) {
        if (RUE_OK = ExpressionValidateur.validRue(rue))
            notifyPropertyChanged(BR.rue);
        this.rue = rue;
    }
    @Bindable/*PROPRIETY FOR BINDING PARAMS AND RETURN STRING TO NOT USING CONVERTER*/
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

    /*FOR DAO AND NORMAL ACCESS*/
    public int getNumeroRue() {
        return numeroRue;
    }
    public void setNumeroRue(int numeroRue) {
        this.numeroRue = numeroRue;
    }
    @Bindable/*PROPRIETY FOR BINDING PARAMS AND RETURN STRING*/
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

    /*FOR DAO AND NORMAL ACCESS*/
    public int getCodePostal() {
        return codePostal;
    }
    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
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
}
