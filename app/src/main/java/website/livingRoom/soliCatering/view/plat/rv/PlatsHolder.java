package website.livingRoom.soliCatering.view.plat.rv;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;

import website.livingRoom.soliCatering.databinding.ModelPlatsBinding;
import website.livingRoom.soliCatering.model.entitys.Plat;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.utile.ItemClickListener;
import website.livingRoom.soliCatering.view.IconHolder;
import website.livingRoom.soliCatering.viewModel.PlatViewModel;
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

        bindControl(plat);

        IconHolder iconHolder = new IconHolder(binding.imageViewVegi,binding.imageViewDegureEpice,plat);

        iconHolder.upDateIconVegi();
        iconHolder.updateIconEpice();

        updateItemClick(plat, platViewModel);
    }

    private void bindControl(Plat plat) {
        bindImage(plat);
        fixWidth();
        binding.setPlat(plat);
    }

    private void bindImage(Plat plat) {
        //SET DATA IN VIEW
        binding.imageViewPlat.setImageResource(Helper.getIdResourceByName(plat.getNomPic()));
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
        naviguer();
    }

    private void naviguer() {
        Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main)
                .navigate(R.id.action_navigation_plat_to_dialogPlat2);
    }

    private void blockItem(int visible, boolean clickable) {
        //MAKE ITEM GRIS AND POINT GREEN
        itemView.setClickable(clickable);
        binding.griserItemPlat.setVisibility(visible);
    }


    private void fixWidth() {
        //FIX WIDTH OF CARD LIKE WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.cardViewCategorie.setMinimumWidth(width);
    }

}