package com.flyback.am.services;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.flyback.am.module.YourCompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AMDataBaseImpl implements AMDataBase {

    @Autowired
    private YourCompany yc;

    private List<AMItem> items = new ArrayList<AMItem>();

    @PostConstruct
    public void init() {
        this.Initialize();
    }

    @Override
    public void addItem(AMItem item) {
        items.add(item);
        saveToFile();
    }

    @Override
    public void Initialize() {
        // 保存到当前目录下
        loadFromFile();
    }

    @Override
    public void saveToFile() {
        try {
            File f = new File(yc.getDbpath() + "/db.xml");

            if(!f.exists()){
                f.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, false));
            String xml = formatXML(items);
            bw.write(xml);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(yc.getDbpath() + "/db.xml"));
            String content = "";
            StringBuilder sb = new StringBuilder();
            while (content != null) {
                content = reader.readLine();
                if (content == null)
                    break;
                sb.append(content.trim());
            }
            reader.close();
            items.clear();
            items = parserXML(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T parserXML(String xml) {
        ByteArrayInputStream in = new ByteArrayInputStream(xml.getBytes());
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(in));
        decoder.close();
        return (T) decoder.readObject();
    }

    public static <T> String formatXML(T entity) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(out));
        encoder.writeObject(entity);
        encoder.close();
        return out.toString();
    }

    @Override
    public Object getItem(String strProjectName) {
        loadFromFile();
        Optional<AMItem> it = items.stream().filter(item -> item.getProjectName().equals(strProjectName)).findFirst();
        return it.orElse(OSFactory.createAMItem());
    }

    @Override
    public Object getItems() {
        return items;
    }
}