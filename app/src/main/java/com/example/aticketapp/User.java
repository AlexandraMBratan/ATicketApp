package com.example.aticketapp;

public class User {
    public String nume, prenume, varsta, telefon, codPostal,email,parola;
    public String esteAdmin;

    public User(){

    }

    public User(String nume,String prenume,String varsta,String telefon,String codPostal,String email,String parola){
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.telefon = telefon;
        this.codPostal = codPostal;
        this.email = email;
        this.parola = parola;
        esteAdmin = "false";
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getVarsta() {
        return varsta;
    }

    public void setVarsta(String varsta) {
        this.varsta = varsta;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getEsteAdmin(){
        return esteAdmin;
    }

    public void setEsteAdmin(String esteAdmin){
        this.esteAdmin=esteAdmin;
    }

}
