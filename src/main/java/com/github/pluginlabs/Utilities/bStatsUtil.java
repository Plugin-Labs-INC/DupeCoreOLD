package com.github.pluginlabs.Utilities;
import org.bstats.bukkit.Metrics;
import com.github.pluginlabs.DupeCore;

public class bStatsUtil {
    private static Metrics metrics;
    private int pluginID = 10754;

    public bStatsUtil() {
        metrics = new Metrics(DupeCore.instance, pluginID);
    }
}
