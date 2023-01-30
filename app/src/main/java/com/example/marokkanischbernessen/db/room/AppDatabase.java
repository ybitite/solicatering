package com.example.marokkanischbernessen.db.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.marokkanischbernessen.db.entity.ArticlePanier;
import com.example.marokkanischbernessen.db.entity.Categorie;
import com.example.marokkanischbernessen.db.entity.Client;
import com.example.marokkanischbernessen.db.entity.Evenement;
import com.example.marokkanischbernessen.db.entity.Menu;
import com.example.marokkanischbernessen.db.entity.Panier;
import com.example.marokkanischbernessen.db.entity.Plat;
import com.example.marokkanischbernessen.db.room.DAO.ArticlePanierDAO;
import com.example.marokkanischbernessen.db.room.DAO.CategorieDAO;
import com.example.marokkanischbernessen.db.room.DAO.ClientDAO;
import com.example.marokkanischbernessen.db.room.DAO.EvenementDAO;
import com.example.marokkanischbernessen.db.room.DAO.MenuDAO;
import com.example.marokkanischbernessen.db.room.DAO.PanierDAO;
import com.example.marokkanischbernessen.db.room.DAO.PlatDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Evenement.class, Menu.class, Categorie.class, Plat.class, ArticlePanier.class, Panier.class, Client.class}, version = 1)
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
    //EXPOSE EVENT DAO WITH ABSTRACT METHODE
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
                EvenementDAO evenementDAO = INSTANCE.evenementDAO();
                MenuDAO menuDAO = INSTANCE.menuDAO();
                CategorieDAO categorieDAO = INSTANCE.categorieDAO();
                PlatDAO platDAO = INSTANCE.platDAO();
                ArticlePanierDAO articlePanierDAO = INSTANCE.articlePanierDAO();
                ClientDAO clientDAO = INSTANCE.clientDAO();
                // dao.deleateall();
//
//                Evenement evenement = new Evenement("Cabane B","17.05.2022",30,0,"cabaneb");
//                evenementDAO.insert(evenement);
//
//                evenement = new Evenement("Summer Academy","03.08.2022",40,0,"hanna");
//                evenementDAO.insert(evenement);
//
//                evenement = new Evenement("Summer Academy","03.08.2022",40,0,"hanna");
//                evenementDAO.insert(evenement);
//
//
//                Menu menu=new Menu("Menu Royale","idéal pour vos mariage et vos batéme ...",30.0f,20,0,
//                        "*Le menu doit etre commander 72h à l’avance","menu_royale");
//                menuDAO.insert(menu);
//
//                menu=new Menu("Menu Gourmant","Le menu parfet porfet pour vos invitaion et vos fetes...",25.0f,5,0,
//                        "**Le menu doit etre commander 48h à l’avance","menu_gourmant");
//                menuDAO.insert(menu);
//                menu=new Menu("Menu Gourmant","Le menu parfet porfet pour vos invitaion et vos fetes...",20.0f,15,0,
//                        "**Le menu doit etre commander 48h à l’avance","menu_gourmant");
//                menuDAO.insert(menu);
//
//                Categorie categorie=new Categorie("Plats du Chef","Les meilleurs plats proposé par vos chefs ...",7,0,"cat_plat_chef");
//                categorieDAO.insert(categorie);
//                categorie=new Categorie("Plats du Chef","Les meilleurs plats proposé par vos chefs ...",5,0,"cat_plat_chef");
//                categorieDAO.insert(categorie);
//                categorie=new Categorie("Plats du Chef","Les meilleurs plats proposé par vos chefs ...",3,0,"cat_plat_chef");
//                categorieDAO.insert(categorie);
//                categorie=new Categorie("Plats du Chef","Les meilleurs plats proposé par vos chefs ...",4,0,"cat_plat_chef");
//                categorieDAO.insert(categorie);
//                Plat plat=new Plat("Tajine au prune  ","",7,30.0f,"tajine","n",2,2,"tpruneau",0);
//                platDAO.insert(plat);
//                plat=new Plat("Couscous 7 legumes    ","  Couscous aux septs legumes est un couscous traditionel marocain qui se fait depuis des generations.\n" +
//                        "  Il comporte sept légumes : \n" +
//                        "  les tomates, courgettes, navets, carottes, potiron, chou, aubergines \n" +
//                        "  (les légumes peuvent varier). \n" +
//                        "  Ce couscous est assaisoné avec de persil, de lacoriande, de l'ail et un mélanger d'épices que vous pouvez trouvez sur l'épecerie.",5,25.0f,"couscous","o",1,1,"c7legume",0);
//                platDAO.insert(plat);
//                plat=new Plat("Courget farssi","",3,15.0f,"diversplatschaud","n",1,1,"ptcourget",0);
//                platDAO.insert(plat);
//                plat=new Plat("Tomat farssi","",4,15.0f,"diversplatschaud","n",1,1,"ptcourget",0);
//                platDAO.insert(plat);
//                plat=new Plat("Aubergine farssi","",3,15.0f,"diversplatschaud","n",1,1,"ptcourget",0);
//                platDAO.insert(plat);

                clientDAO.insert(new Client());

            });
        }
    };
}
