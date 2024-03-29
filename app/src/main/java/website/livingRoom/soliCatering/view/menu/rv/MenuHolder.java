package website.livingRoom.soliCatering.view.menu.rv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.ModelMenuBinding;
import website.livingRoom.soliCatering.model.entitys.Menu;
import website.livingRoom.soliCatering.repository.ArticlePanierRepository;
import website.livingRoom.soliCatering.utile.Helper;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;

public class MenuHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    private final ModelMenuBinding binding;
    final ArticlePanierRepository articlePanierRepository;
    Menu menu;

    private ConteurViewModel conteurViewModel;

    //CONSTRUCTOR
    public MenuHolder(ModelMenuBinding modelMenuBinding, ConteurViewModel conteurViewModel) {
        super(modelMenuBinding.getRoot());

        this.conteurViewModel=conteurViewModel;

        // instantiate binding object
        binding = modelMenuBinding;

        //SET CLICK LISTENER IN ITEM
        itemView.setOnClickListener(this);

        //INSTANTIATE REPOSITORY
        articlePanierRepository = new ArticlePanierRepository(binding.getRoot().getContext());

        this.conteurViewModel = conteurViewModel;
    }

    //METHODE
    static MenuHolder create(ViewGroup parent, ConteurViewModel conteurViewModel) {
        //INSTANTIATE BINDING OBJECT CLASS FROM MODEL MENU
        ModelMenuBinding modelMenuBinding = ModelMenuBinding
                .inflate(LayoutInflater.from(parent.getContext()));
        return new MenuHolder(modelMenuBinding,conteurViewModel);
    }


    public void bind(Menu menu) {
        this.menu = menu;
        Helper.fixWidth(binding.mockViewFormMenu);

        bindControl(menu);

        if (conteurViewModel.getConteur().isSemMenu(menu.getPoint())) {
            //CHANGE COLOR OF ITEM WHEN IS CLICKED
            upDateCardViewColor(R.color.grey_100);
        }


    }

    private void bindControl(Menu menu) {
        //SET DATA IN VIEW
        binding.setMenu(menu);
        Helper.bindPicassoImage(menu.getNomPic(),binding.imageViewIconMenu);
    }

    private void upDateCardViewColor(int colorId) {
        binding.constraintLayoutMenu.setBackgroundColor(Helper.getColor(colorId));
    }


    private void updateConteur(Menu menu, int menuActuel) {
        //IF USER CLICK IN THE NEW MENU A NEW CONTEUR START AGAIN
        if (menu.getPoint() != menuActuel) {
            //START NEW CONTEUR
            conteurViewModel.setConteur(menu.getNom(), menu.getPoint());

            //clear list of article panier
            articlePanierRepository.deleteCurentPanier(conteurViewModel.getConteur().getPanierActuel());
        }
    }

    @Override
    public void onClick(View v) {
        updateConteur(menu, conteurViewModel.getConteur().getPointDepart());
        Helper.naviguer(R.id.action_navigation_menu_to_navigation_categorie);
    }

}
