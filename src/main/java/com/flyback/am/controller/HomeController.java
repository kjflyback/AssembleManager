package com.flyback.am.controller;

import java.util.Base64;
import java.util.List;

import com.flyback.am.module.YourCompany;
import com.flyback.am.services.AMDataBase;
import com.flyback.am.services.AMItem;
import com.flyback.am.services.OSFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class HomeController{

    @Autowired
    private YourCompany yc;


    @Autowired
    private AMDataBase db;


    
    @RequestMapping("/")
    public String home(Model mod){
        // System.out.println(yc.getTitle());
        mod.addAttribute("title", yc.getTitle());

        return "index";
    }

    @RequestMapping(value = {"/login", "/index.html"})
    public String login(Model mod){
        mod.addAttribute("title", yc.getTitle());
        return "main";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = {"/mgr"})
    public String mgr(RedirectAttributes rmod, Model mod){
        mod.addAttribute("title", yc.getTitle());

        List<AMItem> items = (List<AMItem>)db.getItems();        
        mod.addAttribute("items", items);
        
        String errMsg = (String)rmod.getAttribute("errmsg");
        if(errMsg != null){
            mod.addAttribute("errmsg", errMsg);
        }
        return "prjMgr";
    }

    @RequestMapping(value = {"/newProject"} ,method= RequestMethod.GET)
    public String newProject(
        @RequestParam("prjName") String prjName, 
        @RequestParam("remotepath") String remotePath, 
        @RequestParam("command") String command,
        RedirectAttributes model){
        AMItem item = OSFactory.createAMItem();
        // System.out.println(prjName + ":" + remotePath);
        item.setProjectName(prjName);
        item.setRemotePath(remotePath); 
        item.setCompileCommandLine(command);
        if(item.CheckIsPathValid())
            db.addItem(item);
        else{
            model.addFlashAttribute("errmsg", "can not locate " + remotePath );
        }
        
        return "redirect:/mgr";
    }
}