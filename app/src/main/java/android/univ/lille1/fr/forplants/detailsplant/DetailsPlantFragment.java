package android.univ.lille1.fr.forplants.detailsplant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.univ.lille1.fr.forplants.R;
import android.univ.lille1.fr.forplants.addeditplant.AddEditPlantActivity;
import android.univ.lille1.fr.forplants.data.Plant;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by charlie on 28/11/16.
 */

public class DetailsPlantFragment extends Fragment implements DetailsPlantContract.View {

    public static final String ARGUMENT_DETAIL_PLANT = "DETAIL_PLANT";

    private DetailsPlantContract.Presenter mPresenter;

    private TextView mNomPlant;
    private TextView mDescriptionPlant;
    private TextView mFreqPlant;
    private TextView mDateArroser;
    private TextView mDateArrosage;


    public DetailsPlantFragment() {
        // Requires empty public constructor
    }

    public static DetailsPlantFragment newInstance(@Nullable int id_plant) {
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_DETAIL_PLANT, id_plant);
        DetailsPlantFragment fragment = new DetailsPlantFragment();
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
        mPresenter.showPlant();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_plant);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.editPlant();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_detail_plant, container, false);

        setHasOptionsMenu(true);
        mNomPlant = (TextView) root.findViewById(R.id.detail_plant_title);
        mDescriptionPlant = (TextView) root.findViewById(R.id.detail_plant_description);
        mFreqPlant = (TextView) root.findViewById(R.id.detail_plant_freq);
        mDateArroser = (TextView) root.findViewById(R.id.detail_arroser);
        mDateArrosage = (TextView) root.findViewById(R.id.detail_arrosage);

        mPresenter.showPlant();


        return root;
    }

    @Override
    public void showPlant(Plant plant) {

        mNomPlant.setText(plant.getNom());
        mDescriptionPlant.setText(plant.getDescription());
        if(plant.getFreq() == 1 )
            mFreqPlant.setText("Cette plante doit être arrosée tous les " + String.valueOf(plant.getFreq()) + " jours.");
        else
            mFreqPlant.setText("Cette plante doit être arrosée tous les jours.");
        mDateArrosage.setText(plant.getDateArrosage());
        mDateArroser.setText(plant.getDate());
    }

    @Override
    public void showPlantsList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }


    @Override
    public void showEditPlant(int id_p) {
        Intent intent = new Intent(getContext(), AddEditPlantActivity.class);
        intent.putExtra("plant", id_p);
        startActivityForResult(intent, AddEditPlantActivity.REQUEST_EDIT_PLANT);
    }

    @Override
    public void showSuccessfullyEditedMessage() {
        showMessage(getString(R.string.successfully_edited_plant_message));
    }

    @Override
    public void showMessageNotHandled() {
        Log.d("MESSAGE", "Ce type de message n'est pas géré par l'application");
    }


    @Override
    public void setPresenter(DetailsPlantContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }


}