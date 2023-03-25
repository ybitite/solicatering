package website.livingRoom.soliCatering.view.Menu.rv;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;

import website.livingRoom.soliCatering.databinding.ModelMenuBinding;
import website.livingRoom.soliCatering.model.entitys.Menu;
import website.livingRoom.soliCatering.repository.ArticlePanierRepository;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.utile.Helper;

public class MenuHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    private final ModelMenuBinding binding;
    private final Context context;
    ArticlePanierRepository articlePanierRepository;
    Menu menu;

    //CONSTRUCTOR
    public MenuHolder(ModelMenuBinding modelMenuBinding) {
        super(modelMenuBinding.getRoot());

        //GET CONTEXT TO USE LATER
        context = modelMenuBinding.getRoot().getContext();

        // instantiate binding object
        binding = modelMenuBinding;

        //SET CLICK LISTENER IN ITEM
        itemView.setOnClickListener(this);

        //INSTANTIATE REPOSITORY
        articlePanierRepository = new ArticlePanierRepository(context);
    }

    //METHODE

    @Override
    public void onClick(View v) {
        updateConteur(menu, ConteurRepository.getPointDepart());
        naviguer(R.id.action_navigation_menu_to_navigation_categorie);
    }

    private void naviguer(int actionId) {
        Navigation.findNavController(itemView).navigate(actionId);
    }


    static MenuHolder create(ViewGroup parent) {
        //INSTANTIATE BINDING OBJECT CLASS FROM MODEL MENU
        ModelMenuBinding modelMenuBinding = ModelMenuBinding
                .inflate(LayoutInflater.from(parent.getContext()));
        return new MenuHolder(modelMenuBinding);
    }

    public void bind(Menu menu) {
        this.menu = menu;
        Helper.fixWidth(binding.mockViewFormMenu);

        bindControl(menu);

        if (menu.getPoint() == ConteurRepository.getPointDepart()) {
            //CHANGE COLOR OF ITEM WHEN IS CLICKED
            upDateLinearLayoutColor(R.color.grey_100);
        }
        else
            //CHANGE COLOR OF ITEM WHEN IS NOT CLICKED
            upDateLinearLayoutColor(R.color.white_100);

    }

    private void bindControl(Menu menu) {
        //SET DATA IN VIEW
        binding.setMenu(menu);
        Helper.bindPicassoImage(menu.getNomPic(),binding.imageViewIconMenu);
    }

    private void upDateLinearLayoutColor(int colorId) {
        binding.linearLayoutMenu.setBackgroundColor(Helper.getColor(colorId));
    }


    private void updateConteur(Menu menu, int menuActuel) {
        //IF USER CLICK IN THE NEW MENU A NEW CONTEUR START AGAIN
        if (menu.getPoint() != menuActuel) {
            //START NEW CONTEUR
            ConteurRepository.setConteur(menu.getNom(), menu.getPoint());

            //clear list of article panier
            articlePanierRepository.deleteCurentPanier(ConteurRepository.getPanierActuel());
        }
    }

}
