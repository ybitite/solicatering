package website.livingRoom.soliCatering.ui.panier;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.R;

import website.livingRoom.soliCatering.databinding.FragmentPanierBinding;
import website.livingRoom.soliCatering.db.entitys.Conteur;
import website.livingRoom.soliCatering.ui.panier.rvpanier.ArticlePanierListAdapter;
import website.livingRoom.soliCatering.ui.panier.rvpanier.ArticlePanierPiedAdapter;


public class PanierFragment extends Fragment {

    //FIELD
    private FragmentPanierBinding binding;
    private SharedPreferences.OnSharedPreferenceChangeListener listenerSP;
    private SharedPreferences sharedPreferences;

    //METHODE
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //CREATE BINDING WITH INFLATE LAYOUT
        binding = FragmentPanierBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //INSTANTIATE CONTROLE
        RecyclerView rv = binding.includePanier.pRecycler;

        //INSTANTIATE VIEW MODEL
        PanierViewModel panierViewModel = new ViewModelProvider(this).get(PanierViewModel.class);

        //SET FOOTER ADAPTER + LIST ADAPTER TO RV
        ArticlePanierListAdapter articlePanierListAdapter = new ArticlePanierListAdapter(new ArticlePanierListAdapter.ArticlePanierWithPlatDiff(), panierViewModel);
        ArticlePanierPiedAdapter articlePanierPiedAdapter = new ArticlePanierPiedAdapter();
        ConcatAdapter concatAdapter = new ConcatAdapter(articlePanierListAdapter, articlePanierPiedAdapter);
        rv.setAdapter(concatAdapter);

        //ATTACHE ITEM TOUCHE HELPER TO RECYCLE VIEW
        new ItemTouchHelper(articlePanierListAdapter.simpleCallback).attachToRecyclerView(rv);

        //SET PROPERTIES TO RV
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        //INSTANTIATE LISTENER FOR UPDATE BT VALIDER WEN CONTEUR CHANGE IN SHARED PREFERENCES
        listenerSP = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key == Conteur.POINT_RESTE_KEY) articlePanierPiedAdapter.bind();
            }
        };

        //CREATE INSTANCE OF SHARED PREFERENCES AND REGISTER THE LISTENER IN
        sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.conteur_file_name), Context.MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(listenerSP);

        //OBSERVE DATA FROM LIVE DATA AND UPDATE RV WEN DATA CHANGE
        panierViewModel.getListArticlePanierAndPlat().observe(getViewLifecycleOwner(), articlePanierWithPlat -> {
            // UPDATE THE CACHED COPY OF THE ARTICLE PANIER IN THE ADAPTER.
            articlePanierListAdapter.submitList(articlePanierWithPlat);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterListener();
        binding = null;
    }

    /* TO LIBER LISTENER*/
    public void unregisterListener() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listenerSP);
    }
}