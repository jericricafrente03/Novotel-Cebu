package com.ph.bittelasia.mesh.tv.libFragment.control.model;

import androidx.annotation.NonNull;
import java.io.Serializable;

@SuppressWarnings("rawtypes")
public class AddCart implements Serializable,Comparable<AddCart> {

    private int id;
    private String name;
    private String desc;
    private String imagePath;
    private String priceFormat;
    private Class  aClass;
    private double price;


    public AddCart(Builder builder){
        this.id=builder.id;
        this.name=builder.name;
        this.imagePath = builder.imagePath;
        this.desc=builder.desc;
        this.priceFormat = builder.priceFormat;
        this.aClass = builder.aClass;
        this.price=builder.price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Class getaClass() {
        return aClass;
    }

    public String getDesc() {
        return desc;
    }

    public String getPriceFormat() {
        return priceFormat;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "AddCart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", priceFormat='" + priceFormat + '\'' +
                ", aClass=" + aClass +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(AddCart addCart) {
        return (int) (this.price-addCart.price);
    }

    @SuppressWarnings("rawtypes")
    public static class Builder{

        private int id;
        private String name;
        private String desc;
        private String imagePath;
        private String priceFormat;
        private Class  aClass;
        private double price;

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder imagePath(String imagePath){
            this.imagePath=imagePath;
            return this;
        }

        public Builder desc(String desc){
            this.desc = desc;
            return this;
        }

        public Builder aClass(Class aClass){
            this.aClass=aClass;
            return this;
        }

        public Builder price(double price,@NonNull String priceFormat){
            this.price = price;
            this.priceFormat = priceFormat;
            return this;
        }

        public AddCart buildCart(){
            return new AddCart(this);
        }

    }
}
