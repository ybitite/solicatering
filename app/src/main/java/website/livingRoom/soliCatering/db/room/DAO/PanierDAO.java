package website.livingRoom.soliCatering.db.room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import website.livingRoom.soliCatering.db.entitys.Panier;
import website.livingRoom.soliCatering.db.entitys.PanierWithAarticlePanierAndPlat;

import java.util.List;

@Dao
public interface PanierDAO {

    //inser new panier in paniers
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Panier panier);

    /**
     * Panierwithaarticlepanier a was made to make
     * the relation (1 - *) between paniers and articlepaniers
     */

    //select all panier from paniers with article panier using this relation
    @Transaction
    @Query("SELECT * FROM paniers ORDER BY etat ASC")
    LiveData<List<PanierWithAarticlePanierAndPlat>> getAllPanierWithArticlePanier();

    //DELETE CURENT  PANIER
    @Query("DELETE FROM paniers WHERE id LIKE :idPanier")
    void deleteCurentPanier(int idPanier);

}
