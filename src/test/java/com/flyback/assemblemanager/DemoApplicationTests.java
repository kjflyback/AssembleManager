package com.flyback.assemblemanager;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flyback.am.services.AMDataBase;
import com.flyback.am.services.AMDataBaseImpl;
import com.flyback.am.services.AMItem;
import com.flyback.am.services.CommandLineResponse;
import com.flyback.am.services.MacSVNAMItemImpl;
import com.flyback.am.services.VersionWithIdendify;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;



class DemoApplicationTests {

	@Test
	void SvnValidPath(){
		List<String> lines = CommandLineResponse.GetCommandLineResponseList("svn info svn://localhost |grep -n '^URL:'");
		lines.forEach(line -> {
			System.out.println(line);
		});
		assertTrue(lines.size() > 0, "no console return");
	}
	@Test
	void SvnInValidPath(){
		List<String> lines = CommandLineResponse.GetCommandLineResponseList("svn info svn://localhost/login2 |grep -n '^URL:'");
		assertTrue(lines.size() == 0, "can not check a invalid path to remote svn url");
	}

	@Test
	void SvnLogList(){
		
			AMItem item =  new MacSVNAMItemImpl();
			item.setProjectName("bho");
			item.setRemotePath("svn://localhost/bho");
            item.UpdateCache();
			List<VersionWithIdendify> vers = item.getProjectVersions();
			
			System.out.println( vers.get(0));
            		
	}

	@Test
	void Stringsplit(){
		String tmp = "r323 | 2323423| 232353423 | 2342343 |";
		String [] t = tmp.split("\\|");
		System.out.println(t[0]);
	}
}
