package website.livingRoom.soliCatering.repository;

import website.livingRoom.soliCatering.model.entitys.Conteur;
import website.livingRoom.soliCatering.model.sharedPreferences.ConteurSharedPreferencesOA;

public class ConteurRepository {

    //methode to get value
    public Conteur getConteur() {
        return ConteurSharedPreferencesOA.getConteur();
    }

    public void setConteur(Conteur conteur){
        ConteurSharedPreferencesOA.setConteur(conteur);
    }

    public void resetConteur() {
        ConteurSharedPreferencesOA.createDefaultConteur();
    }





}
