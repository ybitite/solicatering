package website.livingRoom.soliCatering.view.categorie.rv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.ModelCategorieBinding;
import website.livingRoom.soliCatering.model.entitys.Categorie;
import website.livingRoom.soliCatering.utile.Helper;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;

public class CategorieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    private final ModelCategorieBinding binding;
    private Categorie categorie;
        private final ConteurViewModel conteurViewModel;

    //CONTRUCTOR
    public CategorieHolder(ModelCategorieBinding modelCategorieBinding, ConteurViewModel conteurViewModel) {
        super(modelCategorieBinding.getRoot());

        this.conteurViewModel = conteurViewModel;

        // instantiate binding object
        binding = modelCategorieBinding;

        //SET CLICK LISTENER IN ITEM
        itemView.setOnClickListener(this);

    }

    //METHODE


    public static CategorieHolder create(ViewGroup parent, ConteurViewModel conteurViewModel) {
        //INSTANTIATE BINDING OBJECT CLASS FROM MODEL CATEGORIE
        ModelCategorieBinding modelCategorieBinding = ModelCategorieBinding
                .inflate(LayoutInflater.from(parent.getContext()));
        return new CategorieHolder(modelCategorieBinding,conteurViewModel);
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
        //BLOCK CLICK AND MAKE ITEM GRIS when not point enough for categorise
        if (conteurViewModel.getConteur().isEnoughRestePoint(categorie.getPoint())) {

            changeVisibility(R.color.white_100, true, Helper.getColor(R.color.green_100));

        }
        else {
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
        conteurViewModel.getConteur().setCategorieActuel(categorie.getPoint());
        Helper.naviguer(R.id.action_navigation_categorie_to_navigation_plat);
    }

}
