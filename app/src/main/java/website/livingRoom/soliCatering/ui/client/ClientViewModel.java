package website.livingRoom.soliCatering.ui.client;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import website.livingRoom.soliCatering.db.entitys.Client;
import website.livingRoom.soliCatering.db.entitys.InformationLivraison;
import website.livingRoom.soliCatering.db.entitys.Menu;
import website.livingRoom.soliCatering.repository.ClientRepository;
import website.livingRoom.soliCatering.repository.MenuRepository;
import website.livingRoom.soliCatering.repository.PanierRepository;

public class ClientViewModel extends AndroidViewModel {
    //FIELD
    //to create new panier
    private Client client;
    private InformationLivraison informationLivraison;
    private Menu menu;

    //to insert date in data base
    public ClientRepository clientRepository;
    public PanierRepository panierRepository;

    //te retrive
    private MenuRepository menuRepository;

    //FIX WIDTH OF CARD TO WIDTH OF SCREEN
    public int width = getApplication().getBaseContext().getResources().getDisplayMetrics().widthPixels;

    //CONSTRUCTOR
    public ClientViewModel(@NonNull Application application) {
        super(application);
        clientRepository = new ClientRepository(application);
        menuRepository = new MenuRepository(application);
        panierRepository = new PanierRepository(application);
        informationLivraison = new InformationLivraison();
        client = new Client();

    }

    //METHODE
    //tu get current menu to create panier
    public Menu getCurrentMenu(int pointMenu) {
        menu = menuRepository.getMenu(pointMenu);
        return menu;
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
