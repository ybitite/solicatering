package website.livingRoom.soliCatering.view.plat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import website.livingRoom.soliCatering.viewModel.ConteurViewModel;
import website.livingRoom.soliCatering.viewModel.PlatViewModel;
import website.livingRoom.soliCatering.databinding.ViewPlatsBinding;

import website.livingRoom.soliCatering.repository.ArticlePanierRepository;

public class DialogPlatFragment extends DialogFragment {
    //FIELD
    private ViewPlatsBinding binding;
    private PlatViewModel platViewModel;

    private ConteurViewModel conteurViewModel;
    private DialogPlatHolder dialogPlatHolder;
    private ArticlePanierRepository articlePanierRepository;


    //METHODE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //INSTANTIATE LIVE MODEL
        platViewModel = new ViewModelProvider(requireActivity()).get(PlatViewModel.class);

        conteurViewModel = new ViewModelProvider(requireActivity()).get(ConteurViewModel.class);

        //INSTANTIATE binding
        binding = ViewPlatsBinding.inflate(inflater, container, false);

        //INSTANTIATE reposiroty
        articlePanierRepository = new ArticlePanierRepository(getContext());

        //CREATE VIEW FROM INFLATER
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //BIND VIEW
        dialogPlatHolder = new DialogPlatHolder(binding,platViewModel,conteurViewModel,articlePanierRepository);

        //set life cycle here and not on onStart to use it to observe live data in dialogPlatHolder.bind()
        binding.setLifecycleOwner(getViewLifecycleOwner());

        //observe live data and bond view
        dialogPlatHolder.bind();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView( );
    }
}
