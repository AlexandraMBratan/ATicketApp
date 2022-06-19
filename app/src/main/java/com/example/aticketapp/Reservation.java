package com.example.aticketapp;

public class Reservation {
    private String idRezervation, idEventRezervat, idUserRezervat, numeEvenimentRezervat, artistEvenimentRezervat;
    private String locatieEvenimentRezervat,imagineEvenimentRezervat, cantitateBileteRezervat , status, pretTotalRezervat;
    private String descriereEvenimentRezervat, oraEvenimentRezervat, dataEvenimentRezervat, pretBiletEvenimentRezervat;

    public Reservation(){
    }

    public Reservation(String idRezervation, String idEventRezervat, String idUserRezervat, String numeEvenimentRezervat, String artistEvenimentRezervat, String locatieEvenimentRezervat, String imagineEvenimentRezervat, String cantitateBileteRezervat, String status, String pretTotalRezervat, String descriereEvenimentRezervat, String oraEvenimentRezervat, String dataEvenimentRezervat, String pretBiletEvenimentRezervat) {
        this.idRezervation = idRezervation;
        this.idEventRezervat = idEventRezervat;
        this.idUserRezervat = idUserRezervat;
        this.numeEvenimentRezervat = numeEvenimentRezervat;
        this.artistEvenimentRezervat = artistEvenimentRezervat;
        this.locatieEvenimentRezervat = locatieEvenimentRezervat;
        this.imagineEvenimentRezervat = imagineEvenimentRezervat;
        this.cantitateBileteRezervat = cantitateBileteRezervat;
        this.status = status;
        this.pretTotalRezervat = pretTotalRezervat;
        this.descriereEvenimentRezervat = descriereEvenimentRezervat;
        this.oraEvenimentRezervat = oraEvenimentRezervat;
        this.dataEvenimentRezervat = dataEvenimentRezervat;
        this.pretBiletEvenimentRezervat = pretBiletEvenimentRezervat;
    }

    public String getIdRezervation() {
        return idRezervation;
    }

    public void setIdRezervation(String idRezervation) {
        this.idRezervation = idRezervation;
    }

    public String getIdEventRezervat() {
        return idEventRezervat;
    }

    public void setIdEventRezervat(String idEventRezervat) {
        this.idEventRezervat = idEventRezervat;
    }

    public String getIdUserRezervat() {
        return idUserRezervat;
    }

    public void setIdUserRezervat(String idUserRezervat) {
        this.idUserRezervat = idUserRezervat;
    }

    public String getNumeEvenimentRezervat() {
        return numeEvenimentRezervat;
    }

    public void setNumeEvenimentRezervat(String numeEvenimentRezervat) {
        this.numeEvenimentRezervat = numeEvenimentRezervat;
    }

    public String getArtistEvenimentRezervat() {
        return artistEvenimentRezervat;
    }

    public void setArtistEvenimentRezervat(String artistEvenimentRezervat) {
        this.artistEvenimentRezervat = artistEvenimentRezervat;
    }

    public String getLocatieEvenimentRezervat() {
        return locatieEvenimentRezervat;
    }

    public void setLocatieEvenimentRezervat(String locatieEvenimentRezervat) {
        this.locatieEvenimentRezervat = locatieEvenimentRezervat;
    }

    public String getImagineEvenimentRezervat() {
        return imagineEvenimentRezervat;
    }

    public void setImagineEvenimentRezervat(String imagineEvenimentRezervat) {
        this.imagineEvenimentRezervat = imagineEvenimentRezervat;
    }

    public String getCantitateBileteRezervat() {
        return cantitateBileteRezervat;
    }

    public void setCantitateBileteRezervat(String cantitateBileteRezervat) {
        this.cantitateBileteRezervat = cantitateBileteRezervat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPretTotalRezervat() {
        return pretTotalRezervat;
    }

    public void setPretTotalRezervat(String pretTotalRezervat) {
        this.pretTotalRezervat = pretTotalRezervat;
    }

    public String getDescriereEvenimentRezervat() {
        return descriereEvenimentRezervat;
    }

    public void setDescriereEvenimentRezervat(String descriereEvenimentRezervat) {
        this.descriereEvenimentRezervat = descriereEvenimentRezervat;
    }

    public String getOraEvenimentRezervat() {
        return oraEvenimentRezervat;
    }

    public void setOraEvenimentRezervat(String oraEvenimentRezervat) {
        this.oraEvenimentRezervat = oraEvenimentRezervat;
    }

    public String getDataEvenimentRezervat() {
        return dataEvenimentRezervat;
    }

    public void setDataEvenimentRezervat(String dataEvenimentRezervat) {
        this.dataEvenimentRezervat = dataEvenimentRezervat;
    }

    public String getPretBiletEvenimentRezervat() {
        return pretBiletEvenimentRezervat;
    }

    public void setPretBiletEvenimentRezervat(String pretBiletEvenimentRezervat) {
        this.pretBiletEvenimentRezervat = pretBiletEvenimentRezervat;
    }
}
