package website.livingRoom.soliCatering;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import website.livingRoom.soliCatering.R;

import website.livingRoom.soliCatering.databinding.ActivityMainBinding;
import website.livingRoom.soliCatering.ripository.ConteurRipository;

public class MainActivity extends AppCompatActivity {

    //FIELD
    static SharedPreferences sharedPreferences;
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    private static ActivityMainBinding bindingActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //INSTANTIATE BINDING FOR ACTIVITY TO update ui from code
        bindingActivity = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bindingActivity.getRoot());

        //NAVIGATION CONTROL AND MENU INSTANTIATION
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(bindingActivity.navView, navController);

        /*CHANGE CONTEUR VISIBILITY WEN DESTINATION CHANGE*/
        conteurVisibility(navController);

        /*INITIATE CONTEUR AND SHARED PREFERENCES LISTENER to UPDATE UI WITH NEW DATA*/
        initiateContour();

    }

    private void initiateContour() {

        //START NEW CONTEUR
        if (!ConteurRipository.createConteur(getBaseContext())) {
            bindingActivity.includeConteur.setConteur(ConteurRipository.getConteur());
        }

        //INSTANTIATE LISTENER TO UPDATE UI WEN DATA IN SHARED PREFERENCES CHANGE
        listener = (sharedPreferences, key) -> {
            //UPDATE CONTEUR IN UI
            bindingActivity.includeConteur.setConteur(ConteurRipository.getConteur());
        };

        //CREATE INSTANCE OF SHARED PREFERENCES AND REGISTER THE LISTENER IN
        sharedPreferences = this.getSharedPreferences(String.valueOf(R.string.conteur_file_name), Context.MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    private void conteurVisibility(NavController navController) {
        //GET CONTEUR CONSTRAINT LAYOUT FROM BINDING
        ConstraintLayout cl = bindingActivity.includeConteur.constraintLayoutConteurCat;

        //ADD DESTINATION LISTENER TO NAVIGATION CONTROLLER
        /*LISTEN TO CHANGE OF DESTINATION? CONTEUR VISIBILITY WEN DESTINATION CHANGE*/
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()) {
                case R.id.navigation_categorie:
                case R.id.navigation_plat:
                case R.id.navigation_panier:
                case R.id.dialogPlat:
                    cl.setVisibility(View.VISIBLE);
                    break;
                default:
                    cl.setVisibility(View.GONE);
                    break;
            }
        });
    }

    /* TO unregister LISTENER*/
    public void unregisterListener() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterListener();
    }
}