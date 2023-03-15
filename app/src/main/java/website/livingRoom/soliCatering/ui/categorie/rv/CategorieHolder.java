package website.livingRoom.soliCatering.ui.categorie.rv;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.ModelCategorieBinding;
import website.livingRoom.soliCatering.db.entitys.Categorie;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.utile.Helper;

public class CategorieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    private ItemClickListener itemClickListener;
    private final ModelCategorieBinding binding;
    private final Context context;

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
    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(view, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }

    public static CategorieHolder create(ViewGroup parent) {
        //INSTANTIATE BINDING OBJECT CLASS FROM MODEL PANIER
        ModelCategorieBinding modelCategorieBinding = ModelCategorieBinding
                .inflate(LayoutInflater.from(parent.getContext()));

        return new CategorieHolder(modelCategorieBinding);
    }

    public void bind(Categorie categorie) {
        fixWidth();

        //SET DATA IN VIEW
        binding.setCategorie(categorie);
        binding.imageViewCat.setImageResource(Helper.idResource(categorie.getNomPic()));


        //BLOCK CLICK AND MAKE ITEM GRIS
        if (categorie.getPoint() <= ConteurRepository.getPointReste()) {

            changeVisibility(View.INVISIBLE, true, context.getResources().getColor(R.color.green_100));

            setItemClickListener((v, pos) -> {
                ConteurRepository.setCategorieActuel(categorie.getPoint());
                Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_categorie_to_navigation_plat);
            });
        }
        else {
            //MAKE ITEM GRIS AND POINT GREEN
            changeVisibility(View.VISIBLE, false, Color.RED);
        }
    }

    private void changeVisibility(int invisible, boolean clickable, int context) {
        //Change visibility and point color
        binding.griserItemCat.setVisibility(invisible);
        binding.linearLayoutCategorie.setClickable(clickable);
        binding.textViewPointCat.setTextColor(context);
    }

    private void fixWidth() {
        //FIX WIDTH OF CARD TO WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.constraintLayoutCategorie.setMinimumWidth(width);
        binding.cardViewCategorie.setMinimumWidth(width);
    }
}
