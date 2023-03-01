package com.example.marokkanischbernessen.ui.categorie.rv;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marokkanischbernessen.R;
import com.example.marokkanischbernessen.databinding.ModelCategorieBinding;
import com.example.marokkanischbernessen.db.entity.Categorie;
import com.example.marokkanischbernessen.ripository.ConteurRipository;
import com.example.marokkanischbernessen.utile.Helper;

public class CategorieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    ItemClickListener itemClickListener;
    static ModelCategorieBinding binding;
    static Context context;

    //CONTRUCTOR
    public CategorieHolder(@NonNull View itemView) {
        super(itemView);

        //GET CONTEXT TO USE LATER
        context = binding.getRoot().getContext();
        //SET CLICK LISTENER IN ITEM
        itemView.setOnClickListener(this);
        //FIX WIDTH OF CARD TO WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.cardViewCategorie.setMinimumWidth(width);
    }

    //METHODE
    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(view, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }

    public static CategorieHolder create(ViewGroup parent) {
        //INSTANTIATE BINDING OBJECT CLASS FROM MODEL PANIER
        binding = ModelCategorieBinding
                .inflate(LayoutInflater.from(parent.getContext()));

        return new CategorieHolder(binding.getRoot());
    }

    public void bind(Categorie categorie) {

        //SET DATA IN VIEW
        binding.textViewNomCat.setText(categorie.getNom());
        binding.textViewDiscriptionCat.setText(categorie.getDiscription());
        binding.imageViewCat.setImageResource(Helper.idResource(categorie.getNomPic()));
        binding.textViewPointCat.setText(String.valueOf(categorie.getPoint() + " Points"));


        //TODO : GET LIST OF EXEMPLE
//        holder.textViewContenuCat1.setText(categorie.get(position).getContenu1());
//        holder.textViewContenuCat2.setText(categorie.get(position).getContenu1());
//        holder.textViewContenuCat3.setText(categorie.get(position).getContenu2());

        //BLOCK CLICK AND MAKE ITEM GRIS
        int ptRest = ConteurRipository.getPointRest();
        if (categorie.getPoint() <= ptRest) {
            //MAKE ITEM VISIBLE AND POINT RED
            binding.griserItemCat.setVisibility(View.INVISIBLE);
            binding.linearLayoutCategorie.setClickable(true);
            binding.textViewPointCat.setTextColor(context.getResources().getColor(R.color.colorVertClair));
            setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(View v, int pos) {
                    ConteurRipository.seteCatActuel(categorie.getPoint());
                    Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_categorie_to_navigation_plat);
                }
            });
        } else {
            //MAKE ITEM GRIS AND POINT GREEN
            binding.griserItemCat.setVisibility(View.VISIBLE);
            binding.linearLayoutCategorie.setClickable(false);
            binding.textViewPointCat.setTextColor(Color.RED);
        }
    }
}
