package website.livingRoom.soliCatering.view.panier;

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
import website.livingRoom.soliCatering.model.entitys.Conteur;
import website.livingRoom.soliCatering.view.panier.rvpanier.ArticlePanierListAdapter;
import website.livingRoom.soliCatering.view.panier.rvpanier.ArticlePanierPiedAdapter;
import website.livingRoom.soliCatering.viewModel.PanierViewModel;


public class PanierFragment extends Fragment {

    //FIELD
    private FragmentPanierBinding binding;
    private SharedPreferences.OnSharedPreferenceChangeListener listenerSP;
    private SharedPreferences sharedPreferences;
    private PanierViewModel panierViewModel;

    //METHODE
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //CREATE BINDING WITH INFLATE LAYOUT
        binding = FragmentPanierBinding.inflate(inflater, container, false);

        //INSTANTIATE VIEW MODEL
        panierViewModel = new ViewModelProvider(this).get(PanierViewModel.class);

        initiateRecycleView();


        return binding.getRoot();
    }

    private void initiateRecycleView() {
        //INSTANTIATE CONTROLE
        RecyclerView rv = binding.pRecycler;

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


        observeDataAndUpdateView(panierViewModel, articlePanierListAdapter, articlePanierPiedAdapter);
    }

    private void observeDataAndUpdateView(PanierViewModel panierViewModel, ArticlePanierListAdapter articlePanierListAdapter, ArticlePanierPiedAdapter articlePanierPiedAdapter) {
        //OBSERVE DATA FROM LIVE DATA AND UPDATE RV WEN DATA CHANGE
        panierViewModel.getListArticlePanierAndPlat().observe(getViewLifecycleOwner(), articlePanierWithPlat -> {
            // UPDATE THE CACHED COPY OF THE ARTICLE PANIER IN THE ADAPTER.
            articlePanierListAdapter.submitList(articlePanierWithPlat);
        });
        registerSharedPreferencesListener(articlePanierPiedAdapter);
    }

    private void registerSharedPreferencesListener(ArticlePanierPiedAdapter articlePanierPiedAdapter) {
        //INSTANTIATE LISTENER FOR UPDATE BT VALIDER WEN CONTEUR CHANGE IN SHARED PREFERENCES
        listenerSP = (sharedPreferences, key) -> {
            if (key.equals(Conteur.POINT_RESTE_KEY)) articlePanierPiedAdapter.bind();
        };

        //CREATE INSTANCE OF SHARED PREFERENCES AND REGISTER THE LISTENER IN
        sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.conteur_file_name), Context.MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(listenerSP);
    }

    /* TO LIBER LISTENER*/
    public void unregisterListener() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listenerSP);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterListener();
        binding = null;
    }
}