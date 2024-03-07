package com.ph.bittelasia.meshtv.tv.carlson.Temp.Model;

/**
 * Created by ramil on 1/24/18.
 */


public enum MeshTVButtons {


    SEARCH("SEARCH"),
    MYVIDEOS("MYVIDEOS"),
    PREVIEW("PREVIEW"),
    RENT("RENT"),
    OPTION("OPTION"),
    SAVE("SAVE"),
    CHECKOUT("CHECKOUT"),
    REQUEST("REQUEST"),
    ADDORDERS("ADDORDERS"),
    VIEWORDERS("VIEWORDERS"),
    RESERVE("RESERVE"),
    RESERVATIONS("RESERVATIONS"),
    FILTER("FILTER"),
    DELETE("DELETE"),
    TEMP("TEMP");

    private String button;

    public String getButton() {
        return button;
    }

    MeshTVButtons(String button)
    {
        this.button=button;
    }
}
