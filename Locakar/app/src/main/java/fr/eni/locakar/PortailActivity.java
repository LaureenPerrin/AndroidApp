package fr.eni.locakar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class PortailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portail);
    }


    public void agenceDetail(View view) {
        Intent intent = new Intent(this, AgenceVisuActivity.class);
        startActivity(intent);

    }

    public void parking(View view) {
        Intent intent = new Intent(this, ParkingActivity.class);
        startActivity(intent);

    }

    public void listeVehiculesLoues(View view) {
        Intent intent = new Intent(this, VehiculesLouesActivity.class);
        startActivity(intent);
    }

    public void listeVehiculesDisponibles(View view) {
    }
}
