package fr.eni.locakar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import fr.eni.locakar.bo.Agence;
import fr.eni.locakar.bo.Vehicule;
import fr.eni.locakar.dao.VehiculeDao;

public class AjouterVehiculeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_vehicule);

        TextView erreur = findViewById(R.id.erreur_ajout_vehciule);

        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        String erreurAjoutVehicule = sharedPreferences.getString("erreur_ajout_vehicule", null);
        String erreur_modif = "Vous devez renseigner tous les champs pour enregistrer votre véhicule.";

        if (erreurAjoutVehicule != null){
            erreur.setText(erreur_modif);
        }


    }

    public void AjouterVehicule(View view) {

        EditText marque = findViewById(R.id.marque_vehicule);
        EditText immat = findViewById(R.id.immat_vehicule);
        EditText type = findViewById(R.id.transmission_vehicule);
        EditText prix = findViewById(R.id.prix_vehicule);

        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);

        if(marque.getText().toString().isEmpty() | immat.getText().toString().isEmpty() | type.getText().toString().isEmpty()
                | prix.getText().toString().isEmpty()) {


            String erreur_modif = "Vous devez renseigner tous les champs pour ajouter votre véhicule.";

            sharedPreferences.edit()
                    .putString("erreur_ajout_vehicule", erreur_modif)
                    .apply();

            Intent intent = new Intent(this, ParkingActivity.class);
            startActivity(intent);

        } else {

            sharedPreferences.edit()
                    .remove("erreur_ajout_vehicule")
                    .apply();


            String marqueVehicule = marque.getText().toString();
            String immatVehicule = immat.getText().toString();
            String typeVehicule = type.getText().toString();
            int prixVehicule = Integer.parseInt(prix.getText().toString());

            //on récupère l'objet agence créé :
            Gson gson = new Gson();


            String json = sharedPreferences.getString("agence_enregistree",null);
            Agence agence = null;

            if(json != null)
                agence = gson.fromJson(json, Agence.class);

            Vehicule vehicule = new Vehicule(immatVehicule, marqueVehicule, typeVehicule, prixVehicule, false, agence.getId());
            new VehiculeDao(this).insert(vehicule);


            Intent intent = new Intent(this, ParkingActivity.class);
            startActivity(intent);


        }



    }

    public void retourListeParking(View view) {
        Intent intent = new Intent(this, ParkingActivity.class);
        startActivity(intent);
    }
}
