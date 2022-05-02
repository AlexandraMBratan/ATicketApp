package com.example.aticketapp;

public class User {
    public String surname,nameUser,ageUser,phone,postalCode,email,password;
    public boolean isAdmin;

    public User(){

    }

    public User(String surname,String nameUser,String ageUser,String phone,String postalCode,String email,String password){
        this.surname = surname;
        this.nameUser = nameUser;
        this.ageUser = ageUser;
        this.phone = phone;
        this.postalCode = postalCode;
        this.email = email;
        this.password = password;
    }

}
