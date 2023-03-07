package website.livingRoom.soliCatering.ui.historique;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.db.entitys.PanierWithAarticlePanierAndPlat;
import website.livingRoom.soliCatering.repository.PanierRepository;

import java.util.List;

public class HistoriqueViewModel extends AndroidViewModel {
    //FIELD
    private final PanierRepository panierRepository;
    private final LiveData<List<PanierWithAarticlePanierAndPlat>> allPanierWithArticlePanier;

    //CONSTRUCTOR
    public HistoriqueViewModel(Application application) {
        super(application);
        panierRepository = new PanierRepository(application.getBaseContext());
        allPanierWithArticlePanier = panierRepository.getPanierWithArticlePanierAndPlats();
    }

    /*TO OBSERVED LIVE DATA LIST PANIER FROM REPOSITORY*/
    public LiveData<List<PanierWithAarticlePanierAndPlat>> getAllPanierWithArticlePanier() {
        return allPanierWithArticlePanier;
    }

}