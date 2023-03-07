package website.livingRoom.soliCatering.ui.panier.rvpanier;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;

import website.livingRoom.soliCatering.databinding.ModelPiedArticlePanierBinding;
import website.livingRoom.soliCatering.repository.ConteurRepository;


public class ArticlePanierPiedAdapter extends RecyclerView.Adapter<ArticlePanierPiedAdapter.ArticlePanierPiedHolder> {

    //FIELD
    ModelPiedArticlePanierBinding binding;
    Context context;

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
        final int ptRest = ConteurRepository.getPointRest();
        final int ptCat = ConteurRepository.getActuelMenu();
        Button buttonValider = binding.buttonValiderCmd;
        /*DEFINE TEXT, COLOR AND ACCESSIBILITY TO VALIDER BUTTON */
        if (ptRest == 0 && ptCat > 0) {
            buttonValider.setText(R.string.bt_valider_cmd);
            buttonValider.setClickable(true);
            int color = ContextCompat.getColor(context, R.color.gris_principale);
            buttonValider.setBackgroundColor(color);
            buttonValider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        //TODO: beug dû au context / ok with try catch
                        Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_main)
                                .navigate(R.id.action_navigation_panier_to_dialogClient);
                    } catch (Exception exception) {
                    }
                }
            });
        } else if (0 < ptRest && ptRest < ptCat) {
            buttonValider.setText(R.string.bt_valider_cmd);
            buttonValider.setClickable(false);
            int color = ContextCompat.getColor(context, R.color.gris_moyen);
            buttonValider.setBackgroundColor(color);

        } else {
            buttonValider.setClickable(false);
            buttonValider.setText(R.string.bt_valider_cmd_0_article);
            int color = ContextCompat.getColor(context, R.color.gris_moyen);
            buttonValider.setBackgroundColor(color);

        }
    }

    class ArticlePanierPiedHolder extends RecyclerView.ViewHolder {
        ArticlePanierPiedHolder(View itemView) {
            super(itemView);
        }
    }
}
