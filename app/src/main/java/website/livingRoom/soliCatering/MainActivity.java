package website.livingRoom.soliCatering;

import android.os.Bundle;
import android.util.Log;

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
    private ConteurHolder conteurHolder;

    //beginning of life cycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindingActivity = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bindingActivity.getRoot());

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(bindingActivity.navView, navController);

    }

    //activity is visible
    @Override
    protected void onStart() {
        //create conteur view with live model
        bindConteur();
        super.onStart();
    }

    private void bindConteur() {
        if (!ConteurSharedPreferencesOA.createConteur()){
            ConteurSharedPreferencesOA.createDefaultConteur();
        }
        conteurViewModel = new ViewModelProvider(this).get(ConteurViewModel.class);

        conteurHolder = new ConteurHolder(conteurViewModel, navController, bindingActivity, this);
    }

    //activity is accessible
    @Override
    protected void onResume() {
        //start to listen to destination and up date visibility of conteur
        conteurHolder.registerListenerForVisibility();
        super.onResume();
    }

    //activity is not any more accessible
    @Override
    protected void onPause() {
        //stop to listen to destination and up date visibility of conteur
        conteurHolder.unregisterListenerForVisibility();
        super.onPause();
    }



    //end of life cycle
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}