package android.univ.lille1.fr.forplants.detailsplant;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.univ.lille1.fr.forplants.R;
import android.univ.lille1.fr.forplants.data.source.local.PlantsLocalDataSource;
import android.univ.lille1.fr.forplants.util.ActivityUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by charlie on 28/11/16.
 *
 * Classe reprend l'activité du détails d'une plante
 */
public class DetailsPlantActivity extends AppCompatActivity {

    public static final int DETAIL_PLANT = 2;
    public DetailsPlantContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_plant);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Permet le retour à l'application de la liste des plantes
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        // Créer le fragment et recupere la plant envoyé
        DetailsPlantFragment detailPlantFragment =
                (DetailsPlantFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        int id_plant = getIntent().getIntExtra("plant", 0);

        if (detailPlantFragment == null) {
            detailPlantFragment = DetailsPlantFragment.newInstance(id_plant);

            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), detailPlantFragment, R.id.contentFrame);
        }

        mPresenter = new DetailsPlantPresenter(id_plant, detailPlantFragment, new PlantsLocalDataSource(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detailsplant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.remove:
                areyousure();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Cette fonctionne demande à l'utilisateur s'il est sur de vouloir supprimer la plante
     * (Sorte de sécurité)
     */
    private void areyousure(){
        new MaterialDialog.Builder(this)
                .title(R.string.title_dialog_delete)
                .content(R.string.content_dialog_remove)
                .positiveText(R.string.ok)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        mPresenter.deletePlant();
                    }
                })
                .negativeText(R.string.annuler)
                .show();
    }
}
