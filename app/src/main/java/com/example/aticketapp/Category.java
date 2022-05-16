package com.example.aticketapp;

public class Category {
    public String idCategorie, denumire, imagineCategorie;

    public Category(){

    }

    public Category(String idCategorie, String denumire, String imagineCategorie) {
        this.idCategorie = idCategorie;
        this.denumire = denumire;
        this.imagineCategorie = imagineCategorie;
    }

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getImagineCategorie() {
        return imagineCategorie;
    }

    public void setImagineCategorie(String imagineCategorie) {
        this.imagineCategorie = imagineCategorie;
    }
}
