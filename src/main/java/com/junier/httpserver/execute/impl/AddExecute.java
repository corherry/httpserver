package com.junier.httpserver.execute.impl;

import com.junier.httpserver.execute.Execute;
import com.junier.httpserver.model.Book;

import java.util.Map;

public class AddExecute implements Execute{

    public String ececute(Map<String, Book> map, Book book) {
        String id = book.getId();
        if(map.containsKey(id)){
            return "图书已存在";
        }
        map.put(id, book);
        return "添加成功";
    }
}
