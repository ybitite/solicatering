package website.livingRoom.soliCatering.ui.historique.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;

import website.livingRoom.soliCatering.databinding.ModelPanierBinding;
import website.livingRoom.soliCatering.db.entitys.ArticlePanierAndPlat;
import website.livingRoom.soliCatering.db.entitys.PanierWithAarticlePanierAndPlat;
import website.livingRoom.soliCatering.utile.Helper;

import java.util.List;


public class PanierHolder extends RecyclerView.ViewHolder implements ItemClickListener {

    //FIELD
    private final Context context;
    private final ModelPanierBinding binding;

    public PanierHolder(ModelPanierBinding modelPanierBinding) {
        super(modelPanierBinding.getRoot());

        //GET CONTEXT TO USE LATER
        context = modelPanierBinding.getRoot().getContext();

        binding =modelPanierBinding;
    }

    @Override
    public void onItemClick(View v, int pos) {    }

    public static PanierHolder create(ViewGroup parent) {
        // instantiate binding object
        ModelPanierBinding modelPanierBinding = ModelPanierBinding
                .inflate(LayoutInflater.from(parent.getContext()));

        return new PanierHolder(modelPanierBinding);
    }

    public void bind(PanierWithAarticlePanierAndPlat panierWithAarticlePanierAndPlat) {

        //push data to actualise ui
        binding.setPanier(panierWithAarticlePanierAndPlat.panier);
        binding.imageViewMenuP.setImageResource(Helper.idResource(panierWithAarticlePanierAndPlat.panier.getNomPic()));

        //bind imbricated recycle view
        bindSecondRecycleView(panierWithAarticlePanierAndPlat.listArticlePanierAndPlat);

        //bind element of suivie commande
        bindSuiviCommande(panierWithAarticlePanierAndPlat.panier.getEtat());

        //bind button plus

        fixWhidth();
    }

    /**
     * to bind the imbricated recycle view
     **/
    void bindSecondRecycleView(List<ArticlePanierAndPlat> articlePanierAndPlat) {
        //instantiate rv
        RecyclerView rv = binding.miniAPRecyclerView;

        //set adapter to rv
        final MiniArticlePanierListAdapter miniArticlePanierListAdapter =
                new MiniArticlePanierListAdapter(new MiniArticlePanierListAdapter.ArticlePanierWithPlatDiff());

        rv.setAdapter(miniArticlePanierListAdapter);


        //set properties to rv
        rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true));
        rv.setItemAnimator(new DefaultItemAnimator());

        //  this data is already observed
        miniArticlePanierListAdapter.submitList(articlePanierAndPlat);
    }

    //bind suivi de commande

    void bindSuiviCommande(int etat) {

        /*change color of control considering the stat and save some values considering the stat
         to set later to do control**/
        switch (etat) {
            case 1:
                bindValidation(getString(R.string.validation_en_cours_label),getColor(R.color.red_100));
                break;
            case 2:
                bindPrisEnCharge(getString(R.string.prise_en_charge_label),getColor(R.color.green_100));
                break;
            case 3:
                bindPreparation(getString(R.string.preparation_en_cours_label),getColor(R.color.orange_100));
                break;
            case 4:
                bindLivrer(getString(R.string.catering_livrer_label),getColor(R.color.grey_200));
                break;
        }
    }

    private String getString(int id) {
        return  '\u2022' + " " +context.getString(id);
    }

    private int getColor(int id) {
        return ResourcesCompat.getColor(context.getResources(), id, null);
    }

    private void bindValidation(String textEtat,int colorEtat) {
        //text view titre suivie commande
        bindTextAndImageView(binding.textViewEtatValidation, binding.imageViewCBValidation);
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

    private void bindControl(TextView textView, ImageView imageView1, ImageView imageView2) {
        //bind text and image for check box
        bindTextAndImageView(textView, imageView1);
        //image view trait
        imageView2.setColorFilter(getColor(R.color.grey_400));
    }

    private void bindTextAndImageView(TextView textView, ImageView imageView) {
        //text view check box
        textView.setTextColor(getColor(R.color.grey_400));
        //image view check box
        imageView.setImageResource(R.drawable.checkbox_on_background);
    }

    private void bindTextViewEtat(String textEtat, int colorEtat) {
        binding.textViewEtat.setText(textEtat);
        binding.textViewEtat.setTextColor(colorEtat);
    }

    private void fixWhidth() {
        //FIX WIDTH OF CARD TO WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.cardViewPanier.setMinimumWidth(width);
    }

}
