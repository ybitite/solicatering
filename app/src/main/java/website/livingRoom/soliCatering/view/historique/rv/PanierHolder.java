package website.livingRoom.soliCatering.view.historique.rv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.ModelPanierBinding;
import website.livingRoom.soliCatering.model.entitys.ArticlePanierAndPlat;
import website.livingRoom.soliCatering.model.entitys.PanierWithAarticlePanierAndPlat;
import website.livingRoom.soliCatering.utile.Helper;
import website.livingRoom.soliCatering.view.panier.rvpanier.ArticlePanierListAdapter;


public class PanierHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    //FIELD
    private final ModelPanierBinding binding;

    public PanierHolder(ModelPanierBinding modelPanierBinding) {
        super(modelPanierBinding.getRoot());


        binding =modelPanierBinding;
    }

    public static PanierHolder create(ViewGroup parent) {
        // instantiate binding object
        ModelPanierBinding modelPanierBinding = ModelPanierBinding
                .inflate(LayoutInflater.from(parent.getContext()));

        return new PanierHolder(modelPanierBinding);
    }

    public void bind(PanierWithAarticlePanierAndPlat panierWithAarticlePanierAndPlat) {

        Helper.fixWidth(binding.mockViewFormPanier);
        Helper.bindPicassoImage(panierWithAarticlePanierAndPlat.panier.getNomPic(),binding.imageViewMenuP);

        //push data to actualise ui
        binding.setPanier(panierWithAarticlePanierAndPlat.panier);

        //bind element of suivie commande
        bindSuiviCommande(panierWithAarticlePanierAndPlat.panier.getEtat());

        //bind imbricated recycle view
        bindSecondRecycleView(panierWithAarticlePanierAndPlat.listArticlePanierAndPlat);


    }

    /**
     * to bind the imbricated recycle view
     **/
    void bindSecondRecycleView(List<ArticlePanierAndPlat> articlePanierAndPlat) {


        //set adapter to rv
        final MiniArticlePanierListAdapter miniArticlePanierListAdapter =
                new MiniArticlePanierListAdapter(new ArticlePanierListAdapter.ArticlePanierWithPlatDiff());

        binding.miniAPRecyclerView.setAdapter(miniArticlePanierListAdapter);


        //set properties to rv
        binding.miniAPRecyclerView.setLayoutManager(new LinearLayoutManager(Helper.getContext(), LinearLayoutManager.HORIZONTAL, true));
        binding.miniAPRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //  this data is already observed
        miniArticlePanierListAdapter.submitList(articlePanierAndPlat);
    }

    //bind suivi de commande

    void bindSuiviCommande(int etat) {

        /*change color of control considering the stat and save some values considering the stat
         to set later to do control**/
        switch (etat) {
            case 1:
                bindValidation(getString(R.string.validation_en_cours_label),Helper.getColor(R.color.red_100));
                break;
            case 2:
                bindPrisEnCharge(getString(R.string.prise_en_charge_label),Helper.getColor(R.color.green_100));
                break;
            case 3:
                bindPreparation(getString(R.string.preparation_en_cours_label),Helper.getColor(R.color.orange_100));
                break;
            case 4:
                bindLivrer(getString(R.string.catering_livrer_label),Helper.getColor(R.color.grey_200));
                break;
        }
    }

    private String getString(int id) {
        return  '\u2022' + " " +Helper.getContext().getString(id);
    }


    private void bindValidation(String textEtat,int colorEtat) {
        //text view titre suivie commande
        bindCheckBoxEtat(binding.textViewEtatValidation, binding.imageViewCBValidation);
        //text view etat d avencment
        bindTextViewEtat(textEtat,colorEtat);
    }

    private void bindPrisEnCharge(String textEtat,int colorEtat) {
        //bind validation
        bindValidation(textEtat,colorEtat);
        //bind control
        bindControl(binding.textViewEtatPrisEnCharge, binding.imageViewCBPriseEnCharge, binding.imageViewTrait1);
        //text view état d'avancement
        bindTextViewEtat(textEtat,colorEtat);
    }

    private void bindPreparation(String textEtat,int colorEtat) {
        //bind prise en charge
        bindPrisEnCharge(textEtat,colorEtat);
        //bind control
        bindControl(binding.textViewEtatPreparation, binding.imageViewCBPreparation, binding.imageViewTrait2);
        //text view état d'avancement
        bindTextViewEtat(textEtat,colorEtat);
    }

    private void bindLivrer(String textEtat,int colorEtat) {
        //bind preparation
        bindPreparation(textEtat,colorEtat);
        //bind control
        bindControl(binding.textViewEtatLivrer, binding.imageViewCBLivrer, binding.imageViewTrait3);
        //text view état d'avancement
        bindTextViewEtat(textEtat,colorEtat);
    }

    private void bindControl(TextView textViewEtat, ImageView imageViewCheckBox, ImageView imageViewTrait) {
        //bind text and image for check box
        bindCheckBoxEtat(textViewEtat, imageViewCheckBox);
        //image view trait
        imageViewTrait.setColorFilter(Helper.getColor(R.color.grey_400));
    }

    private void bindCheckBoxEtat(TextView textViewEtat, ImageView imageViewCheckBox) {
        //text view check box
        textViewEtat.setTextColor(Helper.getColor(R.color.grey_400));
        //image view check box
        imageViewCheckBox.setImageResource(R.drawable.checkbox_on_background);
    }

    private void bindTextViewEtat(String textEtat, int colorEtat) {
        binding.textViewEtat.setText(textEtat);
        binding.textViewEtat.setTextColor(colorEtat);
    }

    @Override
    public void onClick(View v) {

    }
}
