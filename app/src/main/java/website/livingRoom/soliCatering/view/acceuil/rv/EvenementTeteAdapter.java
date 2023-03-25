package website.livingRoom.soliCatering.view.acceuil.rv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;
public class EvenementTeteAdapter extends RecyclerView.Adapter<EvenementTeteAdapter.EvenementTeteHolder> {

    //OVERRIDE METHODE
    @NonNull
    @Override
    public EvenementTeteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_tete_evenement, parent, false);

        return new EvenementTeteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EvenementTeteHolder holder, int position) {
        bind();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    //METHODE BIND
    public void bind() {
    }

    static class EvenementTeteHolder extends RecyclerView.ViewHolder {
        public EvenementTeteHolder(View view) {
            super(view);
        }
    }
}

