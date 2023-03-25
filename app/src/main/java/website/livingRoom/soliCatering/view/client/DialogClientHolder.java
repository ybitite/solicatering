package website.livingRoom.soliCatering.view.client;


import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.ViewClientBinding;
import website.livingRoom.soliCatering.model.entitys.Client;
import website.livingRoom.soliCatering.model.entitys.InformationLivraison;
import website.livingRoom.soliCatering.model.entitys.Menu;
import website.livingRoom.soliCatering.model.entitys.Panier;
import website.livingRoom.soliCatering.model.room.AppDatabase;
import website.livingRoom.soliCatering.repository.ClientRepository;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.repository.PanierRepository;
import website.livingRoom.soliCatering.utile.Helper;
import website.livingRoom.soliCatering.viewModel.ClientViewModel;

public class DialogClientHolder {


    private final ViewClientBinding binding;
    private final ClientViewModel clientViewModel;

    public DialogClientHolder(ViewClientBinding binding, ClientViewModel clientViewModel) {
        this.binding = binding;
        this.clientViewModel = clientViewModel;
    }

    public void bind() {

        Helper.fixWidth(binding.mockViewFormClient);

        binding.buttonValider.setOnClickListener(v -> onValiderClick());
    }

    //methode to link to onclick valider button listener

    private void onValiderClick() {
        /*CHECK IF ALL EXPRESSION ALL ACCEPTED*/
        if (clientViewModel.getClient().checkAllExpression()) {

        /* ... go to be excute in background because all
          this bloc and data access on one shut can have impact to the UI if we use UI thread**/
            AppDatabase.databaseWriteExecutor.execute(this::run);

            Helper.naviguer(R.id.action_dialogClient_to_historiqueFragment);
        }
    }
    private void run() {

        insertData();

        updateConteur();
    }

    private void insertData() {

        Client client = clientViewModel.getClient();

        //Insert client or update it in Data Base
        ClientRepository clientRepository = new ClientRepository(Helper.getContext());
        clientRepository.insertClient(client);

        Menu menu = clientViewModel.getCurrentMenu(ConteurRepository.getPointDepart());
        InformationLivraison informationLivraison = clientViewModel.getInformationLivraison();

        insertPanier(menu, client,informationLivraison);
    }

    private void insertPanier(Menu menu, Client client, InformationLivraison informationLivraison) {

        //create panier object
        Panier panier = new Panier(ConteurRepository.getPanierActuel(), client.getIdClient(), client.getNomPrenom(),
                1, menu.getId(), menu.getPrix(),
                menu.getNomPic(), informationLivraison);

        //insert panier in date base
        PanierRepository panierRepository = new PanierRepository(Helper.getContext());
        panierRepository.insertPanier(panier);
    }

    private void updateConteur() {
        /*already asynchronous**/
        //create new id for panier
        ConteurRepository.updatePanierActuel();

        //create default conteur
        ConteurRepository.resetConteur();
    }
}
