package website.livingRoom.soliCatering.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import website.livingRoom.soliCatering.model.entitys.Client;
import website.livingRoom.soliCatering.model.entitys.InformationLivraison;
import website.livingRoom.soliCatering.model.entitys.Menu;
import website.livingRoom.soliCatering.repository.MenuRepository;

public class ClientViewModel extends AndroidViewModel {
    //FIELD
    private Client client;
    private InformationLivraison informationLivraison;
    private final MenuRepository menuRepository;


    //CONSTRUCTOR
    public ClientViewModel(@NonNull Application application) {
        super(application);

        menuRepository = new MenuRepository(application);
        client = new Client();
        informationLivraison = new InformationLivraison();
    }

    //METHODE
    //tu get current menu to create panier
    public Menu getCurrentMenu(int pointMenu) {
        return menuRepository.getMenu(pointMenu);
    }

    //PROPRIETY
    //to access make access for data binding
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public InformationLivraison getInformationLivraison() {
        return informationLivraison;
    }

    public void setInformationLivraison(InformationLivraison informationLivraison) {
        this.informationLivraison = informationLivraison;
    }
}
