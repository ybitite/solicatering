package website.livingRoom.soliCatering.ui.client;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import website.livingRoom.soliCatering.db.entitys.Client;
import website.livingRoom.soliCatering.db.entitys.InformationLivraison;
import website.livingRoom.soliCatering.db.entitys.Menu;
import website.livingRoom.soliCatering.ripository.ClientRipository;
import website.livingRoom.soliCatering.ripository.MenuRipository;
import website.livingRoom.soliCatering.ripository.PanierRipository;

public class ClientViewModel extends AndroidViewModel {
    //FIELD
    //to create new panier
    private Client client;
    private InformationLivraison informationLivraison;
    private Menu menu;

    //to insert date in data base
    public ClientRipository clientRipository;
    public PanierRipository panierRipository;

    //te retrive
    private MenuRipository menuRipository;

    //FIX WIDTH OF CARD TO WIDTH OF SCREEN
    public int width = getApplication().getBaseContext().getResources().getDisplayMetrics().widthPixels;

    //CONSTRUCTOR
    public ClientViewModel(@NonNull Application application) {
        super(application);
        clientRipository = new ClientRipository(application);
        menuRipository = new MenuRipository(application);
        panierRipository = new PanierRipository(application);
        informationLivraison = new InformationLivraison();
        client = new Client();


    }

    //METHODE
    //tu get current menu to create panier
    public Menu getCurrentMenu(int pointMenu) {
        menu = menuRipository.getMenuByPoint(pointMenu);
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
