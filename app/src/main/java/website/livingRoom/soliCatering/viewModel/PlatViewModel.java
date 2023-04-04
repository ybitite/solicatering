package website.livingRoom.soliCatering.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import website.livingRoom.soliCatering.model.entitys.Plat;
import website.livingRoom.soliCatering.repository.PlatRepository;

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
        //INCREMENT NUMBER OF PLAT
        updateNumerPlat( + 1);
    }

    /*DECREMENT NUMBER PLAT*/
    public void decrementNbPlat() {
        //DECREMENT NUMBER OF PLAT
        updateNumerPlat( - 1);
    }
    public void updateNumerPlat(int i){
        nombrePlat.setValue(nombrePlat.getValue() + i);
    }

    /*RESET NUMBER PLAT*/
    public void resetNumberPlat() {
        nombrePlat.setValue(1);
    }

    public void setNombrePlat(int value){
        nombrePlat.setValue(value);
    }


}