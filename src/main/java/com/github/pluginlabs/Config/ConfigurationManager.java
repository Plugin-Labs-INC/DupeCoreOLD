package com.github.pluginlabs.Config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class ConfigurationManager {

    public Plugin plugin;
    public FileConfiguration config;

    public ConfigurationManager(Plugin plugin) {
        this.plugin = plugin;
        config = this.plugin.getConfig();
    }

    public void getNewConfig() {
        config = plugin.getConfig();
    }

    public void setDefaults() {
        plugin.saveDefaultConfig();
    }

    public void setConfigValue(String setting, Object value) {
        config.set(setting, value);
        saveConfig();
    }

    public void saveConfig() {
        try {
            plugin.saveConfig();
            Bukkit.getConsoleSender().sendMessage("Saved configuration file");
        } catch(Error e) {
            e.printStackTrace();
        }
    }

    public Boolean getConfigBoolean(String value) {
        Boolean returnValue;
        try{
            returnValue = config.getBoolean(value);
        }catch(Exception e){
            returnValue = null;
        }

        return returnValue;
    }

    public String getConfigString(String value) {
        String returnValue;
        try{
            returnValue = config.getString(value);
        }catch(Exception e){
            returnValue = null;
        }

        return returnValue;
    }

    public Double getConfigDouble(String value) {
        Double returnValue;
        try{
            returnValue = config.getDouble(value);
        }catch(Exception e){
            returnValue = null;
        }

        return returnValue;
    }

    public List getConfigList(String value) {
        List returnValue;
        try{
            returnValue = config.getList(value);
        }catch(Exception e){
            returnValue = null;
        }

        return returnValue;
    }
}
