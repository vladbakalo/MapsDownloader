package com.vladik_bakalo.mapsdownloader.dummy;

import java.util.List;

/**
 * Created by Владислав on 12.05.2017.
 */

public class RegionHelper {
    private static RegionHelper instance;
    private Region continent;
    private RegionHelper() {}
    public static synchronized RegionHelper getInstance()
    {
        if (instance == null)
        {
            instance = new RegionHelper();
        }
        return instance;
    }

    public Region getContinent() {
        return continent;
    }

    public void setContinent(Region continent) {
        this.continent = continent;
    }
}
