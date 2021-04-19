package com.example.testrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bdd.DetecteurDeClicSurRecycler;
import com.example.bdd.Donnee;
import com.example.bdd.R;

import java.util.ArrayList;

public class MonRecyclerViewAdapteur extends RecyclerView.Adapter<MonRecyclerViewAdapteur.ConteneurDeDonnee> {

    private static DetecteurDeClicSurRecycler detecteurDeClicSurRecycler;
    private ArrayList<Donnee> donnees;
    public MonRecyclerViewAdapteur(ArrayList<Donnee> donnees) {
        this.donnees = donnees;
    }
    @Override
    public ConteneurDeDonnee onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ConteneurDeDonnee(view);
    }
    @Override
    public void onBindViewHolder(ConteneurDeDonnee conteneur, int
            position) {
        conteneur.tv_principal.setText(donnees.get(position).getPrincipal());
        conteneur.tv_auxiliaire.setText(donnees.get(position).getAuxiliaire());
        conteneur.imageView.setImageDrawable(donnees.get(position).getimage());
    }
    @Override
    public int getItemCount() {
        return donnees.size();
    }

    public static class ConteneurDeDonnee extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_principal;
        TextView tv_auxiliaire;
        ImageView imageView;
        public ConteneurDeDonnee(View itemView) {
            super(itemView);
            tv_principal = (TextView) itemView.findViewById(R.id.tv_principal);
            tv_auxiliaire = (TextView) itemView.findViewById(R.id.tv_auxiliaire);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener((View.OnClickListener) this);
        }

        @Override
        public void onClick(View v) {
            detecteurDeClicSurRecycler.clicSurRecyclerItem(getAdapterPosition(), v);
        }
    }
    public void setDetecteurDeClicSurRecycler(DetecteurDeClicSurRecycler detecteurDeClicSurRecycler) {
        this.detecteurDeClicSurRecycler = detecteurDeClicSurRecycler;
    }


}