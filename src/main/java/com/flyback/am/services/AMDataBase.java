package com.flyback.am.services;


/**
 * 提供数据存储的接口
 */
public interface AMDataBase{
    void addItem(AMItem item);
    Object getItem(String strProjectName);
    void Initialize();
    void saveToFile();
    void loadFromFile();
    Object getItems();
}