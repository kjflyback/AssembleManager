package com.flyback.am.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CommandLineResponse {
    public static List<String> GetCommandLineResponseList(String cmd){
        String[] cmdStr = {"/bin/sh","-c", cmd};
        Runtime run = Runtime.getRuntime();
        List<String> retResult = new ArrayList<>();
        try {
			Process process = run.exec(cmdStr);
			
            InputStream in = process.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(reader);
			
            // StringBuffer sb = new StringBuffer();
            String message;
            while((message = br.readLine()) != null) {
                retResult.add(message);
            }
			process.waitFor();
			
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return retResult;
    }
}