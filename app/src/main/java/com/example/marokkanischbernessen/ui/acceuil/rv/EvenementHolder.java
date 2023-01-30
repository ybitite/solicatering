package com.example.marokkanischbernessen.ui.acceuil.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.marokkanischbernessen.databinding.ModelEvenementBinding;
import com.example.marokkanischbernessen.db.entity.Evenement;
import com.example.marokkanischbernessen.ui.categorie.rv.ItemClickListener;
import com.example.marokkanischbernessen.utile.Helper;

public class EvenementHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    ItemClickListener itemClickListener;
    static ModelEvenementBinding binding;
    static Context context;

    //CONTRUCTOR
    public EvenementHolder(View itemView) {
        super(itemView);

        //GET CONTEXT TO USE LATER
        context = binding.getRoot().getContext();
        //SET CLICK LISTENER IN ITEM
        itemView.setOnClickListener(this);
        //FIX WIDTH OF CARD TO WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.cardViewEvenement.setMinimumWidth(width);
    }

    //METHODE
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;

    }

    public static EvenementHolder create(ViewGroup parent) {
        //GENERATE CLASS FROM MODEL EVENT
        binding = ModelEvenementBinding
                .inflate(LayoutInflater.from(parent.getContext()));
        return new EvenementHolder(binding.getRoot());
    }

    public void bind(Evenement evenement) {
        //SET DATA IN VIEW
        binding.textViewNomEvenement.setText(evenement.getNom());
        binding.textViewNbPersEvnement.setText(String.valueOf(evenement.getNb()));
        binding.textViewDateEvenement.setText(evenement.getDate());
        binding.imageViewPlat.setImageResource(Helper.idResource(context, evenement.getNomPic()));
        //OVRRIDE ON ITEM CLICK
        setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context, evenement.getNom(), Toast.LENGTH_SHORT);
            }
        });
    }


}