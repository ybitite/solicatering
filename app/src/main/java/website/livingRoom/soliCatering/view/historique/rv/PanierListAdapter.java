package website.livingRoom.soliCatering.view.historique.rv;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import website.livingRoom.soliCatering.model.entitys.PanierWithAarticlePanierAndPlat;

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

    /* IMPLEMENTATION OF DIFFUTIL, ITEMCALLBACK FOR CALCULATING THE DIFF BETWEEN TWO ITEMS IN A LIST*/
    public static class PanierDiff extends DiffUtil.ItemCallback<PanierWithAarticlePanierAndPlat> {
        @Override
        public boolean areItemsTheSame(@NonNull PanierWithAarticlePanierAndPlat oldItem, @NonNull PanierWithAarticlePanierAndPlat newItem) {
            return oldItem.panier.getId() == newItem.panier.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull PanierWithAarticlePanierAndPlat oldItem, @NonNull PanierWithAarticlePanierAndPlat newItem) {
            return oldItem.panier.equals(newItem.panier);
        }
    }
}
