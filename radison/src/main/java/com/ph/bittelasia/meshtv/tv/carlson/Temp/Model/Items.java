package com.ph.bittelasia.meshtv.tv.carlson.Temp.Model;

import java.util.Date;

/**
 * Created by ramil on 1/24/18.
 */

public class Items {

    private int id;
    private String title;
    private String city;
    private String country;
    private String number;
    private String carrier;
    private Date   date;
    private String status;
    private String min;
    private String code;


   public Items(){}


    public Items(int id, String title)
    {
        this.id=id;
        this.title=title;
    }

    public Items(String country, String city)
    {
        this.country=country;
        this.city=city;
    }

    public Items(int id, String country, String code)
    {
        this.id=id;
        this.country=country;
        this.code=code;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getNumber() {
        return number;
    }

    public String getCarrier() {
        return carrier;
    }

    public Date getDate() {
        return date;
    }

    public String getMin() {
        return min;
    }

    public String getStatus() {
        return status;
    }

    public String getCode() {return code;}

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMin(String min) {
        this.min = min;
    }

}
