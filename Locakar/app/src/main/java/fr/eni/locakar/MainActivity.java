package fr.eni.locakar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import fr.eni.locakar.bo.Agence;
import fr.eni.locakar.dao.AgenceDao;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void connexion(View view) {

        EditText motDePasse =  findViewById(R.id.password);
        String passwordSaisi = String.valueOf(motDePasse);

        Agence agence_recuperee = new AgenceDao(this).getPassword(passwordSaisi);

        if(agence_recuperee == null){
             Agence agence = new Agence("Agence de Niort", "password", 0);
            long agenceAppli = new AgenceDao(this).insert(agence);
            Agence agenceapplitrouve = new AgenceDao(this).get(agenceAppli);
            agence_recuperee = agenceapplitrouve;
        }


        SharedPreferences sharedPreferences = this.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        Gson gson = new Gson();


        sharedPreferences.edit()
                .putString("agence_enregistree",gson.toJson(agence_recuperee))
                .apply();



        assert agence_recuperee != null;
        String passwordAppli = agence_recuperee.getPassword();


        if (passwordAppli.contentEquals(motDePasse.getText())){
            Intent intent = new Intent(this, PortailActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, ErreurActivity.class);
            startActivity(intent);

        }



    }
}
