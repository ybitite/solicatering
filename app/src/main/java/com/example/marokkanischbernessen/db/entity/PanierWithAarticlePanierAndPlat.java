package com.example.marokkanischbernessen.db.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

/**
 * Classe to make the imbricated relationship
 * Panier with Article panier and plat which is panier (1 - *) list:article panier / article panier (1 - 1) plat

 **/

public class PanierWithAarticlePanierAndPlat {

    @Embedded
    public Panier panier;
    @Relation(
            entity = ArticlePanier.class,
            parentColumn = ("id"),
            entityColumn = ("idPanier")
    )
    public List<ArticlePanierAndPlat> listArticlePanierAndPlat;
}

