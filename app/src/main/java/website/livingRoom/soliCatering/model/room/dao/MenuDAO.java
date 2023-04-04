package website.livingRoom.soliCatering.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import website.livingRoom.soliCatering.model.entitys.Menu;

@Dao
public interface MenuDAO {
    //INSER NEW MENU in MENUS TABLE
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Menu menu);

    //SELECT ALL MENU FROM MENUS TABLE
    @Query("SELECT * FROM menus ORDER BY point desc")
    LiveData<List<Menu>> getMenus();

    //select current menu
    @Query("select * from menus where point like :actuelMenuPoint limit 1")
    Menu getMenus(int actuelMenuPoint);
}
