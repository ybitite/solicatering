package com.example.marokkanischbernessen.ui.historique.rv;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marokkanischbernessen.db.entity.PanierWithAarticlePanierAndPlat;
import com.example.marokkanischbernessen.ripository.PanierRipository;

public class PanierListAdapter extends ListAdapter<PanierWithAarticlePanierAndPlat, PanierHolder> {

    //CONSTRUCTOR
    public PanierListAdapter(@NonNull DiffUtil.ItemCallback<PanierWithAarticlePanierAndPlat> diffCallback) {
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
        panierRipository.deleteCurentPanier(getItem(viewHolder.getBindingAdapterPosition()).panier.getIdClientOwner());
    }

    /* IMPLEMENTATION OF DIFFUTIL, ITEMCALLBACK FOR CALCULATING THE DIFF BETWEEN TWO ITEMS IN A LIST*/
    public static class PanierDiff extends DiffUtil.ItemCallback<PanierWithAarticlePanierAndPlat> {
        @Override
        public boolean areItemsTheSame(@NonNull PanierWithAarticlePanierAndPlat oldItem, @NonNull PanierWithAarticlePanierAndPlat newItem) {
            return oldItem.panier.id == newItem.panier.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull PanierWithAarticlePanierAndPlat oldItem, @NonNull PanierWithAarticlePanierAndPlat newItem) {
            return oldItem.panier.equals(newItem.panier);
        }
    }
}
