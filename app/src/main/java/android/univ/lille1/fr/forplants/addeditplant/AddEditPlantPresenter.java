package android.univ.lille1.fr.forplants.addeditplant;

import android.univ.lille1.fr.forplants.data.Plant;
import android.univ.lille1.fr.forplants.data.source.local.PlantsLocalDataSource;

/**
 * Created by charlie on 24/11/16.
 *
 * Reprend les différents actions a faire avec la DB
 */
public class AddEditPlantPresenter implements AddEditPlantContract.Presenter{

    private final AddEditPlantContract.View mView;
    private final PlantsLocalDataSource mRepository;
    private final int id_plant;

    public AddEditPlantPresenter(int id, AddEditPlantContract.View view, PlantsLocalDataSource repository){
        id_plant = id;
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void showAddEditPlant() {
        if(id_plant >= 0){
            mRepository.open();
            Plant plant = mRepository.getPlant(id_plant);
            mRepository.close();
            mView.showEditPlant(plant);
        }
    }

    @Override
    public void savePlant(String nom, String description, String freq) {

        if(nom == null || nom.equals("") || description == null || description.equals("") || freq == null  || freq.equals(""))
            mView.showErrorSavePlant("Vous devez remplir tous les champs pour créer une plante !");
        else {
            if (Integer.parseInt(freq) <= 0)
                mView.showErrorSavePlant("La fréquence donnée n'est pas correcte !");

            Plant plant = new Plant(nom, description, Integer.parseInt(freq));

            mRepository.open();
            if (id_plant >= 0){
                mRepository.updatePlant(plant, id_plant);

            }else {
                mRepository.addPlant(plant);
            }
            mRepository.close();
            mView.returnOldActivity();


        }
    }
}
