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

public class EvenementBtCmdAdapter extends RecyclerView.Adapter<EvenementBtCmdAdapter.EvenementBtCmdHolder> {

    //FIELD
    private ModelBtCmdEvenementBinding binding;
    private Context context;

    //OVERRIDE METHODE
    @NonNull
    @Override
    public EvenementBtCmdHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_bt_cmd_evenement, parent, false);

        binding = ModelBtCmdEvenementBinding.bind(view);

        context = parent.getContext();

        return new EvenementBtCmdHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EvenementBtCmdHolder holder, int position) {
        bind();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    //METHOD BIND
    private void bind() {
        //NAVIGATE TO COMMANDE ON BUTTON CLICK
        binding.buttonCommander.setOnClickListener(view ->
                Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main)
                        .navigate(R.id.action_navigation_home_to_navigation_commande));
    }

    //HOLDER
    static class EvenementBtCmdHolder extends RecyclerView.ViewHolder {
        public EvenementBtCmdHolder(View itemView) {
            super(itemView);
        }

    }
}