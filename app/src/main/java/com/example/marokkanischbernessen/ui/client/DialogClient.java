package com.example.marokkanischbernessen.ui.client;

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

import com.example.marokkanischbernessen.R;
import com.example.marokkanischbernessen.databinding.ViewClientBinding;
import com.example.marokkanischbernessen.db.entity.Client;
import com.example.marokkanischbernessen.db.entity.InformationLivraison;
import com.example.marokkanischbernessen.db.entity.Menu;
import com.example.marokkanischbernessen.db.entity.Panier;
import com.example.marokkanischbernessen.db.room.AppDatabase;
import com.example.marokkanischbernessen.ripository.ConteurRipository;
import com.example.marokkanischbernessen.utile.Helper;

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
    }

    //methode to link to onclick valider button listener
    public static void onValiderClick() {

        /** the all insert  and  retreive and object creating check ... go to be excute in background because all
         * this bloc and data access on one shut can have impact to the UI if we use UI thread**/

        //execute block in work thread, Executor was chose because the task is imperative and not long
        AppDatabase.databaseWriteExecutor.execute(() -> {

            /*CHECK IF ALL EXPRESSION ALL ACCEPTED*/
            /**the check also can take a short time to build string error and check all expression**/
            if (clientViewModel.getClient().checkAllExpression()) {

                //get object menu by point from data
                /** we create a panier object whith some information from menu and client to make more
                 * clear the local data base archetecture we don't need to have more than 3 rows in paniers table
                 * that mean is more practic for this case to insert the key information direct to the table,
                 * in the selection the panier go to have relation with article panier(1-*) Panier,List<ArticlePanier> **/
                /**also for client**/
                Menu menu = clientViewModel.getCurrentMenu(ConteurRipository.getActuelMenu());
                Client client = clientViewModel.getClient();

                //information livraison are base for panier and note entity
                InformationLivraison informationLivraison = clientViewModel.getInformationLivraison();

                //create panier object
                Panier panier = new Panier(ConteurRipository.getIdPanier(), client.idClient, client.getNomPrenom(),
                        1, menu.id, menu.getPrix(), Helper.idResource(context,
                        menu.getNomPic()), informationLivraison);

                //Insert client or update it in Data Base
                clientViewModel.clientRipository.insertClient(client);

                //insert panier in date base
                clientViewModel.panierRipository.insertPanier(panier);

            }
            /**already asynchronous**/
            //create new id for panier
            ConteurRipository.createNewIdPanier();

            //delete conteur
            ConteurRipository.deleteConteur(context);
        });
        //
        // Navigation.findNavController(viewClientBinding.getRoot()).navigate(R.id.action_dialogClient_to_historiqueFragment);
    }

    //methode to link to onclick colse button listener
    public void onColseClick() {
        dismiss();
    }


}
