package com.example.marokkanischbernessen.db.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

/**
 * Classe to make the ralation between
 * Panier and Article panier which is (1 - *)
 **/

public class PanierWithAarticlePanier {

    @Embedded
    public Panier panier;
    @Relation(
            parentColumn = ("idPanier"),
            entityColumn = ("idPanier")
    )
    public List<ArticlePanier> articlePanierList;
}

