package website.livingRoom.soliCatering.ui.historique;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.db.entitys.PanierWithAarticlePanierAndPlat;
import website.livingRoom.soliCatering.repository.PanierRepository;

import java.util.List;

public class HistoriqueViewModel extends AndroidViewModel {
    //FIELD

    private final LiveData<List<PanierWithAarticlePanierAndPlat>> listPanierWithArticlePanierAndPlat;

    //CONSTRUCTOR
    public HistoriqueViewModel(Application application) {
        super(application);
        PanierRepository panierRepository = new PanierRepository(application.getBaseContext());
        listPanierWithArticlePanierAndPlat = panierRepository.getPanierWithArticlePanierAndPlats();
    }

    /*TO OBSERVED LIVE DATA LIST PANIER FROM REPOSITORY*/
    public LiveData<List<PanierWithAarticlePanierAndPlat>> getListPanierWithArticlePanierAndPlat() {
        return listPanierWithArticlePanierAndPlat;
    }

}