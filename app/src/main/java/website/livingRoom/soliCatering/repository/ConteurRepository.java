package website.livingRoom.soliCatering.repository;

import android.content.Context;
import android.content.SharedPreferences;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.db.entitys.Conteur;

public final class ConteurRepository {
    //FIELD
    private static SharedPreferences sharedPref;
    public static final String POINT_RESTE_KEY = "ptReste";

    private ConteurRepository() {

    }

    /*START CONTEUR WHITH NEW POINT DEPART AND NAME*/
    public static void createConteur(String nom, int ptDepart) {

        if (sharedPref != null) {
            SharedPreferences.Editor editor = sharedPref.edit();
            //SAVE CONTEUR IN SHARED PREFERENCES
            editor.putString("name", nom);
            editor.putInt("ptDepart", ptDepart);
            editor.putInt("ptReste", ptDepart);
            editor.putInt("catActuel", 0);
            editor.putInt("selectedPlat", 0);

            editor.apply();

        }
    }

    //START EMPTY  CONTEUR
    public static boolean createConteur(Context context) {
        if (sharedPref == null) {
            sharedPref = context.getSharedPreferences(String.valueOf(R.string.conteur_file_name), Context.MODE_PRIVATE);
            if (!sharedPref.contains("name")) {
                SharedPreferences.Editor editor = sharedPref.edit();
                //CREATE CONTEUR IN SHARED PREFERENCES
                editor.putString("name", "Choisir un menu");
                editor.putInt("ptDepart", 0);
                editor.putInt("ptReste", 0);
                editor.putInt("catActuel", 0);
                editor.putInt("selectedPlat", 0);
                editor.putInt("idPanier", 1);

                editor.apply();
                return true;
            } else return false;
        } else return false;
    }

    /*DELETE CONTEUR*/
    public static void deleteConteur(Context context) {
        if (sharedPref != null) {
            SharedPreferences.Editor editor = sharedPref.edit();
            //SAVE CONTEUR IN SHARED PREFERENCES
            editor.putString("name", "Choisir un menu");
            editor.putInt("ptDepart", 0);
            editor.putInt("ptReste", 0);
            editor.putInt("catActuel", 0);
            editor.putInt("selectedPlat", 0);

            editor.apply();
        }
    }


    /*RETURN CURENT CONTEUR FROM SHARED PREFERENCES*/
    public static Conteur getConteur() {
        if (sharedPref != null) {
            //GET DATA FROM SHARED PREFERENCES
            String name = sharedPref.getString("name", "");
            int ptDepart = sharedPref.getInt("ptDepart", 0);
            int ptReste = sharedPref.getInt("ptReste", 0);
            //CREAT AND RETURN CONTEUR OBJECT
            return new Conteur(name, ptDepart, ptReste);
        } else return new Conteur("", 0, 0);

    }

    /*RETURN CURENT POINT CATEGORIE FROM SHARED PREFERENCES*/
    public static int getActuelCat() {
        if (sharedPref != null) {
            //GET DATA FROM SHARED PREFERENCES
            int catActuel = sharedPref.getInt("catActuel", 0);
            //RETURN POINT OF ACTUEL CATEGORIE
            return catActuel;
        } else return 0;
    }

    /*RETURN CURENT MENU POINT FROM SHARED PREFERENCES*/
    public static int getActuelMenu() {
        if (sharedPref != null) {
            //GET DATA FROM SHARED PREFERENCES
            int menuActuel = sharedPref.getInt("ptDepart", 0);
            //RETURN POINT OF ACTUEL MENU
            return menuActuel;
        } else return 0;
    }

    /*RETURN LET POINT FROM SHARED PREFERENCES*/
    public static int getPointRest() {
        if (sharedPref != null) {
            //GET DATA FROM SHARED PREFERENCES
            int pointRest = sharedPref.getInt("ptReste", -1);
            //RETURN POINT RESTE
            return pointRest;
        } else return -1;
    }

    /*GET ID PANIER*/
    public static int getIdPanier() {
        if (sharedPref != null) {
            //GET DATA FROM SHARED PREFERENCES
            int idPanier = sharedPref.getInt("idPanier", 0);
            //RETURN ID PANIER
            return idPanier;
        } else return 0;
    }

    /*SAVE ACTUEL CATEGORIE IN SHARED PREFERENCES*/
    public static boolean seteCatActuel(int pointCat) {

        if (sharedPref != null) {
            SharedPreferences.Editor editor;

            //SAVE ACTUEL CATEGORIE
            editor = sharedPref.edit();
            editor.putInt("catActuel", pointCat);

            editor.apply();
            return true;

        } else return false;
    }

    /*SAVE NEW RESETE POINT TO SHARED PREFERENCES*/
    public static void upDatePointRest(int ptUpdate) {
        if (sharedPref != null) {
            SharedPreferences.Editor editor;
            //GET CURENT POINT RESTE FROM SP
            int oldPoint = sharedPref.getInt("ptReste", 0);
            //SAVE POINT RESTE CATEGORIE
            editor = sharedPref.edit();
            editor.putInt("ptReste", oldPoint + ptUpdate);

            editor.apply();
        }
    }

    /*CREATE NEW ID PANIER*/
    public static boolean createNewIdPanier() {
        // TODO: IMPLIMENT IN BUTTON VALIDER COMMANDE
        //GET OLD ID PANIER
        int oldId = getIdPanier();
        SharedPreferences.Editor editor;
        if (sharedPref != null) {
            editor = sharedPref.edit();
            //INCREMENT ID PANIER
            int newID = oldId + 1;
            editor.putInt("idPanier", newID);

            editor.apply();
            return true;
        } else return false;
    }
}
