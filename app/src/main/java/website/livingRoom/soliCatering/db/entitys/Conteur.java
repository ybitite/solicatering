package website.livingRoom.soliCatering.db.entitys;

import android.content.res.Resources;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.utile.AppUtile;

public class Conteur {
    //FIELD
    final String name;
    final int ptDepart;
    final int ptReste;

    //CONSTRUCTOR
    public Conteur(String name, int ptDepart, int ptReste) {
        this.name = name;
        this.ptDepart = ptDepart;
        this.ptReste = ptReste;
    }

    //PROPRIETY
    public String getName() {
        return name;
    }

    public int getpDepart() {
        return ptDepart;
    }

    public int getpReste() {
        return ptReste;
    }

    public String getNameFormat() {
        //get resources from AppUtil
        Resources resources = AppUtile.getResource();
        return name + " (" + ptDepart + ") "+ resources.getString(R.string.text_point);
    }

    public String getPtRestFormat() {
        Resources resources = AppUtile.getResource();

        return ptReste + " " + resources.getString(R.string.text_point);

    }
}
