package com.codestates.seb.StatesAirlineServer.Service;

import com.codestates.seb.StatesAirlineServer.Data.BookData;
import com.codestates.seb.StatesAirlineServer.Domain.BookDTO;

import java.util.List;

public class BookServiceImpl implements BookService{

    private List<BookDTO> bootList = BookData.getInstance().getBookList();

    @Override
    public List<BookDTO> SearchAll() {
        return null;
    }

    @Override
    public List<BookDTO> SearchByFlightUuid(String uuid) {
        return null;
    }

    @Override
    public List<BookDTO> SearchByPhone(String phone) {
        return null;
    }

    @Override
    public BookDTO SaveBook(BookDTO data) {
        return null;
    }

    @Override
    public List<BookDTO> DeleteByPhone(String phone) {
        return null;
    }
}
