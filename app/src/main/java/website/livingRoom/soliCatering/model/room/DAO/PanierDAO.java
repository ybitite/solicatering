package website.livingRoom.soliCatering.model.room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import website.livingRoom.soliCatering.model.entitys.Panier;
import website.livingRoom.soliCatering.model.entitys.PanierWithAarticlePanierAndPlat;

import java.util.List;

@Dao
public interface PanierDAO {

    //inser new panier in paniers
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Panier panier);

    //select all panier from paniers with article panier and plat
    @Transaction
    @Query("SELECT * FROM paniers ORDER BY etat ASC")
    LiveData<List<PanierWithAarticlePanierAndPlat>> getPanierWithArticlePanierAndPlats();

    //DELETE CURENT  PANIER
    @Query("DELETE FROM paniers WHERE id LIKE :idPanier")
    void deletePanier(int idPanier);

}
