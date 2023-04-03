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
import website.livingRoom.soliCatering.viewModel.ClientViewModel;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;

public class DialogClientFragment extends DialogFragment {

    //FIELD
    ClientViewModel clientViewModel;
    ConteurViewModel conteurViewModel;
    ViewClientBinding binding;
    DialogClientHolder dialogClientHolder;

    //OVERRIDE METHODE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //instantiate live model
        clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);

        conteurViewModel = new ViewModelProvider(requireActivity()).get(ConteurViewModel.class);

        //inflate view to get bind
        binding = ViewClientBinding.inflate(inflater, container, false);

        //specif at life cycle owner
        binding.setLifecycleOwner(getViewLifecycleOwner());

        //set the view model to the binding class
        binding.setViewModel(clientViewModel);

        //return view from binding
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dialogClientHolder= new DialogClientHolder(binding,clientViewModel, requireActivity(),this, conteurViewModel);
        dialogClientHolder.bind();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
