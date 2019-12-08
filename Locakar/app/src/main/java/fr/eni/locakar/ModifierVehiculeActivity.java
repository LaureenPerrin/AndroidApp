package fr.eni.locakar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import fr.eni.locakar.bo.Vehicule;
import fr.eni.locakar.dao.VehiculeDao;

public class ModifierVehiculeActivity extends AppCompatActivity {

    Vehicule vehicule = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_vehicule);

        vehicule = getIntent().getParcelableExtra("vehicule");

        EditText marque = findViewById(R.id.marque_vehicule);
        EditText immat = findViewById(R.id.immat_vehicule);
        EditText transmission  = findViewById(R.id.transmission_vehicule);
        EditText prix = findViewById(R.id.prix_vehicule);
        TextView erreur = findViewById(R.id.erreur_modifications_vehciule);

        marque.setText(vehicule.getMarques());
        immat.setText(vehicule.getImmatriculation());
        transmission.setText(vehicule.getTypes());
        prix.setText(String.valueOf(vehicule.getPrix()));


        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        String erreurModifVehicule = sharedPreferences.getString("erreur_modif_vehicule", null);
        String erreur_modif = "Vous devez renseigner tous les champs pour enregistrer vos modifications.";

        if (erreurModifVehicule != null){
            erreur.setText(erreur_modif);
        }


    }

    public void enregistrerModifVehicule(View view) {

        vehicule = getIntent().getParcelableExtra("vehicule");

        EditText marque = findViewById(R.id.marque_vehicule);
        EditText immat = findViewById(R.id.immat_vehicule);
        EditText transmission  = findViewById(R.id.transmission_vehicule);
        EditText prix = findViewById(R.id.prix_vehicule);
        TextView erreur = findViewById(R.id.erreur_modifications_vehciule);


        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);

        if(marque.getText().toString().isEmpty() | immat.getText().toString().isEmpty() | transmission.getText().toString().isEmpty()
                | prix.getText().toString().isEmpty()){



            String erreur_modif = "Vous devez renseigner tous les champs pour enregistrer vos modifications.";

            sharedPreferences.edit()
                    .putString("erreur_modif_vehicule",erreur_modif)
                    .apply();

            Intent intent = new Intent(this, ModifierVehiculeActivity.class);
            intent.putExtra("vehicule", vehicule);
            startActivity(intent);

        } else {

            sharedPreferences.edit()
                    .remove("erreur_modif_vehicule")
                    .apply();


            String marque_vehicule = marque.getText().toString();
            String immat_vehicule = immat.getText().toString();
            String type = transmission.getText().toString();
            int prix_vehicule = Integer.parseInt(prix.getText().toString());



            vehicule.setMarques(marque_vehicule);
            vehicule.setImmatriculation(immat_vehicule);
            vehicule.setTypes(type);
            vehicule.setPrix(prix_vehicule);

            new VehiculeDao(this).update(vehicule);

            Intent intent = new Intent(this, VehiculeParkingActivity.class);
            intent.putExtra("vehicule", vehicule);
            startActivity(intent);

        }


    }

    public void retourDetailVehiculeParking(View view) {
        Intent intent = new Intent(this, VehiculeParkingActivity.class);
        startActivity(intent);
    }
}
