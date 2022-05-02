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

    public String getEsteAdmin(){
        return esteAdmin;
    }

    public void setEsteAdmin(String esteAdmin){
        this.esteAdmin=esteAdmin;
    }

}
