package website.livingRoom.soliCatering.db.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import website.livingRoom.soliCatering.db.entitys.ArticlePanier;
import website.livingRoom.soliCatering.db.entitys.Categorie;
import website.livingRoom.soliCatering.db.entitys.Client;
import website.livingRoom.soliCatering.db.entitys.Evenement;
import website.livingRoom.soliCatering.db.entitys.Menu;
import website.livingRoom.soliCatering.db.entitys.Panier;
import website.livingRoom.soliCatering.db.entitys.Plat;
import website.livingRoom.soliCatering.db.room.DAO.ArticlePanierDAO;
import website.livingRoom.soliCatering.db.room.DAO.CategorieDAO;
import website.livingRoom.soliCatering.db.room.DAO.ClientDAO;
import website.livingRoom.soliCatering.db.room.DAO.EvenementDAO;
import website.livingRoom.soliCatering.db.room.DAO.MenuDAO;
import website.livingRoom.soliCatering.db.room.DAO.PanierDAO;
import website.livingRoom.soliCatering.db.room.DAO.PlatDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Evenement.class, Menu.class, Categorie.class, Plat.class, ArticlePanier.class, Panier.class, Client.class},
        version = 1,
        autoMigrations = {
                @AutoMigration(from = 1, to = 2)
        }
        )
public abstract class AppDatabase extends RoomDatabase {
    //FIELD
    /*DEFINED A SINGLETON, PREVENT MULTIPLE INSTANCES OF DATA BASE OPENED AT THE SAME TIME.*/
    private static volatile AppDatabase INSTANCE;
    /*FIXED THREAD POOL*/
    private static final int NUMBER_OF_THREADS = 4;
    /* EXECUTOR SERVICE TO RUN DATA BASE OPERATIONS ASYNCHRONOUSLY ON A BACKGROUND THREAD*/
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //METHODE
    //EXPOSE DAO WITH ABSTRACT METHODE
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
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "RoomDB")
                            .addCallback(sRoomDatabaseCallback)
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
}
