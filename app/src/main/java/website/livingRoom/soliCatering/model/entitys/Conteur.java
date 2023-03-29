package website.livingRoom.soliCatering.model.entitys;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import website.livingRoom.soliCatering.BR;
import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.utile.Helper;

public class Conteur extends BaseObservable {
    //FIELD
    private String name;
    private int pointDepart;
    private int pointReste;
    private int categorieActuel;
    private int panierActuel;
    //KEY
    public static final String NAME_KEY = "conteur_name";
    public static final String POINT_RESTE_KEY = "conteur_point_reste";
    public static final String POINT_DEPART_KEY = "conteur_point_depart";
    public static final String CATEGORIE_ACTUEL_KEY = "conteur_categorie_actuel";
    public static final String PANIER_ACTUEL_KEY = "conteur_panier_actuel";




    //CONSTRUCTOR
    public Conteur(String name, int pointDepart, int pointReste, int categorieActuel, int panierActuel) {
        this.name = name;
        this.pointDepart = pointDepart;
        this.pointReste = pointReste;
        this.categorieActuel= categorieActuel;
        this.panierActuel =panierActuel;

    }

    //PROPRIETY
    public String getName() {
        return name;
    }
    public int getPointDepart() {
        return pointDepart;
    }
    public int getPointReste() {
        return pointReste;
    }
    public int getCategorieActuel() {
        return categorieActuel;
    }
    public int getPanierActuel() {
        return panierActuel;
    }
    @Bindable
    public String getNameFormat() {
        //get resources from AppUtil
        return name + " (" + pointDepart + ") "+ Helper.getString(R.string.text_point);
    }
    @Bindable
    public String getPointResteFormat() {

        return pointReste + " " + Helper.getString(R.string.text_point);

    }

    public void setName(String value) {
        name = value;
        notifyPropertyChanged(BR.nameFormat);
    }

    public void setPointDepart(int value) {
        pointDepart = value;
        notifyPropertyChanged(BR.nameFormat);
    }

    public void setPointReste(int value) {
        pointReste = value;
        notifyPropertyChanged(BR.pointResteFormat);
    }

    public void setCategorieActuel(int value) {
        categorieActuel = value;
    }


    //methode tu update value
    public void upDatePointReste(int value) {
        pointReste += value;
        notifyPropertyChanged(BR.pointResteFormat);
    }

    public void updatePanierActuel() {
        panierActuel ++ ;
    }
}
