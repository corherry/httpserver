package com.junier.httpserver.execute;

import com.junier.httpserver.model.Book;

import java.util.Map;

public interface Execute {

    String ececute(Map<String, Book> map, Book book);
}
