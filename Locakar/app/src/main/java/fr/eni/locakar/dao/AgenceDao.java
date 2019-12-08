package fr.eni.locakar.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import fr.eni.locakar.bo.Agence;

public class AgenceDao {


    private SQLiteDatabase db;
    private BddHelper helper;


    public AgenceDao(Context context) {
        helper = new BddHelper(context);
        db = helper.getWritableDatabase();
    }

    public long insert(Agence agence) {
        ContentValues values = new ContentValues();
        values.put(AgenceContract.COL_NOM, agence.getNom());
        values.put(AgenceContract.COL_PASSWORD, agence.getPassword());
        values.put(AgenceContract.COL_CA, agence.getCA());

        return db.insert(AgenceContract.TABLE_NAME, null, values);
    }

    public Agence get(long id) {
        Agence agence = null;

        Cursor cursor = db.query(AgenceContract.TABLE_NAME, new String[]{AgenceContract.COL_ID, AgenceContract.COL_NOM,
                        AgenceContract.COL_PASSWORD,  AgenceContract.COL_CA},
                AgenceContract.COL_ID + " =?",
                new String[]{String.valueOf(id)},
                null, null, null);


        if (cursor.moveToNext()) {
            agence = new Agence();
            agence.setId(cursor.getInt(cursor.getColumnIndex(AgenceContract.COL_ID.trim())));
            agence.setNom(cursor.getString(cursor.getColumnIndex(AgenceContract.COL_NOM.trim())));
            agence.setPassword(cursor.getString(cursor.getColumnIndex(AgenceContract.COL_PASSWORD.trim())));
            agence.setCA(cursor.getInt(cursor.getColumnIndex(AgenceContract.COL_CA.trim())));
        }
        return agence;
    }

    public Agence getPassword(String password) {
        Agence agence = null;

        Cursor cursor = db.query(AgenceContract.TABLE_NAME, new String[]{AgenceContract.COL_ID, AgenceContract.COL_NOM,
                        AgenceContract.COL_PASSWORD,  AgenceContract.COL_CA},
                AgenceContract.COL_PASSWORD + " =?",
                new String[]{String.valueOf(password)},
                null, null, null);


        if (cursor.moveToNext()) {
            agence = new Agence();
            agence.setId(cursor.getInt(cursor.getColumnIndex(AgenceContract.COL_ID.trim())));
            agence.setNom(cursor.getString(cursor.getColumnIndex(AgenceContract.COL_NOM.trim())));
            agence.setPassword(cursor.getString(cursor.getColumnIndex(AgenceContract.COL_PASSWORD.trim())));
            agence.setCA(cursor.getInt(cursor.getColumnIndex(AgenceContract.COL_CA.trim())));
        }
        return agence;
    }


    public boolean update(Agence agence) {
        Log.i("ACOS", "Entrée dans update avec " + agence.toString());

        ContentValues cv = new ContentValues();
        cv.put(AgenceContract.COL_NOM, agence.getNom());
        cv.put(AgenceContract.COL_PASSWORD, agence.getPassword());
        cv.put(AgenceContract.COL_CA, agence.getPassword());

        return db.update(AgenceContract.TABLE_NAME, cv, AgenceContract.COL_ID + "=?", new String[]{String.valueOf(agence.getId())}) > 0;
    }

    public boolean delete(Agence agence) {
        return db.delete(AgenceContract.TABLE_NAME, AgenceContract.COL_ID + " =?", new String[]{String.valueOf(agence.getId())}) > 0;
    }

}
