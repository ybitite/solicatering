package com.example.marokkanischbernessen.ui.historique.rv;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marokkanischbernessen.db.entity.PanierWithAarticlePanier;
import com.example.marokkanischbernessen.ripository.PanierRipository;

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

    //todo implement link delete panier
    //DELETE curent panier from data base
    private void deletePanier(RecyclerView.ViewHolder viewHolder) {
        //DELETE  PANIER FROM DATA BASE
        PanierRipository panierRipository = new PanierRipository(viewHolder.itemView.getContext());
        panierRipository.deleteCurentPanier(getItem(viewHolder.getBindingAdapterPosition()).panier.idPanier);
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
