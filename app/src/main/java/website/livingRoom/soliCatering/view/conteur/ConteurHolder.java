package website.livingRoom.soliCatering.view.conteur;

import android.os.Bundle;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.FragmentConteurBinding;

public class ConteurHolder {
    //FIELD
    private final FragmentConteurBinding binding;
    private final NavController navController;


    public ConteurHolder(FragmentConteurBinding binding, NavController navController) {
        this.navController = navController;
        this.binding = binding;

    }

    public void registerListenerForVisibility() {

        //add on destination changer listener to navigation controller

        navController.addOnDestinationChangedListener(this::onDestinationChanged);
    }

    public void unregisterListenerForVisibility() {
        //remove on destination changer listener
        navController.removeOnDestinationChangedListener(this::onDestinationChanged);
    }

    //to listen  change of destination and update conteur visibility wen destination change
    private void onDestinationChanged(NavController controller, NavDestination destination, Bundle arguments) {
        switch (destination.getId()) {
            case R.id.navigation_menu:
            case R.id.navigation_categorie:
            case R.id.navigation_plat:
            case R.id.navigation_panier:
            case R.id.dialogPlat:
                updateConteurVisibility(View.VISIBLE);
                break;
            default:
                updateConteurVisibility(View.GONE);
                break;
        }
    }

    //update conteur visibility
    private void updateConteurVisibility(int visible) {
        binding.constraintLayoutConteurCat.setVisibility(visible);
    }
}
