package website.livingRoom.soliCatering.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.model.entitys.Evenement;
import website.livingRoom.soliCatering.repository.EvenementRepository;

import java.util.List;

public class AcceuilViewModel extends AndroidViewModel {
    //FIELD
    private final LiveData<List<Evenement>> listEvenement;

    //CONSTRUCTOR
    public AcceuilViewModel(Application application) {
        super(application);
        EvenementRepository evenementRepository = new EvenementRepository(application.getBaseContext());
        listEvenement = evenementRepository.getListEvenement();
    }

    /*TO OBSERVED LIVE DATA LIST EVENT*/
    public LiveData<List<Evenement>> getListEvenement() {
        return listEvenement;
    }
}