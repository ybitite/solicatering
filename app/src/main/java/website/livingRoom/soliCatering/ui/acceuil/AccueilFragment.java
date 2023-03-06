package website.livingRoom.soliCatering.ui.acceuil;

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

import website.livingRoom.soliCatering.databinding.FragmentAcceuilBinding;
import website.livingRoom.soliCatering.ui.acceuil.rv.EvenementBtCmdAdapter;
import website.livingRoom.soliCatering.ui.acceuil.rv.EvenementListAdapter;
import website.livingRoom.soliCatering.ui.acceuil.rv.EvenementTeteAdapter;

public class AccueilFragment extends Fragment {

    //FIELD
    private FragmentAcceuilBinding binding;

    //METHODE
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //CREATE BINDING WITH INFLATE LAYOUT
        binding = FragmentAcceuilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //INSTANTIATE CONTROLE
        RecyclerView rv = binding.includeacceuil.evRecycler;

        //SET HEADER ADAPTER + BT COMMANDER ADAPTER + LIST ADAPTER TO RV
        final EvenementTeteAdapter evenementTeteAdapter = new EvenementTeteAdapter();
        final EvenementBtCmdAdapter evenementBtCmdAdapter = new EvenementBtCmdAdapter();
        final EvenementListAdapter evenementListAdapter = new EvenementListAdapter(new EvenementListAdapter.EvenementDiff());
        final ConcatAdapter concatAdapter = new ConcatAdapter(evenementTeteAdapter, evenementBtCmdAdapter, evenementListAdapter);
        rv.setAdapter(concatAdapter);

        //SET PROPERTIES TO RV
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        //OBSERVE DATA FROM LIVE DATA AND UPDATE RV WEN DATA CHANGE
        AcceuilViewModel acceuilViewModel =
                new ViewModelProvider(this).get(AcceuilViewModel.class);
        acceuilViewModel.getAllEvenement().observe(getViewLifecycleOwner(), evenements -> {
            // UPDATE THE CACHED COPY OF THE EVENEMENTS IN THE ADAPTER.
            evenementListAdapter.submitList(evenements);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}