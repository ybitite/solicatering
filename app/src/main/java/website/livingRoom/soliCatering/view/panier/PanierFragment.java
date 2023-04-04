package website.livingRoom.soliCatering.view.panier;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import website.livingRoom.soliCatering.databinding.FragmentPanierBinding;
import website.livingRoom.soliCatering.databinding.ModelPiedArticlePanierBinding;
import website.livingRoom.soliCatering.view.panier.rvpanier.ArticlePanierListAdapter;
import website.livingRoom.soliCatering.view.panier.rvpanier.ArticlePanierPiedAdapter;
import website.livingRoom.soliCatering.viewModel.ConteurViewModel;
import website.livingRoom.soliCatering.viewModel.PanierViewModel;


public class PanierFragment extends Fragment {

    //FIELD
    private FragmentPanierBinding binding;
    private PanierViewModel panierViewModel;
    private ConteurViewModel conteurViewModel;
    private ModelPiedArticlePanierBinding modelPiedArticlePanierBinding;

    //METHODE
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //CREATE BINDING WITH INFLATE LAYOUT
        binding = FragmentPanierBinding.inflate(inflater, container, false);
        modelPiedArticlePanierBinding= ModelPiedArticlePanierBinding
                .inflate(LayoutInflater.from(getContext()));
        //INSTANTIATE VIEW MODEL
        conteurViewModel = new ViewModelProvider(requireActivity()).get(ConteurViewModel.class);
        panierViewModel = new PanierViewModel(getActivity().getApplication(),conteurViewModel);

        //set life cycle owner and view model to the pied article panier binding
        modelPiedArticlePanierBinding.setLifecycleOwner(getViewLifecycleOwner());
        modelPiedArticlePanierBinding.setViewModel(conteurViewModel);

        initiateRecycleView();


        return binding.getRoot();
    }

    private void initiateRecycleView() {


        //SET FOOTER ADAPTER + LIST ADAPTER TO RV
        ArticlePanierListAdapter articlePanierListAdapter =
                new ArticlePanierListAdapter(new ArticlePanierListAdapter.ArticlePanierWithPlatDiff( ), panierViewModel, conteurViewModel);

        ArticlePanierPiedAdapter articlePanierPiedAdapter = new ArticlePanierPiedAdapter(conteurViewModel, modelPiedArticlePanierBinding);

        ConcatAdapter concatAdapter = new ConcatAdapter(articlePanierListAdapter, articlePanierPiedAdapter);

        binding.pRecycler.setAdapter(concatAdapter);

        //ATTACHE ITEM TOUCHE HELPER TO RECYCLE VIEW
        new ItemTouchHelper(articlePanierListAdapter.simpleCallback).attachToRecyclerView(binding.pRecycler);

        //SET PROPERTIES TO RV
        binding.pRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.pRecycler.setItemAnimator(new DefaultItemAnimator());


        observeDataAndUpdateView(panierViewModel, articlePanierListAdapter);
    }

    private void observeDataAndUpdateView(PanierViewModel panierViewModel, ArticlePanierListAdapter articlePanierListAdapter) {
        //OBSERVE DATA FROM LIVE DATA AND UPDATE RV WEN DATA CHANGE
        panierViewModel.getListArticlePanierAndPlat().observe(getViewLifecycleOwner(), articlePanierListAdapter::submitList);
    }

    @Override
    public void onDestroyView() {
        binding=null;
        super.onDestroyView( );
    }
}