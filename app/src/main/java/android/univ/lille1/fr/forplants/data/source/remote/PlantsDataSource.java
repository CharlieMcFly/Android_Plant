package android.univ.lille1.fr.forplants.data.source.remote;

import android.univ.lille1.fr.forplants.data.Plant;

import java.util.List;

/**
 * Created by charlie on 24/11/16.
 *
 * Cette interface sert pour les actions a faire sur la DB
 */

public interface PlantsDataSource {

    /**
     * Recupere les plantes de la DB
     *
     * @return la liste des plantes de l'application
     */
    List<Plant> getPlants();

    /**
     * Ajoute une plante à la db
     *
     * @param plant
     * @return l'id de la plante
     */
    long addPlant(Plant plant);

    /**
     * Supprime un plante de la db
     * @param id
     * @return l'id de la plante
     */
    int delete(long id);

    /**
     * Récupère la plante dans la db grace à l'id
     *
     * @param id
     * @return la plante avec l'id
     */
    Plant getPlant(int id);

    /**
     * Update que le nom, la description et la fréquence en jour
     *
     * @param plant
     * @param id
     * @return l'id de la plante
     */
    int updatePlant(Plant plant, int id);

    /**
     * Update la date de la plante arroser
     *
     * @param id_plant
     * @return l'id de la plante arroser
     */
    int updateDatePlant(int id_plant);
}
