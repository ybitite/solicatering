package website.livingRoom.soliCatering.view.historique;

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

import website.livingRoom.soliCatering.viewModel.HistoriqueViewModel;
import website.livingRoom.soliCatering.databinding.FragmentHistoriqueBinding;
import website.livingRoom.soliCatering.view.historique.rv.PanierListAdapter;

public class HistoriqueFragment extends Fragment {

    private HistoriqueViewModel historiqueViewModel;
    private FragmentHistoriqueBinding binding;

    //METHODE
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //INSTANTIATE BINDING
        binding = FragmentHistoriqueBinding
                .inflate(inflater, container, false);

        historiqueViewModel = new ViewModelProvider(this).get(HistoriqueViewModel.class);

        initiateRecycleView();

        return binding.getRoot();
    }

    private void initiateRecycleView() {

        //INSTANTIATE RV
        RecyclerView rv = binding.panierRecycler;

        //SET ADAPTER TO RV
        final PanierListAdapter panierListAdapter = new PanierListAdapter(new PanierListAdapter.PanierDiff());
        rv.setAdapter(panierListAdapter);

        //SET PROPERTIES TO RV
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        observeDataAndUpdateView(panierListAdapter);
    }

    private void observeDataAndUpdateView(PanierListAdapter panierListAdapter) {

        //OBSERVE DATA FROM LIVE DATA AND UPDATE RV WEN DATA CHANGE
        historiqueViewModel.getListPanierWithArticlePanierAndPlat().observe(getViewLifecycleOwner(), listPanierWithAP -> panierListAdapter.submitList(listPanierWithAP));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}