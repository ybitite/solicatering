package com.example.marokkanischbernessen.db.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

/**
 * Classe to make the ralation between
 * Panier and Article panier which is (1 - *)
 **/

public class PanierWithAarticlePanierAndPlat {

    @Embedded
    public Panier panier;
    @Relation(
            entity = ArticlePanier.class,
            parentColumn = ("idPanier"),
            entityColumn = ("idPanier")
    )
    public List<ArticlePanierAndPlat> listArticlePanierAndPlat;
}

