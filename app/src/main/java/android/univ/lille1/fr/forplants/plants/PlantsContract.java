package android.univ.lille1.fr.forplants.plants;

import android.univ.lille1.fr.forplants.data.Plant;

import java.util.List;

/**
 * Created by charlie on 24/11/16.
 *
 *  Cette classe sert à définir les vues et le présenteur (les fonctions qu'ils proposent)
 */
public interface PlantsContract {

    interface View {

        /**
         * Affiche la liste de plantes
         * @param plants
         */
        void showPlants(List<Plant> plants);

        /**
         * Affiche l'activité pour l'ajout d'une plante
         */
        void showAddPlant();

        /**
         * Affiche lorsqu'il n'y a pas de plantes (au début).
         */
        void showNoPlants();

        /**
         * Set le présenter de la vue
         * @param presenter
         */
        void setPresenter(PlantsContract.Presenter presenter);

        /**
         * Affiche que la plante a été bien sauvé
         */
        void showSuccessfullySavedMessage();

        /**
         * Affiche l'activité pour montré une plante
         * @param plant
         */
        void showPlant(int plant);

        /**
         * Montre un message lorsque la plante a été correctement supprimer
         */
        void showSuccessfullyDeletedMessage();

        /**
         * Affiche les logs lorsque le resultat de l'activité n'est pas gérer
         */
        void showMessageNotHandled();

        /**
         * Affiche un message comme quoi la plante a bien été arrosée.
         */
        void showSuccessfullyArrosedMessage();

        /**
         * Affiche un message comme quoi la plante a déjà été arrossée.
         */
        void showAlreadyArrosed();
    }

    interface Presenter {

        /**
         * Récupère les plantes dans la db et les affiche
         */
        void loadPlants();

        /**
            Ajoute des plantes (fixtures)
         */
        void addPlants();

        /**
         * Ajoute une plante
         */
        void addPlant();

        /**
         * Gère les résult des activité une fois finies.
         *
         * @param requestCode
         * @param resultCode
         */
        void result(int requestCode, int resultCode);

        /**
         * Affiche la plante après l'avoir récupérer
         *
         * @param id_plant
         */
        void showPlantsDetails(int id_plant);

        /**
         * Utiliser pour le débug, pour la version final, vaut mieux le retirer
         */
        void addDayAllPlant();

        /**
         * Permet l'arrosage d'une plante avec mise a jour dans la DB
         * @param id_plant
         */
        void arroserPlant(int id_plant);

        /**
         * Si la plante a déjà été arrosée
         */
        void cannotArroser();
    }


}
