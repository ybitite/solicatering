package website.livingRoom.soliCatering.model.room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import website.livingRoom.soliCatering.model.entitys.ArticlePanier;
import website.livingRoom.soliCatering.model.entitys.ArticlePanierAndPlat;

import java.util.List;

@Dao
public interface ArticlePanierDAO {
    //INSER NEW ARTICLEPANIER IN ARTICLEPANIERS TABLE
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ArticlePanier articlePaniers);

    //SELECT ALL ArticlePanierAndPlat  in the curent panier
    @Transaction
    @Query("SELECT * FROM articlepaniers WHERE idPanier LIKE :idPanier ")
    LiveData<List<ArticlePanierAndPlat>> getArticlePanierWithPlats(int idPanier);

    //find article panier
    @Query("select * from articlepaniers where idPanier like :idPanier and idPlats like :idPlat")
    ArticlePanier getArticlePanier(int idPlat, int idPanier);

    //UPDATE GIVEN ID AND NUMBER PLAT
    @Query("UPDATE articlepaniers SET nombrePlat = :nbr + nombrePlat  WHERE idPlats LIKE :idPlat " +
            "AND idPanier like :idPanier")
    void updateNombreArticlePanier(int idPlat, int idPanier, int nbr);

    //DELETE all article panier in the curent panier
    @Query("DELETE FROM articlepaniers WHERE idPanier LIKE :idPanier")
    void deleteArticlePanier(int idPanier);

    //DELETE GIVING ARTICLE PANIER
    @Delete
    void deleteArticlePanier(ArticlePanier... articlePaniers);


}
