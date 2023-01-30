package com.example.marokkanischbernessen.ui.historique.rv;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.marokkanischbernessen.db.entity.PanierWithAarticlePanier;

public class PanierListAdapter extends ListAdapter<PanierWithAarticlePanier, PanierHolder> {

    //CONSTRUCTOR
    public PanierListAdapter(@NonNull DiffUtil.ItemCallback<PanierWithAarticlePanier> diffCallback) {
        super(diffCallback);
    }

    //METHODE
    @NonNull
    @Override
    public PanierHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return PanierHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull PanierHolder holder, int position) {
        holder.bind(getItem(position));
    }

    /* IMPLEMENTATION OF DIFFUTIL, ITEMCALLBACK FOR CALCULATING THE DIFF BETWEEN TWO ITEMS IN A LIST*/
    public static class PanierDiff extends DiffUtil.ItemCallback<PanierWithAarticlePanier> {
        @Override
        public boolean areItemsTheSame(@NonNull PanierWithAarticlePanier oldItem, @NonNull PanierWithAarticlePanier newItem) {
            return oldItem.panier.idPanier == newItem.panier.idPanier;
        }

        @Override
        public boolean areContentsTheSame(@NonNull PanierWithAarticlePanier oldItem, @NonNull PanierWithAarticlePanier newItem) {
            return oldItem.panier.equals(newItem.panier);
        }
    }
}
