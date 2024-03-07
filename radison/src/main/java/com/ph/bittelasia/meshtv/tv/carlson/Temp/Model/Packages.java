package com.ph.bittelasia.meshtv.tv.carlson.Temp.Model;

import com.ph.bittelasia.meshtv.tv.carlson.Airmedia.View.Activity.CarlsonAirMedia;
import com.ph.bittelasia.meshtv.tv.carlson.Billing.View.Activity.CarlsonBilling;
import com.ph.bittelasia.meshtv.tv.carlson.Dining.View.Activity.CarlsonDining;
import com.ph.bittelasia.meshtv.tv.carlson.Facilities.View.Activity.CarlsonFacility;
import com.ph.bittelasia.meshtv.tv.carlson.FacilitiesV2.view.activity.FacilityActivity;
import com.ph.bittelasia.meshtv.tv.carlson.HotelInfo.View.Activity.HotelInfoActivity;
import com.ph.bittelasia.meshtv.tv.carlson.Message.View.Activity.CarlsonMessaging;
import com.ph.bittelasia.meshtv.tv.carlson.Movies.View.Activity.CarlsonVod;
import com.ph.bittelasia.meshtv.tv.carlson.Shopping.View.Activity.CarlsonShopping;
import com.ph.bittelasia.meshtv.tv.carlson.TV.View.Activity.CarlsonTV;
import com.ph.bittelasia.meshtv.tv.carlson.Weather.View.Activity.CarlsonWeather;
import com.ph.bittelasia.meshtv.tv.carlson.Website.View.Activity.CarlsonWebsite;


/**
 * Created by ramil on 2/5/18.
 */

public abstract class Packages {

    public static final String FACEBOOK="com.facebook.katana";
    public static final String ENEWS="com.foxnews.android";
    public static final String NETFLIX="com.netflix.mediaclient";
    public static final String TWITTER="com.twitter.android";
    public static final String SPOTIFY="com.spotify.music";
    public static final String AIRMEDIA="com.waxrain.airplaydmr";
    public static final String WEATHER = "com.yahoo.mobile.client.android.weather";
    public static final String YOUTUBE = "com.google.android.youtube.tv";

    public static final String FILENAME_FACEBOOK="facebook.apk";
    public static final String FILENAME_ENEWS="fox.apk";
    public static final String FILENAME_NETFLIX="netflix.apk";
    public static final String FILENAME_TWITTER="twitter.apk";
    public static final String FILENAME_SPOTIFY="spotify.apk";
    public static final String FILENAME_AIRMEDIA="airmedia.apk";

    public static final String DISPLAY_FACEBOOK="FACEBOOK";
    public static final String DISPLAY_ENEWS="ENEWS";
    public static final String DISPLAY_NETFLIX="NETFLIX";
    public static final String DISPLAY_TWITTER="TWITTER";
    public static final String DISPLAY_SPOTIFY="SPOTIFY";
    public static final String DISPLAY_AIRMEDIA= CarlsonAirMedia.class.getName();
    public static final String DISPLAY_WEBSITE = CarlsonWebsite.class.getName();
    public static final String DISPLAY_WEATHER= CarlsonWeather.class.getName();
    public static final String DISPLAY_MESSAGE= CarlsonMessaging.class.getName();
    public static final String DISPLAY_BILL= CarlsonBilling.class.getName();
    public static final String DISPLAY_FACILITY= CarlsonFacility.class.getName();
    public static final String DISPLAY_FACILITYV2= FacilityActivity.class.getName();
    public static final String DISPLAY_FACILITYV3= HotelInfoActivity.class.getName();
    public static final String DISPLAY_SHOPPING= CarlsonShopping.class.getName();
    public static final String DISPLAY_DINING= CarlsonDining.class.getName();
    public static final String DISPLAY_VOD= CarlsonVod.class.getName();
    public static final String DISPLAY_TV=CarlsonTV.class.getName();
    public static final String DISPLAY_TV2="com.ph.bittelasia.meshtv.tv.staticvlcplayer";
}
