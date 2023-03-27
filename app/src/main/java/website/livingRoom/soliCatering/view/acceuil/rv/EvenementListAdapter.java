package website.livingRoom.soliCatering.view.acceuil.rv;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import website.livingRoom.soliCatering.model.entitys.Evenement;


public class EvenementListAdapter extends ListAdapter<Evenement, EvenementHolder> {

    //CONSTRUCTOR
    public EvenementListAdapter(@NonNull DiffUtil.ItemCallback<Evenement> diffCallback) {
        super(diffCallback);
    }

    //METHODE
    @NonNull
    @Override
    public EvenementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return EvenementHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull EvenementHolder holder, int position) {
        try {
            holder.bind(getItem(position));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }

    }

    /* IMPLEMENTATION OF DIFFUTIL, ITEMCALLBACK FOR CALCULATING THE DIFF BETWEEN TWO ITEMS IN A LIST*/
    public static class EvenementDiff extends DiffUtil.ItemCallback<Evenement> {

        @Override
        public boolean areItemsTheSame(@NonNull Evenement oldItem, @NonNull Evenement newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Evenement oldItem, @NonNull Evenement newItem) {
            return oldItem.equals(newItem);
        }
    }


}