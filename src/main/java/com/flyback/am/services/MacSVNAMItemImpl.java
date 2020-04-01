package com.flyback.am.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MacSVNAMItemImpl extends AMItem {

    private Date lastUpdate = new Date();
    private List<VersionWithIdendify> cacheVWI = new ArrayList<>();

    @Override
    public List<VersionWithIdendify> getProjectVersions() {
        return cacheVWI;
    }

    @Override
    public void UpdateCache() {
        List<String> lines = CommandLineResponse
                .GetCommandLineResponseList("svn log " + getRemotePath());

        cacheVWI.clear();

        int index = 0;
        String id = "";
        String commit = "";
        String time = "";
        for(int i = 0;i<lines.size();i++){
            if(index == 0 && lines.get(i).indexOf("r") == 0){
                index = 1;
                String [] s = lines.get(i).split("\\|");
                id = s[0].trim();
                time = s[2].trim();
            }else if(index == 1 && lines.get(i).indexOf("-----") != 0){
                commit += lines.get(i);
            }else if(index == 1){
                index = 0;
                // parse commit to version and description
                if(commit.length() > 10)
                    commit = commit.substring(0, 10) + "...";
                
                if(time != null){
                    String [] t = time.split(" ");
                    time = t[0] + " " + t[1];
                }
                VersionWithIdendify vwiOne = new VersionWithIdendify(id, commit);
                vwiOne.setDescription(time);
                cacheVWI.add(vwiOne);
                commit = "";
                id = "";
                time = "";
            }
        }
    }

    @Override
    public Boolean CheckIsPathValid() {
        System.out.println(getRemotePath());
        System.out.println("before call getcommandline");
        String cmdLine = "svn info " + getRemotePath() + " |grep -n '^URL:'";
        List<String> lines = CommandLineResponse.GetCommandLineResponseList(cmdLine);

        lines.forEach(l -> {
            System.out.println(l);
        });
        System.out.println(lines.size());
        if (lines.size() > 0) {
            String[] ret = lines.get(0).split(":");
            for (int i = 0; i < ret.length; i++) {
                System.out.println("--------" + Integer.toString(i) + ":" + ret[i]);
            }
            if (ret[1].trim().equals("URL")) {
                System.out.println(" return true");
                return true;

            }
        }
        return false;
    }

    @Override
    public AMCompileResult Compile(String versionIdentify) {
        // versionId should be a x.x.x.x

        return super.Compile(versionIdentify);
    }


    

}