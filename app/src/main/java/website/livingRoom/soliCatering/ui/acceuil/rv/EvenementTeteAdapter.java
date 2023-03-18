package website.livingRoom.soliCatering.ui.acceuil.rv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.ModelTeteEvenementBinding;

public class EvenementTeteAdapter extends RecyclerView.Adapter<EvenementTeteAdapter.EvenementTeteHolder> {
    //FIELD
    private ModelTeteEvenementBinding bindingEvtTete;
    private int geton = 1;

    //OVERRIDE METHODE
    @NonNull
    @Override
    public EvenementTeteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_tete_evenement, parent, false);

        bindingEvtTete = ModelTeteEvenementBinding.bind(view);

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
        //UPDATE SIZE OF TEXT HOME
        bindingEvtTete.textViewDescriptionProjet.setOnClickListener(view -> updateDescription());
        bindingEvtTete.imageViewAgrandir.setOnClickListener(view -> updateDescription());
    }

    public void updateDescription() {
        //EXPAND DESCRIPTION
        if (geton == 1) {
            update(R.string.description_projet_grande, android.R.drawable.arrow_up_float, 2);
        }
        //REDUCE DESCRIPTION
        else if (geton == 2) {
            update(R.string.description_projet_petite, android.R.drawable.arrow_down_float, 1);
        }

    }

    private void update(int description_projet_grande, int arrow_up_float, int geton) {
        bindingEvtTete.textViewDescriptionProjet.setText(description_projet_grande);
        bindingEvtTete.imageViewAgrandir.setImageResource(arrow_up_float);
        geton = geton;
    }

    static class EvenementTeteHolder extends RecyclerView.ViewHolder {
        public EvenementTeteHolder(View view) {
            super(view);
        }
    }
}

