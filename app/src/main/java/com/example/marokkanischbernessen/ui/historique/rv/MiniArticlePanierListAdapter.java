package com.example.marokkanischbernessen.ui.historique.rv;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.marokkanischbernessen.db.entity.ArticlePanierAndPlat;

public class MiniArticlePanierListAdapter extends ListAdapter<ArticlePanierAndPlat, MiniArticlePanierHolder> {

    //CONSTRUCTOR
    protected MiniArticlePanierListAdapter(@NonNull DiffUtil.ItemCallback<ArticlePanierAndPlat> diffCallback) {
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
    public static class ArticlePanierWithPlatDiff extends DiffUtil.ItemCallback<ArticlePanierAndPlat> {
        @Override
        public boolean areItemsTheSame(@NonNull ArticlePanierAndPlat oldItem, @NonNull ArticlePanierAndPlat newItem) {
            return oldItem.articlePanier.getIdPanier() == newItem.articlePanier.getIdPanier();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ArticlePanierAndPlat oldItem, @NonNull ArticlePanierAndPlat newItem) {
            return oldItem.articlePanier.getNombrePlat() == oldItem.articlePanier.getNombrePlat();
        }
    }
}
