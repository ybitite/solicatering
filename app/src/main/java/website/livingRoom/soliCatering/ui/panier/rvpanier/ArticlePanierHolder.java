package website.livingRoom.soliCatering.ui.panier.rvpanier;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.databinding.ModelArticlePanierBinding;
import website.livingRoom.soliCatering.db.entitys.ArticlePanierAndPlat;
import website.livingRoom.soliCatering.repository.ArticlePanierRepository;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.ui.categorie.rv.ItemClickListener;
import website.livingRoom.soliCatering.ui.panier.PanierViewModel;
import website.livingRoom.soliCatering.utile.Helper;

public class ArticlePanierHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    website.livingRoom.soliCatering.ui.categorie.rv.ItemClickListener itemClickListener;
    static ModelArticlePanierBinding binding;

    static Context context;


    //CONTRUCTOR
    public ArticlePanierHolder(View itemView) {
        super(itemView);
        //GET CONTEXT TO USE LATER
        context = binding.getRoot().getContext();
        //SET CLICK LISTENER IN ITEM
        itemView.setOnClickListener(this);
        //FIX WIDTH OF CARD TO WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.textViewTitreAP.setWidth(width);
        binding.cardViewArticlePanier.setMinimumWidth(width);

    }

    //METHODE
    //@Override
    //public void onClick(View v) { this.itemClickListener.onItemClick(v,getLayoutPosition()); }
    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }

    public static ArticlePanierHolder create(ViewGroup parent) {
        //GENERATE CLASS FROM MODEL ARTICLE PANIER
        binding = ModelArticlePanierBinding
                .inflate(LayoutInflater.from(parent.getContext()));
        return new ArticlePanierHolder(binding.getRoot());
    }

    public void bind(ArticlePanierAndPlat articlePanierAndPlat, PanierViewModel panierViewModel) {
        //SET DATA IN VIEW
        binding.textViewTitreAP.setText(articlePanierAndPlat.plat.getNom());
        binding.textViewPointAP.setText(String.valueOf(articlePanierAndPlat.plat.getPoint() +" P"));
        binding.tvNbrPlatAP.setText(String.valueOf(articlePanierAndPlat.articlePanier.getNombrePlat()));
        binding.imageViewTitreAP.setImageResource(Helper.idResource(articlePanierAndPlat.plat.getNomPic()));

        //SET CLICK LISTENER to INCREMENT BUTTON
        TextView tvNbrPlat = binding.tvNbrPlatAP;
        binding.btIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //IF THE INCREMENT WAS DONE UPDATE CONTROL
                int nbrPlat = panierViewModel.incrimenteNbPlat(getBindingAdapterPosition());
                if (nbrPlat != 0) {
                    tvNbrPlat.setText(String.valueOf(nbrPlat));
                }
                Log.e("article panier", "adapter : " + getBindingAdapterPosition());
                Log.e("article panier", "layout : " + getLayoutPosition());
            }
        });

        //SET CLICK LISTENER to DECREMENT BUTTON
        binding.btDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nbrPlat = panierViewModel.decrementNbPlat(getBindingAdapterPosition());
                if (nbrPlat != 0) {
                    //IF THE DECREMENT WAS DONE UPDATE CONTROL
                    tvNbrPlat.setText(String.valueOf(nbrPlat));
                }
                if (nbrPlat == 0) {
                    //IF JUST ONE ARTICLE STAY DELETE IT
                    deleteArticlePanier(articlePanierAndPlat);
                }
            }

        });

    }

    public void deleteArticlePanier(ArticlePanierAndPlat articlePanierAndPlat) {
        //DELETE ARTICLE PANIER FROM DATA BASE
        ArticlePanierRepository articlePanierRepository = new ArticlePanierRepository(context);
        articlePanierRepository.deleteArticlePanier(articlePanierAndPlat.articlePanier);

        //UPDATE RESTE POINT IN CONTEUR
        ConteurRepository.upDatePointRest(articlePanierAndPlat.plat.getPoint());
    }


    @Override
    public void onClick(View v) {

    }
}