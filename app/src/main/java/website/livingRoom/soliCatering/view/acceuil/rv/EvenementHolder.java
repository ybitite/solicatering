package website.livingRoom.soliCatering.view.acceuil.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.databinding.ModelEvenementBinding;
import website.livingRoom.soliCatering.model.entitys.Evenement;
import website.livingRoom.soliCatering.utile.Helper;

public class EvenementHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    private final ModelEvenementBinding binding;
    private final Context context;
    private Evenement evenement;

    //CONSTRUCTOR
    public EvenementHolder(ModelEvenementBinding modelEvenementBinding) {
        super(modelEvenementBinding.getRoot());

        //GET CONTEXT TO USE LATER
        context = modelEvenementBinding.getRoot().getContext();

        // instantiate binding object
        binding = modelEvenementBinding;

        //SET CLICK LISTENER IN ITEM
        itemView.setOnClickListener(this);
    }

    //METHODE
    public static EvenementHolder create(ViewGroup parent) {
        ModelEvenementBinding modelEvenementBinding = ModelEvenementBinding
                .inflate(LayoutInflater.from(parent.getContext()));

        return new EvenementHolder(modelEvenementBinding);
    }

    public void bind(Evenement evenement) {
        this.evenement=evenement;
        fixWidth();

        //SET DATA IN VIEW
        binding.setEvenement(evenement);
        binding.imageViewPlat.setImageResource(Helper.getIdResourceByName(evenement.getNomPic()));

    }

    private void fixWidth() {
        //FIX WIDTH OF CARD TO WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.cardViewEvenement.setMinimumWidth(width);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, evenement.getDiscription(), Toast.LENGTH_LONG).show();
    }


}