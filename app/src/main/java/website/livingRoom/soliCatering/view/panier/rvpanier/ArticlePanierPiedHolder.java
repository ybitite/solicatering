package website.livingRoom.soliCatering.view.panier.rvpanier;

import androidx.databinding.library.baseAdapters.BR;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;
import website.livingRoom.soliCatering.databinding.ModelPiedArticlePanierBinding;
import website.livingRoom.soliCatering.utile.Helper;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;

public class ArticlePanierPiedHolder extends RecyclerView.ViewHolder {

    final ModelPiedArticlePanierBinding binding;

    final ConteurViewModel conteurViewModel;
    public ArticlePanierPiedHolder(ModelPiedArticlePanierBinding modelPiedArticlePanierBinding, ConteurViewModel conteurViewModel) {
        super(modelPiedArticlePanierBinding.getRoot());

        binding = modelPiedArticlePanierBinding;
        this.conteurViewModel = conteurViewModel;

    }

    public static ArticlePanierPiedHolder create(ModelPiedArticlePanierBinding modelPiedArticlePanierBinding, ConteurViewModel conteurViewModel){

        return  new ArticlePanierPiedHolder(modelPiedArticlePanierBinding,conteurViewModel);
    }

    //BIND METHODE
    public void  bind() {
        bindButtonValider();
        conteurViewModel.getConteur().notifyPropertyChanged(BR.clickableButtonValiderValue);
    }

    private  void bindButtonValider() {
        Helper.fixWidth(binding.mockViewFormPiedArticlePanier);
        /*set on click listener button valider */
        setOnClickListenerToButtonValider();
    }


    private void setOnClickListenerToButtonValider() {
        binding.buttonValiderCmd.setOnClickListener(v -> naviguer(R.id.dialogClient));
    }

    private void naviguer(int actionId){
        Navigation.findNavController(itemView).navigate(actionId);
    }


}