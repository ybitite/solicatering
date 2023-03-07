package website.livingRoom.soliCatering.ui.panier.rvpanier;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.db.entitys.ArticlePanierAndPlat;
import website.livingRoom.soliCatering.repository.ArticlePanierRepository;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.ui.panier.PanierViewModel;

public class ArticlePanierListAdapter extends ListAdapter<ArticlePanierAndPlat, ArticlePanierHolder> {
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
    public ArticlePanierListAdapter(@NonNull DiffUtil.ItemCallback<ArticlePanierAndPlat> diffCallback, PanierViewModel panierViewModel) {
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
        ArticlePanierRepository articlePanierRepository = new ArticlePanierRepository(viewHolder.itemView.getContext());
        articlePanierRepository.deleteArticlePanier(getItem(viewHolder.getBindingAdapterPosition()).articlePanier);

        //UPDATE RESTE POINT IN CONTEUR
        ArticlePanierAndPlat articlePanierAndPlat = getItem(viewHolder.getBindingAdapterPosition());
        ConteurRepository.upDatePointRest(articlePanierAndPlat.plat.getPoint() * articlePanierAndPlat.articlePanier.getNombrePlat());
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