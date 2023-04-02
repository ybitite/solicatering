package website.livingRoom.soliCatering.model.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.utile.AppUtile;
import website.livingRoom.soliCatering.utile.Helper;

public abstract class AppSharedPreferences{
    //FIELD
    /*DEFINED A SINGLETON, PREVENT MULTIPLE INSTANCES OF shared preferences OPENED AT THE SAME TIME.*/

    private static volatile AppSharedPreferences INSTANCE;

    private  static SharedPreferences sharedPreferences;

    private AppSharedPreferences() {
    }

    //methode to return instance of shared preferences
    public static AppSharedPreferences getInstance(){
        //CREATE NEW INSTANCE WZN IS IT NULL
        if(INSTANCE == null){
            synchronized (AppSharedPreferences.class){
                INSTANCE = new  AppSharedPreferences() {
                };
                sharedPreferences =
                        Helper.getContext().getSharedPreferences(String.valueOf(R.string.conteur_file_name), Context.MODE_PRIVATE);
            }
        }
        return INSTANCE;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
