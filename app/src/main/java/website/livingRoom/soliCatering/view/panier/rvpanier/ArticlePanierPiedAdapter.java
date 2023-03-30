package website.livingRoom.soliCatering.view.panier.rvpanier;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.databinding.ModelPiedArticlePanierBinding;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;


public class ArticlePanierPiedAdapter extends RecyclerView.Adapter<ArticlePanierPiedHolder> {

    //FIELD
    private final ConteurViewModel conteurViewModel;
    private final ModelPiedArticlePanierBinding modelPiedArticlePanierBinding;

    //contractor
    public ArticlePanierPiedAdapter(ConteurViewModel conteurViewModel, ModelPiedArticlePanierBinding modelPiedArticlePanierBinding) {
        super();
        this.conteurViewModel = conteurViewModel;
        this.modelPiedArticlePanierBinding = modelPiedArticlePanierBinding;
    }



    //OVERRIDE METHODE
    @NonNull
    @Override
    public ArticlePanierPiedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return ArticlePanierPiedHolder.create(modelPiedArticlePanierBinding,conteurViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlePanierPiedHolder holder, int position) {

        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 1;
    }



}
