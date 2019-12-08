package fr.eni.locakar.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.eni.locakar.bo.Vehicule;

public class VehiculeDao {

    private SQLiteDatabase db;
    private BddHelper helper;

    public VehiculeDao(Context context) {
        helper = new BddHelper(context);
        db = helper.getWritableDatabase();
    }

    public long insert(Vehicule vehicule) {
        ContentValues values = new ContentValues();
        values.put(VehiculeContract.COL_IMMAT, vehicule.getImmatriculation());
        values.put(VehiculeContract.COL_MARQUE, vehicule.getMarques());
        values.put(VehiculeContract.COL_TYPE, vehicule.getTypes());
        values.put(VehiculeContract.COL_PRIX, vehicule.getPrix());
        values.put(VehiculeContract.COL_ISLOUE, vehicule.isLoue());
        values.put(VehiculeContract.COL_AGENCE, vehicule.getAgence_id());

        return db.insert(VehiculeContract.TABLE_NAME, null, values);
    }


    public Vehicule get(long id) {
        Vehicule vehicule = null;

        Cursor cursor = db.query(VehiculeContract.TABLE_NAME, new String[]{VehiculeContract.COL_ID,
                        VehiculeContract.COL_IMMAT, VehiculeContract.COL_MARQUE, VehiculeContract.COL_TYPE,
                        VehiculeContract.COL_PRIX,  VehiculeContract.COL_ISLOUE, VehiculeContract.COL_AGENCE},
                VehiculeContract.COL_ID + " =?",
                new String[]{String.valueOf(id)},
                null, null, null);



        if (cursor.moveToNext()) {

            boolean isloue = cursor.getInt(cursor.getColumnIndex(VehiculeContract.COL_ISLOUE.trim())) == 1;

            vehicule = new Vehicule();
            vehicule.setId(cursor.getInt(cursor.getColumnIndex(VehiculeContract.COL_ID.trim())));
            vehicule.setImmatriculation(cursor.getString(cursor.getColumnIndex(VehiculeContract.COL_IMMAT.trim())));
            vehicule.setMarques(cursor.getString(cursor.getColumnIndex(VehiculeContract.COL_MARQUE.trim())));
            vehicule.setTypes(cursor.getString(cursor.getColumnIndex(VehiculeContract.COL_TYPE.trim())));
            vehicule.setPrix(cursor.getInt(cursor.getColumnIndex(VehiculeContract.COL_PRIX.trim())));
            vehicule.setLoue(isloue);
            vehicule.setAgence_id(cursor.getInt(cursor.getColumnIndex(VehiculeContract.COL_AGENCE.trim())));
        }
        return vehicule;
    }

    public List<Vehicule> get(boolean boutonLoue) {
        List<Vehicule> resultat = new ArrayList<>();

        int loue = boutonLoue ? 1 : 0;

        Cursor cursor = db.query(VehiculeContract.TABLE_NAME, new String[]{VehiculeContract.COL_ID, VehiculeContract.COL_IMMAT, VehiculeContract.COL_MARQUE, VehiculeContract.COL_TYPE,
                        VehiculeContract.COL_PRIX, VehiculeContract.COL_ISLOUE,VehiculeContract.COL_AGENCE}, VehiculeContract.COL_ISLOUE + "=?",
                new String[]{String.valueOf(loue)}, null, null, null);


        while (cursor.moveToNext()) {
            boolean isloue = cursor.getInt(cursor.getColumnIndex(VehiculeContract.COL_ISLOUE.trim())) == 1;

            Vehicule vehicule = new Vehicule();
            vehicule.setId(cursor.getInt(cursor.getColumnIndex(VehiculeContract.COL_ID.trim())));
            vehicule.setImmatriculation(cursor.getString(cursor.getColumnIndex(VehiculeContract.COL_IMMAT.trim())));
            vehicule.setMarques(cursor.getString(cursor.getColumnIndex(VehiculeContract.COL_MARQUE.trim())));
            vehicule.setTypes(cursor.getString(cursor.getColumnIndex(VehiculeContract.COL_TYPE.trim())));
            vehicule.setPrix(cursor.getInt(cursor.getColumnIndex(VehiculeContract.COL_PRIX.trim())));
            vehicule.setLoue(isloue);
            vehicule.setAgence_id(cursor.getInt(cursor.getColumnIndex(VehiculeContract.COL_AGENCE.trim())));

            resultat.add(vehicule);
        }
        return resultat;
    }

    public List<Vehicule> getAll() {
        List<Vehicule> resultat = new ArrayList<>();


        Cursor cursor = db.query(VehiculeContract.TABLE_NAME, new String[]{VehiculeContract.COL_ID, VehiculeContract.COL_IMMAT, VehiculeContract.COL_MARQUE, VehiculeContract.COL_TYPE,
                        VehiculeContract.COL_PRIX, VehiculeContract.COL_ISLOUE,VehiculeContract.COL_AGENCE}, null,
                null, null, null, null);



        while (cursor.moveToNext()) {

            boolean isloue = cursor.getInt(cursor.getColumnIndex(VehiculeContract.COL_ISLOUE.trim())) == 1;
            Vehicule vehicule = new Vehicule();
            vehicule.setId(cursor.getInt(cursor.getColumnIndex(VehiculeContract.COL_ID.trim())));
            vehicule.setImmatriculation(cursor.getString(cursor.getColumnIndex(VehiculeContract.COL_IMMAT.trim())));
            vehicule.setMarques(cursor.getString(cursor.getColumnIndex(VehiculeContract.COL_MARQUE.trim())));
            vehicule.setTypes(cursor.getString(cursor.getColumnIndex(VehiculeContract.COL_TYPE.trim())));
            vehicule.setPrix(cursor.getInt(cursor.getColumnIndex(VehiculeContract.COL_PRIX.trim())));
            vehicule.setLoue(isloue);
            vehicule.setAgence_id(cursor.getInt(cursor.getColumnIndex(VehiculeContract.COL_AGENCE.trim())));

            resultat.add(vehicule);
        }
        return resultat;
    }

    public boolean update(Vehicule vehicule) {
        Log.i("ACOS", "Entrée dans update avec " + vehicule.toString());

        ContentValues cv = new ContentValues();
        cv.put(VehiculeContract.COL_IMMAT, vehicule.getImmatriculation());
        cv.put(VehiculeContract.COL_MARQUE, vehicule.getMarques());
        cv.put(VehiculeContract.COL_TYPE, vehicule.getTypes());
        cv.put(VehiculeContract.COL_PRIX, vehicule.getPrix());
        cv.put(VehiculeContract.COL_ISLOUE, vehicule.isLoue());
        cv.put(VehiculeContract.COL_AGENCE, vehicule.getAgence_id());

        return db.update(VehiculeContract.TABLE_NAME, cv, VehiculeContract.COL_ID + "=?", new String[]{String.valueOf(vehicule.getId())}) > 0;
    }

    public boolean delete(Vehicule vehicule) {
        return db.delete(VehiculeContract.TABLE_NAME, VehiculeContract.COL_ID + " =?", new String[]{String.valueOf(vehicule.getId())}) > 0;
    }
}
