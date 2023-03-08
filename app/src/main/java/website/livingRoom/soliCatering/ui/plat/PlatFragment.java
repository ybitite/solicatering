package website.livingRoom.soliCatering.ui.plat;

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

import website.livingRoom.soliCatering.databinding.FragmentPlatBinding;
import website.livingRoom.soliCatering.repository.ConteurRepository;
import website.livingRoom.soliCatering.ui.plat.rv.PlatsListAdapter;

public class PlatFragment extends Fragment {

    //FIELD
    PlatViewModel platViewModel;
    FragmentPlatBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //CREATE BINDING WITH INFLATE LAYOUT
        binding = FragmentPlatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //INSTANTIATE CONTROLE
        RecyclerView rv = binding.includePlat.plRecycler;

        //INSTANTIATE VIEW MODEL
        platViewModel =
                new ViewModelProvider(requireActivity()).get(PlatViewModel.class);

        //SET ADAPTER TO RV
        final PlatsListAdapter platsListAdapter = new PlatsListAdapter(new PlatsListAdapter.platDiff(), platViewModel);
        rv.setAdapter(platsListAdapter);

        //SET PROPERTIES TO RV
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        //GET CURENT CATEGORIE POINT
        int ptRest = ConteurRepository.getCategorieActuel();

        //OBSERVE DATA FROM LIVE DATA AND UPDATE RV WEN DATA CHANGE
        platViewModel =
                new ViewModelProvider(this).get(PlatViewModel.class);

        platViewModel.getAllPlat(ptRest).observe(getViewLifecycleOwner(), plat -> {
            platsListAdapter.submitList(plat);
        });

        return root;
    }
}