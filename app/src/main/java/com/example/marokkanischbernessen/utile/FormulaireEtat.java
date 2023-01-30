package com.example.marokkanischbernessen.utile;

import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.room.Ignore;

import com.example.marokkanischbernessen.BR;

public class FormulaireEtat extends BaseObservable {

    //STATIC FIELD FOR LABEL
    @Ignore
    public final static String NOM_LABEL = "Nom";
    @Ignore
    public final static String PRENOM_LABEL = "Prénom";
    @Ignore
    public final static String RUE_LABEL = "Rue";
    @Ignore
    public final static String NUMERO_RUE_LABEL = "Numéro";
    @Ignore
    public final static String CODE_POSTAL_LABEL = "Code postal";
    @Ignore
    public final static String VILLE_LABEL = "Ville";
    @Ignore
    public final static String EMAIL_LABEL = "E-mail";
    @Ignore
    public final static String NUM_TEL_LABEL = "Télèphone";
    @Ignore
    public final static String DATE_LVR_LABEL = "Date de livraison";
    @Ignore
    public final static String HEURE_LVR_LABEL = "Heure";
    @Ignore
    public final static String REMARQUE_LABEL = "Remarque";

    //STATIC FIELD FOR EXPRESSION VALIDATION RESULT
    @Ignore
    public static boolean NOM_OK = false;
    @Ignore
    public static boolean PRENOM_OK = false;
    @Ignore
    public static boolean RUE_OK = false;
    @Ignore
    public static boolean NUMERO_RUE_OK = false;
    @Ignore
    public static boolean CODE_POSTAL_OK = false;
    @Ignore
    public static boolean VILLE_OK = false;
    @Ignore
    public static boolean EMAIL_OK = false;
    @Ignore
    public static boolean NUM_TEL_OK = false;
    @Ignore
    public static boolean DATE_LVR_OK = false;
    @Ignore
    public static boolean HEURE_LVR_OK = false;
    @Ignore
    public static boolean REMARQUE_OK = false;
    @Ignore
    public static boolean NOMBRE_OK = false;
    @Ignore
    public static boolean ALL_EXPRESSION_VALID = false;

    //VARIABLE TO DETERMINE VISIBILITY FROM ALL RESULT
    @Ignore
    protected int visibilityError = View.GONE;

    //STRING BUILDER TO CONTAIN ERROR MESSAGE
    @Ignore
    StringBuilder errorBuilder;

    //TO OBSERVE CHANGE
    @Ignore
    protected PropertyChangeRegistry callbacks;

    //CONSTRUCTOR
    public FormulaireEtat() {
        errorBuilder = new StringBuilder();
        callbacks = new PropertyChangeRegistry();
    }

    /*PROPRIETY BINDABLE TO UPDATE ERROR MESSAGE TEXT*/
    @Bindable
    public String getErrorMessage() {
        return errorBuilder.toString();
    }

    /*PROPRIETY BINDABLE TO UPDATE ERROR MESSAGE VISIBILITY*/
    @Bindable
    public int getVisibilityError() {
        return visibilityError;
    }

    public void setVisibilityError(int visibilityError) {
        this.visibilityError = visibilityError;
        notifyPropertyChanged(BR.visibilityError);
    }

    //OVERRIDE METHODE
    @Override
    public void addOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    /*NOTIFIES OBSERVERS THAT ALL PROPERTIES OF THIS INSTANCE HAVE CHANGED.*/
    public void notifyPropertyChanged(int fieldId) {
        callbacks.notifyCallbacks(this, fieldId, null);
    }


    /* FIND IF ALL EXPRESSION ARE VALID*/
    public boolean checkAllExpression() {
        //SOMME LOGIC OF ALL RESULT
        ALL_EXPRESSION_VALID = NOM_OK && PRENOM_OK && RUE_OK && NUMERO_RUE_OK && CODE_POSTAL_OK
                && VILLE_OK && EMAIL_OK && NUM_TEL_OK && DATE_LVR_OK && HEURE_LVR_OK && REMARQUE_OK && NOMBRE_OK;

        //BUILD ERROR MESSAGE
        errorBuilder = ErrorBuilder.buildErrorMessage();

        //UPDATE VISIBILITY OF ERROR TEXT VIEW
        updateVisibilityError();

        return ALL_EXPRESSION_VALID;
    }

    /*METHODE TO UPDATE VISIBILITY OF ERROR TEXT VIEW*/
    void updateVisibilityError() {
        //IF TRU HIDE ERROR MESSAGE
        if (ALL_EXPRESSION_VALID) setVisibilityError(View.GONE);
            //ELSE SHOW ERROR MESSAGE
        else setVisibilityError(View.VISIBLE);
        //NOTIFY CHANGE
        notifyPropertyChanged(BR.errorMessage);

    }
}
