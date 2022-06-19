package com.example.aticketapp;

public class Purchase {

    private String idPurchase, idEvent, idUser, numeEveniment, artistEveniment, locatieEveniment, imagineEveniment, cantitateBilete , status, pretTotal;
    private String descriereEveniment, oraEveniment, dataEveniment, pretBiletEveniment;


    public Purchase(){

    }

    public Purchase(String idPurchase, String idEvent, String idUser, String numeEveniment, String artistEveniment, String locatieEveniment, String imagineEveniment, String cantitateBilete, String status, String pretTotal, String descriereEveniment, String oraEveniment, String dataEveniment, String pretBiletEveniment) {
        this.idPurchase = idPurchase;
        this.idEvent = idEvent;
        this.idUser = idUser;
        this.numeEveniment = numeEveniment;
        this.artistEveniment = artistEveniment;
        this.locatieEveniment = locatieEveniment;
        this.imagineEveniment = imagineEveniment;
        this.cantitateBilete = cantitateBilete;
        this.status = status;
        this.pretTotal = pretTotal;
        this.descriereEveniment = descriereEveniment;
        this.oraEveniment = oraEveniment;
        this.dataEveniment = dataEveniment;
        this.pretBiletEveniment = pretBiletEveniment;
    }

    public String getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(String idPurchase) {
        this.idPurchase = idPurchase;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getCantitateBilete() {
        return cantitateBilete;
    }

    public void setCantitateBilete(String cantitateBilete) {
        this.cantitateBilete = cantitateBilete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPretTotal() {
        return pretTotal;
    }

    public void setPretTotal(String pretTotal) {
        this.pretTotal = pretTotal;
    }

    public String getNumeEveniment() {
        return numeEveniment;
    }

    public void setNumeEveniment(String numeEveniment) {
        this.numeEveniment = numeEveniment;
    }

    public String getArtistEveniment() {
        return artistEveniment;
    }

    public void setArtistEveniment(String artistEveniment) {
        this.artistEveniment = artistEveniment;
    }

    public String getLocatieEveniment() {
        return locatieEveniment;
    }

    public void setLocatieEveniment(String locatieEveniment) {
        this.locatieEveniment = locatieEveniment;
    }

    public String getImagineEveniment() {
        return imagineEveniment;
    }

    public void setImagineEveniment(String imagineEveniment) {
        this.imagineEveniment = imagineEveniment;
    }

    public String getDescriereEveniment() {
        return descriereEveniment;
    }

    public void setDescriereEveniment(String descriereEveniment) {
        this.descriereEveniment = descriereEveniment;
    }

    public String getOraEveniment() {
        return oraEveniment;
    }

    public void setOraEveniment(String oraEveniment) {
        this.oraEveniment = oraEveniment;
    }

    public String getDataEveniment() {
        return dataEveniment;
    }

    public void setDataEveniment(String dataEveniment) {
        this.dataEveniment = dataEveniment;
    }

    public String getPretBiletEveniment() {
        return pretBiletEveniment;
    }

    public void setPretBiletEveniment(String pretBiletEveniment) {
        this.pretBiletEveniment = pretBiletEveniment;
    }
}

