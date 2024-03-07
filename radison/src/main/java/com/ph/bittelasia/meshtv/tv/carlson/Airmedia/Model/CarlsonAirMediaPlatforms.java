package com.ph.bittelasia.meshtv.tv.carlson.Airmedia.Model;

/**
 * Created by ramil on 12/27/17.
 */

public enum CarlsonAirMediaPlatforms {
    ANDROID("ANDROID"),
    IOS("IOS"),
    WINDOWS("WINDOWS");

    private String platform;


    public String getPlatform() {
        return platform;
    }

    CarlsonAirMediaPlatforms(String platform)
    {
        this.platform=platform;
    }

}
