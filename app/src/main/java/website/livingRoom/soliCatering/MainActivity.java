package website.livingRoom.soliCatering;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import website.livingRoom.soliCatering.databinding.ActivityMainBinding;
import website.livingRoom.soliCatering.model.sharedPreferences.AppSharedPreferences;
import website.livingRoom.soliCatering.model.sharedPreferences.ConteurSharedPreferencesOA;
import website.livingRoom.soliCatering.view.conteur.ConteurHolder;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;

public class MainActivity extends AppCompatActivity {

    //FIELD
    private SharedPreferences sharedPreferences;
    private static ActivityMainBinding bindingActivity;
    private ConteurHolder conteurHolder;
    private AppSharedPreferences appSharedPreferences;

    private ConteurViewModel conteurViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //INSTANTIATE BINDING FOR ACTIVITY TO update ui from code
        bindingActivity = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bindingActivity.getRoot());

        //NAVIGATION CONTROL AND MENU INSTANTIATION
/*        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_menu, R.id.navigation_categorie,R.id.navigation_panier,R.id.navigation_historique)
                .build();*/
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(bindingActivity.navView, navController);


        appSharedPreferences = AppSharedPreferences.getInstance();
        sharedPreferences = appSharedPreferences.getSharedPreferences();

        bindConteur(navController);

    }

    private void bindConteur(NavController navController) {
        if (!ConteurSharedPreferencesOA.createConteur()){
            ConteurSharedPreferencesOA.createDefaultConteur();
        }
        conteurViewModel = new ViewModelProvider(this).get(ConteurViewModel.class);

        conteurHolder = new ConteurHolder(bindingActivity, navController,conteurViewModel,this);

        /*CHANGE CONTEUR VISIBILITY WEN DESTINATION CHANGE*/
        conteurHolder.conteurVisibility();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}