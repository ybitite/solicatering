package website.livingRoom.soliCatering.view.plat.rv;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import website.livingRoom.soliCatering.model.entitys.Plat;
import website.livingRoom.soliCatering.viewModel.PlatViewModel;

public class PlatsListAdapter extends ListAdapter<Plat, PlatsHolder> {
    //FIELD
    final PlatViewModel platViewModel;

    //CONSTRUCTOR
    public PlatsListAdapter(@NonNull DiffUtil.ItemCallback<Plat> diffCallback, PlatViewModel platViewModel) {
        super(diffCallback);
        this.platViewModel = platViewModel;

    }

    //METHODE
    @NonNull
    @Override
    public PlatsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return PlatsHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(PlatsHolder holder, int position) {
        holder.bind(getItem(position), platViewModel);
    }

    /* IMPLEMENTATION OF DIFFUTIL, ITEMCALLBACK FOR CALCULATING THE DIFF BETWEEN TWO ITEMS IN A LIST*/
    public static class platDiff extends DiffUtil.ItemCallback<Plat> {

        @Override
        public boolean areItemsTheSame(@NonNull Plat oldItem, @NonNull Plat newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Plat oldItem, @NonNull Plat newItem) {
            return oldItem.equals(newItem);
        }
    }

}