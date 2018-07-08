package com.junier.httpserver.execute.impl;

import com.junier.httpserver.execute.Execute;
import com.junier.httpserver.model.Book;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class QueryExecute implements Execute {

    public String ececute(Map<String, Book> map, Book book) {


        Map<String, Book> resultMap = new HashMap<String, Book>();
        if(book == null){
            return map.toString();
        }
        String id = book.getId();
        String name = book.getName();
        String author = book.getAuthor();
        String price = book.getPrice();
        if(StringUtils.isBlank(id) == false){
            if(map.containsKey(id)){
                return map.get(id).toString();
            }else{
                return "图书不存在";
            }
        }else{
            int matchCount = 0;
            int count = (StringUtils.isBlank(name) ? 0 : 1) + (StringUtils.isBlank(author) ? 0 : 1) + (StringUtils.isBlank(price) ? 0 : 1);
            for(String key: map.keySet()){
                int flag = 0;
                Book book1 = map.get(key);
                if(!StringUtils.isBlank(name) && name.equals(book1.getName())){
                    flag++;
                }
                if(!StringUtils.isBlank(author) && author.equals(book1.getAuthor())){
                    flag++;
                }
                if(!StringUtils.isBlank(price) && price.equals(book1.getPrice())){
                    flag++;
                }
                if(flag == count){
                    resultMap.put(book1.getId(),book1);
                    matchCount++;
                }
            }
            if(matchCount == 0){
                return "不存在对应图书";
            }else{
                return resultMap.toString();
            }

        }
    }
}
