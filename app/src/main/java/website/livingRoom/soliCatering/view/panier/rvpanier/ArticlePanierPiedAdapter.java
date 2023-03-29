package website.livingRoom.soliCatering.view.panier.rvpanier;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;

import website.livingRoom.soliCatering.databinding.ModelPiedArticlePanierBinding;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;


public class ArticlePanierPiedAdapter extends RecyclerView.Adapter<ArticlePanierPiedHolder> {

    //FIELD
    private ConteurViewModel conteurViewModel;

    //contractor
    public ArticlePanierPiedAdapter(ConteurViewModel conteurViewModel) {
        super();
        this.conteurViewModel = conteurViewModel;
    }

    //OVERRIDE METHODE
    @NonNull
    @Override
    public ArticlePanierPiedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return ArticlePanierPiedHolder.create(parent,conteurViewModel);
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
