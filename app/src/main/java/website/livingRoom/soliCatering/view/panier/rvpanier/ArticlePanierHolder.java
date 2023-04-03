package website.livingRoom.soliCatering.view.panier.rvpanier;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.databinding.ModelArticlePanierBinding;
import website.livingRoom.soliCatering.model.entitys.ArticlePanierAndPlat;
import website.livingRoom.soliCatering.repository.ArticlePanierRepository;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;
import website.livingRoom.soliCatering.viewModel.PanierViewModel;
import website.livingRoom.soliCatering.utile.Helper;

public class ArticlePanierHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    private final ModelArticlePanierBinding binding;

    private final ConteurViewModel conteurViewModel;

    //CONSTRUCTOR
    public ArticlePanierHolder(ModelArticlePanierBinding modelArticlePanierBinding, ConteurViewModel conteurViewModel) {
        super(modelArticlePanierBinding.getRoot());

        this.conteurViewModel=conteurViewModel;

        // instantiate binding object
        binding = modelArticlePanierBinding;

        //SET CLICK LISTENER IN ITEM
        itemView.setOnClickListener(this);
    }

    public static ArticlePanierHolder create(ViewGroup parent, ConteurViewModel conteurViewModel) {
        //GENERATE CLASS FROM MODEL ARTICLE PANIER
        ModelArticlePanierBinding modelArticlePanierBinding = ModelArticlePanierBinding
                .inflate(LayoutInflater.from(parent.getContext()));

        return new ArticlePanierHolder(modelArticlePanierBinding,conteurViewModel);
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
        int position = getBindingAdapterPosition();
        if (position > -1){
            int newNumberPlat = panierViewModel.decrementNbPlat(position);
            if (newNumberPlat != 0) {
                //IF THE DECREMENT WAS DONE UPDATE CONTROL
                binding.tvNbrPlatAP.setText(String.valueOf(newNumberPlat));
            }
            else {
                //IF JUST ONE ARTICLE STAY DELETE IT
                deleteArticlePanier(articlePanierAndPlat);
            }
            //save date
            conteurViewModel.saveConteur();
        }
    }

    private void bindIncrement(PanierViewModel panierViewModel) {
        binding.btIncrement.setOnClickListener(v -> increment(panierViewModel));
    }

    private void increment(PanierViewModel panierViewModel) {
        //IF THE INCREMENT WAS DONE UPDATE CONTROL
        int newNumberPlat = panierViewModel.incrimenteNbPlat(getBindingAdapterPosition());
        if (newNumberPlat != 0) {
            binding.tvNbrPlatAP.setText(String.valueOf(newNumberPlat));
            //save date
            conteurViewModel.saveConteur();
        }
    }

    public void deleteArticlePanier(ArticlePanierAndPlat articlePanierAndPlat) {
        //DELETE ARTICLE PANIER FROM DATA BASE
        ArticlePanierRepository articlePanierRepository = new ArticlePanierRepository(binding.getRoot().getContext());
        articlePanierRepository.deleteArticlePanier(articlePanierAndPlat.articlePanier);

        //UPDATE RESTE POINT IN CONTEUR
        conteurViewModel.getConteur().upDatePointReste(articlePanierAndPlat.plat.getPoint()
                * articlePanierAndPlat.articlePanier.getNombrePlat());
    }

    @Override
    public void onClick(View v) {

    }
}