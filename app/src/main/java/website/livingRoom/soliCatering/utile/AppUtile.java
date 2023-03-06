package website.livingRoom.soliCatering.utile;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import java.lang.ref.WeakReference;

public class AppUtile extends Application {

    //FIELD
    private static Resources resources;
    private static WeakReference<Context> contextWeakReference;

    //METHODE
    @Override
    public void onCreate() {
        super.onCreate();
        resources=getApplicationContext().getResources();
        contextWeakReference = new WeakReference<>(getApplicationContext());
    }

    public static Resources getResource(){
        return resources;
    }
    public static Context getContext(){
        return contextWeakReference.get();
    }
}
