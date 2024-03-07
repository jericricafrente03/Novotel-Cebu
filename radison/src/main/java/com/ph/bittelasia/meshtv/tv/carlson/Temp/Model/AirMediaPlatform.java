package com.ph.bittelasia.meshtv.tv.carlson.Temp.Model;

/**
 * Created by ramil on 2/5/18.
 */


public class AirMediaPlatform {
    String   name;
    int      image;

    public AirMediaPlatform(String name,int image)
    {
        this.name=name;
        this.image=image;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
