package com.example.marokkanischbernessen.ui.historique;

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

import com.example.marokkanischbernessen.databinding.FragmentHistoriqueBinding;
import com.example.marokkanischbernessen.ui.historique.rv.PanierListAdapter;

public class HistoriqueFragment extends Fragment {

    //FIELD
    private HistoriqueViewModel historiqueViewModel;
    FragmentHistoriqueBinding binding;

    //METHODE
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //INSTANTIATE BINDING
        binding = FragmentHistoriqueBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //INSTANTIATE RV
        RecyclerView rv = binding.panierRecycler;

        //SET ADAPTER TO RV
        final PanierListAdapter panierListAdapter = new PanierListAdapter(new PanierListAdapter.PanierDiff());
        rv.setAdapter(panierListAdapter);

        //SET PROPERTIES TO RV
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        //OBSERVE DATA FROM LIVE DATA AND UPDATE RV WEN DATA CHANGE
        historiqueViewModel =
                new ViewModelProvider(this).get(HistoriqueViewModel.class);

        historiqueViewModel.getAllPanierWithArticlePanier().observe(getViewLifecycleOwner(), listPanierWithAP -> {
            panierListAdapter.submitList(listPanierWithAP);
        });

        return root;
    }
}