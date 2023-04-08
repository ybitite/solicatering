package website.livingRoom.soliCatering.view.menu;

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

import website.livingRoom.soliCatering.databinding.FragmentMenuBinding;
import website.livingRoom.soliCatering.model.room.AppDatabase;
import website.livingRoom.soliCatering.view.menu.rv.MenuListAdapter;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;
import website.livingRoom.soliCatering.viewModel.MenuViewModel;


public class MenuFragment extends Fragment {

    //FIELD
    MenuViewModel menuViewModel;

    ConteurViewModel conteurViewModel;
    FragmentMenuBinding binding;

    //METHODE
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //CREATE BINDING WITH INFLATE LAYOUT
        binding = FragmentMenuBinding.inflate(inflater, container, false);

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
        final MenuListAdapter menuListAdapter = new MenuListAdapter(new MenuListAdapter.MenuDiff(),conteurViewModel);
        binding.mRecycler.setAdapter(menuListAdapter);

        //SET PROPERTIES TO RV
        binding.mRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.mRecycler.setItemAnimator(new DefaultItemAnimator());

        observeDataAndUpdateView(menuListAdapter);
    }

    private void observeDataAndUpdateView(MenuListAdapter menuListAdapter) {
        //OBSERVE DATA FROM LIVE DATA AND UPDATE RV WEN DATA CHANGE
        menuViewModel =
                new ViewModelProvider(this).get(MenuViewModel.class);

        menuViewModel.getListMenu().observe(getViewLifecycleOwner(), menus -> menuListAdapter.submitList(menus));
    }

    @Override
    public void onPause() {
        AppDatabase.shutdownAfterTermination();
        super.onPause( );
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

}