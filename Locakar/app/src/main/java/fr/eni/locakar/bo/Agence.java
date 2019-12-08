package fr.eni.locakar.bo;

import java.util.ArrayList;

public class Agence {

    private int id;
    private String nom;
    private String password;
    private int ca;

    private ArrayList<Vehicule> listVehicule;

    public Agence(){

    }

    public Agence(String nom, String password, int ca) {
        this.nom = nom;
        this.password = password;
        this.ca = ca;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCA() {
        return ca;
    }

    public void setCA(int CA) {
        this.ca = CA;
    }

    public ArrayList<Vehicule> getListVehicule() {
        return listVehicule;
    }

    public void setListVehicule(ArrayList<Vehicule> listVehicule) {
        this.listVehicule = listVehicule;
    }
}
