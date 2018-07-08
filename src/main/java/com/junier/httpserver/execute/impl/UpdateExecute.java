package com.junier.httpserver.execute.impl;

import com.junier.httpserver.execute.Execute;
import com.junier.httpserver.model.Book;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class UpdateExecute implements Execute{

    public String ececute(Map<String, Book> map, Book book) {
        String id = book.getId();
        if(map.containsKey(id) == false){
            return "更新失败";
        }
        Book book1 = map.get(id);
        if(StringUtils.isBlank(book.getName()) == false){
            book1.setName(book.getName());
        }
        if(StringUtils.isBlank(book.getAuthor()) == false){
            book1.setAuthor(book.getAuthor());
        }
        if(StringUtils.isBlank(book.getPrice()) == false){
            book1.setPrice(book.getPrice());
        }
        map.remove(id);
        map.put(id, book1);
        return "更新成功";
    }
}
