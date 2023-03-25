package website.livingRoom.soliCatering.view.client;

import android.content.Context;
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

public class DialogClientFragment extends DialogFragment {

    //FIELD
    ClientViewModel clientViewModel;
    ViewClientBinding binding;
    Context context;

    //OVERRIDE METHODE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //instantiate live model
        clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);

        //inflate view to get bind
        binding = ViewClientBinding.inflate(inflater, container, false);

        //specif at life cycle owner
        binding.setLifecycleOwner(getViewLifecycleOwner());

        //set the view model to the binding class
        binding.setViewModel(clientViewModel);

        //context to use later
        context = binding.getRoot().getContext();
        //return view from binding
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DialogClientHolder dialogClientHolder= new DialogClientHolder(binding,clientViewModel);
        dialogClientHolder.bind();
    }

}
