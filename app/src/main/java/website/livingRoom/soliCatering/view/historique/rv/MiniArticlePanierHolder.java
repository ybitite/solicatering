package website.livingRoom.soliCatering.view.historique.rv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.databinding.ModelMiniArticlePanierBinding;
import website.livingRoom.soliCatering.model.entitys.ArticlePanierAndPlat;
import website.livingRoom.soliCatering.utile.Helper;

public class MiniArticlePanierHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    //FIELD
    private final ModelMiniArticlePanierBinding binding;

    public MiniArticlePanierHolder(ModelMiniArticlePanierBinding modelMiniArticlePanierBinding) {
        super(modelMiniArticlePanierBinding.getRoot());

        // instantiate binding object
        binding = modelMiniArticlePanierBinding;
    }

    public static MiniArticlePanierHolder create(ViewGroup parent) {
        ModelMiniArticlePanierBinding modelMiniArticlePanierBinding = ModelMiniArticlePanierBinding
                .inflate(LayoutInflater.from(parent.getContext()));
        return new MiniArticlePanierHolder(modelMiniArticlePanierBinding);
    }

    public void bind(ArticlePanierAndPlat articlePanierAndPlat) {

        binding.setArticlePanierAndPlat(articlePanierAndPlat);
        binding.imageViewMiniAP.setImageResource(Helper.idResource(articlePanierAndPlat.plat.getNomPic()));
    }

    @Override
    public void onClick(View v) {

    }
}
