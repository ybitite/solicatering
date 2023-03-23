package website.livingRoom.soliCatering.ui.Menu.rv;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;

import website.livingRoom.soliCatering.databinding.ModelMenuBinding;
import website.livingRoom.soliCatering.db.entitys.Menu;
import website.livingRoom.soliCatering.repository.ArticlePanierRepository;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.ui.categorie.rv.ItemClickListener;
import website.livingRoom.soliCatering.utile.Helper;

public class MenuHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    private ItemClickListener itemClickListener;
    private final ModelMenuBinding binding;
    private final Context context;
    ArticlePanierRepository articlePanierRepository;

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
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }

    static MenuHolder create(ViewGroup parent) {
        //INSTANTIATE BINDING OBJECT CLASS FROM MODEL MENU
        ModelMenuBinding modelMenuBinding = ModelMenuBinding
                .inflate(LayoutInflater.from(parent.getContext()));
        return new MenuHolder(modelMenuBinding);
    }

    public void bind(Menu menu) {
        fixWidth();

        //SET DATA IN VIEW
        binding.setMenu(menu);
        binding.imageViewIconMenu.setImageResource(Helper.idResource(menu.getNomPic()));

        if (menu.getPoint() == ConteurRepository.getPointDepart()) {
            //CHANGE COLOR OF ITEM WHEN IS CLICKED
            updateBLinearLayoutColor(R.color.grey_100);
        }
        else
            //CHANGE COLOR OF ITEM WHEN IS NOT CLICKED
            updateBLinearLayoutColor(R.color.white_100);

        updateItemClick(menu, ConteurRepository.getPointDepart());
    }

    private void updateItemClick(Menu menu, int menuActuel) {
        //select menu
        setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                updateConteur(menu, menuActuel);
                //NAVIGATE TO CATEGORIE
                Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main)
                        .navigate(R.id.action_navigation_menu_to_navigation_categorie);
            }
        });
    }

    private void updateBLinearLayoutColor(int grey_100) {
        binding.linearLayoutMenu.setBackgroundColor(context.getResources().getColor(grey_100));
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

    private void fixWidth() {
        //FIX WIDTH OF CARD TO WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.cardViewMenu.setMinimumWidth(width);
        binding.imageViewiFormMenu.setMinimumWidth(width);
    }

}
