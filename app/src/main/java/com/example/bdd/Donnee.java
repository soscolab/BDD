package com.example.bdd;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;


public class Donnee {
        private String principal;
        private String auxiliaire;
         private Bitmap image;


    @SuppressLint("UseCompatLoadingForDrawables")
    Donnee(String text1, String text2, Bitmap image){
            principal = text1;
            auxiliaire = text2;
            this.image= image;
        };



    public String getPrincipal() {
            return principal;
        }
        public String getAuxiliaire() {
            return auxiliaire;
        }
        public Bitmap getimage() {
        return image;
    }



}

