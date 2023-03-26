package website.livingRoom.soliCatering;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import website.livingRoom.soliCatering.databinding.ActivityMainBinding;
import website.livingRoom.soliCatering.model.sharedPreferences.AppSharedPreferences;
import website.livingRoom.soliCatering.repository.ConteurRepository;

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
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_menu, R.id.navigation_categorie,R.id.navigation_panier,R.id.navigation_historique)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(bindingActivity.navView, navController);

        /*CHANGE CONTEUR VISIBILITY WEN DESTINATION CHANGE*/
        conteurVisibility(navController);

        /*INITIATE CONTEUR AND SHARED PREFERENCES LISTENER to UPDATE UI WITH NEW DATA*/
        initiateContour();

    }

    private void initiateContour() {

        //INSTANTIATE LISTENER TO UPDATE UI WEN DATA IN SHARED PREFERENCES CHANGE
        listener = (SharedPreferences sharedPreferences, String key) -> updateConteurControl();

        //GET INSTANCE OF SP TO REGISTER A LISTENER
        sharedPreferences = AppSharedPreferences.getSharedPreferences(getBaseContext());
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);

        updateConteurControl();

    }

    private void updateConteurControl() {
        //UPDATE CONTEUR IN UI
        bindingActivity.includeConteur.setConteur(ConteurRepository.getConteur());
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