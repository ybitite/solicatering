package website.livingRoom.soliCatering.view.plat;

import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.view.IconHolder;
import website.livingRoom.soliCatering.viewModel.PlatViewModel;
import website.livingRoom.soliCatering.databinding.ViewPlatsBinding;
import website.livingRoom.soliCatering.model.entitys.ArticlePanier;
import website.livingRoom.soliCatering.model.entitys.Plat;
import website.livingRoom.soliCatering.model.room.AppDatabase;
import website.livingRoom.soliCatering.repository.ArticlePanierRepository;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.utile.Helper;

public class DialogPlatHolder {
    private final PlatViewModel platViewModel;
    private final ArticlePanierRepository articlePanierRepository;
    private final ViewPlatsBinding binding;

    private final FragmentActivity fragmentActivity;

    private final DialogPlatFragment dialogPlatFragment;

    public DialogPlatHolder(PlatViewModel platViewModel, ArticlePanierRepository articlePanierRepository,
                            ViewPlatsBinding binding, FragmentActivity fragmentActivity, DialogPlatFragment dialogPlatFragment) {
        this.platViewModel = platViewModel;
        this.articlePanierRepository = articlePanierRepository;
        this.binding = binding;
        this.fragmentActivity=fragmentActivity;
        this.dialogPlatFragment=dialogPlatFragment;
    }

    public void bind() {
        /*BIND VIEW WITH SELECTED PLAT*/
        //OBSERVE SELECTED PLAT
        platViewModel.getSelectedPlat().observe(fragmentActivity, this::bindView);
        //OBSERVE NUMBER PLAT
        platViewModel.getNumberPlat().observe(fragmentActivity, this::upDateNombrePlatTextView);
    }

    private void bindView(Plat plat) {

        bindControle(plat);

        IconHolder iconHolder = new IconHolder(binding.imageViewVegi,binding.imageViewDegureEpice,plat);

        iconHolder.upDateIconVegi();
        iconHolder.updateIconEpice();

        setLestenerClick();
    }

    private void bindControle(Plat plat) {
        Helper.bindPicassoImage(plat.getNomPic(),binding.imageViewPlatTop);
        binding.setPlat(plat);
    }

    private void setLestenerClick() {
        //LINK METHODE TO INCREMENT DECREMENT ajouter annuller BUTTON
        binding.imageButtonIncriment.setOnClickListener(v -> incrimenteNombre());
        binding.imageButtonDecrement.setOnClickListener(v-> decrementNombre());
        binding.buttonAjouterPanier.setOnClickListener(v -> ajouter());
        binding.buttonAnnuller.setOnClickListener(v->annuller());

    }

    private void incrimenteNombre() {
        //IF THE PRODUCT  OF POINT AND NUMBER OF PLAT ARE LAST OR EQUAL AT POINT RESTE
        if (checkIncrease(ConteurRepository.getPointReste(), platViewModel.getSelectedPlat().getValue().getPoint(), platViewModel.getNumberPlat().getValue())) {
            platViewModel.incrimenteNbPlat();
        }

    }
    private void decrementNombre() {
        //IF NUMBER ARE MOR THAN 1
        if (platViewModel.getNumberPlat().getValue() > 1) {
            platViewModel.decrementNbPlat();
        }
    }

    private boolean checkIncrease(int pntReste, int pntPlat, int nbrPlat) {
        return pntPlat * (nbrPlat + 1) <= pntReste;
    }


    private void ajouter( ) {
        if(checkUpDatePointRest()){
            int newPtRest = upDatePtRest();
            addToPanier();
            //IF POINT FINISH NAVIGATE TO PANIER
            if (newPtRest == 0) {
                naviguer(R.id.action_dialogPlat_to_navigation_panier);
            }
            //IF CAN NOT CHOSE IN SEM CATEGORIE NAVIGATE TO CATEGORIE
            else if (newPtRest < ConteurRepository.getCategorieActuel()) {
                naviguer(R.id.action_dialogPlat_to_navigation_categorie);
            }
            //IF CAN NOT CHOSE IN SEM CATEGORIE NAVIGATE TO plat
            else {
                dialogPlatFragment.dismiss();
            }
            platViewModel.resetNumberPlat();
        }
    }

    private void naviguer(int actionId) {
        Navigation.findNavController(fragmentActivity,R.id.nav_host_fragment_activity_main).navigate(actionId);
    }

    private void annuller() {
        platViewModel.resetNumberPlat();
        dialogPlatFragment.dismiss();
    }
    private boolean checkUpDatePointRest() {
        return ConteurRepository.getPointReste()
                - platViewModel.getSelectedPlat().getValue().getPoint()
                * platViewModel.getNumberPlat().getValue() >=0;
    }

    private int upDatePtRest() {
        /*SAVE NEW RESTE POINT IN CONTEUR*/
        ConteurRepository.upDatePointReste(- platViewModel.getSelectedPlat().getValue().getPoint()
                * platViewModel.getNumberPlat().getValue());
        return ConteurRepository.getPointReste();
    }

    private void addToPanier() {
        /*go to be excute in background int no ui tread
          to not impact to the UI**/
        AppDatabase.databaseWriteExecutor.execute(this::run);

    }

    private void run() {
        //CREATE NEW ARTICLE PANIER or UPDATE it
        ArticlePanier articlePanier = new ArticlePanier(ConteurRepository.getPanierActuel(),
                platViewModel.getSelectedPlat().getValue().getId(),
                platViewModel.getNumberPlat().getValue());

        //update if excite or create a new article panier
        if (articlePanierRepository.finArticlePanier(articlePanier))
            articlePanierRepository.updateArticlePanier(articlePanier, platViewModel.getNumberPlat().getValue());
        else articlePanierRepository.insert(articlePanier);
    }

    private void upDateNombrePlatTextView(Integer nbrPlat) {
        binding.textViewNbrPlat.setText(String.valueOf(nbrPlat));
    }
}
