package com.vladik_bakalo.mapsdownloader.storage;

import android.os.Environment;
import android.os.StatFs;

/**
 * Created by Владислав on 11.05.2017.
 */

public class StorageHelper {

    private String path;

    public StorageHelper(String path) {
        this.path = path;
    }

    public long getAvailableMemorySpace()
    {
        StatFs stat = new StatFs(path);
        return stat.getBlockSizeLong() * stat.getAvailableBlocksLong();
    }
    public long getTotalMemorySpace()
    {
        StatFs stat = new StatFs(path);
        return stat.getBlockSizeLong() * stat.getBlockCountLong();
    }
    public long getUsedMemorySpace()
    {
        return getTotalMemorySpace() - getAvailableMemorySpace();
    }
    public String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
    public static int safeLongToInt(long l) {
        return (int) Math.max(Math.min(Integer.MAX_VALUE, l), Integer.MIN_VALUE);
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
