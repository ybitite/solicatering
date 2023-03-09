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
    website.livingRoom.soliCatering.ui.categorie.rv.ItemClickListener itemClickListener;
    static ModelPlatsBinding binding;
    static Context context;

    public PlatsHolder(View itemView) {
        super(itemView);

        //GET CONTEXT TO USE LATER
        context = itemView.getContext();
        //FIX WIDTH OF CARD LIKE WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.cardViewCategorie.setMinimumWidth(width);
        //SET CLICK LISTENER IN ITEM
        itemView.setOnClickListener(this);
    }

    //METHODE
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(website.livingRoom.soliCatering.ui.categorie.rv.ItemClickListener ic) {
        this.itemClickListener = ic;
    }

    public static PlatsHolder create(ViewGroup parent) {
        //GENERATE CLASS FROM MODEL EVENT
        binding = ModelPlatsBinding
                .inflate(LayoutInflater.from(parent.getContext()));

        return new PlatsHolder(binding.getRoot());
    }

    public void bind(Plat plat, PlatViewModel platViewModel) {

        //SET DATA IN VIEW
        binding.imageViewPlat.setImageResource(Helper.idResource(plat.getNomPic()));
        binding.textViewTitrePlat.setText(plat.getNom());

        //SET ICON VEGUI
        if (plat.getVegui() == "o")
            binding.imageViewVegi.setImageResource(R.drawable.icon_vegi);
        else
            binding.imageViewVegi.setImageResource(R.drawable.icon_vegi_no);
        //SET ICON EPICE
        if (plat.getDegureEpice() == 3)
            binding.imageViewDegureEpice.setImageResource(R.drawable.icon_degure_epice_3);

        else if (plat.getDegureEpice() == 2)
            binding.imageViewDegureEpice.setImageResource(R.drawable.icon_degure_epice_2);
        else
            binding.imageViewDegureEpice.setImageResource(R.drawable.icon_degure_epice_1);

        /*BLOCK CLICK AND MAKE ITEM GREW WHEN THE POINT ARE NOT ENOUGH*/
        //GET CURENT CATEGORIE POINT
        int ptRest = ConteurRepository.getCategorieActuel();
        //WHERE IT IS ENOUGH
        if (plat.getPoint() <= ptRest) {
            //MAKE ITEM VISIBLE AND POINT RED
            binding.griserItemPlat.setVisibility(View.INVISIBLE);
            binding.linearLayoutPlat.setClickable(true);
            //OVERRIDE ON ITEM CLICK
            setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(View v, int pos) {
                    try {
                        //SAVE SELECTED PLAT
                        platViewModel.setSelectedPlat(plat);
                        //NAVIGATE TO VIEW PLAT
                        //TODO: beug / cause context / ok with try catch
                        Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_plat_to_dialogPlat2);
                    } catch (Exception exception) {
                    }
                }
            });
        } else {
            //MAKE ITEM GRIS AND POINT GREEN
            binding.griserItemPlat.setVisibility(View.VISIBLE);
            binding.linearLayoutPlat.setClickable(false);

        }
    }

}