package website.livingRoom.soliCatering.view.panier.rvpanier;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.model.entitys.ArticlePanierAndPlat;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;
import website.livingRoom.soliCatering.viewModel.PanierViewModel;

public class ArticlePanierListAdapter extends ListAdapter<ArticlePanierAndPlat, ArticlePanierHolder> {
    //FIELD
    final PanierViewModel panierViewModel;
    final ConteurViewModel conteurViewModel;
    private ArticlePanierHolder holder;


    //CREATE SIMPLE CALLBACK TO DELETE ITEM ON SWIPED LEF OR RIGHT
    public ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                holder.deleteArticlePanier(getItem(viewHolder.getLayoutPosition()));
        }
        @Override
        public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof  ArticlePanierPiedHolder) return 0;
            return super.getSwipeDirs(recyclerView, viewHolder);
        }

    };

    //CONSTRUCTOR
    public ArticlePanierListAdapter(@NonNull DiffUtil.ItemCallback<ArticlePanierAndPlat> diffCallback, PanierViewModel panierViewModel, ConteurViewModel conteurViewModel) {
        super(diffCallback);
        this.panierViewModel = panierViewModel;
        this.conteurViewModel = conteurViewModel;

    }

    //METHODE
    @NonNull
    @Override
    public ArticlePanierHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return ArticlePanierHolder.create(parent,conteurViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlePanierHolder holder, int position) {
        this.holder=holder;
        holder.bind(getItem(position), panierViewModel);
    }

    /* IMPLEMENTATION OF DIFFUTIL, ITEMCALLBACK FOR CALCULATING THE DIFF BETWEEN OLD AND NEW ITEM*/
    public static class ArticlePanierWithPlatDiff extends DiffUtil.ItemCallback<ArticlePanierAndPlat> {
        @Override
        public boolean areItemsTheSame(@NonNull ArticlePanierAndPlat oldItem, @NonNull ArticlePanierAndPlat newItem) {
            return oldItem.articlePanier.getIdPlats() == newItem.articlePanier.getIdPlats();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ArticlePanierAndPlat oldItem, @NonNull ArticlePanierAndPlat newItem) {
            return oldItem.articlePanier.getNombrePlat() == oldItem.articlePanier.getNombrePlat();
        }
    }
}