package website.livingRoom.soliCatering.view.categorie.rv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.ModelCategorieBinding;
import website.livingRoom.soliCatering.model.entitys.Categorie;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.utile.Helper;

public class CategorieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    private final ModelCategorieBinding binding;
    private Categorie categorie;


    //CONTRUCTOR
    public CategorieHolder(ModelCategorieBinding modelCategorieBinding) {
        super(modelCategorieBinding.getRoot());

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

        Helper.fixWidth(binding.mockViewFormCategorie);
        Helper.bindPicassoImage(categorie.getNomPic(),binding.imageViewCat);
        //SET DATA IN VIEW
        binding.setCategorie(categorie);

        upDateClick();
    }

    private void upDateClick() {
        //BLOCK CLICK AND MAKE ITEM GRIS
        if (categorie.getPoint() <= ConteurRepository.getPointReste()) {

            changeVisibility(R.color.white_100, true, Helper.getColor(R.color.green_100));

        }
        else {
            //MAKE ITEM GRIS AND POINT GREEN
            changeVisibility(R.color.grey_100, false, Helper.getColor(R.color.red_100));
        }

    }

    private void changeVisibility(int backGroundColorId, boolean clickable, int pointColorId) {
        //Change visibility and point color
        Helper.blockItem(backGroundColorId,clickable,itemView,binding.cardViewCategorie);
        binding.textViewPointCat.setTextColor(pointColorId);
    }

    @Override
    public void onClick(View v) {
        ConteurRepository.setCategorieActuel(categorie.getPoint());
        Helper.naviguer(R.id.action_navigation_categorie_to_navigation_plat);
    }
}
