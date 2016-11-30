package android.univ.lille1.fr.forplants.addeditplant;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.univ.lille1.fr.forplants.R;
import android.univ.lille1.fr.forplants.data.source.local.PlantsLocalDataSource;
import android.univ.lille1.fr.forplants.util.ActivityUtils;

/**
 *  Cette classe est utilisée pour Ajouter et Editer des plantes
 */
public class AddEditPlantActivity extends AppCompatActivity {

    private AddEditPlantContract.Presenter mAddEditPresenter;
    public static final int REQUEST_ADD_PLANT = 1;
    public static final int REQUEST_EDIT_PLANT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_edit_plant);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Permet le retour à l'activité de la liste des plantes
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // Creation du fragment et recupération de la plante passée en paramètre s'il y a
        AddEditPlantFragment addEditPlantFragment =
                (AddEditPlantFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        int id_plant = getIntent().getIntExtra("plant", 0);

        if (addEditPlantFragment == null) {
            addEditPlantFragment = AddEditPlantFragment.newInstance();

            if (id_plant >= 0) {
                actionBar.setTitle(R.string.edit_plant);
                Bundle bundle = new Bundle();
                bundle.putInt(AddEditPlantFragment.ARGUMENT_EDIT_PLANT, id_plant);
                addEditPlantFragment.setArguments(bundle);
            } else {
                actionBar.setTitle(R.string.add_task);
            }
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), addEditPlantFragment, R.id.contentFrame);
        }

        mAddEditPresenter = new AddEditPlantPresenter(id_plant, addEditPlantFragment, new PlantsLocalDataSource(this));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
