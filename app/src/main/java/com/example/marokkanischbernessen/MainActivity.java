package com.example.marokkanischbernessen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.marokkanischbernessen.databinding.ActivityMainBinding;
import com.example.marokkanischbernessen.ripository.ConteurRipository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    static SharedPreferences sharedPreferences;
    //FIELD
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    private static ActivityMainBinding bindingActiv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //INSTANTIATE BINDING FOR ACTIVITY
        bindingActiv = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bindingActiv.getRoot());


        //NAVIGATION CONTROL AND MENU INSTANTIATION
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_menu, R.id.navigation_panier)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(bindingActiv.navView, navController);

        /*CHANGE CONTEUR VISIBILITY WEN DESTINATION CHANGE*/
        conteurVisibility(navController);

        /*INITIATE CONTEUR AND SHARED PREFERENCES LISTENER to UPDATE UI WITH NEW DATA*/
        intiatConteur();

    }

    private void intiatConteur() {

        //START NEW CONTEUR
        if (!ConteurRipository.createConteur(getBaseContext())) {
            bindingActiv.includeConteur.setConteur(ConteurRipository.getConteur());
        }

        //INSTANTIATE LISTENER TO UPDATE UI WEN DATA IN SHARED PREFERENCES CHANGE
        listener = (sharedPreferences, key) -> {
            //UPDATE CONTEUR IN UI
            bindingActiv.includeConteur.setConteur(ConteurRipository.getConteur());
        };

        //CREATE INSTANCE OF SHARED PREFERENCES AND REGISTER THE LISTENER IN
        sharedPreferences = this.getSharedPreferences(String.valueOf(R.string.conteur_file_name), Context.MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    private void conteurVisibility(NavController navController) {
        //GET CONTEUR CONSTRAINT LAYOUT FROM BINDING
        ConstraintLayout cl = bindingActiv.includeConteur.constraintLayoutConteurCat;
        //ADD DESTINATION LISTENER TO NAVIGATION CONTROLLER
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                switch (destination.getId()) {
                    case R.id.navigation_home:
                        cl.setVisibility(View.GONE);
                        break;
                    case R.id.navigation_menu:
                        cl.setVisibility(View.GONE);
                        break;
                    case R.id.navigation_categorie:
                        cl.setVisibility(View.VISIBLE);
                        break;
                    case R.id.navigation_plat:
                        cl.setVisibility(View.VISIBLE);

                        break;
                    case R.id.navigation_panier:
                        cl.setVisibility(View.VISIBLE);
                        break;
                    case R.id.dialogPlat:
                        cl.setVisibility(View.VISIBLE);
                        break;
                    default:
                        cl.setVisibility(View.GONE);
                        break;
                }
                if (destination.getId() == R.id.navigation_menu) ;
            }
        });
    }

    /* TO LIBER LISTENER*/
    public void unregisterListener() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterListener();
    }

    /*METHODE TO LINK WITH BUTTON RETOUR*/
    public void onClickRetour() {
        /*** todo : link to view */
        //DELETE CONTEUR AND NAVIGATE TO MENU TO CHOSE AGAIN
        ConteurRipository.deleteConteur(this);
        Navigation.findNavController(this, R.id.nav_host_fragment_activity_main)
                .navigate(R.id.navigation_menu);
    }
}