package website.livingRoom.soliCatering.view.plat;

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

import website.livingRoom.soliCatering.viewModel.ConteurViewModel;
import website.livingRoom.soliCatering.viewModel.PlatViewModel;
import website.livingRoom.soliCatering.databinding.FragmentPlatBinding;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.view.plat.rv.PlatsListAdapter;

public class PlatFragment extends Fragment {

    //FIELD
    PlatViewModel platViewModel;
    FragmentPlatBinding binding;
    private ConteurViewModel conteurViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //CREATE BINDING WITH INFLATE LAYOUT
        binding = FragmentPlatBinding.inflate(inflater, container, false);


        //INSTANTIATE VIEW MODEL
        platViewModel = new ViewModelProvider(requireActivity()).get(PlatViewModel.class);

        conteurViewModel = new ViewModelProvider(requireActivity()).get(ConteurViewModel.class);

        initiateRecycleView();

        return binding.getRoot();
    }

    private void initiateRecycleView() {
        //INSTANTIATE CONTROLE
        RecyclerView rv = binding.plRecycler;

        //SET ADAPTER TO RV
        final PlatsListAdapter platsListAdapter = new PlatsListAdapter(new PlatsListAdapter.platDiff(), platViewModel, conteurViewModel);
        rv.setAdapter(platsListAdapter);

        //SET PROPERTIES TO RV
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        observeDataAndUpdateView(platsListAdapter);
    }

    private void observeDataAndUpdateView(PlatsListAdapter platsListAdapter) {

        //OBSERVE DATA FROM LIVE DATA AND UPDATE RV WEN DATA CHANGE
        platViewModel.getAllPlat(conteurViewModel.getConteur().getCategorieActuel())
                .observe(getViewLifecycleOwner(), plat -> platsListAdapter.submitList(plat));
    }
}