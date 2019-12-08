package fr.eni.locakar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import fr.eni.locakar.adapters.VehiculeAdapter;
import fr.eni.locakar.bo.Agence;
import fr.eni.locakar.bo.Vehicule;
import fr.eni.locakar.dao.AgenceDao;
import fr.eni.locakar.dao.VehiculeDao;

public class ParkingActivity extends AppCompatActivity {


    private static final String TAG = "ACOS";
    //Objet représentant le recyclerView
    private RecyclerView mRecyclerView;
    //Objet représentant l"adapter remplissant le recyclerView
    private RecyclerView.Adapter mAdapter;
    //Objet permettant de structurer l'ensemble des sous vues contenues dans le RecyclerView.
    private RecyclerView.LayoutManager mLayoutManager;
    //Liste bouchon
    private List<Vehicule> vehicules = new ArrayList<>();
    private List<Vehicule> listeVehicule = new ArrayList<>();


    /**
     * Définition de l'action du clic sur un item.
     */
    private View.OnClickListener monClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            int position = Integer.parseInt(view.getTag().toString());
            Log.i(TAG,"POSITION : " + view.getTag());
            Intent intent = new Intent(ParkingActivity.this,VehiculeParkingActivity.class);
            intent.putExtra("vehicule", vehicules.get(position));
            startActivity(intent);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        chargementVehicule();
        chargementRecycler();
    }

    /**
     * Permet de charger le recycler view
     */
    private void chargementRecycler()
    {
       
        //Lie le recyclerView aux fonctionnalité offerte par le linear layout
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //Liaison permettant de structurer l'ensemble des sous vues contenues dans le RecyclerView.
        mAdapter = new VehiculeAdapter(vehicules,monClickListener);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Permet de charger les vehicules
     */
    private void chargementVehicule()
    {
        //récupère une agence :
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedPreferences.getString("agence_enregistree",null);
        Agence agence = null;

        if(json != null)
            agence = gson.fromJson(json, Agence.class);

        listeVehicule = new VehiculeDao(this).getAll();


        if(listeVehicule.isEmpty()){
            //on créé les vehicules :
            Vehicule vehicule1 = new Vehicule("PM-785-LM", "Renault Clio", "Essence", 20, false, agence.getId());
            Vehicule vehicule2 = new Vehicule("YH-456-DE", "Honda Civic", "Diesel", 15, false, agence.getId());
            Vehicule vehicule3 = new Vehicule("RE-659-TY", "Peugeot 208","Electrique", 25, false, agence.getId());

            //insertion des vehicules dans la bdd vehicule :
            new VehiculeDao(this).insert(vehicule1);
            new VehiculeDao(this).insert(vehicule2);
            new VehiculeDao(this).insert(vehicule3);
        }

        //récupération de la liste des vehicules:
        vehicules = new VehiculeDao(this).getAll();

    }


    public void ajouterVehciuleParking(View view) {
        Intent intent = new Intent(this, AjouterVehiculeActivity.class);
        startActivity(intent);
    }

    public void retourPortail(View view) {
        Intent intent = new Intent(this, PortailActivity.class);
        startActivity(intent);
    }
}
