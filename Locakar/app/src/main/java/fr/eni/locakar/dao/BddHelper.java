package fr.eni.locakar.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class BddHelper extends SQLiteOpenHelper {
    private final static int VERSION = 2;
    private final static String BDD_NAME = "Lokacar.db";

    private final static String TAG = "ACOS";


    public BddHelper(Context context) {
        super(context, BDD_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "Passage dans le onCreate");
        sqLiteDatabase.execSQL(AgenceContract.CREATE_TABLE);
        sqLiteDatabase.execSQL(VehiculeContract.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i(TAG, "Passage dans le onUpgrade");
        sqLiteDatabase.execSQL(AgenceContract.DROP_TABLE);
        sqLiteDatabase.execSQL(VehiculeContract.DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
}
