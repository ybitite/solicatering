package website.livingRoom.soliCatering.model.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import website.livingRoom.soliCatering.model.entitys.ArticlePanier;
import website.livingRoom.soliCatering.model.entitys.Categorie;
import website.livingRoom.soliCatering.model.entitys.Client;
import website.livingRoom.soliCatering.model.entitys.Evenement;
import website.livingRoom.soliCatering.model.entitys.Menu;
import website.livingRoom.soliCatering.model.entitys.Panier;
import website.livingRoom.soliCatering.model.entitys.Plat;
import website.livingRoom.soliCatering.model.room.dao.ArticlePanierDAO;
import website.livingRoom.soliCatering.model.room.dao.CategorieDAO;
import website.livingRoom.soliCatering.model.room.dao.ClientDAO;
import website.livingRoom.soliCatering.model.room.dao.EvenementDAO;
import website.livingRoom.soliCatering.model.room.dao.MenuDAO;
import website.livingRoom.soliCatering.model.room.dao.PanierDAO;
import website.livingRoom.soliCatering.model.room.dao.PlatDAO;

@Database(entities = {Evenement.class, Menu.class, Categorie.class, Plat.class, ArticlePanier.class, Panier.class, Client.class},
        version = 1
        /*autoMigrations = {
                @AutoMigration(from = 1, to = 2)
        }*/
        )
public abstract class AppDatabase extends RoomDatabase {
    //FIELD
    /*DEFINED A SINGLETON, PREVENT MULTIPLE INSTANCES OF DATA BASE OPENED AT THE SAME TIME.*/
    private static volatile AppDatabase INSTANCE;
    /*FIXED THREAD POOL*/
    public static final int NUMBER_OF_THREADS = 4;
    /* EXECUTOR SERVICE TO RUN DATA BASE OPERATIONS ASYNCHRONOUSLY ON A BACKGROUND THREAD*/
    public static  ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //METHODE
    //EXPOSE dao WITH ABSTRACT METHODE
    public abstract EvenementDAO evenementDAO();
    public abstract MenuDAO menuDAO();
    public abstract CategorieDAO categorieDAO();
    public abstract PlatDAO platDAO();
    public abstract ArticlePanierDAO articlePanierDAO();
    public abstract PanierDAO panierDAO();
    public abstract ClientDAO clientDAO();
    public static AppDatabase getDatabase(final Context context) {
        //BUILD DATA BASE WEN IS IT NULL
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, "RoomDB")
                            .addCallback(sRoomDatabaseCallback)
                            /*.createFromAsset("database/RoomDB.model")*/
                            .build();
                }
            }
        }
        //RETURN INSTANCE OF DATA BASE
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // POPULATE  DATA BASE IN THE BACKGROUND WITH EXECUTOR SERVICE
            databaseWriteExecutor.execute(() -> {
            });
        }
    };

    public static ExecutorService getDatabaseWriteExecutor() {
        if (AppDatabase.databaseWriteExecutor.isShutdown()){
            AppDatabase.databaseWriteExecutor = Executors.newFixedThreadPool(AppDatabase.NUMBER_OF_THREADS);
        }
        return databaseWriteExecutor;
    }
}
