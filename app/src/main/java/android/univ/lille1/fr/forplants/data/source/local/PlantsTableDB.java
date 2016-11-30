package android.univ.lille1.fr.forplants.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by charlie on 24/11/16.
 */

public final class PlantsTableDB {


    private PlantsTableDB() {

    }

    /**
     *  Défini le contenu de la table
     */
    public static abstract class PlantEntry implements BaseColumns {
        public static final String TABLE_NAME = "plant";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_FREQ = "freq";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_DATE_ARROSAGE = "data_arrosage";
    }
}
