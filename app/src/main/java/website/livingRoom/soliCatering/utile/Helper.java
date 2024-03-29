package website.livingRoom.soliCatering.utile;


import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.utils.widget.MockView;
import androidx.navigation.Navigation;

import website.livingRoom.soliCatering.R;


public abstract class Helper {

    //RETURN ID RESOURCE FROM CONTEXT


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

    public static void bindPicassoImage( String  imageName, ImageView imageView) {
        AppPicasso.getInstance().load(getResources().getString(R.string.url_image_picasso) + imageName).into(imageView);
    }


    public static Context getContext() {
        return SoliCateringApplication.getContext();
    }
    public static Resources getResources(){
        return SoliCateringApplication.getResource();
    }

    public static void naviguer(int actionId) {
        Navigation.findNavController( SoliCateringApplication.getActivity(),R.id.nav_host_fragment_activity_main).navigate(actionId);
    }

}
