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
    static Context context;
    static ModelMiniArticlePanierBinding binding;

    public MiniArticlePanierHolder(@NonNull View itemView) {
        super(itemView);

        //get context to use later
        context = binding.getRoot().getContext();
    }

    @Override
    public void onItemClick(View v, int pos) {

    }

    public static MiniArticlePanierHolder create(ViewGroup parent) {
        binding = ModelMiniArticlePanierBinding
                .inflate(LayoutInflater.from(parent.getContext()));
        return new MiniArticlePanierHolder(binding.getRoot());
    }

    public void bind(ArticlePanierAndPlat articlePanierAndPlat) {

        binding.setArticlePanierAndPlat(articlePanierAndPlat);
        binding.imageViewMiniAP.setImageResource(Helper.idResource(articlePanierAndPlat.plat.getNomPic()));
    }
}