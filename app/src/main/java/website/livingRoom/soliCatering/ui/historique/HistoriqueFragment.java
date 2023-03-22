package website.livingRoom.soliCatering.ui.historique;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.databinding.FragmentHistoriqueBinding;
import website.livingRoom.soliCatering.ui.historique.rv.PanierListAdapter;

public class HistoriqueFragment extends Fragment {

    //METHODE
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //INSTANTIATE BINDING
        FragmentHistoriqueBinding binding = FragmentHistoriqueBinding
                .inflate(inflater, container, false);
        View root = binding.getRoot();

        //INSTANTIATE RV
        RecyclerView rv = binding.panierRecycler;
        initiateRecycleView(rv);

        return root;
    }

    private void initiateRecycleView(RecyclerView rv) {

        //SET ADAPTER TO RV
        final PanierListAdapter panierListAdapter = new PanierListAdapter(new PanierListAdapter.PanierDiff());
        rv.setAdapter(panierListAdapter);

        //SET PROPERTIES TO RV
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        observeAndUpdate(panierListAdapter);
    }

    private void observeAndUpdate(PanierListAdapter panierListAdapter) {
        //OBSERVE DATA FROM LIVE DATA AND UPDATE RV WEN DATA CHANGE
        //FIELD
        HistoriqueViewModel historiqueViewModel = new ViewModelProvider(this).get(HistoriqueViewModel.class);

        historiqueViewModel.getListPanierWithArticlePanierAndPlat().observe(getViewLifecycleOwner(), listPanierWithAP -> {
            panierListAdapter.submitList(listPanierWithAP);
        });
    }
}