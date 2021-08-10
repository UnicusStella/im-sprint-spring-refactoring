package com.codestates.seb.StatesAirlineServer.Data;

import java.util.ArrayList;
import java.util.List;

import com.codestates.seb.StatesAirlineServer.Domain.BookDTO;

public class BookData {
    private static final BookData instance = new BookData();
    private List<BookDTO> BookList = new ArrayList<>();

    public static BookData getInstance() {
        return instance;
    }

    public List<BookDTO> getBookList() {
        return BookList;
    }

    public void setBookList(List<BookDTO> bookList) {
        BookList = bookList;
    }

    private BookData(){
        
    }
}
