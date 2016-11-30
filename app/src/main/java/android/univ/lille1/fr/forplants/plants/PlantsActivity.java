package android.univ.lille1.fr.forplants.plants;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.univ.lille1.fr.forplants.R;
import android.univ.lille1.fr.forplants.data.source.local.PlantsLocalDataSource;
import android.univ.lille1.fr.forplants.util.ActivityUtils;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Activité principal de l'application
 */
public class PlantsActivity extends AppCompatActivity {

    private PlantsPresenter mPlantsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PlantsFragment plantsFragment =
                (PlantsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (plantsFragment == null) {
            // Create the fragment
            plantsFragment = PlantsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), plantsFragment, R.id.contentFrame);
        }

        mPlantsPresenter = new PlantsPresenter(plantsFragment, new PlantsLocalDataSource(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_plants, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_avancer_jour:
                mPlantsPresenter.addDayAllPlant();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
