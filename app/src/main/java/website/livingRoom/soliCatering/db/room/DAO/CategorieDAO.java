package website.livingRoom.soliCatering.db.room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import website.livingRoom.soliCatering.db.entitys.Categorie;

import java.util.List;

@Dao
public interface CategorieDAO {
    //INSER NEW CATEGORIE IN CATEGORIES TABLE
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract void insert(Categorie categorie);

    //SELECT ALL CATEGORIE FROM CATEGORIES TABLE
    @Query("SELECT * FROM categories ORDER BY point ASC")
    LiveData<List<Categorie>> getCategories();

    //select plat.name from contenuexemple join plat where contenuexemple.id = "arg"

}
