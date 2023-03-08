package website.livingRoom.soliCatering.ui.plat;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import website.livingRoom.soliCatering.db.entitys.Plat;
import website.livingRoom.soliCatering.repository.ArticlePanierRepository;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.repository.PlatRepository;

import java.util.List;

public class PlatViewModel extends AndroidViewModel {

    //FIELD
    private final PlatRepository platRepository;
    private final ArticlePanierRepository articlePanierRepository;
    private LiveData<List<Plat>> allPlat;
    private final MutableLiveData<Plat> selectedPlat = new MutableLiveData<Plat>();
    private final MutableLiveData<Integer> nombrePlat = new MutableLiveData<Integer>(1);

    //CONSTRUCTOR
    public PlatViewModel(Application application) {
        super(application);
        platRepository = new PlatRepository(application.getBaseContext());
        articlePanierRepository = new ArticlePanierRepository(application.getApplicationContext());
    }

    /*To OBSERVED LIVE DATA LIST PLAT FROM REPOSITORY*/
    public LiveData<List<Plat>> getAllPlat(int pointCat) {
        allPlat = platRepository.gatPlats(pointCat);
        return allPlat;
    }

    /*RETURN SELECTED PLAT*/
    public void selectPlat(Plat plat) {
        selectedPlat.setValue(plat);
    }

    /*RETURN OBSERVABLE LIVE DATA SELECTED PLAT*/
    public LiveData<Plat> getSelectedPlat() {
        return selectedPlat;
    }

    /*INCREMENT NUMBER PLAT*/
    public void incrimenteNbPlat() {
        //GET POINT RESTE FROM CONTEUR IN SHARED PREFERENCES
        int pntReste = ConteurRepository.getPointReste();
        //GET VALUE OF POINT OF SELECTED PLAT
        int pntPlat = selectedPlat.getValue().getPoint();
        //GET VALUE OF NUMBER OF PLAT
        int nbrPlat = nombrePlat.getValue();
        //IF THE PRODUCT  OF POINT AND NUMBER OF PLAT ARE LAST OR EQUAL AT POINT RESTE
        if (pntPlat * (nbrPlat + 1) <= pntReste) {
            //INCREMENT NUMBER OF PLAT
            nombrePlat.setValue(nombrePlat.getValue() + 1);
        }
    }

    /*DECREMENT NUMBER PLAT*/
    public void decrementNbPlat() {
        //GET VALUE OF NUMBER OF PLAT
        int nbrPlat = nombrePlat.getValue();
        //IF NUMBER ARE MOR THAN 1
        if (nbrPlat > 1) {
            //DECREMENT NUMBER OF PLAT
            nombrePlat.setValue(nbrPlat - 1);
        }
    }

    /*RESET NUMBER PLAT*/
    public void resetNumberPlat() {
        nombrePlat.setValue(1);
    }

    /*RETURN OBSERVABLE LIVE DATA OF NUMBER OF PLAT*/
    public LiveData<Integer> getNumberPlat() {
        return nombrePlat;
    }


}