package website.livingRoom.soliCatering.db.entitys;

import androidx.room.Embedded;
import androidx.room.Relation;


public class ArticlePanierAndPlat {
    /**
     * Classe to make the ralation between
     * Article panier and plat which is (1 - 1)
     **/

    @Embedded
    public ArticlePanier articlePanier;
    @Relation(
            parentColumn = ("idPlats"),
            entityColumn = ("id")
    )
    public Plat plat;

}
