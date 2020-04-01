package com.flyback.am.services;

import java.util.List;

public class AMItem {
    
    private String projectName = "";
    private String remotePath = "";
    private String compileCommandLine = "";

    public
    List<VersionWithIdendify> getProjectVersions(){return null;}

    public
    void UpdateCache() {}

    public 
    AMCompileResult Compile(String versionIdentify){return null;}

    public
    Boolean CheckIsPathValid(){return false;}

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public String getCompileCommandLine() {
        return compileCommandLine;
    }

    public void setCompileCommandLine(String compileCommandLine) {
        this.compileCommandLine = compileCommandLine;
    }


   
}