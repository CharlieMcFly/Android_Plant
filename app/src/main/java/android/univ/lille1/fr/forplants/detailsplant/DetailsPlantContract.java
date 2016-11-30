package android.univ.lille1.fr.forplants.detailsplant;

import android.univ.lille1.fr.forplants.data.Plant;

/**
 * Created by charlie on 28/11/16.
 *
 *  Cette classe sert à définir les vues et le présenteur (les fonctions qu'ils proposent)
 */
public interface DetailsPlantContract {

    interface View {

        /**
         * Affiche la plante sélectionné
         *
         * @param plant
         */
        void showPlant(Plant plant);

        /**
         * Renvoie à la liste des plants en l'actualisant
         */
        void showPlantsList();

        /**
         * Set le présenteur de la vue
         * @param presenter
         */
        void setPresenter(DetailsPlantContract.Presenter presenter);

        /**
         * Affiche la vue pour l'édition de cette plante
         *
         * @param id_p de la plante
         */
        void showEditPlant(int id_p);

        /**
         * Affiche un message lorsque l'édition c'est bien passée
         */
        void showSuccessfullyEditedMessage();

        /**
         * Affiche dans les logs une erreur si le message de retour n'est pas gérer par l'application
         * c'est invisible pour l'utilisateur. (au cas ou on veut ajouter des nouvelles fonctionnalités).
         */
        void showMessageNotHandled();
    }

    interface Presenter {

        /**
         * Affiche la plant en allant la récupérer dans la DB
         */
        void showPlant();

        /**
         * Supprime la plante via la db
         */
        void deletePlant();

        /**
         * Edit la plant via la db
         */
        void editPlant();

        /**
         * Affiche le bon message à l'utilisateur en fonction du résultat de l'activité. (uniquement pour lédition ici).
         *
         * @param requestCode
         * @param resultCode
         */
        void result(int requestCode, int resultCode);
    }

}

