package com.junier.httpserver.execute.impl;

import com.junier.httpserver.execute.Execute;
import com.junier.httpserver.model.Book;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class DeleteExecute implements Execute {

    public String ececute(Map<String, Book> map, Book book) {
        String id = book.getId();
        String name = book.getName();
        String author = book.getAuthor();
        String price = book.getPrice();
        int count = (StringUtils.isBlank(name) ? 0 : 1) + (StringUtils.isBlank(author) ? 0 : 1) + (StringUtils.isBlank(price) ? 0 : 1);
        if(StringUtils.isBlank(id) == false){
            map.remove(id);
            return "删除成功";
        }
        int deleteCount = 0;
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
                map.remove(book1.getId());
                deleteCount++;
            }
        }
        if(deleteCount == 0){
            return "不存在该图书";
        }else{
            return "删除了"+deleteCount+"本书";
        }
    }
}
