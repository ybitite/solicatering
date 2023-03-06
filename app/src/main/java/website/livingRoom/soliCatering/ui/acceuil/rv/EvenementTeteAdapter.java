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
    int geton = 1;

    //OVERRIDE METHODE
    @NonNull
    @Override
    public EvenementTeteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_tete_evenement, parent, false);
        bindingEvtTete = ModelTeteEvenementBinding.bind(view);
        EvenementTeteHolder evenementTeteHolder = new EvenementTeteHolder(view);
        return evenementTeteHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EvenementTeteHolder holder, int position) {

        initiateTopUi();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    //METHODE BIND
    public void initiateTopUi() {

        //UPDATE SIZE OF TEXT HOME
        bindingEvtTete.textViewDescriptionProjet.setOnClickListener(view -> updateDescription());

        bindingEvtTete.imageViewAgrandir.setOnClickListener(view -> updateDescription());

    }

    public void updateDescription() {

        //EXPAND DESCRIPTION
        if (geton == 1) {
            bindingEvtTete.textViewDescriptionProjet.setText(R.string.description_projet_grande);
            bindingEvtTete.imageViewAgrandir.setImageResource(android.R.drawable.arrow_up_float);
            geton = 2;
        }
        //REDUCE DESCRIPTION
        else if (geton == 2) {
            bindingEvtTete.textViewDescriptionProjet.setText(R.string.description_projet_petite);
            bindingEvtTete.imageViewAgrandir.setImageResource(android.R.drawable.arrow_down_float);
            geton = 1;
        }

    }

    class EvenementTeteHolder extends RecyclerView.ViewHolder {

        public EvenementTeteHolder(View view) {
            super(view);
        }
    }
}

