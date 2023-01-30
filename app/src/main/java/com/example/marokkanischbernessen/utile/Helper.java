package com.example.marokkanischbernessen.utile;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

public class Helper {

    //RETURN ID RESOURCE FROM CONTEXT
    public static int idResource(Context c, String nom) {
        return c.getResources().getIdentifier(nom, "drawable", c.getPackageName());
    }

    //TP UPPER FIRST CHARTER FROM STRING
    public static String toUpperFistChar(String oldValue) {
        char[] value = oldValue.toCharArray();
        value[0] = Character.toUpperCase(value[0]);
        return value.toString();
    }


    //RETURN ACTIVITY FROM CONTEXT
    public static Activity getActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        throw new IllegalStateException("Context is not an Activity.");
    }
}
