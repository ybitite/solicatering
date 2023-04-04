package website.livingRoom.soliCatering.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import website.livingRoom.soliCatering.model.entitys.Conteur;
import website.livingRoom.soliCatering.repository.ConteurRepository;

public class ConteurViewModel extends AndroidViewModel {

    final ConteurRepository conteurRepository;
    Conteur conteur;

    MutableLiveData<Integer> badgeOrderNumber;

    public ConteurViewModel(@NonNull Application application) {
        super(application);

        conteurRepository = new ConteurRepository();
        conteur = conteurRepository.getConteur();
        badgeOrderNumber = new MutableLiveData<>();
        badgeOrderNumber.setValue(0);
    }

    public LiveData<Integer> getBadgeOrderNumber() {
        return badgeOrderNumber;
    }

    public void updateBadgeOrderNumber() {
        int newValue = badgeOrderNumber.getValue() + 1;
        this.badgeOrderNumber.setValue(newValue);
    }

    public void resetBadgeOrderNumber(){
        this.badgeOrderNumber.setValue(0);
    }

    public Conteur getConteur() {
        return conteur;
    }

    public void setConteur(Conteur conteur) {
        this.conteur = conteur;
    }

    public void setConteur(String nom,int pointDepart) {
        conteur.setName(nom);
        conteur.setPointDepart(pointDepart);
        conteur.setPointReste(pointDepart);
        resetBadgeOrderNumber();
    }

    public void resetConteur(){
        conteurRepository.resetConteur();
        resetBadgeOrderNumber();
    }

    public void saveConteur() {
        conteurRepository.setConteur(conteur);
    }

}
