package website.livingRoom.soliCatering.view.panier.rvpanier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.databinding.ModelArticlePanierBinding;
import website.livingRoom.soliCatering.model.entitys.ArticlePanierAndPlat;
import website.livingRoom.soliCatering.repository.ArticlePanierRepository;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.viewModel.PanierViewModel;
import website.livingRoom.soliCatering.utile.Helper;

public class ArticlePanierHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    private final ModelArticlePanierBinding binding;
    private final Context context;


    //CONSTRUCTOR
    public ArticlePanierHolder(ModelArticlePanierBinding modelArticlePanierBinding) {
        super(modelArticlePanierBinding.getRoot());

        //GET CONTEXT TO USE LATER
        context = modelArticlePanierBinding.getRoot().getContext();

        // instantiate binding object
        binding = modelArticlePanierBinding;

        //SET CLICK LISTENER IN ITEM
        itemView.setOnClickListener(this);
    }

    public static ArticlePanierHolder create(ViewGroup parent) {
        //GENERATE CLASS FROM MODEL ARTICLE PANIER
        ModelArticlePanierBinding modelArticlePanierBinding = ModelArticlePanierBinding
                .inflate(LayoutInflater.from(parent.getContext()));

        return new ArticlePanierHolder(modelArticlePanierBinding);
    }

    public void bind(ArticlePanierAndPlat articlePanierAndPlat, PanierViewModel panierViewModel) {

        Helper.fixWidth(binding.mockViewFormArticlePanier);
        Helper.bindPicassoImage(articlePanierAndPlat.plat.getNomPic(),binding.imageViewTitreAP);

        //SET DATA IN VIEW
        binding.setArticlePanierAndPlat(articlePanierAndPlat);

        bindIncrement(panierViewModel);
        bindDecrement(articlePanierAndPlat, panierViewModel);
    }

    private void bindDecrement(ArticlePanierAndPlat articlePanierAndPlat, PanierViewModel panierViewModel) {

        //SET CLICK LISTENER to DECREMENT BUTTON
        binding.btDecrement.setOnClickListener(v -> decrement(articlePanierAndPlat, panierViewModel));
    }

    private void decrement(ArticlePanierAndPlat articlePanierAndPlat, PanierViewModel panierViewModel) {
        int nbrPlat = panierViewModel.decrementNbPlat(getBindingAdapterPosition());
        if (nbrPlat != 0) {
            //IF THE DECREMENT WAS DONE UPDATE CONTROL
            binding.tvNbrPlatAP.setText(String.valueOf(nbrPlat));
        }
        else {
            //IF JUST ONE ARTICLE STAY DELETE IT
            deleteArticlePanier(articlePanierAndPlat);
        }
    }

    private void bindIncrement(PanierViewModel panierViewModel) {
        binding.btIncrement.setOnClickListener(v -> increment(panierViewModel));
    }

    private void increment(PanierViewModel panierViewModel) {
        //IF THE INCREMENT WAS DONE UPDATE CONTROL
        int nbrPlat = panierViewModel.incrimenteNbPlat(getBindingAdapterPosition());
        if (nbrPlat != 0) {
            binding.tvNbrPlatAP.setText(String.valueOf(nbrPlat));
        }
    }

    public void deleteArticlePanier(ArticlePanierAndPlat articlePanierAndPlat) {
        //DELETE ARTICLE PANIER FROM DATA BASE
        ArticlePanierRepository articlePanierRepository = new ArticlePanierRepository(context);
        articlePanierRepository.deleteArticlePanier(articlePanierAndPlat.articlePanier);

        //UPDATE RESTE POINT IN CONTEUR
        ConteurRepository.upDatePointReste(articlePanierAndPlat.plat.getPoint());
    }

    @Override
    public void onClick(View v) {

    }
}