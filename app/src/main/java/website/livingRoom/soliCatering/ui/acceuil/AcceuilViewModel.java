package website.livingRoom.soliCatering.ui.acceuil;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.db.entitys.Evenement;
import website.livingRoom.soliCatering.ripository.EvenementRipository;

import java.util.List;

public class AcceuilViewModel extends AndroidViewModel {
    //FIELD
    private final EvenementRipository evenementRipository;
    private final LiveData<List<Evenement>> allEvenement;

    //CONSTRUCTOR
    public AcceuilViewModel(Application application) {
        super(application);
        evenementRipository = new EvenementRipository(application.getBaseContext());
        allEvenement = evenementRipository.getListEvenement();
    }

    /*TO OBSERVED LIVE DATA LIST EVENT FROM REPOSITORY*/
    public LiveData<List<Evenement>> getAllEvenement() {
        return allEvenement;
    }
}