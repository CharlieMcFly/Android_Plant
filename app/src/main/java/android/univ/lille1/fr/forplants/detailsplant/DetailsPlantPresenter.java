package android.univ.lille1.fr.forplants.detailsplant;

import android.app.Activity;
import android.univ.lille1.fr.forplants.addeditplant.AddEditPlantActivity;
import android.univ.lille1.fr.forplants.data.Plant;
import android.univ.lille1.fr.forplants.data.source.local.PlantsLocalDataSource;
import android.util.Log;

/**
 * Created by charlie on 28/11/16.
 *
 * Reprend les différents actions a faire avec la DB
 */
public class DetailsPlantPresenter implements DetailsPlantContract.Presenter{

    private final DetailsPlantContract.View mView;
    private final PlantsLocalDataSource mRepository;
    private final int id_plant;

    public DetailsPlantPresenter(int id, DetailsPlantContract.View view, PlantsLocalDataSource repository){

        mRepository = repository;
        mView = view;
        id_plant = id;
        mView.setPresenter(this);
    }

    @Override
    public void showPlant() {

        mRepository.open();
        Plant plant = mRepository.getPlant(id_plant);
        mRepository.close();
        mView.showPlant(plant);
    }

    @Override
    public void deletePlant() {
        mRepository.open();
        mRepository.delete(id_plant);
        mRepository.close();
        mView.showPlantsList();

    }

    @Override
    public void editPlant() {
        mView.showEditPlant(id_plant);
    }

    @Override
    public void result(int requestCode, int resultCode) {
        if (AddEditPlantActivity.REQUEST_EDIT_PLANT == requestCode && Activity.RESULT_OK == resultCode) {
            mView.showSuccessfullyEditedMessage();

        }else{
            mView.showMessageNotHandled();
        }
    }
}
