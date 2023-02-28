package com.example.marokkanischbernessen.db.entity;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.marokkanischbernessen.BR;
import com.example.marokkanischbernessen.utile.ExpressionValidateur;
import com.example.marokkanischbernessen.utile.FormulaireEtat;

@Entity(tableName = "clients")
public class Client extends FormulaireEtat {

    //FIELD
    @PrimaryKey()
    @NonNull
    public int idClient;
    private String nom;
    private String prenom;
    private String email;
    private String numTel;
    @Ignore
    private String nomPrenom;


    //CONSTRUCTOR
    @Ignore
    public Client() {
        super();
        this.idClient = 1;
        this.nom = "Youness";
        this.prenom = "Sirha";
        this.email = "youness@gmail.com";
        this.numTel = "0656326598";
    }

    public Client(String nom, String prenom, String email, String numTel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numTel = numTel;
    }

    //PROPRIETY
    @Bindable
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        //VALID THE EXPRESSION AND LENGTH IF TRUE NOTIFY CHANGE AND MAKE RESULT TRUE
        if (NOM_OK = ExpressionValidateur.validNom(nom))
            notifyPropertyChanged(BR.nom);
        this.nom = nom;
    }

    @Bindable
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        if (PRENOM_OK = ExpressionValidateur.validPrenom(prenom))
            notifyPropertyChanged(BR.prenom);
        this.prenom = prenom;

    }


    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (EMAIL_OK = ExpressionValidateur.validEmail(email))
            notifyPropertyChanged(BR.email);
        this.email = email;
    }

    @Bindable
    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        if (NUM_TEL_OK = ExpressionValidateur.validPhone(numTel))
            notifyPropertyChanged(BR.numTel);
        this.numTel = numTel;
    }


    public String getNomPrenom() {
        return getNom().toUpperCase() + " " + getPrenom();
    }

}
