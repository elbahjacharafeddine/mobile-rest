package com.project.retrofit.model;

import java.util.Date;

public class Restaurant {

    private Long id;
    private String nom;
    private String adresse;
    private double latitude;
    private double longitude;
    private String rank;


    private String heureOpen;
    private String heureClose;



    //Serie


    private Serie serie;

    private Zone zone;

    public Restaurant(Long id, String nom, String adresse, double latitude, double longitude, String rank, String heureOpen, String heureClose, Serie serie, Zone zone) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rank = rank;
        this.heureOpen = heureOpen;
        this.heureClose = heureClose;
        this.serie = serie;
        this.zone = zone;
    }

    public Restaurant(String nom, String adresse, double latitude, double longitude, String rank, String heureOpen, String heureClose, Serie serie, Zone zone) {
        this.nom = nom;
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rank = rank;
        this.heureOpen = heureOpen;
        this.heureClose = heureClose;
        this.serie = serie;
        this.zone = zone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getHeureOpen() {
        return heureOpen;
    }

    public void setHeureOpen(String heureOpen) {
        this.heureOpen = heureOpen;
    }

    public String getHeureClose() {
        return heureClose;
    }

    public void setHeureClose(String heureClose) {
        this.heureClose = heureClose;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
