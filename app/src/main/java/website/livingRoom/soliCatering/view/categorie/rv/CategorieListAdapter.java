package website.livingRoom.soliCatering.view.categorie.rv;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import website.livingRoom.soliCatering.model.entitys.Categorie;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;

public class CategorieListAdapter extends ListAdapter<Categorie, CategorieHolder> {

        private final ConteurViewModel conteurViewModel;

    //CONSTRUCTOR
    public CategorieListAdapter(@NonNull DiffUtil.ItemCallback<Categorie> diffCallback, ConteurViewModel conteurViewModel) {
        super(diffCallback);
        this.conteurViewModel = conteurViewModel;
    }

    //METHODE
    @NonNull
    @Override
    public CategorieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CategorieHolder.create(parent,conteurViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull CategorieHolder holder, int position) {

        holder.bind(getItem(position));
    }

    /* IMPLEMENTATION OF DIFFUTIL, ITEMCALLBACK FOR CALCULATING THE DIFF BETWEEN TWO ITEMS IN A LIST*/
    public static class CategorieDiff extends DiffUtil.ItemCallback<Categorie> {

        @Override
        public boolean areItemsTheSame(@NonNull Categorie oldItem, @NonNull Categorie newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Categorie oldItem, @NonNull Categorie newItem) {
            return oldItem.equals(newItem);
        }
    }
}
