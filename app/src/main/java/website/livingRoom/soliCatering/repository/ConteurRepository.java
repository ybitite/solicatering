package website.livingRoom.soliCatering.repository;

import static website.livingRoom.soliCatering.model.sharedPreferences.AppSharedPreferences.*;
import website.livingRoom.soliCatering.model.entitys.Conteur;

public class ConteurRepository {

    //methode to get value
    public static Conteur getConteur() {
        return new Conteur(getName(), getPointDepart(), getPointReste(), getCategorieActuel(), getPanierActuel());
    }
    public static String getName() {
        return getStringValue(Conteur.NAME_KEY);
    }
    public static int getPointDepart() {
        return getIntValue(Conteur.POINT_DEPART_KEY, 0);
    }
    public static int getPointReste() {
        return getIntValue(Conteur.POINT_RESTE_KEY, -1);
    }
    public static int getCategorieActuel() {
        return getIntValue(Conteur.CATEGORIE_ACTUEL_KEY, 0);
    }
    public static int getPanierActuel() {
        return getIntValue(Conteur.PANIER_ACTUEL_KEY, 0);
    }

    //methode to set values
    public static void resetConteur() {
        createDefaultConteur();
    }
    public static void setConteur(String nom, int ptDepart) {

        setName(nom);
        setPointDepart(ptDepart);
        setPointReste(ptDepart);

    }
    private static void setName(String value) {
        putStringValue(Conteur.NAME_KEY,value);
    }
    private static void setPointDepart(int value) {
        putIntValue(Conteur.POINT_DEPART_KEY, value);
    }
    private static void setPointReste(int value) {
        putIntValue(Conteur.POINT_RESTE_KEY, value);
    }
    public static void setCategorieActuel(int value) {
        putIntValue(Conteur.CATEGORIE_ACTUEL_KEY, value);
    }

    //methode tu update value
    public static void upDatePointReste(int value) {
        putIntValue(Conteur.POINT_RESTE_KEY, getPointReste() + value);
    }
    public static void updatePanierActuel() {
        putIntValue(Conteur.PANIER_ACTUEL_KEY, getPanierActuel()+1);
    }

}
