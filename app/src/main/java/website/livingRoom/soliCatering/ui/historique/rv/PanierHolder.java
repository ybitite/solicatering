package website.livingRoom.soliCatering.ui.historique.rv;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
    private static ModelPanierBinding binding;
    private  int grey_400;

    public PanierHolder(@NonNull View itemView, ModelPanierBinding modelPanierBinding) {
        super(itemView);

        //GET CONTEXT TO USE LATER
        context = itemView.getContext();

        binding =modelPanierBinding;

    }

    @Override
    public void onItemClick(View v, int pos) {

    }

    public static PanierHolder create(ViewGroup parent) {
        // instantiate binding object
        ModelPanierBinding modelPanierBinding = ModelPanierBinding.inflate(LayoutInflater.from(parent.getContext()));

        return new PanierHolder(modelPanierBinding.getRoot(),modelPanierBinding);
    }


    public void bind(PanierWithAarticlePanierAndPlat panierWithAarticlePanierAndPlat) {

        //get color from resources
        grey_400 = ResourcesCompat.getColor(context.getResources(), R.color.grey_400, null);

        //FIX WIDTH OF CARD TO WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.cardViewPanier.setMinimumWidth(width);

        //push data to actualise ui
        binding.setPanier(panierWithAarticlePanierAndPlat.panier);
        binding.imageViewMenuP.setImageResource(Helper.idResource(panierWithAarticlePanierAndPlat.panier.getNomPic()));

        //bind imbricated recycle view
        bindSecondRecycleView(panierWithAarticlePanierAndPlat.listArticlePanierAndPlat);

        //bind element of suivie commande
        bindSuiviCommande(panierWithAarticlePanierAndPlat.panier.getEtat());

        //bind button plus
        bindButtonPlus();
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
        //local variable
        String textEtat = "";
        int colorEtat = Color.RED;
        int visibilityValue = View.GONE;

        /*change color of control considering the stat and save some values considering the stat
         to set later to do control**/
        switch (etat) {
            case 1:
                bindValidation();
                textEtat = context.getString(R.string.validation_en_cours_label);
                colorEtat = ResourcesCompat.getColor(context.getResources(), R.color.red_100, null);
                visibilityValue = View.VISIBLE;
                break;
            case 2:
                bindPrisEnCharge();
                textEtat = context.getString(R.string.prise_en_charge_label);
                colorEtat = ResourcesCompat.getColor(context.getResources(), R.color.green_100, null);
                visibilityValue = View.VISIBLE;
                break;
            case 3:
                bindPreparation();
                textEtat = context.getString(R.string.preparation_en_cours_label);
                colorEtat = ResourcesCompat.getColor(context.getResources(), R.color.orange_100, null);
                break;
            case 4:
                bindLivrer();
                textEtat = context.getString(R.string.catering_livrer_label);
                colorEtat = ResourcesCompat.getColor(context.getResources(), R.color.grey_200, null);
                break;
        }
        //set value getting before to control
        //text view etat
        String text='\u2022' + " " + textEtat;
        binding.textViewEtat.setText(text);
        binding.textViewEtat.setTextColor(colorEtat);

        //constraint layout info
        binding.constraintLayoutInfoP.setVisibility(visibilityValue);

        //recycle view contenu
        binding.miniAPRecyclerView.setVisibility(visibilityValue);

        //button ajouter
        if (visibilityValue == View.GONE) {
            binding.imageViewPlusIL.setImageResource(R.drawable.plus_gris);
            binding.imageViewPlusContenu.setImageResource(R.drawable.plus_gris);
        } else {
            binding.imageViewPlusIL.setImageResource(R.drawable.minus_gris);
            binding.imageViewPlusContenu.setImageResource(R.drawable.minus_gris);
        }
    }

    private void bindValidation() {
        //text view titre suivie commande
        binding.textViewEtatValidation.setTextColor(grey_400);

        //image view check box
        binding.imageViewCBValidation.setImageResource(R.drawable.checkbox_on_background);

    }

    private void bindPrisEnCharge() {
        //bind validation
        bindValidation();

        //text view titre suivie commande
        binding.textViewEtatPrisEnCharge.setTextColor(grey_400);

        //image view check box
        binding.imageViewCBPriseEnCharge.setImageResource(R.drawable.checkbox_on_background);

        //image view trait
        binding.imageViewTrait1.setColorFilter(grey_400);
    }

    private void bindPreparation() {
        //bind prise en charge
        bindPrisEnCharge();

        //text view titre suivie commande
        binding.textViewEtatPreparation.setTextColor(grey_400);

        //image view check box
        binding.imageViewCBPreparation.setImageResource(R.drawable.checkbox_on_background);

        //image view trait
        binding.imageViewTrait2.setColorFilter(grey_400);
    }

    private void bindLivrer() {
        //bind preparation
        bindPreparation();

        //text view titre suivie commande
        binding.textViewEtatLivrer.setTextColor(grey_400);

        //image view check box
        binding.imageViewCBLivrer.setImageResource(R.drawable.checkbox_on_background);

        //image view trait
        binding.imageViewTrait3.setColorFilter(grey_400);

        //constraint layout
        binding.constraintLayoutSuivieCommande.setVisibility(View.GONE);

        //text view label
        binding.textViewTitreEtatLivraison.setVisibility(View.GONE);
    }

    private void bindButtonPlus() {

        //button to show more information livraison
        ConstraintLayout constraintLayoutIL = binding.constraintLayoutInfoP;
        ImageView imageViewPlusIL = binding.imageViewPlusIL;
        imageViewPlusIL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (constraintLayoutIL.getVisibility() == View.GONE) {
                    constraintLayoutIL.setVisibility(View.VISIBLE);
                    imageViewPlusIL.setImageResource(R.drawable.minus_gris);
                } else {
                    constraintLayoutIL.setVisibility(View.GONE);
                    imageViewPlusIL.setImageResource(R.drawable.plus_gris);
                }
            }
        });

        //button to show more contenu
        RecyclerView miniAPRecyclerView = binding.miniAPRecyclerView;
        ImageView imageViewPlusContenu = binding.imageViewPlusContenu;
        imageViewPlusContenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (miniAPRecyclerView.getVisibility() == View.GONE) {
                    miniAPRecyclerView.setVisibility(View.VISIBLE);
                    imageViewPlusContenu.setImageResource(R.drawable.minus_gris);
                } else {
                    miniAPRecyclerView.setVisibility(View.GONE);
                    imageViewPlusContenu.setImageResource(R.drawable.plus_gris);
                }
            }
        });
    }

}
