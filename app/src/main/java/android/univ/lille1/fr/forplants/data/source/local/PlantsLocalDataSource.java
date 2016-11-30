package android.univ.lille1.fr.forplants.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.univ.lille1.fr.forplants.data.Plant;
import android.univ.lille1.fr.forplants.data.source.remote.PlantsDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charlie on 24/11/16.
 */

public class PlantsLocalDataSource implements PlantsDataSource {

    private SQLiteDatabase bdd;
    private PlantsDbHelper mDbHelper;

    public PlantsLocalDataSource(Context context) {
        mDbHelper = new PlantsDbHelper(context);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = mDbHelper.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    @Override
    public List<Plant> getPlants() {
        Cursor c = bdd.query(PlantsTableDB.PlantEntry.TABLE_NAME, new String[] {PlantsTableDB.PlantEntry.COLUMN_NAME_ID,
                                                                                PlantsTableDB.PlantEntry.COLUMN_NAME_TITLE,
                                                                                PlantsTableDB.PlantEntry.COLUMN_NAME_DESCRIPTION,
                                                                                PlantsTableDB.PlantEntry.COLUMN_NAME_FREQ,
                                                                                PlantsTableDB.PlantEntry.COLUMN_NAME_DATE,
                                                                                PlantsTableDB.PlantEntry.COLUMN_NAME_DATE_ARROSAGE,

                                                                                }, null, null, null, null, null);
        List<Plant> myplants = new ArrayList<Plant>();
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                myplants.add(cursorToPlant(c));
                c.moveToNext();
            }
        }

        c.close();

        return myplants;
    }

    @Override
    public long addPlant(Plant plant) {
        ContentValues values = new ContentValues();

        values.put(PlantsTableDB.PlantEntry.COLUMN_NAME_TITLE, plant.getNom());
        values.put(PlantsTableDB.PlantEntry.COLUMN_NAME_DESCRIPTION, plant.getDescription());
        values.put(PlantsTableDB.PlantEntry.COLUMN_NAME_FREQ, plant.getFreq());
        values.put(PlantsTableDB.PlantEntry.COLUMN_NAME_DATE, plant.getDate());
        values.put(PlantsTableDB.PlantEntry.COLUMN_NAME_DATE_ARROSAGE, plant.getDateArrosage());

        return bdd.insert(PlantsTableDB.PlantEntry.TABLE_NAME, null, values);
    }

    @Override
    public int delete(long id) {
        return bdd.delete(PlantsTableDB.PlantEntry.TABLE_NAME, PlantsTableDB.PlantEntry.COLUMN_NAME_ID + " = " + id, null);
    }

    @Override
    public Plant getPlant(int id) {
        Cursor c = bdd.query(PlantsTableDB.PlantEntry.TABLE_NAME, new String[] {PlantsTableDB.PlantEntry.COLUMN_NAME_ID,
                                                                                PlantsTableDB.PlantEntry.COLUMN_NAME_TITLE,
                                                                                PlantsTableDB.PlantEntry.COLUMN_NAME_DESCRIPTION,
                                                                                PlantsTableDB.PlantEntry.COLUMN_NAME_FREQ,
                                                                                PlantsTableDB.PlantEntry.COLUMN_NAME_DATE,
                                                                                PlantsTableDB.PlantEntry.COLUMN_NAME_DATE_ARROSAGE,
                                                                                }, PlantsTableDB.PlantEntry.COLUMN_NAME_ID + " = " + id +"", null, null, null, null);
        c.moveToFirst();
        return cursorToPlant(c);
    }

    @Override
    public int updatePlant(Plant plant, int id) {
        Plant p = getPlant(id);
        ContentValues values = new ContentValues();
        values.put(PlantsTableDB.PlantEntry.COLUMN_NAME_TITLE, plant.getNom());
        values.put(PlantsTableDB.PlantEntry.COLUMN_NAME_DESCRIPTION, plant.getDescription());
        values.put(PlantsTableDB.PlantEntry.COLUMN_NAME_FREQ, plant.getFreq());
        if(p.getFreq() != plant.getFreq()){
            String newDataArr = p.addXday(plant.getFreq());
            values.put(PlantsTableDB.PlantEntry.COLUMN_NAME_DATE_ARROSAGE, newDataArr);
        }
        return bdd.update(PlantsTableDB.PlantEntry.TABLE_NAME, values, PlantsTableDB.PlantEntry.COLUMN_NAME_ID + " = " +id, null);
    }

    @Override
    public int updateDatePlant(int id_plant) {

        Plant p = getPlant(id_plant);

        Plant newPlant = new Plant(p.getNom(), p.getDescription(), p.getFreq());
        ContentValues values = new ContentValues();
        values.put(PlantsTableDB.PlantEntry.COLUMN_NAME_DATE, newPlant.getDate());
        values.put(PlantsTableDB.PlantEntry.COLUMN_NAME_DATE_ARROSAGE, newPlant.getDateArrosage());

        return bdd.update(PlantsTableDB.PlantEntry.TABLE_NAME, values, PlantsTableDB.PlantEntry.COLUMN_NAME_ID + " = " +id_plant, null);
    }

    private Plant cursorToPlant(Cursor c){

        if (c.getCount() == 0)
            return null;

        Plant plant = new Plant();
        plant.setId(c.getInt(0));
        plant.setNom(c.getString(1));
        plant.setDescription(c.getString(2));
        plant.setFreq(c.getInt(3));
        plant.setDate(c.getString(4));
        plant.setDateArrosage(c.getString(5));

        return plant;
    }

}
