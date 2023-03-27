package website.livingRoom.soliCatering.utile;


import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.utils.widget.MockView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

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

    public static void bindPicassoImage( String  imageName, ImageView imageView) {
        AppPicasso.getInstance().load(getResources().getString(R.string.url_image_picasso) + imageName).into(imageView);
    }


    public static Context getContext() {
        return AppUtile.getContext();
    }
    public static Resources getResources(){
        return AppUtile.getResource();
    }


}
