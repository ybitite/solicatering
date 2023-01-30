package com.example.marokkanischbernessen.ui.client;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.marokkanischbernessen.db.entity.Client;
import com.example.marokkanischbernessen.db.entity.InformationLivraison;
import com.example.marokkanischbernessen.db.entity.Menu;
import com.example.marokkanischbernessen.ripository.ClientRipository;
import com.example.marokkanischbernessen.ripository.MenuRipository;
import com.example.marokkanischbernessen.ripository.PanierRipository;

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
        client = new Client();
        informationLivraison = new InformationLivraison();
        menu = new Menu();

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
