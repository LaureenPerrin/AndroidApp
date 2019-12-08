package fr.eni.locakar.bo;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class Vehicule implements Parcelable {

    private int id;
    private String immatriculation;
    private String marque;
    private String type;
    private int prix;
    private boolean isLoue;
    private int agence_id;

    private Location location;
    private List<Location> locations;
    private Agence agence;




    public Vehicule(){

    }

   /* public Vehicule(Blob photo, String immatriculation, Enum marques, Enum types, Float prix, boolean isLoue, ArrayList locations) {
        this.photo = photo;
        this.immatriculation = immatriculation;
        this.marques = marques;
        this.types = types;
        this.prix = prix;
        this.isLoue = isLoue;
        this.locations = locations;
    }*/

    public Vehicule(String immatriculation, String marque, String type, int prix, boolean isLoue, int id_agence) {
        this.marque = marque;
        this.immatriculation = immatriculation;
        this.type = type;
        this.prix = prix;
        this.isLoue = isLoue;
        this.agence_id = id_agence;
    }

    protected Vehicule(Parcel in) {
        id = in.readInt();
        immatriculation = in.readString();
        marque = in.readString();
        type = in.readString();
        prix = in.readInt();
        isLoue = in.readByte() != 0;
        agence_id = in.readInt();
    }

    public static final Creator<Vehicule> CREATOR = new Creator<Vehicule>() {
        @Override
        public Vehicule createFromParcel(Parcel in) {
            return new Vehicule(in);
        }

        @Override
        public Vehicule[] newArray(int size) {
            return new Vehicule[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getMarques() {
        return marque;
    }

    public void setMarques(String marque) {
        this.marque = marque;
    }

    public String getTypes() {
        return type;
    }

    public void setTypes(String type) {
        this.type = type;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public boolean isLoue() {
        return isLoue;
    }

    public void setLoue(boolean loue) {
        isLoue = loue;
    }

    public List getLocations() {
        return locations;
    }

    public void setLocations(List locations) {
        this.locations = locations;
    }

    public int getAgence_id() {
        return agence_id;
    }

    public void setAgence_id(int agence_id) {
        this.agence_id = agence_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(immatriculation);
        dest.writeString(marque);
        dest.writeString(type);
        dest.writeInt(prix);
        dest.writeByte((byte) (isLoue ? 1 : 0));
        dest.writeInt(agence_id);
    }
}
