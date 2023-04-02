package website.livingRoom.soliCatering.view.acceuil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import website.livingRoom.soliCatering.viewModel.AcceuilViewModel;
import website.livingRoom.soliCatering.databinding.FragmentAcceuilBinding;
import website.livingRoom.soliCatering.view.acceuil.rv.EvenementBtCmdAdapter;
import website.livingRoom.soliCatering.view.acceuil.rv.EvenementListAdapter;
import website.livingRoom.soliCatering.view.acceuil.rv.EvenementTeteAdapter;

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

        //initiate recycle view
        initiateRecycleView();

        return binding.getRoot();
    }

    private void initiateRecycleView() {
        RecyclerView rv = binding.evRecycler;

        final EvenementListAdapter evenementListAdapter = setAdapterToRv(rv);

        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        //observe data from live data and update rv
        observeDataAndUpdateView(evenementListAdapter);
    }

    @NonNull
    private EvenementListAdapter setAdapterToRv(RecyclerView rv) {
        //create concat adapter from HEADER ADAPTER + BT COMMANDER ADAPTER + LIST ADAPTER
        final EvenementListAdapter evenementListAdapter = new EvenementListAdapter(new EvenementListAdapter.EvenementDiff());
        final ConcatAdapter concatAdapter = new ConcatAdapter(new EvenementTeteAdapter(),  new EvenementBtCmdAdapter(), evenementListAdapter);

        //SET PROPERTIES TO RV

        rv.setAdapter(concatAdapter);
        return evenementListAdapter;
    }

    private void observeDataAndUpdateView(EvenementListAdapter evenementListAdapter) {

        //OBSERVE DATA FROM LIVE DATA AND UPDATE RV WEN DATA CHANGE
        acceuilViewModel.getListEvenement().observe(getViewLifecycleOwner(), evenements -> {
            // UPDATE THE CACHED COPY OF THE EVENEMENTS IN THE ADAPTER.
            evenementListAdapter.submitList(evenements);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}