package website.livingRoom.soliCatering.ui.Menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.databinding.FragmentMenuBinding;
import website.livingRoom.soliCatering.ui.Menu.rv.MenuListAdapter;


public class MenuFragment extends Fragment {

    //FIELD
    MenuViewModel menuViewModel;
    FragmentMenuBinding binding;

    //METHODE
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //CREATE BINDING WITH INFLATE LAYOUT
        binding = FragmentMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //INSTANTIATE CONTROLE
        RecyclerView rv = binding.includeCommande.mRecycler;

        //SET ADAPTER TO RV
        final MenuListAdapter menuListAdapter = new MenuListAdapter(new MenuListAdapter.MenuDiff());
        rv.setAdapter(menuListAdapter);

        //SET PROPERTIES TO RV
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        //OBSERVE DATA FROM LIVE DATA AND UPDATE RV WEN DATA CHANGE
        menuViewModel =
                new ViewModelProvider(this).get(MenuViewModel.class);

        menuViewModel.getAllMenu().observe(getViewLifecycleOwner(), menus -> {
            menuListAdapter.submitList(menus);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}