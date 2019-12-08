package fr.eni.locakar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import fr.eni.locakar.bo.Agence;
import fr.eni.locakar.dao.AgenceDao;

public class ModifierAgence extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_agence);

        TextView nom_agence = findViewById(R.id.new_nom_agence);
        TextView password_agence = findViewById(R.id.new_password_agence);
        TextView erreurModificationChamps = findViewById(R.id.text_erreur_agence_modif);

        //on récupère l'objet agence créé :
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedPreferences.getString("agence_enregistree",null);
        Agence agence = null;


        String erreurModifAgence = sharedPreferences.getString("erreur_modification_Agence",null);

        if (erreurModifAgence != null){
            erreurModificationChamps.setText(erreurModifAgence);
        }

        if(json != null)
            agence = gson.fromJson(json, Agence.class);

            //on charge la vue visu article :
            assert agence != null;
            nom_agence.setText(agence.getNom());
            password_agence.setText(agence.getPassword());

    }

    public void enregistrerModificationAgence(View view) {

        TextView nom_agence = findViewById(R.id.new_nom_agence);
        TextView password_agence = findViewById(R.id.new_password_agence);

        String nom = nom_agence.getText().toString();
        String motDePasse = password_agence.getText().toString();

        //on récupère l'objet agence créé :
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedPreferences.getString("agence_enregistree",null);

        Agence agence = null;
        if(json != null) {
            agence = gson.fromJson(json, Agence.class);

        }

        if( nom.isEmpty() | motDePasse.isEmpty()) {
            String erreurModifAgence = "Vous devez renseigner tous les champs pour effectuer la modification.";

            sharedPreferences.edit()
                    .putString("erreur_modification_Agence",erreurModifAgence)
                    .apply();

            Intent intent = new Intent(this, ModifierAgence.class);
            startActivity(intent);

        } else {

            sharedPreferences.edit()
            .remove("erreur_modification_Agence")
                    .apply();


            if (agence != null) {
                agence.setNom(nom);
                agence.setPassword(motDePasse);
                new AgenceDao(this).update(agence);

                sharedPreferences.edit()
                        .putString("agence_enregistree",gson.toJson(agence))
                        .apply();

            }


            Intent intent = new Intent(this, AgenceVisuActivity.class);
            startActivity(intent);
        }




    }

}
