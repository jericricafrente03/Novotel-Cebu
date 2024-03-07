package com.ph.bittelasia.mesh.tv.libFragment.control.model;

import java.io.Serializable;

public class CartItem implements Serializable {

    private int id ;
    private int quantity;
    private AddCart addCart;
    private boolean isBought;

    public CartItem(Builder builder){
        this.id= builder.id;
        this.quantity=builder.quantity;
        this.addCart=builder.addCart;
        this.isBought=builder.isBought;
    }


    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public AddCart getAddCart() {
        return addCart;
    }

    public boolean isBought() {
        return isBought;
    }

    public static class Builder{

        private int id ;
        private int quantity;
        private AddCart addCart;
        private boolean isBought;

        public Builder id(int id){
            this.id=id;
            return this;
        }

        public Builder quantity(int quantity){
            this.quantity=quantity;
            return this;
        }

        public Builder cart(AddCart addCart){
            this.addCart=addCart;
            return this;
        }

        public Builder isBought(boolean isBought){
            this.isBought=isBought;
            return this;
        }
        public CartItem buildCartItem(){
            return new CartItem(this);
        }
    }
}
