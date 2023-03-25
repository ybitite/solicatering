package website.livingRoom.soliCatering.view.categorie.rv;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.ModelCategorieBinding;
import website.livingRoom.soliCatering.model.entitys.Categorie;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.utile.Helper;

public class CategorieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    private final ModelCategorieBinding binding;
    private final Context context;
    private Categorie categorie;


    //CONTRUCTOR
    public CategorieHolder(ModelCategorieBinding modelCategorieBinding) {
        super(modelCategorieBinding.getRoot());

        //GET CONTEXT TO USE LATER
        context = modelCategorieBinding.getRoot().getContext();

        // instantiate binding object
        binding = modelCategorieBinding;

        //SET CLICK LISTENER IN ITEM
        itemView.setOnClickListener(this);
    }

    //METHODE


    public static CategorieHolder create(ViewGroup parent) {
        //INSTANTIATE BINDING OBJECT CLASS FROM MODEL CATEGORIE
        ModelCategorieBinding modelCategorieBinding = ModelCategorieBinding
                .inflate(LayoutInflater.from(parent.getContext()));

        return new CategorieHolder(modelCategorieBinding);
    }

    public void bind(Categorie categorie) {
        this.categorie=categorie;

        fixWidth();

        //SET DATA IN VIEW
        binding.setCategorie(categorie);
        binding.imageViewCat.setImageResource(Helper.getIdResourceByName(categorie.getNomPic()));

        upDateClick();
    }

    private void upDateClick() {
        //BLOCK CLICK AND MAKE ITEM GRIS
        if (categorie.getPoint() <= ConteurRepository.getPointReste()) {

            changeVisibility(R.color.white_100, true, context.getResources().getColor(R.color.green_100));

        }
        else {
            //MAKE ITEM GRIS AND POINT GREEN
            changeVisibility(R.color.grey_100, false, context.getResources().getColor(R.color.red_100));
        }

    }

    private void changeVisibility(int backGroundColorId, boolean clickable, int pointColorId) {
        //Change visibility and point color
        blockItem(backGroundColorId,clickable);
        binding.textViewPointCat.setTextColor(pointColorId);
    }
    private void blockItem(int colorId, boolean clickable) {
        //MAKE ITEM GRIS AND POINT GREEN
        itemView.setClickable(clickable);
        binding.cardViewCategorie.setCardBackgroundColor(getColor(colorId));
    }
    private int getColor(int id) {
        return ResourcesCompat.getColor(context.getResources(), id, null);
    }
    private void fixWidth() {
        //FIX WIDTH OF CARD TO WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.mockViewFormCategorie.setMinimumWidth(width);
    }

    @Override
    public void onClick(View v) {
        ConteurRepository.setCategorieActuel(categorie.getPoint());
        Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_categorie_to_navigation_plat);
    }
}
