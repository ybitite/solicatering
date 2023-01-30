package com.example.marokkanischbernessen.ui.historique.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marokkanischbernessen.databinding.ModelPanierBinding;
import com.example.marokkanischbernessen.db.entity.ArticlePanier;
import com.example.marokkanischbernessen.db.entity.PanierWithAarticlePanier;

import java.util.List;


public class PanierHolder extends RecyclerView.ViewHolder implements ItemClickListener {

    //FIELD
    static Context context;
    static ModelPanierBinding binding;

    public PanierHolder(@NonNull View itemView) {
        super(itemView);
        //GET CONTEXT TO USE LATER
        context = binding.getRoot().getContext();

        //FIX WIDTH OF CARD TO WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.cardViewPanier.setMinimumWidth(width);
    }

    @Override
    public void onItemClick(View v, int pos) {

    }

    public static PanierHolder create(ViewGroup parent) {
        binding = ModelPanierBinding
                .inflate(LayoutInflater.from(parent.getContext()));
        return new PanierHolder(binding.getRoot());
    }

    public void bind(PanierWithAarticlePanier panierWithAarticlePanier) {
        //push data to actualise ui
        binding.setPanier(panierWithAarticlePanier.panier);
        binding.imageViewMenuP.setImageResource(panierWithAarticlePanier.panier.getIdPic());

        //call methode to bind imbricated recycle view
        bindSecondRecycleView(panierWithAarticlePanier.articlePanierList);
    }

    /**
     * to bind the imbricated recycle view
     **/
    void bindSecondRecycleView(List<ArticlePanier> articlePanierList) {
        //instantiate rv
        RecyclerView rv = binding.miniAPRecyclerView;

        //set adapter to rv
        final MiniArticlePanierListAdapter miniArticlePanierListAdapter =
                new MiniArticlePanierListAdapter(new MiniArticlePanierListAdapter.ArticlePanierDiff());

        rv.setAdapter(miniArticlePanierListAdapter);


        //set properties to rv
        rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true));
        rv.setItemAnimator(new DefaultItemAnimator());

        /**  this data is already observed **/
        miniArticlePanierListAdapter.submitList(articlePanierList);
    }

}
