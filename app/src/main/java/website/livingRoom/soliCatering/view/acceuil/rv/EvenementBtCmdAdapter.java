
package website.livingRoom.soliCatering.view.acceuil.rv;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.ModelBtCmdEvenementBinding;

public class EvenementBtCmdAdapter extends RecyclerView.Adapter<EvenementBtCmdHolder> {

    //OVERRIDE METHODE
    @NonNull
    @Override
    public EvenementBtCmdHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_bt_cmd_evenement, parent, false);

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



}
