package website.livingRoom.soliCatering.ui.historique.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.databinding.ModelMiniArticlePanierBinding;
import website.livingRoom.soliCatering.db.entitys.ArticlePanierAndPlat;
import website.livingRoom.soliCatering.utile.Helper;

public class MiniArticlePanierHolder extends RecyclerView.ViewHolder implements ItemClickListener {

    //FIELD
    private Context context;
    private final ModelMiniArticlePanierBinding binding;

    public MiniArticlePanierHolder(ModelMiniArticlePanierBinding modelMiniArticlePanierBinding) {
        super(modelMiniArticlePanierBinding.getRoot());

        //GET CONTEXT TO USE LATER
        context = modelMiniArticlePanierBinding.getRoot().getContext();

        // instantiate binding object
        binding = modelMiniArticlePanierBinding;
    }

    @Override
    public void onItemClick(View v, int pos) {

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
}
