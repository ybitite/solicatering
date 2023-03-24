package website.livingRoom.soliCatering.view.client;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import website.livingRoom.soliCatering.R;

import website.livingRoom.soliCatering.viewModel.ClientViewModel;
import website.livingRoom.soliCatering.databinding.ViewClientBinding;
import website.livingRoom.soliCatering.model.entitys.Client;
import website.livingRoom.soliCatering.model.entitys.InformationLivraison;
import website.livingRoom.soliCatering.model.entitys.Menu;
import website.livingRoom.soliCatering.model.entitys.Panier;
import website.livingRoom.soliCatering.model.room.AppDatabase;
import website.livingRoom.soliCatering.repository.ClientRepository;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.repository.PanierRepository;

public class DialogClientFragment extends DialogFragment {

    //FIELD
    ClientViewModel clientViewModel;
    ViewClientBinding binding;
    Context context;

    //OVERRIDE METHODE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //instantiate live model
        clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);

        //inflate view to get bind
        binding = ViewClientBinding.inflate(inflater, container, false);

        //specif at life cycle owner
        binding.setLifecycleOwner(getViewLifecycleOwner());

        //set the view model to the binding class
        binding.setViewModel(clientViewModel);

        //context to use later
        context = binding.getRoot().getContext();
        //return view from binding
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bind();
    }

    private void fixWidth() {
        //FIX WIDTH OF CARD TO WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        binding.textViewTitre.setWidth(width);
    }

    private void bind() {
        fixWidth();

        binding.buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onValiderClick();
            }
        });
    }

    //methode to link to onclick valider button listener
    public void onValiderClick() {
        /*CHECK IF ALL EXPRESSION ALL ACCEPTED*/
        /*the check also can take a short time to build string error and check all expression**/

        if (clientViewModel.getClient().checkAllExpression()) {

        /* the all insert  and  retreive and object creating check ... go to be excute in background because all
          this bloc and data access on one shut can have impact to the UI if we use UI thread**/

        //execute block in work thread, Executor was chose because the task is imperative and not long
        AppDatabase.databaseWriteExecutor.execute(this::run);
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_dialogClient_to_historiqueFragment);

        }
    }

    private void run() {

        //get object menu by point from data
        /* we create a panier object whith some information from menu and client to make more
          clear the local data base archetecture we don't need to have more than 3 rows in paniers table
          that mean is more practic for this case to insert the key information direct to the table,
          in the selection the panier go to have relation with article panier(1-*) Panier,List<ArticlePanier> **/
        /*also for client**/

        insertData();

        updateConteur();
    }

    private void insertData() {
        Menu menu = clientViewModel.getCurrentMenu(ConteurRepository.getPointDepart());
        Client client = clientViewModel.getClient();

        //Insert client or update it in Data Base
        ClientRepository clientRepository = new ClientRepository(getContext());
        clientRepository.insertClient(client);

        insertPanier(menu, client);
    }

    private void insertPanier(Menu menu, Client client) {
        //information livraison are base for panier and note entity
        InformationLivraison informationLivraison = clientViewModel.getInformationLivraison();

        //create panier object
        Panier panier = new Panier(ConteurRepository.getPanierActuel(), client.getIdClient(), client.getNomPrenom(),
                1, menu.getId(), menu.getPrix(),
                menu.getNomPic(), informationLivraison);


        //insert panier in date base
        PanierRepository panierRepository = new PanierRepository(getContext());
        panierRepository.insertPanier(panier);
    }

    private void updateConteur() {
        /*already asynchronous**/
        //create new id for panier
        ConteurRepository.updatePanierActuel();

        //delete conteur
        ConteurRepository.setConteur();
    }
}
