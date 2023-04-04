package website.livingRoom.soliCatering.view.categorie;

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

import website.livingRoom.soliCatering.databinding.FragmentCategorieBinding;
import website.livingRoom.soliCatering.view.categorie.rv.CategorieListAdapter;
import website.livingRoom.soliCatering.viewModel.CategorieViewModel;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;


public class CategorieFragment extends Fragment {

    //FIELD
    CategorieViewModel categorieViewModel;
    ConteurViewModel conteurViewModel;
    FragmentCategorieBinding binding;


    //METHODE
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //INSTANTIATE BINDING
        binding = FragmentCategorieBinding.inflate(inflater, container, false);

        categorieViewModel =  new ViewModelProvider(this).get(CategorieViewModel.class);

        conteurViewModel = new ViewModelProvider(requireActivity()).get(ConteurViewModel.class);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initiateRecycleView();
        super.onViewCreated(view, savedInstanceState);
    }

    private void initiateRecycleView() {

        //SET ADAPTER TO RV
        final CategorieListAdapter categorieListAdapter = new CategorieListAdapter(new CategorieListAdapter.CategorieDiff(),conteurViewModel);
        binding.cRecycler.setAdapter(categorieListAdapter);

        //SET PROPERTIES TO RV
        binding.cRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.cRecycler.setItemAnimator(new DefaultItemAnimator());

        //observe data and update recycle view
        observeDataAndUpdateView(categorieListAdapter);
    }

    private void observeDataAndUpdateView(CategorieListAdapter categorieListAdapter) {
        //OBSERVE DATA FROM LIVE DATA AND UPDATE RV WEN DATA CHANGE
        categorieViewModel.getListCategorie().observe(getViewLifecycleOwner(), categorieListAdapter::submitList);
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}