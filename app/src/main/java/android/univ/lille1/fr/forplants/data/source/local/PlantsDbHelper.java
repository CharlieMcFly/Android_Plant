package android.univ.lille1.fr.forplants.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by charlie on 24/11/16.
 *
 * Permet la creation de la base de données
 */
public class PlantsDbHelper extends SQLiteOpenHelper{


    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Plants.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PlantsTableDB.PlantEntry.TABLE_NAME + " (" +
                    PlantsTableDB.PlantEntry.COLUMN_NAME_ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    PlantsTableDB.PlantEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    PlantsTableDB.PlantEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    PlantsTableDB.PlantEntry.COLUMN_NAME_FREQ + INTEGER_TYPE + COMMA_SEP +
                    PlantsTableDB.PlantEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
                    PlantsTableDB.PlantEntry.COLUMN_NAME_DATE_ARROSAGE + TEXT_TYPE +
                    " )";

    public PlantsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " +  PlantsTableDB.PlantEntry.TABLE_NAME + ";");
        onCreate(sqLiteDatabase);
    }
}
