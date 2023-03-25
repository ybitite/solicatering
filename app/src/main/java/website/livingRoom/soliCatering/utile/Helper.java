package website.livingRoom.soliCatering.utile;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.utils.widget.MockView;
import androidx.navigation.Navigation;

import website.livingRoom.soliCatering.R;

public class Helper {

    //RETURN ID RESOURCE FROM CONTEXT
    public static int getIdResourceByName(String nom) {
        return getResources().getIdentifier(nom, "drawable", getContext().getPackageName());
    }

    public static void fixWidth(MockView mockView){
        //FIX WIDTH OF CARD TO WIDTH OF SCREEN
        int width = getResources().getDisplayMetrics().widthPixels;
        mockView.setMinimumWidth(width);
    }

    public static void blockItem(int colorId, boolean clickable, View itemView, CardView cardView){
        //MAKE ITEM GRIS AND POINT GREEN
        itemView.setClickable(clickable);
        cardView.setCardBackgroundColor(getColor(colorId));
    }

    public static int getColor(int colorId) {
        return getResources().getColor(colorId);
    }

    public static String getString(int idStringResource){
        return getResources().getString(idStringResource);
    }

    public static void bindPicassoImage(String  imageName, ImageView imageView) {
        //SET DATA IN VIEW
        imageView.setImageResource(getIdResourceByName(imageName));
    }

    public static void naviguer(int idActionNavigation) {
        Navigation.findNavController((Activity) getContext(), R.id.nav_host_fragment_activity_main)
                .navigate(idActionNavigation);
    }

    public static Context getContext() {
        return AppUtile.getContext();
    }
    public static Resources getResources(){
        return AppUtile.getResource();
    }


}
