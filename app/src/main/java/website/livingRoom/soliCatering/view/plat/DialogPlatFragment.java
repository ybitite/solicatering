package website.livingRoom.soliCatering.view.plat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import website.livingRoom.soliCatering.viewModel.PlatViewModel;
import website.livingRoom.soliCatering.databinding.ViewPlatsBinding;

import website.livingRoom.soliCatering.repository.ArticlePanierRepository;

public class DialogPlatFragment extends DialogFragment {
    //FIELD
    private PlatViewModel platViewModel;
    private ArticlePanierRepository articlePanierRepository;

    private ViewPlatsBinding binding;

    //METHODE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //INSTANTIATE LIVE MODEL
        platViewModel = new ViewModelProvider(requireActivity()).get(PlatViewModel.class);
        //INSTANTIATE binding
        binding = ViewPlatsBinding.inflate(inflater, container, false);

        articlePanierRepository = new ArticlePanierRepository(getContext());

        //CREATE VIEW FROM INFLATER
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //BIND VIEW
        DialogPlatHolder dialogPlatHolder = new DialogPlatHolder(platViewModel,articlePanierRepository,binding,requireActivity(),this);

        dialogPlatHolder.bind();
    }
}
