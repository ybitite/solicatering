package website.livingRoom.soliCatering;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import androidx.navigation.ui.NavigationUI;



import website.livingRoom.soliCatering.databinding.ActivityMainBinding;
import website.livingRoom.soliCatering.model.sharedPreferences.ConteurSharedPreferencesOA;
import website.livingRoom.soliCatering.view.conteur.ConteurHolder;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;

public class MainActivity extends AppCompatActivity {

    //FIELD
    private static ActivityMainBinding bindingActivity;
    private ConteurViewModel conteurViewModel;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindingActivity = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bindingActivity.getRoot());

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(bindingActivity.navView, navController);

        bindConteur();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    private void bindConteur() {
        if (!ConteurSharedPreferencesOA.createConteur()){
            ConteurSharedPreferencesOA.createDefaultConteur();
        }
        conteurViewModel = new ViewModelProvider(this).get(ConteurViewModel.class);

        ConteurHolder conteurHolder = new ConteurHolder(conteurViewModel, navController, bindingActivity, this);

        /*CHANGE CONTEUR VISIBILITY WEN DESTINATION CHANGE*/
        conteurHolder.conteurVisibility();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}