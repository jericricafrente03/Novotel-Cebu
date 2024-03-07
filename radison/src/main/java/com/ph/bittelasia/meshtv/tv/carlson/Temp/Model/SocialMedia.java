package com.ph.bittelasia.meshtv.tv.carlson.Temp.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by ramil on 12/11/17.
 */

public class SocialMedia {

    private String   package_name;
    private String   title;
    private Drawable icon;



    public SocialMedia(Drawable drawable, String title, String package_name)
    {
        this.icon=drawable;
        this.title=title;
        this.package_name=package_name;
    }


    public Drawable getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getPackage_name() {
        return package_name;
    }
}
