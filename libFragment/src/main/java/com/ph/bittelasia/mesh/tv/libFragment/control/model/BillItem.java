package com.ph.bittelasia.mesh.tv.libFragment.control.model;

import java.io.Serializable;

public class BillItem implements Serializable,Comparable<BillItem> {

    private int id;
    private String name;
    private double price;
    private int qty;
    private double time;
    private double total;
    private String image;

    public BillItem(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.qty = builder.qty;
        this.time = builder.time;
        this.total = builder.total;
        this.image = builder.image;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public double getTime() {
        return time;
    }

    public double getTotal() {
        return total;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "BillItem{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", qty=" + qty +
                ", time=" + time +
                ", total=" + total +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public int compareTo(BillItem billItem) {
        return (int) (this.price - billItem.price);
    }

    public static class Builder{

        private int id;
        private String name;
        private double price;
        private int qty;
        private double time;
        private double total;
        private String image;

        public Builder id(int id){
            this.id=id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder price(double price){
            this.price=price;
            return this;
        }

        public Builder qty(int qty){
            this.qty=qty;
            return this;
        }

        public Builder time(double time){
            this.time=time;
            return this;
        }

        public Builder total(double total){
            this.total=total;
            return this;
        }

        public Builder image(String image){
            this.image=image;
            return this;
        }

        public BillItem buildBillItem(){
            return new BillItem(this);
        }
    }
}
