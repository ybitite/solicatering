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

    @Bindable
    public String getTextButtonValiderValue(){

        if (isPanierNotEmpty()) return Helper.getString(R.string.bt_valider_cmd);

        else return Helper.getString(R.string.bt_valider_cmd_0_article);
    }

    @Bindable
    public boolean getClickableButtonValiderValue(){
        return isPanierFull();
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
        notifyPropertyChanged(BR.clickableButtonValiderValue);
        notifyPropertyChanged(BR.textButtonValiderValue);
    }

    public void upDatePointReste(int value) {
        pointReste += value;
        notifyPropertyChanged(BR.pointResteFormat);
        notifyPropertyChanged(BR.clickableButtonValiderValue);
        notifyPropertyChanged(BR.textButtonValiderValue);
    }

    public void setCategorieActuel(int value) {
        categorieActuel = value;
    }

    //methode tu update value

    public void updatePanierActuel() {
        panierActuel ++ ;
    }

    private boolean isPanierNotEmpty() {
        return pointReste < pointDepart  && isMenuSelected();
    }

    public boolean isPanierFull() {
        return pointReste == 0 && isMenuSelected();
    }

    private boolean isMenuSelected() {
        return pointDepart > 0;
    }

    public boolean isEnoughRestePoint(int value){
        return value <= pointReste;
    }
    public boolean isSemMenu(int value){
        return value == pointDepart;
    }
    public boolean isSemCategoriePossible(int value){
        return value < categorieActuel;
    }

    public void resetConteur(){
        setName(Helper.getString(R.string.text_aucun_menu));
        setPointDepart(0);
        setPointReste(0);
        setCategorieActuel(0);
        updatePanierActuel();
    }
}
