package com.example.marokkanischbernessen.ui.historique.rv;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.marokkanischbernessen.db.entity.ArticlePanier;

public class MiniArticlePanierListAdapter extends ListAdapter<ArticlePanier, MiniArticlePanierHolder> {

    //CONSTRUCTOR
    protected MiniArticlePanierListAdapter(@NonNull DiffUtil.ItemCallback<ArticlePanier> diffCallback) {
        super(diffCallback);
    }

    //METHODE
    @NonNull
    @Override
    public MiniArticlePanierHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MiniArticlePanierHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MiniArticlePanierHolder holder, int position) {
        holder.bind(getItem(position));
    }

    /* IMPLEMENTATION OF DIFFUTIL, ITEMCALLBACK FOR CALCULATING THE DIFF BETWEEN OLD AND NEW ITEM*/
    public static class ArticlePanierDiff extends DiffUtil.ItemCallback<ArticlePanier> {
        @Override
        public boolean areItemsTheSame(@NonNull ArticlePanier oldItem, @NonNull ArticlePanier newItem) {
            return oldItem.getIdPanier() == newItem.getIdPanier();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ArticlePanier oldItem, @NonNull ArticlePanier newItem) {
            return oldItem.getNombrePlat() == oldItem.getNombrePlat();
        }
    }
}
