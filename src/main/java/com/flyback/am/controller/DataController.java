package com.flyback.am.controller;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.SealedObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyback.am.services.AMDataBase;
import com.flyback.am.services.AMItem;
import com.flyback.am.services.VersionWithIdendify;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class DataController {

    @Autowired
    private AMDataBase db;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = { "/version/{prj}" })
    public Object getVersions(@PathVariable("prj") String prjName) throws JsonProcessingException {
        System.out.println(prjName);
        Object obj = db.getItem(prjName);
        System.out.println(obj);
        if (obj == null)
            return "{}";

        AMItem item = (AMItem) obj;
        item.UpdateCache();
        List<VersionWithIdendify> vers = item.getProjectVersions();

        
        List<String> ls = new ArrayList<>();
        ObjectMapper om = new ObjectMapper();
        for(int i = 0;i<vers.size();i++){
            ls.add( om.writeValueAsString(vers.get(i)));
        }
        String ret = "[" + StringUtils.join(ls, ',') + "]";
        return ret;

    }
}