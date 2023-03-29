package website.livingRoom.soliCatering.model.sharedPreferences;

import android.content.SharedPreferences;
import android.util.Log;

import website.livingRoom.soliCatering.model.entitys.Conteur;

public abstract class ConteurSharedPreferencesOA {

    private static SharedPreferences.Editor editor;
    private static SharedPreferences sharedPreferences;

    public static boolean createConteur() {
        //create new key value when don't exist
        AppSharedPreferences appSharedPreferences = AppSharedPreferences.getInstance();
        sharedPreferences = appSharedPreferences.getSharedPreferences();
        editor = sharedPreferences.edit();
        Log.e("conteurSPDO","get references");
        return sharedPreferences.contains(Conteur.NAME_KEY);
    }

    //create new key-value in shared preferences
    public static void createDefaultConteur() {
        putAllValue("", 0, 0, 0, 1);
    }

    public static Conteur getConteur(){
        return new Conteur(getStringValue(Conteur.NAME_KEY),
                getIntValue(Conteur.POINT_DEPART_KEY,0),
                getIntValue(Conteur.POINT_RESTE_KEY,-1),
                getIntValue(Conteur.CATEGORIE_ACTUEL_KEY,0),
                getIntValue(Conteur.PANIER_ACTUEL_KEY,0));
    }

    public static void setConteur(Conteur conteur){
        putAllValue(conteur.getName(), conteur.getPointDepart(), conteur.getPointReste(),
                conteur.getCategorieActuel(), conteur.getPanierActuel());
    }
    private static void putAllValue(String name, int pointDepart, int pointRest, int categorieActuel, int panierActuel) {
        putStringValue(Conteur.NAME_KEY, name);
        putIntValue(Conteur.POINT_DEPART_KEY, pointDepart);
        putIntValue(Conteur.POINT_RESTE_KEY, pointRest);
        putIntValue(Conteur.CATEGORIE_ACTUEL_KEY, categorieActuel);
        putIntValue(Conteur.PANIER_ACTUEL_KEY, panierActuel);
        editor.apply();

    }

    //methode to access au shared preferences
    public static String getStringValue(String key) {
        return sharedPreferences.getString(key, "");
    }
    public static int getIntValue(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }
    public static void putStringValue(String key, String value) {
        editor.putString(key, value);
    }
    public static void putIntValue(String key, int value) {
        editor.putInt(key, value);
    }
}
