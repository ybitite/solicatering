package website.livingRoom.soliCatering.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import website.livingRoom.soliCatering.model.entitys.Conteur;
import website.livingRoom.soliCatering.repository.ConteurRepository;

public class ConteurViewModel extends AndroidViewModel {

    ConteurRepository conteurRepository;
    Conteur conteur;

    public ConteurViewModel(@NonNull Application application) {
        super(application);


        conteurRepository = new ConteurRepository();
        conteur = conteurRepository.getConteur();
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
    }

    public void resetConteur(){
        conteurRepository.resetConteur();
    }

    @Override
    protected void onCleared() {
        conteurRepository.setConteur(conteur);
        super.onCleared();
    }
}
