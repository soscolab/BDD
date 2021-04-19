package com.example.bdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testrecyclerview.MonRecyclerViewAdapteur;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class MainActivity<objectByteArrayOutputStream> extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MonRecyclerViewAdapteur mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    CoordinatorLayout mcoordinatorLayout;

    final String PREFS_NAME = "preferences_file";

    TextView tv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "planetesDB").build();

        PlaneteDao planeteDao = db.planeteDao();

        loadData(planeteDao);
    }

    private void loadData(PlaneteDao planeteDao) {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        new Thread(new Runnable() {
            @Override
            public void run() {

                if (settings.getBoolean("is_data_loaded", true)) {
                    initData(planeteDao);
                    settings.edit().putBoolean("is_data_loaded", false).commit();
                }

                List<Planete> planetes = planeteDao.getAll();

                tv.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("Il y a [" + planetes.size() + "] Planètes dans la base de données" );
                        for (int i =0; i< planetes.size();i++) {
                            Toast.makeText(MainActivity.this, "Planete = " + planetes.get(i).getNom(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }).start();

    }

    private void initData(PlaneteDao planeteDao) {
        Context mContext = getApplicationContext();


        ArrayList<Planete> planetes = new ArrayList<>();
        Blob img = (Blob) mContext.getResources().getDrawable(R.drawable.pic1);
        planetes.add(new Planete(1,"Mercure","4900", img));
        planetes.add(new Planete(2,"Venus","12000",img));
        planetes.add(new Planete(3,"Terre","12800",img));
        planetes.add(new Planete(4,"Mars","6800",img));
        planetes.add(new Planete(5,"Jupiter","144000",img));
        planetes.add(new Planete(6,"Saturne","120000",img));
        planetes.add(new Planete(7,"Uranus","52000",img));
        planetes.add(new Planete(8,"Neptune","50000",img));
        planetes.add(new Planete(9,"Pluton","2300",img));

        for (int index = 0; index < planetes.size(); index++) {
            Planete planete = planetes.get(index);
            planeteDao.insert(planete);
        }
    }
}