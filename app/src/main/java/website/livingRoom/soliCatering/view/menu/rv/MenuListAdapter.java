package website.livingRoom.soliCatering.view.menu.rv;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import website.livingRoom.soliCatering.model.entitys.Menu;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;

public class MenuListAdapter extends ListAdapter<Menu, MenuHolder> {

    ConteurViewModel conteurViewModel;

    //CONSTRUCTOR
    public MenuListAdapter(@NonNull DiffUtil.ItemCallback<Menu> diffCallback, ConteurViewModel conteurViewModel) {
        super(diffCallback);
        this.conteurViewModel = conteurViewModel;
    }

    //METHODE
    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MenuHolder.create(parent,conteurViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, int position) {
        holder.bind(getItem(position));
    }

    /* IMPLEMENTATION OF DIFFUTIL, ITEMCALLBACK FOR CALCULATING THE DIFF BETWEEN TWO ITEMS IN A LIST*/
    public static class MenuDiff extends DiffUtil.ItemCallback<Menu> {

        @Override
        public boolean areItemsTheSame(@NonNull Menu oldItem, @NonNull Menu newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Menu oldItem, @NonNull Menu newItem) {
            return oldItem.equals(newItem);
        }
    }
}
