package com.example.bdd;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Blob;

@Entity
public class Planete {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "name")
    private String nom;

    @ColumnInfo(name = "size")
    private String taille;
    @ColumnInfo(name = "photo")
    private Bitmap photo;
    Planete(int uid, String nom, String taille, Bitmap photo){
        this.uid = uid;
        this.nom = nom;
        this.taille = taille;
        this.photo = photo;

    }

    public int getUid() {
        return uid;
    }

    public String getNom() {
        return nom;
    }

    public String getTaille() {
        return taille;
    }
    public Bitmap getphoto() {
        return photo;
    }


}