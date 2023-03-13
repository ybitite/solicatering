package website.livingRoom.soliCatering.ui.acceuil.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.databinding.ModelEvenementBinding;
import website.livingRoom.soliCatering.db.entitys.Evenement;
import website.livingRoom.soliCatering.utile.Helper;

public class EvenementHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    website.livingRoom.soliCatering.ui.categorie.rv.ItemClickListener itemClickListener;
    private final ModelEvenementBinding binding;
    private final Context context;

    //CONTRUCTOR
    public EvenementHolder(View itemView, ModelEvenementBinding modelEvenementBinding) {
        super(itemView);

        //GET CONTEXT TO USE LATER
        context = itemView.getContext();

        // instantiate binding object
        binding = modelEvenementBinding;

        //SET CLICK LISTENER IN ITEM
        itemView.setOnClickListener(this);
    }

    //METHODE
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(website.livingRoom.soliCatering.ui.categorie.rv.ItemClickListener ic) {
        this.itemClickListener = ic;
    }

    public static EvenementHolder create(ViewGroup parent) {
        ModelEvenementBinding modelEvenementBinding = ModelEvenementBinding
                .inflate(LayoutInflater.from(parent.getContext()));

        return new EvenementHolder(modelEvenementBinding.getRoot(),modelEvenementBinding);
    }

    public void bind(Evenement evenement) {

        //FIX WIDTH OF CARD TO WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.cardViewEvenement.setMinimumWidth(width);

        //SET DATA IN VIEW
        binding.setEvenement(evenement);
        binding.imageViewPlat.setImageResource(Helper.idResource(evenement.getNomPic()));

        //OVRRIDE ON ITEM CLICK
        setItemClickListener((v, position) -> Toast.makeText(context, evenement.getNom(), Toast.LENGTH_SHORT).show());
    }


}