package website.livingRoom.soliCatering.model.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.model.entitys.Conteur;

public abstract class AppSharedPreferences {
    //FIELD
    /*DEFINED A SINGLETON, PREVENT MULTIPLE INSTANCES OF shared preferences OPENED AT THE SAME TIME.*/
    public static volatile SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    //methode to return instance of shared preferences
    public static SharedPreferences getSharedPreferences(final Context context){
        //CREATE NEW INSTANCE WZN IS IT NULL
        if(sharedPreferences == null){
            synchronized (SharedPreferences.class){
                createConteur(context);
            }
        }
        return sharedPreferences;
    }

    private static void createConteur(Context context) {
        if(sharedPreferences == null){

            //instantiate shared preferences
            sharedPreferences =
                    context.getSharedPreferences(String.valueOf(R.string.conteur_file_name), Context.MODE_PRIVATE);

            //instantiate editor tu update data
            editor = sharedPreferences.edit();

            //create new key value when don't exist
            if (!sharedPreferences.contains(Conteur.NAME_KEY))
                createDefaultConteur();

        }
    }

    //create new key-value in shared preferences
    public static void createDefaultConteur() {
        putStringValue(Conteur.NAME_KEY,"");
        putIntValue(Conteur.POINT_DEPART_KEY,0);
        putIntValue(Conteur.POINT_RESTE_KEY,0);
        putIntValue(Conteur.CATEGORIE_ACTUEL_KEY,0);
        putIntValue(Conteur.PANIER_ACTUEL_KEY,1);
    }

    //methode to access au shared preferences
    public static String getStringValue(String key) {
        return sharedPreferences.getString(key, "");
    }
    public static int getIntValue(String pointDepartKey, int defValue) {
        return sharedPreferences.getInt(pointDepartKey, defValue);
    }
    public static void putStringValue(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }
    public static void putIntValue(String pointResteKey, int value) {
        editor.putInt(pointResteKey, value);
        editor.apply();
    }
}
