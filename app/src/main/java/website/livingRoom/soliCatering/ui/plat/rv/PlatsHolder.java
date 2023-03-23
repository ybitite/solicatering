package website.livingRoom.soliCatering.ui.plat.rv;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;

import website.livingRoom.soliCatering.databinding.ModelPlatsBinding;
import website.livingRoom.soliCatering.db.entitys.Plat;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.ui.categorie.rv.ItemClickListener;
import website.livingRoom.soliCatering.ui.plat.PlatViewModel;
import website.livingRoom.soliCatering.utile.Helper;

public class PlatsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    private ItemClickListener itemClickListener;
    private final ModelPlatsBinding binding;
    private final Context context;

    public PlatsHolder(ModelPlatsBinding modelPlatsBinding) {
        super(modelPlatsBinding.getRoot());

        //GET CONTEXT TO USE LATER
        context = modelPlatsBinding.getRoot().getContext();

        // instantiate binding object
        binding = modelPlatsBinding;

        //SET CLICK LISTENER IN ITEM
        itemView.setOnClickListener(this);
    }

    //METHODE

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }

    public static PlatsHolder create(ViewGroup parent) {
        //GENERATE CLASS FROM MODEL EVENT
        ModelPlatsBinding modelPlatsBinding = ModelPlatsBinding
                .inflate(LayoutInflater.from(parent.getContext()));

        return new PlatsHolder(modelPlatsBinding);
    }

    public void bind(Plat plat, PlatViewModel platViewModel) {

        fixWidth();

        //SET DATA IN VIEW
        binding.imageViewPlat.setImageResource(Helper.idResource(plat.getNomPic()));
        binding.setPlat(plat);

        upDateIconVegi(plat);

        updateIconEpice(plat);

        updateItemClick(plat, platViewModel);
    }

    private void updateItemClick(Plat plat, PlatViewModel platViewModel) {
        /*BLOCK CLICK AND MAKE ITEM GREW WHEN THE POINT ARE NOT ENOUGH*/
        if (plat.getPoint() <= ConteurRepository.getCategorieActuel()) {

            blockItem(View.INVISIBLE, true);

            //navigate to dialog en click
            setItemClickListener((v, pos) -> {
                navigateToDialogPlat(plat, platViewModel); });
        }
        else {
            blockItem(View.VISIBLE, false);
        }
    }

    private void navigateToDialogPlat(Plat plat, PlatViewModel platViewModel) {
        //SAVE SELECTED PLAT
        platViewModel.setSelectedPlat(plat);
        //NAVIGATE TO VIEW PLAT
        Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main)
                .navigate(R.id.action_navigation_plat_to_dialogPlat2);
    }

    private void blockItem(int visible, boolean clickable) {
        //MAKE ITEM GRIS AND POINT GREEN
        itemView.setClickable(clickable);
        binding.griserItemPlat.setVisibility(visible);
    }

    private void updateIconEpice(Plat plat) {
        //SET ICON EPICE
        if (plat.getDegureEpice() == 3)
            setIconEpice(R.drawable.icon_degure_epice_3);

        else if (plat.getDegureEpice() == 2)
            setIconEpice(R.drawable.icon_degure_epice_2);

        else
            setIconEpice(R.drawable.icon_degure_epice_1);
    }

    private void upDateIconVegi(Plat plat) {
        //SET ICON VEGUI
        if (plat.getVegui().equals("o"))
            setIconVegi(R.drawable.icon_vegi);
        else
            setIconVegi(R.drawable.icon_vegi_no);
    }

    private void setIconVegi(int drawble) {
        binding.imageViewVegi.setImageResource(drawble);
    }

    private void setIconEpice(int drawble) {
        binding.imageViewDegureEpice.setImageResource(drawble);
    }

    private void fixWidth() {
        //FIX WIDTH OF CARD LIKE WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.cardViewCategorie.setMinimumWidth(width);
    }

}