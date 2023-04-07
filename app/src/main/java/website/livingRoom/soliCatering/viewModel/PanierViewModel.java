package website.livingRoom.soliCatering.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import website.livingRoom.soliCatering.model.entitys.ArticlePanierAndPlat;
import website.livingRoom.soliCatering.model.room.AppDatabase;
import website.livingRoom.soliCatering.repository.ArticlePanierRepository;

public class PanierViewModel extends AndroidViewModel {
    //FIELD
    private  LiveData<List<ArticlePanierAndPlat>> listArticlePanierAndPlat = new LiveData<>() {};
    private final ArticlePanierRepository articlePanierRepository;

    private final ConteurViewModel conteurViewModel;
    //CONSTRUCTOR
    public PanierViewModel(Application application, ConteurViewModel conteurViewModel) {
        super(application);

        articlePanierRepository = new ArticlePanierRepository(application);
        this.conteurViewModel = conteurViewModel;
    }

    /*TO OBSERVED LIVE DATA LIST ARTICLE PANIER FROM REPOSITORY*/
    public  LiveData<List<ArticlePanierAndPlat>> getListArticlePanierAndPlat() {

        //GET DATA LIVE OF LIST OF ARTICLE IN THIS PANIER
        listArticlePanierAndPlat = articlePanierRepository.getListArticlePanierWithPlat(conteurViewModel.getConteur().getPanierActuel());

        //RETURN LIVE DATA LIST
        return listArticlePanierAndPlat;
    }

    /*INCREMENT NUMBER PLAT*/
    public int incrimenteNbPlat(int position) {

        ArticlePanierAndPlat article = listArticlePanierAndPlat.getValue().get(position);

        /*INCREMENT NUMBER OF PLAT IF IT POINT PLAT ARE LAST OR EQUAL AT POINT RESTE*/
        if (conteurViewModel.getConteur().isEnoughRestePoint(article.plat.getPoint())) {

            return update(article, 1, -article.plat.getPoint());
        } else return 0;
    }

    /*DECREMENT NUMBER PLAT*/

    public int decrementNbPlat(int position) {

        ArticlePanierAndPlat article = listArticlePanierAndPlat.getValue().get(position);

        /*DECREMENT NUMBER OF PLAT IF IT POSSIBLE IF NUMBER ARE MOR THAN 1*/
        if (article.articlePanier.getNombrePlat() > 1) {

            //UPDATE IT IN DATA BASE on work thread
            return update(article, -1, article.plat.getPoint());
        } else return 0;
    }

    private int update(ArticlePanierAndPlat article, int upDateValue, int pointPlat) {
        //UPDATE IT IN DATA BASE on work thread
        AppDatabase.getDatabaseWriteExecutor().execute(()->{
            try {
                articlePanierRepository.updateArticlePanier(article.articlePanier, upDateValue);
            }
            catch (Exception exception){
                Log.e("update artPanier failed",exception.getMessage());
            }
        });

        //UPDATE RESTE IN CONTEUR
        conteurViewModel.getConteur().upDatePointReste(pointPlat);

        //RETURN NEW NUMBER
        return article.articlePanier.getNombrePlat() + upDateValue;
    }


}