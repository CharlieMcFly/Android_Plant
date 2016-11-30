package android.univ.lille1.fr.forplants.addeditplant;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.univ.lille1.fr.forplants.R;
import android.univ.lille1.fr.forplants.data.Plant;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;


/**
 * Fragment de l'ajout et l'édition de la plante
 */
public class AddEditPlantFragment extends Fragment implements AddEditPlantContract.View{


    public static final String ARGUMENT_EDIT_PLANT = "EDIT_PLANT";

    private AddEditPlantContract.Presenter mPresenter;
    private EditText mNomPlant;
    private EditText mDescriptionPlant;
    private EditText mFreqPlant;


    public AddEditPlantFragment() {
        // Requires empty public constructor
    }

    public static AddEditPlantFragment newInstance() {
        return new AddEditPlantFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Affiche le floating button
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_add_plant_done);
        fab.setImageResource(R.mipmap.ic_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.savePlant(mNomPlant.getText().toString(),
                                        mDescriptionPlant.getText().toString(),
                                        mFreqPlant.getText().toString());
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_edit_plant, container, false);

        // Set Up Add
        mNomPlant = (EditText) root.findViewById(R.id.add_plant_title);
        mDescriptionPlant = (EditText) root.findViewById(R.id.add_plant_description);
        mFreqPlant = (EditText) root.findViewById(R.id.add_plant_freq);

        mPresenter.showAddEditPlant();

        return root;
    }


    @Override
    public void returnOldActivity() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setPresenter(AddEditPlantContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showErrorSavePlant(String error) {
        new MaterialDialog.Builder(getActivity())
                .title(R.string.title_dialog_fail_save)
                .content(error)
                .positiveText(R.string.ok)
                .show();
    }

    @Override
    public void showEditPlant(Plant plant) {
        mNomPlant.setText(plant.getNom());
        mDescriptionPlant.setText(plant.getDescription());
        mFreqPlant.setText(String.valueOf(plant.getFreq()));
    }

}
