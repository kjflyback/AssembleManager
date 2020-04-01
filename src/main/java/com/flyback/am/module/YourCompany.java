package com.flyback.am.module;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(encoding = "UTF-8", ignoreResourceNotFound = false, value={"cn.properties"})
@ConfigurationProperties(prefix = "yourcompany")
public class YourCompany{
    private String title;
    private String dbpath;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDbpath() {
        return dbpath;
    }

    public void setDbpath(String dbpath) {
        this.dbpath = dbpath;
    }
}