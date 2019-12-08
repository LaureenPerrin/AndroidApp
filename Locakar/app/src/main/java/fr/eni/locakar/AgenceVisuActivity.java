package fr.eni.locakar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import fr.eni.locakar.bo.Agence;
import fr.eni.locakar.dao.AgenceDao;

public class AgenceVisuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agence_visu);

        TextView nom_agence = findViewById(R.id.nom_agence);
        TextView password_agence = findViewById(R.id.password_agence);
        TextView ca_agence = findViewById(R.id.ca_agence);

        //on récupère l'objet agence créé :
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedPreferences.getString("agence_enregistree",null);

        Agence agence = null;
        if(json != null) {
            agence = gson.fromJson(json, Agence.class);

            //on charge la vue visu article :

            nom_agence.setText(agence.getNom());
            password_agence.setText(agence.getPassword());
            String ca = String.valueOf(agence.getCA());
            ca_agence.setText(ca + " euros ");

            // Log.d("toto", agence.getNom());
        }

    }

    public void modifierAgence(View view) {
        Intent intent = new Intent(this, ModifierAgence.class);
        startActivity(intent);
    }

    public void RetourPortailAgence(View view) {
        Intent intent = new Intent(this, PortailActivity.class);
        startActivity(intent);
    }
}
