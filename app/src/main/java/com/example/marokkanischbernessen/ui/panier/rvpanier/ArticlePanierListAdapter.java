package com.example.marokkanischbernessen.ui.panier.rvpanier;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marokkanischbernessen.db.entity.ArticlePanier;
import com.example.marokkanischbernessen.ripository.ArticlePanierRipository;
import com.example.marokkanischbernessen.ripository.ConteurRipository;
import com.example.marokkanischbernessen.ui.panier.PanierViewModel;

public class ArticlePanierListAdapter extends ListAdapter<ArticlePanier, ArticlePanierHolder> {
    //FIELD
    final PanierViewModel panierViewModel;

    //CREATE SIMPLE CALLBACK TO DELETE ITEM ON SWIPED LEF OR RIGHT
    public ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteArticlePanier(viewHolder);
        }

    };
    //CONSTRUCTOR
    public ArticlePanierListAdapter(@NonNull DiffUtil.ItemCallback<ArticlePanier> diffCallback, PanierViewModel panierViewModel) {
        super(diffCallback);
        this.panierViewModel = panierViewModel;

    }

    //METHODE
    @NonNull
    @Override
    public ArticlePanierHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return ArticlePanierHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlePanierHolder holder, int position) {
        holder.bind(getItem(position), panierViewModel);
    }

    //DELETE all article panier in the curent panier from data base
    private void deleteArticlePanier(RecyclerView.ViewHolder viewHolder) {
        //DELETE ARTICLE PANIER FROM DATA BASE
        ArticlePanierRipository articlePanierRipository = new ArticlePanierRipository(viewHolder.itemView.getContext());
        articlePanierRipository.deleteArticlePanier(getItem(viewHolder.getBindingAdapterPosition()));

        //UPDATE RESTE POINT IN CONTEUR
        ArticlePanier articlePanier = getItem(viewHolder.getBindingAdapterPosition());
        ConteurRipository.upDatePointRest(articlePanier.getPointPlat() * articlePanier.getNombrePlat());
    }

    /* IMPLEMENTATION OF DIFFUTIL, ITEMCALLBACK FOR CALCULATING THE DIFF BETWEEN OLD AND NEW ITEM*/
    public static class ArticlePanierDiff extends DiffUtil.ItemCallback<ArticlePanier> {
        @Override
        public boolean areItemsTheSame(@NonNull ArticlePanier oldItem, @NonNull ArticlePanier newItem) {
            return oldItem.getIdPlats() == newItem.getIdPlats();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ArticlePanier oldItem, @NonNull ArticlePanier newItem) {
            return oldItem.getNombrePlat() == oldItem.getNombrePlat();
        }
    }
}