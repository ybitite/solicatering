package website.livingRoom.soliCatering.utile;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;


public  class AppUtile extends Application {

    //FIELD
    private static Resources resources;
    private static WeakReference<Context> contextWeakReference;

    private static WeakReference<Activity> activityWeakReference;


    //METHODE
    @Override
    public void onCreate() {
        super.onCreate();
        resources=getApplicationContext().getResources();
        contextWeakReference = new WeakReference<>(getApplicationContext());
        registerActivityLifecycleCallbacks(callback);
    }

    public static Resources getResource(){
        return resources;
    }
    public static Context getContext(){
        return contextWeakReference.get();
    }

    public static Activity getActivity() {
        return activityWeakReference.get();
    }

    private final ActivityLifecycleCallbacks callback = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                AppUtile.activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

        }

    };
}
