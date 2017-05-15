package com.vladik_bakalo.mapsdownloader.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Владислав on 12.05.2017.
 */

public class NetworkHelper {
    public static final String BASE_URL = "https://raw.githubusercontent.com/osmandapp/OsmAnd-resources/master/countries-info/regions.xml";

    public static InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        InputStream inputStream = conn.getInputStream();
        return inputStream;
    }
}
