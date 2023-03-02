package com.example.marokkanischbernessen.db.room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.marokkanischbernessen.db.entity.ArticlePanier;
import com.example.marokkanischbernessen.db.entity.ArticlePanierAndPlat;

import java.util.List;

@Dao
public interface ArticlePanierDAO {
    //INSER NEW ARTICLEPANIER IN ARTICLEPANIERS TABLE
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract void insert(ArticlePanier articlePaniers);

    //SELECT ALL ARTICLEPANIER FROM ARTICLEPANIERS TABLE
    @Query("SELECT * FROM articlepaniers WHERE idPanier LIKE :idPanier ")
    LiveData<List<ArticlePanierAndPlat>> getAllArticlePanierWithPlat(int idPanier);

    //DELETE all article panier in the curent panier
    @Query("DELETE FROM articlepaniers WHERE idPanier LIKE :idPanier")
    void deleteCurentPanier(int idPanier);


    //UPDATE GIVEN ID AND NUMBER PLAT
    @Query("UPDATE articlepaniers SET nombrePlat = :nbr + nombrePlat  WHERE idPlats LIKE :idPlat " +
            "AND idPanier like :idPanier")
    int  updateIdAP(int idPlat, int idPanier, int nbr);

    //DELETE GIVING ARTICLE PANIER
    @Delete
    void deleteArticlePanier(ArticlePanier... articlePaniers);

    //find article panier
    @Query("select * from articlepaniers where idPanier like :idPanier and idPlats like :idPlat")
    ArticlePanier findArticlePanier(int idPlat, int idPanier);
}
