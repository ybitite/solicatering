package com.example.marokkanischbernessen.utile;

import com.example.marokkanischbernessen.db.entity.Client;


public class ErrorBuilder {
    //FIELD TO CREATE MESSAGE ERREUR
    private final static String MEDebut = "- veuillez saisir ";

    //BUILD MESSAGE ERROR
    public static StringBuilder buildErrorMessage() {
        StringBuilder builder = new StringBuilder();
        //APPEND ERROR MESSAGE FOR EVERY FIELD WEN THE RESULT IS TRUE
        builder.append(Client.NOM_OK ? "" : MEDebut + "un nom valide" + "\n")
                .append(Client.PRENOM_OK ? "" : MEDebut + "un prénom valide" + "\n")
                .append(Client.RUE_OK ? "" : MEDebut + "un nom de rue valide" + "\n")
                .append(Client.NUMERO_RUE_OK ? "" : MEDebut + "un numéro de rue valide" + "\n")
                .append(Client.CODE_POSTAL_OK ? "" : MEDebut + "un code postal valide" + "\n")
                .append(Client.VILLE_OK ? "" : MEDebut + "un nom de ville valide" + "\n")
                .append(Client.EMAIL_OK ? "" : MEDebut + "un email valide (exemple@gmail.com)" + "\n")
                .append(Client.NUM_TEL_OK ? "" : MEDebut + "un numéro de télephone suisse valide" + "\n")
                .append(Client.DATE_LVR_OK ? "" : MEDebut + "une date valide (DD/MM/YYYY)" + "\n")
                .append(Client.HEURE_LVR_OK ? "" : MEDebut + "une heure valide (HH:MM)" + "\n")
                .append(Client.REMARQUE_OK ? "" : MEDebut + "un text qui ne dépasse pas 500 caractéres" + "\n")
                .append(Client.NOMBRE_OK ? "" : MEDebut + "un nombre entre 30 et 999" + "\n");
        return builder;
    }
}
