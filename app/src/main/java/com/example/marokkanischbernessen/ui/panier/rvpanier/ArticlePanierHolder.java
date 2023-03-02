package com.example.marokkanischbernessen.ui.panier.rvpanier;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.marokkanischbernessen.databinding.ModelArticlePanierBinding;
import com.example.marokkanischbernessen.db.entity.ArticlePanierAndPlat;
import com.example.marokkanischbernessen.ripository.ArticlePanierRipository;
import com.example.marokkanischbernessen.ripository.ConteurRipository;
import com.example.marokkanischbernessen.ui.categorie.rv.ItemClickListener;
import com.example.marokkanischbernessen.ui.panier.PanierViewModel;
import com.example.marokkanischbernessen.utile.Helper;

public class ArticlePanierHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    ItemClickListener itemClickListener;
    static ModelArticlePanierBinding binding;

    static Context context;


    //CONTRUCTOR
    public ArticlePanierHolder(View itemView) {
        super(itemView);
        //GET CONTEXT TO USE LATER
        context = binding.getRoot().getContext();
        //SET CLICK LISTENER IN ITEM
        itemView.setOnClickListener(this);
        //FIX WIDTH OF CARD TO WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.textViewTitreAP.setWidth(width);
        binding.cardViewArticlePanier.setMinimumWidth(width);

    }

    //METHODE
    //@Override
    //public void onClick(View v) { this.itemClickListener.onItemClick(v,getLayoutPosition()); }
    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }

    public static ArticlePanierHolder create(ViewGroup parent) {
        //GENERATE CLASS FROM MODEL ARTICLE PANIER
        binding = ModelArticlePanierBinding
                .inflate(LayoutInflater.from(parent.getContext()));
        return new ArticlePanierHolder(binding.getRoot());
    }

    public void bind(ArticlePanierAndPlat articlePanierAndPlat, PanierViewModel panierViewModel) {
        //SET DATA IN VIEW
        binding.textViewTitreAP.setText(articlePanierAndPlat.plat.getNom());
        binding.textViewPointAP.setText(String.valueOf(articlePanierAndPlat.plat.getPoint() +" P"));
        binding.tvNbrPlatAP.setText(String.valueOf(articlePanierAndPlat.articlePanier.getNombrePlat()));
        binding.imageViewTitreAP.setImageResource(Helper.idResource(articlePanierAndPlat.plat.getNomPic()));

        //SET CLICK LISTENER to INCREMENT BUTTON
        TextView tvNbrPlat = binding.tvNbrPlatAP;
        binding.btIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //IF THE INCREMENT WAS DONE UPDATE CONTROL
                int nbrPlat = panierViewModel.incrimenteNbPlat(getBindingAdapterPosition());
                if (nbrPlat != 0) {
                    tvNbrPlat.setText(String.valueOf(nbrPlat));
                }
                Log.e("article panier", "adapter : " + getBindingAdapterPosition());
                Log.e("article panier", "layout : " + getLayoutPosition());
            }
        });

        //SET CLICK LISTENER to DECREMENT BUTTON
        binding.btDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nbrPlat = panierViewModel.decrementNbPlat(getBindingAdapterPosition());
                if (nbrPlat != 0) {
                    //IF THE DECREMENT WAS DONE UPDATE CONTROL
                    tvNbrPlat.setText(String.valueOf(nbrPlat));
                }
                if (nbrPlat == 0) {
                    //IF JUST ONE ARTICLE STAY DELETE IT
                    deleteArticlePanier(articlePanierAndPlat);
                }
            }

        });

    }

    public void deleteArticlePanier(ArticlePanierAndPlat articlePanierAndPlat) {
        //DELETE ARTICLE PANIER FROM DATA BASE
        ArticlePanierRipository articlePanierRipository = new ArticlePanierRipository(context);
        articlePanierRipository.deleteArticlePanier(articlePanierAndPlat.articlePanier);

        //UPDATE RESTE POINT IN CONTEUR
        ConteurRipository.upDatePointRest(articlePanierAndPlat.plat.getPoint());
    }


    @Override
    public void onClick(View v) {

    }
}