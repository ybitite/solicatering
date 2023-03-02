package com.example.marokkanischbernessen.db.entity;

import androidx.room.DatabaseView;
import androidx.room.Embedded;
import androidx.room.Relation;

@DatabaseView
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
