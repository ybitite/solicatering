package website.livingRoom.soliCatering.utile;


import android.content.res.Resources;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.db.entitys.Client;


public class ErrorBuilder {
    //BUILD MESSAGE ERROR
    public static StringBuilder buildErrorMessage() {

        //get resources from AppUtil
        Resources resources = AppUtile.getResource();

        //create string builder to concatenate all error message
        StringBuilder builder = new StringBuilder();

        //APPEND ERROR MESSAGE FOR EVERY FIELD WEN THE RESULT IS TRUE
        if(!Client.NOM_OK) builder.append(resources.getString(R.string.erreur_nom)).append("\n");
        if(!Client.PRENOM_OK) builder.append(resources.getString(R.string.erreur_prenom)).append("\n");
        if(!Client.RUE_OK) builder.append(resources.getString(R.string.erreur_rue)).append("\n");
        if(!Client.NUMERO_RUE_OK) builder.append(resources.getString(R.string.erreur_numero_rue)).append("\n");
        if(!Client.CODE_POSTAL_OK) builder.append(resources.getString(R.string.erreur_code_postal)).append("\n");
        if(!Client.VILLE_OK) builder.append(resources.getString(R.string.erreur_ville)).append("\n");
        if(!Client.EMAIL_OK) builder.append(resources.getString(R.string.erreur_email)).append("\n");
        if(!Client.NUM_TEL_OK) builder.append(resources.getString(R.string.erreur_tel)).append("\n");
        if(!Client.DATE_LVR_OK) builder.append(resources.getString(R.string.erreur_date)).append("\n");
        if(!Client.HEURE_LVR_OK) builder.append(resources.getString(R.string.erreur_heure)).append("\n");
        if(!Client.REMARQUE_OK) builder.append(resources.getString(R.string.erreur_remarque)).append("\n");
        if(!Client.NOMBRE_OK) builder.append(resources.getString(R.string.erreur_nombre)).append("\n");

        return builder;
    }
}
