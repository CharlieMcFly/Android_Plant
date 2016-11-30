package android.univ.lille1.fr.forplants.plants;

import android.app.Activity;
import android.univ.lille1.fr.forplants.addeditplant.AddEditPlantActivity;
import android.univ.lille1.fr.forplants.data.Plant;
import android.univ.lille1.fr.forplants.data.source.local.PlantsLocalDataSource;
import android.univ.lille1.fr.forplants.detailsplant.DetailsPlantActivity;
import android.univ.lille1.fr.forplants.util.ActivityUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charlie on 24/11/16.
 */

public class PlantsPresenter implements PlantsContract.Presenter{

    private final PlantsLocalDataSource mRepository;
    private final PlantsContract.View mView;

    public PlantsPresenter(PlantsContract.View view, PlantsLocalDataSource repository){

        mRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void loadPlants() {

        mRepository.open();

        // Récupérer toutes les plantes
        List<Plant> mesPlants = mRepository.getPlants();

        // Afficher la bonne vue
        if(mesPlants == null || mesPlants.size() == 0)
            mView.showNoPlants();
        else
            mView.showPlants(mesPlants);

        mRepository.close();
    }


    @Override
    public void addPlants() {

        mRepository.open();

        // Créer 10 plantes
        Plant p1 = new Plant("Bromelia", "Bromelia est un genre de plantes tropicales américaines de la famille des Bromeliaceae. Il doit son nom au botaniste suédois Olof Bromelius ", 5);
        Plant p2 = new Plant("Centranthe", "Les centranthes sont des plantes herbacées du genre Centranthus (du grec \"kentron\", éperon et \"anthos\", fleur), appartenant à la famille des Valérianacées selon la classification classique. Tous les genres de la famille des Valérianacées ont été rangés dans la famille des Caprifoliacées par la classification phylogénétique APG III (2009). Il existe 9 espèces de centranthes, dont 8 en Europe.", 3);
        Plant p3 = new Plant("Cerisier", "Le terme cerisier est un nom vernaculaire générique qui désigne en français plusieurs espèces d'arbres du genre Prunus de la famille des Rosaceae. Ce sont soit des arbres fruitiers donnant des cerises, soit des arbres ornementaux originaires d'Asie Orientale (Chine, Japon) plantés uniquement pour leurs fleurs et dont les fruits sont insignifiants.", 2);
        Plant p4 = new Plant("Filao", "Le Filao (Casuarina equisetifolia) est un arbre d'origine australienne de la famille des Casuarinacées présent également sur les côtes d'Indonésie, de Malaisie, des îles du Pacifique et des Mascareignes ainsi qu'aux Antilles. On le trouve aussi au Sénégal, notamment en bord de mer.", 1);
        Plant p5 = new Plant("Gentiane", "« Gentiane » est un nom vernaculaire qui désigne en français plusieurs espèces de la famille des Gentianaceae (Gentianacées), appartenant aux genres Gentiana, Gentianella ou Gentianopsis.", 1);
        Plant p6 = new Plant("Héliotrope", "Les héliotropes (genre Heliotropium) sont des plantes appartenant à la famille des Boraginacées, qui doivent leur nom au fait que leurs feuilles se tourneraient vers le soleil. Il en existe environ 250 espèces dans le monde, notamment dans les régions subtropicales.", 1);
        Plant p7 = new Plant("Igname", "« Igname » est un nom vernaculaire ambigu désignant en français plusieurs espèces de plantes appartenant au genre Dioscorea, famille des Dioscoreaceae, cultivées dans toutes les régions tropicales du globe terrestre, dans un but alimentaire, pour leurs tubercules riches en amidon. Le terme désigne aussi le tubercule lui-même consommé comme légume-racine. En Amérique du Nord et au Québec, ce qu'on appelle igname est souvent en fait une patate douce. ", 2);
        Plant p8 = new Plant("Jacaranda", "Jacaranda est un genre d'arbres, les jacarandas, de la famille des Bignoniaceae, originaire du Paraguay, Uruguay, sud du Brésil et de l'Argentine. ", 6);
        Plant p9 = new Plant("Jasmin", "Le mot jasmin désigne les plantes dicotylédones appartenant au genre Jasminum.", 4);
        Plant p10 = new Plant("Lotus", "En botanique, le lotus, dont le nom dérive du grec lotos par le latin lotus, est un nom vernaculaire ambigu qui désigne en français diverses plantes, arbres, arbustes ou herbes, terrestres ou aquatiques, qui portaient déjà ce nom dans l'Antiquité.", 8);

        mRepository.addPlant(p1);
        mRepository.addPlant(p2);
        mRepository.addPlant(p3);
        mRepository.addPlant(p4);
        mRepository.addPlant(p5);
        mRepository.addPlant(p6);
        mRepository.addPlant(p7);
        mRepository.addPlant(p8);
        mRepository.addPlant(p9);
        mRepository.addPlant(p10);

        mRepository.close();

        // Reload la vue
        this.loadPlants();

    }

    @Override
    public void addPlant() {
        mView.showAddPlant();
    }

    @Override
    public void result(int requestCode, int resultCode) {
        if (AddEditPlantActivity.REQUEST_ADD_PLANT == requestCode && Activity.RESULT_OK == resultCode) {
            mView.showSuccessfullySavedMessage();
        }else if(DetailsPlantActivity.DETAIL_PLANT == requestCode && Activity.RESULT_OK == resultCode){
            mView.showSuccessfullyDeletedMessage();
        }else{
            mView.showMessageNotHandled();
        }
    }

    @Override
    public void showPlantsDetails(int id_plant) {
        mView.showPlant(id_plant);
    }

    @Override
    public void addDayAllPlant() {
        // Diminuer le jour d'arrosage
        ActivityUtils.add1day();
        loadPlants();
    }

    @Override
    public void arroserPlant(int id_plant) {
        mRepository.open();
        mRepository.updateDatePlant(id_plant);
        mRepository.close();
        loadPlants();
        mView.showSuccessfullyArrosedMessage();
    }

    @Override
    public void cannotArroser() {
        mView.showAlreadyArrosed();
    }

}
