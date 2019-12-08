package fr.eni.locakar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import fr.eni.locakar.bo.Vehicule;

public class VehiculeParkingActivity extends AppCompatActivity {

    Vehicule vehicule = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicule_parking);

        vehicule = getIntent().getParcelableExtra("vehicule");

        TextView marque = findViewById(R.id.marque_vehicule);
        TextView immat = findViewById(R.id.immat_vehicule);
        TextView transmission  = findViewById(R.id.transmission_vehicule);
        TextView prix = findViewById(R.id.prix_vehicule);

        marque.setText(vehicule.getMarques());
        immat.setText(vehicule.getImmatriculation());
        transmission.setText(vehicule.getTypes());
        prix.setText(String.valueOf(vehicule.getPrix()));
    }

    public void ModifierVehicule(View view) {
        Intent intent = new Intent(this, ModifierVehiculeActivity.class);
        intent.putExtra("vehicule", vehicule);
        startActivity(intent);
    }

    public void retourListeParking(View view) {
        Intent intent = new Intent(this, ParkingActivity.class);
        startActivity(intent);
    }
}
