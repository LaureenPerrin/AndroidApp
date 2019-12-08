package fr.eni.locakar.dao;

import java.sql.Blob;
import java.util.ArrayList;

import fr.eni.locakar.bo.Location;

class VehiculeContract {

    public static final String TABLE_NAME = "Vehicule";

    public static final String COL_ID = " id ";
    public static final String COL_IMMAT = " immatriculation ";
    public static final String COL_MARQUE = " marque ";
    public static final String COL_TYPE = " type ";
    public static final String COL_PRIX = " prix ";
    public static final String COL_AGENCE = " agence_id ";
    public static final String COL_ISLOUE = " isLoue ";


    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_IMMAT + " TEXT, "
            + COL_MARQUE + " TEXT, "
            + COL_TYPE + " TEXT, "
            + COL_PRIX + " INT, " +
            COL_ISLOUE + " INT, "
            +  COL_AGENCE + " INT, " +
            "FOREIGN KEY(" + COL_AGENCE + ") REFERENCES " + " Agence("+ AgenceContract.COL_ID +") " + ");";

    public static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME;
}
