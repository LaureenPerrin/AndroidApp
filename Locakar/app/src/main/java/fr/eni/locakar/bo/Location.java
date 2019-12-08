package fr.eni.locakar.bo;

import java.sql.Blob;
import java.util.Date;

public class Location {

    private Vehicule voiture;

    private Date dateDebut;
    private Date dateFin;
    private int jours;
    private Float cout;
    private Blob etatLieuDebut;
    private Blob etatLieuFin;

    private Client client;
    private String coordonnees;
}
