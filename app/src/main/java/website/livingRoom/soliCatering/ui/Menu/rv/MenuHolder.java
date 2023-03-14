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
    website.livingRoom.soliCatering.ui.categorie.rv.ItemClickListener itemClickListener;
    static ModelMenuBinding binding;
    static Context context;
    ArticlePanierRepository articlePanierRepository;

    //CONSTRUCTOR
    public MenuHolder(View itemView) {
        super(itemView);

        //instantiate article Panier Repository
        articlePanierRepository = new ArticlePanierRepository(context);
        //GET CONTEXT TO USE LATER
        context = binding.getRoot().getContext();
        //SET CLICK LISTENER IN ITEM
        itemView.setOnClickListener(this);
        //FIX WIDTH OF CARD TO WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.cardViewMenu.setMinimumWidth(width);
        binding.imageViewiFormMenu.setMinimumWidth(width);

    }

    //METHODE
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(website.livingRoom.soliCatering.ui.categorie.rv.ItemClickListener ic) {
        this.itemClickListener = ic;
    }

    static MenuHolder create(ViewGroup parent) {
        //GENERATE CLASS FROM MODEL EVENT
        binding = ModelMenuBinding
                .inflate(LayoutInflater.from(parent.getContext()));
        return new MenuHolder(binding.getRoot());
    }

    public void bind(Menu menu) {

        //SET DATA IN VIEW
        binding.textViewNomMenu.setText(menu.getNom());
        binding.textViewDiscriptionMenu.setText(menu.getDiscription());
        binding.textViewPrixMenu.setText(String.valueOf(menu.getPrix()));
        binding.textViewPointMenu.setText(String.valueOf(menu.getPoint()));
        binding.textViewInfoMenu.setText(menu.getInfo());
        binding.imageViewIconMenu.setImageResource(Helper.idResource(menu.getNomPic()));
        //CHANGE COLOR OF ACTUEL MENU
        int menuActuel = ConteurRepository.getPointDepart();
        if (menu.getPoint() == menuActuel) {
            //CHANGE COLOR OF ITEM WHEN IS CLICKED
            binding.constraintLayoutMenu.setBackgroundColor(context.getResources().getColor(R.color.grey_100));
        } else
            //CHANGE COLOR OF ITEM WHEN IS NOT CLICKED
            binding.constraintLayoutMenu.setBackgroundColor(context.getResources().getColor(R.color.white_100));

        //select menu
        setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //IF USER CLICK IN THE NEW MENU A NEW CONTEUR START AGAIN
                if (menu.getPoint() != menuActuel) {
                    //START NEW CONTEUR
                    ConteurRepository.setConteur(menu.getNom(), menu.getPoint());

                    //clear list of article panier
                    articlePanierRepository.deleteCurentPanier(ConteurRepository.getPanierActuel());
                }
                //NAVIGATE TO CATEGORIE
                Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main)
                        .navigate(R.id.action_navigation_menu_to_navigation_categorie);

            }
        });
    }

}
