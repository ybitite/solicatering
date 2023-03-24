package website.livingRoom.soliCatering.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.model.entitys.ArticlePanierAndPlat;
import website.livingRoom.soliCatering.repository.ArticlePanierRepository;
import website.livingRoom.soliCatering.repository.ConteurRepository;

import java.util.List;

public class PanierViewModel extends AndroidViewModel {
    //FIELD
    private  LiveData<List<ArticlePanierAndPlat>> listArticlePanierAndPlat = new LiveData<>() {};
    private final ArticlePanierRepository articlePanierRepository;

    //CONSTRUCTOR
    public PanierViewModel(Application application) {
        super(application);

        articlePanierRepository = new ArticlePanierRepository(application);
    }

    /*TO OBSERVED LIVE DATA LIST ARTICLE PANIER FROM REPOSITORY*/
    public  LiveData<List<ArticlePanierAndPlat>> getListArticlePanierAndPlat() {

        //GET DATA LIVE OF LIST OF ARTICLE IN THIS PANIER
        listArticlePanierAndPlat = articlePanierRepository.getListArticlePanierWithPlat(ConteurRepository.getPanierActuel());

        //RETURN LIVE DATA LIST
        return listArticlePanierAndPlat;
    }

    /*INCREMENT NUMBER PLAT*/
    public int incrimenteNbPlat(int position) {

        ArticlePanierAndPlat article = listArticlePanierAndPlat.getValue().get(position);

        /*INCREMENT NUMBER OF PLAT IF IT POINT PLAT ARE LAST OR EQUAL AT POINT RESTE*/
        if (article.plat.getPoint() <= ConteurRepository.getPointReste()) {

            //UPDATE IT IN DATA BASE on work thread
            articlePanierRepository.updateArticlePanier(article.articlePanier, 1);

            //UPDATE RESTE IN CONTEUR
            ConteurRepository.upDatePointReste(-article.plat.getPoint());

            //RETURN NEW NUMBER
            return article.articlePanier.getNombrePlat() + 1;
        } else return 0;
    }

    /*DECREMENT NUMBER PLAT*/
    public int decrementNbPlat(int position) {

        ArticlePanierAndPlat article = listArticlePanierAndPlat.getValue().get(position);

        /*DECREMENT NUMBER OF PLAT IF IT POSSIBLE IF NUMBER ARE MOR THAN 1*/
        if (article.articlePanier.getNombrePlat() > 1) {

            //UPDATE IT IN DATA BASE on work thread
            articlePanierRepository.updateArticlePanier(article.articlePanier, -1);

            //UPDATE RESTE IN CONTEUR
            ConteurRepository.upDatePointReste(article.plat.getPoint());

            //RETURN NEW NUMBER
            return article.articlePanier.getNombrePlat() - 1;
        } else return 0;
    }


}