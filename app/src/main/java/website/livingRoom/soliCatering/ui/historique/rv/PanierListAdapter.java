package website.livingRoom.soliCatering.ui.historique.rv;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.db.entitys.PanierWithAarticlePanierAndPlat;
import website.livingRoom.soliCatering.repository.PanierRepository;

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
        PanierRepository panierRepository = new PanierRepository(viewHolder.itemView.getContext());
        panierRepository.deletePanier(getItem(viewHolder.getBindingAdapterPosition()).panier.getIdClientOwner());
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
