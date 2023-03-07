package website.livingRoom.soliCatering.ui.acceuil;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.db.entitys.Evenement;
import website.livingRoom.soliCatering.repository.EvenementRepository;

import java.util.List;

public class AcceuilViewModel extends AndroidViewModel {
    //FIELD
    private final EvenementRepository evenementRepository;
    private final LiveData<List<Evenement>> allEvenement;

    //CONSTRUCTOR
    public AcceuilViewModel(Application application) {
        super(application);
        evenementRepository = new EvenementRepository(application.getBaseContext());
        allEvenement = evenementRepository.getListEvenement();
    }

    /*TO OBSERVED LIVE DATA LIST EVENT FROM REPOSITORY*/
    public LiveData<List<Evenement>> getAllEvenement() {
        return allEvenement;
    }
}