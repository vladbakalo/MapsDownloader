package com.vladik_bakalo.mapsdownloader.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Region {
    private String name;
    private String type;
    private boolean isHasMapForDownload = false;
    private List<Region> regionsList;
    private boolean isDownloaded = false;

    public Region() {
        regionsList = new ArrayList<Region>();
    }

    public List<Region> getRegionsList() {
        return regionsList;
    }

    public void setRegionsList(List<Region> regionsList) {
        this.regionsList = regionsList;
    }

    public String getName() {
        char[] nameArr = name.toCharArray();
        nameArr[0] = Character.toUpperCase(nameArr[0]);
        return String.valueOf(nameArr);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }

    public boolean isHasMapForDownload() {
        return isHasMapForDownload;
    }

    public void setHasMapForDownload(boolean hasMapForDownload) {
        isHasMapForDownload = hasMapForDownload;
    }
}
