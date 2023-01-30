package com.example.marokkanischbernessen.ui.categorie;

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

import com.example.marokkanischbernessen.databinding.FragmentCategorieBinding;
import com.example.marokkanischbernessen.ui.categorie.rv.CategorieListAdapter;


public class CategorieFragment extends Fragment {

    //FIELD
    CategorieViewModel categorieViewModel;
    FragmentCategorieBinding binding;


    //METHODE
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //INSTANTIATE BINDING
        binding = FragmentCategorieBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //INSTANTIATE RV
        RecyclerView rv = binding.includeCategorie.cRecycler;

        //SET ADAPTER TO RV
        //todo:add adpter when ptcat actuel is ms
        final CategorieListAdapter categorieListAdapter = new CategorieListAdapter(new CategorieListAdapter.CategorieDiff());
        rv.setAdapter(categorieListAdapter);

        //SET PROPERTIES TO RV
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        //OBSERVE DATA FROM LIVE DATA AND UPDATE RV WEN DATA CHANGE
        categorieViewModel =
                new ViewModelProvider(this).get(CategorieViewModel.class);

        categorieViewModel.getAllCategorie().observe(getViewLifecycleOwner(), categorie -> {
            categorieListAdapter.submitList(categorie);
        });

        //ConteurRipository.createConteur("Choisir un menu",0,root.getContext());

        return root;
    }
}