package website.livingRoom.soliCatering.view.acceuil.rv;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.ModelBtCmdEvenementBinding;
import website.livingRoom.soliCatering.utile.Helper;

public class EvenementBtCmdAdapter extends RecyclerView.Adapter<EvenementBtCmdAdapter.EvenementBtCmdHolder> {


    private Context context;

    //OVERRIDE METHODE
    @NonNull
    @Override
    public EvenementBtCmdHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_bt_cmd_evenement, parent, false);


        context = parent.getContext();

        return new EvenementBtCmdHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EvenementBtCmdHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 1;
    }



    //HOLDER
    static class EvenementBtCmdHolder extends RecyclerView.ViewHolder {
        //FIELD
        private ModelBtCmdEvenementBinding binding;

        public EvenementBtCmdHolder(View itemView) {
            super(itemView);
            binding = ModelBtCmdEvenementBinding.bind(itemView);
        }
        //METHOD BIND
        private void bind() {
            //NAVIGATE TO COMMANDE ON BUTTON CLICK
            binding.buttonCommander.setOnClickListener(view -> naviguer(R.id.action_navigation_home_to_navigation_commande));
        }

        private void naviguer(int actionId) {
            Navigation.findNavController(itemView).navigate(actionId);
        }
    }
}
