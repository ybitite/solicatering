package website.livingRoom.soliCatering.view.historique.rv;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import website.livingRoom.soliCatering.model.entitys.ArticlePanierAndPlat;

public class MiniArticlePanierListAdapter extends ListAdapter<ArticlePanierAndPlat, MiniArticlePanierHolder> {

    //CONSTRUCTOR
    protected MiniArticlePanierListAdapter(@NonNull DiffUtil.ItemCallback<ArticlePanierAndPlat> diffCallback) {
        super(diffCallback);
    }

    //METHODE
    @NonNull
    @Override
    public MiniArticlePanierHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MiniArticlePanierHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MiniArticlePanierHolder holder, int position) {
        holder.bind(getItem(position));
    }

}
