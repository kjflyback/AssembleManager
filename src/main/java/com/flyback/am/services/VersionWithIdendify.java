package com.flyback.am.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


// @JsonSerialize(using = VWIJson.class)
public class VersionWithIdendify{
    private String identify;
    private String version;
    private String description = "";


    public VersionWithIdendify(String id, String version){
        this.identify = id;
        this.version = version;
    }
    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}