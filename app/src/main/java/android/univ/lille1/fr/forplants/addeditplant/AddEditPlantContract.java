package android.univ.lille1.fr.forplants.addeditplant;

import android.univ.lille1.fr.forplants.data.Plant;

/**
 * Created by charlie on 24/11/16.
 *
 * Cette classe sert à définir les vues et le présenteur (les fonctions qu'ils proposent)
 */
public interface AddEditPlantContract {

    interface View {

        /**
         * Retourne à l'ancienne activité
         */
        void returnOldActivity();

        /**
         * Initialise le présenter pour la vue
         *
         * @param presenter
         */
        void setPresenter(AddEditPlantContract.Presenter presenter);

        /**
         * Affiche un message d'erreur s'il y a lors de la création.
         *
         * @param error
         */
        void showErrorSavePlant(String error);

        /**
         * Affiche la vue pour l'édition de la plante.
         *
         * @param plant
         */
        void showEditPlant(Plant plant);
    }

    interface Presenter {

        /**
         * Passe le relais à la vue
         */
        void showAddEditPlant();


        /**
         *
         * Sauvegarde la plante en passant le relais à {@PlantsLocalDataSource}
         *
         * @param nomP de la plante
         * @param descriptionP de la plante
         * @param freqP d'arrosage de la plante en jours
         */
        void savePlant(String nomP, String descriptionP, String freqP);
    }

}
