package website.livingRoom.soliCatering.view.acceuil.rv;


import android.view.View;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.ModelBtCmdEvenementBinding;

//HOLDER
public class EvenementBtCmdHolder extends RecyclerView.ViewHolder {
    //FIELD
    private final ModelBtCmdEvenementBinding binding;

    public EvenementBtCmdHolder(View itemView) {
        super(itemView);
        binding = ModelBtCmdEvenementBinding.bind(itemView);
    }
    //METHOD BIND
    public void bind() {
        //NAVIGATE TO COMMANDE ON BUTTON CLICK
        binding.buttonCommander.setOnClickListener(view -> naviguer(R.id.action_navigation_home_to_navigation_commande));
    }

    private void naviguer(int actionId) {
        Navigation.findNavController(itemView).navigate(actionId);
    }
}
