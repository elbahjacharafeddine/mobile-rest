package com.project.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("adresse")
    @Expose
    private String adresse;

    @SerializedName("heure_close")
    @Expose
    private String heure_close;


    @SerializedName("heure_open")
    @Expose
    private String heure_open;

    @SerializedName("latitude")
    @Expose
    private double latitude;

    @SerializedName("longitude")
    @Expose
    private double longitude;

    @SerializedName("nom")
    @Expose
    private String nom;

    @SerializedName("rank")
    @Expose
    private String rank;

    @SerializedName("serie_id")
    @Expose
    private int serie_id;

    @SerializedName("zone_id")
    @Expose
    private int zone_id;



    public Restaurant() {
    }

    public Restaurant(int id, String adresse, String heure_close, String heure_open, double latitude, double longitude, String nom, String rank, int serie_id, int zone_id) {
        this.id = id;
        this.adresse = adresse;
        this.heure_close = heure_close;
        this.heure_open = heure_open;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nom = nom;
        this.rank = rank;
        this.serie_id = serie_id;
        this.zone_id = zone_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getHeure_close() {
        return heure_close;
    }

    public void setHeure_close(String heure_close) {
        this.heure_close = heure_close;
    }

    public String getHeure_open() {
        return heure_open;
    }

    public void setHeure_open(String heure_open) {
        this.heure_open = heure_open;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getSerie_id() {
        return serie_id;
    }

    public void setSerie_id(int serie_id) {
        this.serie_id = serie_id;
    }

    public int getZone_id() {
        return zone_id;
    }

    public void setZone_id(int zone_id) {
        this.zone_id = zone_id;
    }
}
