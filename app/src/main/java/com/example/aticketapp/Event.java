package com.example.aticketapp;

public class Event {

    public String numeEveniment, artist, tip, data, ora, locatie, pret, cantitateTotala;
    public String imagine;

    public Event(){

    }

    public Event(String numeEveniment, String artist, String tip, String data, String ora, String locatie, String pret, String cantitateTotala, String imagine) {
        this.numeEveniment = numeEveniment;
        this.artist = artist;
        this.tip = tip;
        this.data = data;
        this.ora = ora;
        this.locatie = locatie;
        this.pret = pret;
        this.cantitateTotala = cantitateTotala;
        this.imagine = imagine;
    }

    public String getNumeEveniment() {
        return numeEveniment;
    }

    public void setNumeEveniment(String numeEveniment) {
        this.numeEveniment = numeEveniment;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getPret() {
        return pret;
    }

    public void setPret(String pret) {
        this.pret = pret;
    }

    public String getCantitateTotala() {
        return cantitateTotala;
    }

    public void setCantitateTotala(String cantitateTotala) {
        this.cantitateTotala = cantitateTotala;
    }

    public String getImagine() {
        return imagine;
    }

    public void setImagine(String imagine) {
        this.imagine = imagine;
    }
}
