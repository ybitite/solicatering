package website.livingRoom.soliCatering.view.acceuil.rv;


import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.ModelBtCmdEvenementBinding;
import website.livingRoom.soliCatering.utile.Helper;

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
        binding.buttonCommander.setOnClickListener(view -> Helper.naviguer(R.id.action_global_navigation_home_to_navigation_menu));

    }


}
