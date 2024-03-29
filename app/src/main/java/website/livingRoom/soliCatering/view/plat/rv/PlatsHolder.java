package website.livingRoom.soliCatering.view.plat.rv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.ModelPlatsBinding;
import website.livingRoom.soliCatering.model.entitys.Plat;
import website.livingRoom.soliCatering.utile.Helper;
import website.livingRoom.soliCatering.utile.IconHolder;
import website.livingRoom.soliCatering.utile.ItemClickListener;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;
import website.livingRoom.soliCatering.viewModel.PlatViewModel;

public class PlatsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //FIELD
    private ItemClickListener itemClickListener;
    private final ModelPlatsBinding binding;
    private final ConteurViewModel conteurViewModel;

    public PlatsHolder(ModelPlatsBinding modelPlatsBinding, ConteurViewModel conteurViewModel) {
        super(modelPlatsBinding.getRoot());

        this.conteurViewModel = conteurViewModel;

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

    public static PlatsHolder create(ViewGroup parent, ConteurViewModel conteurViewModel) {
        //GENERATE CLASS FROM MODEL EVENT
        ModelPlatsBinding modelPlatsBinding = ModelPlatsBinding
                .inflate(LayoutInflater.from(parent.getContext()));

        return new PlatsHolder(modelPlatsBinding,conteurViewModel);
    }

    public void bind(Plat plat, PlatViewModel platViewModel) {

        bindControl(plat);

        IconHolder iconHolder = new IconHolder(binding.imageViewVegi,binding.imageViewDegureEpice,plat);

        iconHolder.upDateIconVegi();
        iconHolder.updateIconEpice();

        updateItemClick(plat, platViewModel);
    }

    private void bindControl(Plat plat) {
        Helper.bindPicassoImage(plat.getNomPic(),binding.imageViewPlat);
        Helper.fixWidth(binding.mockViewFormPlat);
        binding.setPlat(plat);
    }



    private void updateItemClick(Plat plat, PlatViewModel platViewModel) {
        /*BLOCK CLICK AND MAKE ITEM GREW WHEN THE POINT ARE NOT ENOUGH*/
        if (conteurViewModel.getConteur().isEnoughRestePoint(plat.getPoint())) {
            //navigate to dialog en click
            setItemClickListener((v, pos) -> navigateToDialogPlat(plat, platViewModel));
        }
        else {
            Helper.blockItem(R.color.red_100,false,itemView,binding.cardViewPlat);
        }
    }

    private void navigateToDialogPlat(Plat plat, PlatViewModel platViewModel) {
        //SAVE SELECTED PLAT
        platViewModel.setSelectedPlat(plat);
        //NAVIGATE TO VIEW PLAT
        naviguer(R.id.dialogPlat);
    }

    private void naviguer(int actionId) {
        Navigation.findNavController(itemView).navigate(actionId);
    }

}