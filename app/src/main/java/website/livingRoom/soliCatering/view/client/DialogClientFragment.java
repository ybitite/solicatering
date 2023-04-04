package website.livingRoom.soliCatering.view.client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import website.livingRoom.soliCatering.databinding.ViewClientBinding;
import website.livingRoom.soliCatering.repository.ClientRepository;
import website.livingRoom.soliCatering.repository.PanierRepository;
import website.livingRoom.soliCatering.viewModel.ClientViewModel;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;

public class DialogClientFragment extends DialogFragment {

    //FIELD
    ViewClientBinding binding;
    ClientViewModel clientViewModel;
    ConteurViewModel conteurViewModel;
    DialogClientHolder dialogClientHolder;
    private PanierRepository panierRepository;
    private ClientRepository clientRepository;

    //OVERRIDE METHODE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //inflate view to get bind
        binding = ViewClientBinding.inflate(inflater, container, false);

        //instantiate live model
        clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);

        conteurViewModel = new ViewModelProvider(requireActivity()).get(ConteurViewModel.class);

        panierRepository = new PanierRepository(getContext());

        clientRepository = new ClientRepository(getContext());

        //return view from binding
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dialogClientHolder= new DialogClientHolder(binding,clientViewModel,conteurViewModel,clientRepository,panierRepository);
        dialogClientHolder.bind();
    }

    @Override
    public void onStart() {
        //specif at life cycle owner
        binding.setLifecycleOwner(getViewLifecycleOwner());

        //set the view model to the binding class
        binding.setViewModel(clientViewModel);

        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
