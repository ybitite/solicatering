package website.livingRoom.soliCatering.ui.panier;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.db.entitys.ArticlePanierAndPlat;
import website.livingRoom.soliCatering.repository.ArticlePanierRepository;
import website.livingRoom.soliCatering.repository.ConteurRepository;

import java.util.List;

public class PanierViewModel extends AndroidViewModel {
    //FIELD
    public static LiveData<List<ArticlePanierAndPlat>> listArticlePanierWithPlat;
    private static ArticlePanierRepository articlePanierRepository;

    //CONSTRUCTOR
    public PanierViewModel(Application application) {
        super(application);

        articlePanierRepository = new ArticlePanierRepository(application);
        listArticlePanierWithPlat = new LiveData<List<ArticlePanierAndPlat>>() {
        };
    }

    public PanierViewModel() {
        super(new Application());
    }

    /*TO OBSERVED LIVE DATA LIST ARTICLE PANIER FROM REPOSITORY*/
    public static LiveData<List<ArticlePanierAndPlat>> getListArticlePanierWithPlat() {
        //GET ID PANIER FROM CONTEUR
        int idPanier = ConteurRepository.getPanierActuel();

        //GET DATA LIVE OF LIST OF ARTICLE IN THIS PANIER
        listArticlePanierWithPlat = articlePanierRepository.getListArticlePanierWithPlat(idPanier);

        //RETURN LIVE DATA LIST
        return listArticlePanierWithPlat;
    }

    /*INCREMENT NUMBER PLAT*/
    public int incrimenteNbPlat(int position) {
        //GET POINT RESTE FROM CONTEUR IN SHARED PREFERENCES
        int pntReste = ConteurRepository.getPointReste();
        int idPanier = ConteurRepository.getPanierActuel();

        //GET VALUE OF POINT OF SELECTED PLAT
        int pntPlat = listArticlePanierWithPlat.getValue().get(position).plat.getPoint();
        //GET VALUE OF NUMBER OF PLAT
        int nbrPlat = listArticlePanierWithPlat.getValue().get(position).articlePanier.getNombrePlat();
        //GET ID
        int idPlat = listArticlePanierWithPlat.getValue().get(position).articlePanier.getIdPlats();


        /*INCREMENT NUMBER OF PLAT IF IT POINT PLAT ARE LAST OR EQUAL AT POINT RESTE*/
        if (pntPlat <= pntReste) {

            //UPDATE IT IN DATA BASE on work thread
            articlePanierRepository.updateArticlePanier(listArticlePanierWithPlat.getValue().get(position).articlePanier, 1);

            //UPDATE RESTE IN CONTEUR
            ConteurRepository.upDatePointReste(-pntPlat);

            //RETURN NEW NUMBER
            return nbrPlat + 1;
        } else return 0;
    }

    /*DECREMENT NUMBER PLAT*/
    public int decrementNbPlat(int position) {

        int idPanier = ConteurRepository.getPanierActuel();

        //GET VALUE OF NUMBER OF PLAT
        int nbrPlat = listArticlePanierWithPlat.getValue().get(position).articlePanier.getNombrePlat();
        //GET VALUE OF POINT OF SELECTED PLAT
        int pntPlat = listArticlePanierWithPlat.getValue().get(position).plat.getPoint();
        //GET ID
        int idPlat = listArticlePanierWithPlat.getValue().get(position).articlePanier.getIdPlats();

        /*DECREMENT NUMBER OF PLAT IF IT POSSIBLE IF NUMBER ARE MOR THAN 1*/
        if (nbrPlat > 1) {

            //UPDATE IT IN DATA BASE on work thread
            articlePanierRepository.updateArticlePanier(listArticlePanierWithPlat.getValue().get(position).articlePanier, -1);

            //UPDATE RESTE IN CONTEUR
            ConteurRepository.upDatePointReste(pntPlat);

            //RETURN NEW NUMBER
            return nbrPlat - 1;
        } else return 0;
    }


}