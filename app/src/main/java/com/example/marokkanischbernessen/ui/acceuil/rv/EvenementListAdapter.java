package com.example.marokkanischbernessen.ui.acceuil.rv;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.marokkanischbernessen.db.entity.Evenement;


public class EvenementListAdapter extends ListAdapter<Evenement, EvenementHolder> {

    //CONSTRUCTOR
    public EvenementListAdapter(@NonNull DiffUtil.ItemCallback<Evenement> diffCallback) {
        super(diffCallback);
    }

    //METHODE
    @NonNull
    @Override
    public EvenementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return EvenementHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull EvenementHolder holder, int position) {
        holder.bind(getItem(position));

    }

    /* IMPLEMENTATION OF DIFFUTIL, ITEMCALLBACK FOR CALCULATING THE DIFF BETWEEN TWO ITEMS IN A LIST*/
    public static class EvenementDiff extends DiffUtil.ItemCallback<Evenement> {

        @Override
        public boolean areItemsTheSame(@NonNull Evenement oldItem, @NonNull Evenement newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Evenement oldItem, @NonNull Evenement newItem) {
            return oldItem.equals(newItem);
        }
    }


}