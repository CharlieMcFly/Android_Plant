package android.univ.lille1.fr.forplants.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.univ.lille1.fr.forplants.plants.PlantsActivity;

/**
 * Created by charlie on 30/11/16.
 * Affiche un splashscreen
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, PlantsActivity.class);
        startActivity(intent);
        finish();
    }
}
