package website.livingRoom.soliCatering.view.panier.rvpanier;

import android.icu.text.DateTimePatternGenerator;
import android.icu.text.ListFormatter;
import android.icu.text.MeasureFormat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.ModelPiedArticlePanierBinding;
import website.livingRoom.soliCatering.utile.AppUtile;
import website.livingRoom.soliCatering.utile.Helper;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;

public class ArticlePanierPiedHolder extends RecyclerView.ViewHolder {

    ModelPiedArticlePanierBinding binding;

    ConteurViewModel conteurViewModel;
    ArticlePanierPiedHolder(ModelPiedArticlePanierBinding modelPiedArticlePanierBinding, ConteurViewModel conteurViewModel) {
        super(modelPiedArticlePanierBinding.getRoot());

        binding = modelPiedArticlePanierBinding;
        this.conteurViewModel = conteurViewModel;
    }

    public static ArticlePanierPiedHolder create(ViewGroup parent, ConteurViewModel conteurViewModel){
        ModelPiedArticlePanierBinding modelPiedArticlePanierBinding= ModelPiedArticlePanierBinding
                .inflate(LayoutInflater.from(parent.getContext()));
        return  new ArticlePanierPiedHolder(modelPiedArticlePanierBinding,conteurViewModel);
    }

    //BIND METHODE
    public void bind() {
        //GET POINT RESTE AND CURENT MENNU FROM CONTEUR
        bindButtonValider(conteurViewModel.getConteur().getPointReste(), conteurViewModel.getConteur().getPointDepart());
    }

    private void bindButtonValider(int ptRest, int ptCat) {
        /*update button valider */
        if (ptRest == 0 && ptCat > 0) {

            upDateBottonValider(R.string.bt_valider_cmd, true);
            setOnClickListenerToButtonValider();
        }
        else if (0 < ptRest && ptRest < ptCat) {

            upDateBottonValider(R.string.bt_valider_cmd, false);
        }
        else {
            upDateBottonValider(R.string.bt_valider_cmd_0_article, false);
        }
    }

    private void upDateBottonValider(int text, boolean clickable) {
        Button buttonValider = binding.buttonValiderCmd;
        buttonValider.setText(text);
        buttonValider.setClickable(clickable);
    }

    private void setOnClickListenerToButtonValider() {
        binding.buttonValiderCmd.setOnClickListener(v -> naviguer(R.id.action_navigation_panier_to_dialogClient));
        Helper.fixWidth(binding.mockViewFormPiedArticlePanier);

    }

    private void naviguer(int actionId){
        Navigation.findNavController(itemView).navigate(actionId);
    }

}