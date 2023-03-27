package website.livingRoom.soliCatering.utile;

import android.widget.ImageView;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.model.entitys.Plat;

public  class IconHolder {

    private final ImageView imageViewVegi;
    private final ImageView imageViewDegureEpice;
    private final Plat plat;

    public IconHolder(ImageView imageViewVegi, ImageView imageViewDegureEpice, Plat plat) {
        this.imageViewVegi = imageViewVegi;
        this.imageViewDegureEpice = imageViewDegureEpice;
        this.plat = plat;
    }

    public  void updateIconEpice() {
        //SET ICON EPICE
        if (plat.getDegureEpice() == 3)
            setIconEpice(R.drawable.icon_degure_epice_3);

        else if (plat.getDegureEpice() == 2)
            setIconEpice(R.drawable.icon_degure_epice_2);

        else
            setIconEpice(R.drawable.icon_degure_epice_1);
    }

    public  void upDateIconVegi() {
        //SET ICON VEGUI
        if (plat.getVegui().equals("o"))
            setIconVegi(R.drawable.icon_vegi);
        else
            setIconVegi(R.drawable.icon_vegi_no);
    }

    private  void setIconVegi(int drawble) {
        imageViewVegi.setImageResource(drawble);
    }

    private  void setIconEpice(int drawble) {
        imageViewDegureEpice.setImageResource(drawble);
    }
}
