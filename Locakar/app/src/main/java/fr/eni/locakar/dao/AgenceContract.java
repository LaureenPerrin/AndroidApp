package fr.eni.locakar.dao;

public class AgenceContract {


    public static final String TABLE_NAME = "Agence";

    public static final String COL_ID = " id ";
    public static final String COL_NOM = " nom ";
    public static final String COL_PASSWORD = " password ";
    public static final String COL_CA = " ca ";


    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_NOM + " TEXT," + COL_PASSWORD + " TEXT, " + COL_CA + " INT " + ");";

    public static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME;

}
