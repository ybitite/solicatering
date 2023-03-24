package website.livingRoom.soliCatering.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import website.livingRoom.soliCatering.model.entitys.Plat;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.repository.PlatRepository;

import java.util.List;

public class PlatViewModel extends AndroidViewModel {

    //FIELD
    private final PlatRepository platRepository;
    private final MutableLiveData<Plat> selectedPlat = new MutableLiveData<>();
    private final MutableLiveData<Integer> nombrePlat = new MutableLiveData<>(1);

    //CONSTRUCTOR
    public PlatViewModel(Application application) {
        super(application);
        platRepository = new PlatRepository(application.getBaseContext());
    }

    /*To OBSERVED LIVE DATA LIST PLAT FROM REPOSITORY*/
    public LiveData<List<Plat>> getAllPlat(int pointCat) {
        return platRepository.gatPlats(pointCat);
    }
    
    public void setSelectedPlat(Plat plat) {
        selectedPlat.setValue(plat);
    }

    /*RETURN OBSERVABLE LIVE DATA SELECTED PLAT*/
    public LiveData<Plat> getSelectedPlat() {
        return selectedPlat;
    }

    /*RETURN OBSERVABLE LIVE DATA OF NUMBER OF PLAT*/
    public LiveData<Integer> getNumberPlat() {
        return nombrePlat;
    }

    /*INCREMENT NUMBER PLAT*/
    public void incrimenteNbPlat() {

        //IF THE PRODUCT  OF POINT AND NUMBER OF PLAT ARE LAST OR EQUAL AT POINT RESTE
        if (checkIncrease(ConteurRepository.getPointReste(), selectedPlat.getValue().getPoint(), nombrePlat.getValue())) {
            //INCREMENT NUMBER OF PLAT
            nombrePlat.setValue(nombrePlat.getValue() + 1);
        }
    }

    private boolean checkIncrease(int pntReste, int pntPlat, int nbrPlat) {
        return pntPlat * (nbrPlat + 1) <= pntReste;
    }

    /*DECREMENT NUMBER PLAT*/
    public void decrementNbPlat() {
        
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




}