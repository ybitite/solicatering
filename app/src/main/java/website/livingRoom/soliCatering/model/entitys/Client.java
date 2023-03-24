package website.livingRoom.soliCatering.model.entitys;

import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import website.livingRoom.soliCatering.BR;
import website.livingRoom.soliCatering.utile.ExpressionValidateur;
import website.livingRoom.soliCatering.utile.FormulaireEtat;

import java.util.Objects;

@Entity(tableName = "clients")
public class Client extends FormulaireEtat {

    //FIELD
    @PrimaryKey()
    private int idClient;
    private String nom;
    private String prenom;
    private String email;
    private String numTel;

    //Constructor
    public Client(int idClient, String nom, String prenom, String email, String numTel) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numTel = numTel;
    }
    //to instantiate object for binding
    @Ignore
    public Client() {
    }

    //PROPRIETY
    //*PROPRIETY FOR BINDING PARAMS AND RETURN STRING*/
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
    /*FOR DAO AND NORMAL ACCESS*/
    public int getIdClient() {
        return idClient;
    }
    public String getNomPrenom() {
        return getNom().toUpperCase() + " " + getPrenom();
    }
    /*EQUAL ET HASH METHODE*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return idClient == client.idClient && Objects.equals(nom, client.nom) && Objects.equals(prenom, client.prenom) && Objects.equals(email, client.email) && Objects.equals(numTel, client.numTel);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idClient, nom, prenom, email, numTel);
    }
}
