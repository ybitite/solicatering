package com.example.marokkanischbernessen.ui.panier;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.marokkanischbernessen.db.entity.ArticlePanier;
import com.example.marokkanischbernessen.ripository.ArticlePanierRipository;
import com.example.marokkanischbernessen.ripository.ConteurRipository;

import java.util.List;

public class PanierViewModel extends AndroidViewModel {
    //FIELD
    public static LiveData<List<ArticlePanier>> listArticlePanier;
    private static ArticlePanierRipository articlePanierRipository;

    //CONSTRUCTOR
    public PanierViewModel(Application application) {
        super(application);

        articlePanierRipository = new ArticlePanierRipository(application);
        listArticlePanier = new LiveData<List<ArticlePanier>>() {
        };
    }

    public PanierViewModel() {
        super(new Application());
    }

    /*TO OBSERVED LIVE DATA LIST ARTICLE PANIER FROM REPOSITORY*/
    public static LiveData<List<ArticlePanier>> getListArticlePanier() {
        //GET ID PANIER FROM CONTEUR
        int idPanier = ConteurRipository.getIdPanier();

        //GET DATA LIVE OF LIST OF ARTICLE IN THIS PANIER
        listArticlePanier = articlePanierRipository.getListArticlePanier(idPanier);

        //RETURN LIVE DATA LIST
        return listArticlePanier;
    }

    /*INCREMENT NUMBER PLAT*/
    public int incrimenteNbPlat(int position) {
        //GET POINT RESTE FROM CONTEUR IN SHARED PREFERENCES
        int pntReste = ConteurRipository.getPointRest();
        int idPanier = ConteurRipository.getIdPanier();

        //GET VALUE OF POINT OF SELECTED PLAT
        int pntPlat = listArticlePanier.getValue().get(position).getPointPlat();
        //GET VALUE OF NUMBER OF PLAT
        int nbrPlat = listArticlePanier.getValue().get(position).getNombrePlat();
        //GET ID
        int idPlat = listArticlePanier.getValue().get(position).getIdPlats();


        /*INCREMENT NUMBER OF PLAT IF IT POINT PLAT ARE LAST OR EQUAL AT POINT RESTE*/
        if (pntPlat <= pntReste) {

            //UPDATE IT IN DATA BASE on work thread
            articlePanierRipository.updateArticlePanier(listArticlePanier.getValue().get(position), 1);

            //UPDATE RESTE IN CONTEUR
            ConteurRipository.upDatePointRest(-pntPlat);

            //RETURN NEW NUMBER
            return nbrPlat + 1;
        } else return 0;
    }

    /*DECREMENT NUMBER PLAT*/
    public int decrementNbPlat(int position) {

        int idPanier = ConteurRipository.getIdPanier();

        //GET VALUE OF NUMBER OF PLAT
        int nbrPlat = listArticlePanier.getValue().get(position).getNombrePlat();
        //GET VALUE OF POINT OF SELECTED PLAT
        int pntPlat = listArticlePanier.getValue().get(position).getPointPlat();
        //GET ID
        int idPlat = listArticlePanier.getValue().get(position).getIdPlats();

        /*DECREMENT NUMBER OF PLAT IF IT POSSIBLE IF NUMBER ARE MOR THAN 1*/
        if (nbrPlat > 1) {

            //UPDATE IT IN DATA BASE on work thread
            articlePanierRipository.updateArticlePanier(listArticlePanier.getValue().get(position), -1);

            //UPDATE RESTE IN CONTEUR
            ConteurRipository.upDatePointRest(pntPlat);

            //RETURN NEW NUMBER
            return nbrPlat - 1;
        } else return 0;
    }


}