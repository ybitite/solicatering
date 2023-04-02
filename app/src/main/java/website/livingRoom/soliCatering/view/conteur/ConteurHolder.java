package website.livingRoom.soliCatering.view.conteur;

import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;

import website.livingRoom.soliCatering.MainActivity;
import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.ActivityMainBinding;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;

public class ConteurHolder {
    //FIELD
    private final ActivityMainBinding bindingActivity;
    private final NavController navController;


    public ConteurHolder(ConteurViewModel conteurViewModel, NavController navController,ActivityMainBinding bindingActivity, MainActivity mainActivity) {
        this.navController = navController;
        this.bindingActivity = bindingActivity;
        bindingActivity.includeConteur.setLifecycleOwner(mainActivity);
        bindingActivity.includeConteur.setViewModel(conteurViewModel);
    }

    public void conteurVisibility() {
        //GET CONTEUR CONSTRAINT LAYOUT FROM BINDING
        ConstraintLayout constraintLayoutConteur = bindingActivity.includeConteur.constraintLayoutConteurCat;

        //ADD DESTINATION LISTENER TO NAVIGATION CONTROLLER
        /*LISTEN TO CHANGE OF DESTINATION? CONTEUR VISIBILITY WEN DESTINATION CHANGE*/
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()) {
                case R.id.navigation_menu:
                case R.id.navigation_categorie:
                case R.id.navigation_plat:
                case R.id.navigation_panier:
                case R.id.dialogPlat:
                    constraintLayoutConteur.setVisibility(View.VISIBLE);
                    break;
                default:
                    constraintLayoutConteur.setVisibility(View.GONE);
                    break;
            }
        });
    }
}
