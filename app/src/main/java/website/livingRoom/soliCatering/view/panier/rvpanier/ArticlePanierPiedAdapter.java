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


public class ArticlePanierPiedAdapter extends RecyclerView.Adapter<ArticlePanierPiedAdapter.ArticlePanierPiedHolder> {

    //FIELD
    private ModelPiedArticlePanierBinding binding;
    private Context context;

    //OVERRIDE METHODE
    @NonNull
    @Override
    public ArticlePanierPiedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_pied_article_panier, parent, false);

        ArticlePanierPiedHolder articlePanierPiedHolder = new ArticlePanierPiedHolder(view);

        binding = ModelPiedArticlePanierBinding.bind(view);

        context = parent.getContext();

        return articlePanierPiedHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlePanierPiedHolder holder, int position) {

        bind();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    //BIND METHODE
    public void bind() {
        //GET POINT RESTE AND CURENT MENNU FROM CONTEUR
        final int ptRest = ConteurRepository.getPointReste();
        final int ptCat = ConteurRepository.getPointDepart();

        bindButtonValider(ptRest, ptCat);
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
        binding.buttonValiderCmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main)
                        .navigate(R.id.action_navigation_panier_to_dialogClient);
            }
        });
    }

    static class ArticlePanierPiedHolder extends RecyclerView.ViewHolder {
        ArticlePanierPiedHolder(View itemView) {
            super(itemView);
        }
    }
}
