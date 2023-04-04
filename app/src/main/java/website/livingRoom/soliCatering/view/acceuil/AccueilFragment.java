package website.livingRoom.soliCatering.view.acceuil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.databinding.FragmentAcceuilBinding;
import website.livingRoom.soliCatering.view.acceuil.rv.EvenementBtCmdAdapter;
import website.livingRoom.soliCatering.view.acceuil.rv.EvenementListAdapter;
import website.livingRoom.soliCatering.view.acceuil.rv.EvenementTeteAdapter;
import website.livingRoom.soliCatering.viewModel.AcceuilViewModel;

public class AccueilFragment extends Fragment {

    //FIELD
    private FragmentAcceuilBinding binding;
    private AcceuilViewModel acceuilViewModel;

    //METHODE
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAcceuilBinding.inflate(inflater, container, false);
        acceuilViewModel =  new ViewModelProvider(this).get(AcceuilViewModel.class);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //initiate recycle view et observer live data
        initiateRecycleView();

        super.onViewCreated(view, savedInstanceState);
    }


    private void initiateRecycleView() {

        final EvenementListAdapter evenementListAdapter = setAdapterToRv(binding.evRecycler);

        binding.evRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.evRecycler.setItemAnimator(new DefaultItemAnimator());

        //observe data from live data and update rv
        observeDataAndUpdateView(evenementListAdapter);
    }

    @NonNull
    private EvenementListAdapter setAdapterToRv(RecyclerView rv) {
        //create concat adapter from HEADER ADAPTER + BT COMMANDER ADAPTER + LIST ADAPTER
        final EvenementListAdapter evenementListAdapter =
                new EvenementListAdapter(new EvenementListAdapter.EvenementDiff());
        final ConcatAdapter concatAdapter =
                new ConcatAdapter(new EvenementTeteAdapter(),  new EvenementBtCmdAdapter(), evenementListAdapter);

        //SET PROPERTIES TO RV

        rv.setAdapter(concatAdapter);
        return evenementListAdapter;
    }

    private void observeDataAndUpdateView(EvenementListAdapter evenementListAdapter) {

        //OBSERVE DATA FROM LIVE DATA AND UPDATE RV WEN DATA CHANGE
        // UPDATE THE CACHED COPY OF THE EVENEMENTS IN THE ADAPTER.
        acceuilViewModel.getListEvenement().observe(getViewLifecycleOwner(), evenementListAdapter::submitList);
    }


    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

}