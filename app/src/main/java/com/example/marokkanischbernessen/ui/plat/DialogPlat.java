package com.example.marokkanischbernessen.ui.plat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.marokkanischbernessen.R;
import com.example.marokkanischbernessen.db.entity.ArticlePanier;
import com.example.marokkanischbernessen.db.entity.Plat;
import com.example.marokkanischbernessen.db.room.AppDatabase;
import com.example.marokkanischbernessen.ripository.ArticlePanierRipository;
import com.example.marokkanischbernessen.ripository.ConteurRipository;
import com.example.marokkanischbernessen.utile.Helper;

public class DialogPlat extends DialogFragment {
    //FIELD
    PlatViewModel platViewModel;
    ArticlePanierRipository articlePanierRipository;

    //METHODE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //INSTANTIATE LIVE MODEL
        platViewModel = new ViewModelProvider(requireActivity()).get(PlatViewModel.class);
        //CREATE VIEW FROM INFLATER
        return inflater.inflate(R.layout.view_plats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //BIND VIEW
        bindView(view);
        //CALL METHODE TO LINK DISMISS DIALOG ON CLICK
        initiateButtonAnnuller(view);

        articlePanierRipository = new ArticlePanierRipository(getContext());
    }

    private void bindView(View view) {
        /*BIND VIEW WITH SELECTED PLAT*/
        //OBSERVE SELECTED PLAT
        platViewModel.getSelectedPlat().observe(requireActivity(), plat -> {
            setLestenerAjouter(view);

            //BIND CONTROLE
            bindControle(view, plat);

            //CHOSE ICONE VIGI AND ICONE EPICE
            bindIconVegiEpice(view, plat);
        });
        //OBSERVE NUMBER PLAT
        platViewModel.getNumberPlat().observe(requireActivity(), nbrPlat -> {
            //SET NUMBER OF PLAT
            TextView textViewNbrPlat = (TextView) view.findViewById(R.id.textViewNbrPlat);
            textViewNbrPlat.setText(String.valueOf(nbrPlat));
        });
        //SET LISTENER TO THE BUTTON CONTROL OF NUMBER
        setLestenerClick(view);
    }

    private void bindControle(View view, Plat plat) {
        //DEFINE IMAGE PLAT TOP
        ImageView imageViewPlatTop = (ImageView) view.findViewById(R.id.imageViewPlatTop);
        imageViewPlatTop.setImageResource(Helper.idResource(plat.getNomPic()));

        //SET TITLE
        TextView textViewTitreAP = (TextView) view.findViewById(R.id.textViewTitreMAP);
        textViewTitreAP.setText(plat.getNom());

        //SET POINT
        TextView textViewPoint = (TextView) view.findViewById(R.id.textViewPoint);
        textViewPoint.setText(String.valueOf(plat.getPoint()) + " Point");

        //SET DISRUPTION
        TextView textViewDiscription = (TextView) view.findViewById(R.id.textViewDiscription);
        textViewDiscription.setText(plat.getDiscription());
    }

    private void setLestenerAjouter(View view) {
        //LINK METHODE TO AJOUTER BUTTON
        Button btAjouter = (Button) view.findViewById(R.id.buttonAjouterPanier);
        btAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("dialog", "click ajouter");
                initiateButtonAjouter(view);
            }
        });
    }

    private void setLestenerClick(View view) {
        //LINK METHODE TO INCREMENT DECREMENT BUTTON
        ImageButton btIncrement = (ImageButton) view.findViewById(R.id.imageButtonIncriment);
        btIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("dialog", "click increment");
                incrimenteNbPlat();
            }
        });
        ImageButton btDecrement = (ImageButton) view.findViewById(R.id.imageButtonDecrement);
        btDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("dialog", "click decrement");
                decrementNbPlat();
            }
        });
    }

    private void incrimenteNbPlat() {
        platViewModel.incrimenteNbPlat();
    }

    private void decrementNbPlat() {
        platViewModel.decrementNbPlat();
    }

    private void bindIconVegiEpice(View view, Plat plat) {
        //SET ICON VEGUI
        ImageView imageViewVegi = (ImageView) view.findViewById(R.id.imageViewVegi);
        if (plat.getVegui() == "o")
            imageViewVegi.setImageResource(R.drawable.icon_vegi);
        else
            imageViewVegi.setImageResource(R.drawable.icon_vegi_no);

        //SET ICON EPICE
        ImageView imageViewDegureEpice = (ImageView) view.findViewById(R.id.imageViewDegureEpice);
        if (plat.getDegureEpice() == 3)
            imageViewDegureEpice.setImageResource(R.drawable.icon_degure_epice_3);

        else if (plat.getDegureEpice() == 2)
            imageViewDegureEpice.setImageResource(R.drawable.icon_degure_epice_2);
        else
            imageViewDegureEpice.setImageResource(R.drawable.icon_degure_epice_1);
    }

    private void initiateButtonAnnuller(@NonNull View view) {
        //CLOSE DIALOG ON ANNULER BUTTON CLICK
        Button btAnnuller = (Button) view.findViewById(R.id.buttonAnnuller);
        btAnnuller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("dialog", "click annuler");
                platViewModel.resetNumberPlat();
                dismiss();
            }
        });
    }

    private void initiateButtonAjouter(View view) {

        int newPtRest = upDatePtRest();

        //GET CURRENT CATEGORIE FROM CONTEUR
        int catActuel = ConteurRipository.getActuelCat();

        //IF POINT FINISH
        if (newPtRest == 0) {
            //NAVIGATE TO PANIER
            addToPanier();
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_dialogPlat_to_navigation_panier);
        }
        //IF CAN CHOSE IN SEM CATEGORIE NAVIGATE TO PLAT
        else if (newPtRest < catActuel) {
            //NAVIGATE TO CATEGORIE
            addToPanier();
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_dialogPlat_to_navigation_categorie);
        }
        //IF CAN NOT CHOSE IN SEM CATEGORIE NAVIGATE TO CATEGORIE
        else {
            //NAVIGATE TO PLAT
            addToPanier();
            dismiss();
        }

        platViewModel.resetNumberPlat();

    }

    private int upDatePtRest() {

        //GET POINT OF SELECTED PLAT
        int ptPlat = platViewModel.getSelectedPlat().getValue().getPoint();
        //GET NUMBER OF PLAT
        int nbrPlat = platViewModel.getNumberPlat().getValue();

        /*SAVE NEW RESTE POINT IN CONTEUR*/
        ConteurRipository.upDatePointRest(-nbrPlat * ptPlat);
        return ConteurRipository.getPointRest();
    }

    private void addToPanier() {
        /** the all insert, retreive, update and object creating ... go to be excute in background int no ui tread
         * to not impact to the UI**/
        AppDatabase.databaseWriteExecutor.execute(() -> {
            //GET DATA TO CONSTRUCT NEW ARTICLE PANIER OBJECT
            Plat plat = platViewModel.getSelectedPlat().getValue();
            int nbrPlat = platViewModel.getNumberPlat().getValue();
            int idPanier = ConteurRipository.getIdPanier();

            //CREATE NEW ARTICLE PANIER or UPDATE it
            ArticlePanier articlePanier = new ArticlePanier(idPanier, plat.getId(), nbrPlat);
            //update if excite or create a new article panier
            if (articlePanierRipository.finArticlePanier(articlePanier))
                articlePanierRipository.updateArticlePanier(articlePanier, nbrPlat);
            else articlePanierRipository.insert(articlePanier);
        });

    }


}
