package website.livingRoom.soliCatering.ui.client;

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

import website.livingRoom.soliCatering.databinding.ViewClientBinding;
import website.livingRoom.soliCatering.db.entitys.Client;
import website.livingRoom.soliCatering.db.entitys.InformationLivraison;
import website.livingRoom.soliCatering.db.entitys.Menu;
import website.livingRoom.soliCatering.db.entitys.Panier;
import website.livingRoom.soliCatering.db.room.AppDatabase;
import website.livingRoom.soliCatering.repository.ConteurRepository;

public class DialogClient extends DialogFragment {

    //FIELD
    static ClientViewModel clientViewModel;
    static ViewClientBinding viewClientBinding;
    static Context context;

    //OVERRIDE METHODE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //instantiate live model
        clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);

        //inflate view to get bind
        viewClientBinding = DataBindingUtil.inflate(inflater, R.layout.view_client, container, false);

        //specif at life cycle owner
        viewClientBinding.setLifecycleOwner(getViewLifecycleOwner());

        //set the view model to the binding class
        viewClientBinding.setViewModel(clientViewModel);

        //context to use later
        context = viewClientBinding.getRoot().getContext();
        //return view from binding
        return viewClientBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //todo :replace with jet compose
        //FIX WIDTH OF CARD TO WIDTH OF SCREEN
        int width = context.getResources().getDisplayMetrics().widthPixels;
        viewClientBinding.textViewTitre.setWidth(width);

        //bind valider click
        bindValiderClick();
    }

    private void bindValiderClick() {
        viewClientBinding.buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onValiderClick();
            }
        });
    }

    //methode to link to onclick valider button listener
    public void onValiderClick() {
        /*CHECK IF ALL EXPRESSION ALL ACCEPTED*/
        /**the check also can take a short time to build string error and check all expression**/
        if (clientViewModel.getClient().checkAllExpression()) {
        /** the all insert  and  retreive and object creating check ... go to be excute in background because all
         * this bloc and data access on one shut can have impact to the UI if we use UI thread**/

        //execute block in work thread, Executor was chose because the task is imperative and not long
        AppDatabase.databaseWriteExecutor.execute(() -> {



                //get object menu by point from data
                /** we create a panier object whith some information from menu and client to make more
                 * clear the local data base archetecture we don't need to have more than 3 rows in paniers table
                 * that mean is more practic for this case to insert the key information direct to the table,
                 * in the selection the panier go to have relation with article panier(1-*) Panier,List<ArticlePanier> **/
                /**also for client**/
                Menu menu = clientViewModel.getCurrentMenu(ConteurRepository.getActuelMenu());
                Client client = clientViewModel.getClient();

                //information livraison are base for panier and note entity
                InformationLivraison informationLivraison = clientViewModel.getInformationLivraison();

                //create panier object
                Panier panier = new Panier(ConteurRepository.getIdPanier(), client.getIdClient(), client.getNomPrenom(),
                        1, menu.getId(), menu.getPrix(),
                        menu.getNomPic(), informationLivraison);

                //Insert client or update it in Data Base
                clientViewModel.clientRepository.insertClient(client);

                //insert panier in date base
                clientViewModel.panierRepository.insertPanier(panier);

            /**already asynchronous**/
            //create new id for panier
            ConteurRepository.createNewIdPanier();

            //delete conteur
            ConteurRepository.deleteConteur(context);
        });
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_dialogClient_to_historiqueFragment);

        }
    }
}
