package com.vladik_bakalo.mapsdownloader.rest;

import android.util.Xml;

import com.vladik_bakalo.mapsdownloader.dummy.Region;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Владислав on 10.05.2017.
 */

public class RegionXmlParser {
    public RegionXmlParser() {
    }
    private static final String ns = null;

    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readData(parser);
        } finally {
            in.close();
        }
    }

    private List<Region> readData(XmlPullParser parser) throws IOException, XmlPullParserException {
        List<Region> entries = new ArrayList<Region>();

        parser.require(XmlPullParser.START_TAG, ns, "regions_list");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the region tag
            if (name.equals("region")) {
                entries.add(readRegion(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }
    private Region readRegion(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "region");
        Region region = new Region();
        if (parser.getAttributeValue(null, "type") != null)
        {
            region.setType(parser.getAttributeValue(null, "type"));
        }
        if (parser.getAttributeValue(null, "map") != null)
        {
            if (parser.getAttributeValue(null, "map").equals("yes"))
                region.setHasMapForDownload(true);
        }
        region.setName(parser.getAttributeValue(null, "name"));
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("region")) {
                region.getRegionsList().add(readRegion(parser));
            } else {
                skip(parser);
            }
        }
        return region;
    }
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

}
