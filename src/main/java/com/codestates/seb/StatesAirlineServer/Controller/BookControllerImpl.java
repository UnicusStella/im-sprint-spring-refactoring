package com.codestates.seb.StatesAirlineServer.Controller;

import com.codestates.seb.StatesAirlineServer.Domain.BookDTO;

import java.util.List;

public class BookControllerImpl implements BookController{

    @Override
    public List<BookDTO> FindBook(String flight_uuid, String phone) {
        return null;
    }

    @Override
    public BookDTO CreateBook(BookDTO createData) {
        return null;
    }

    @Override
    public List<BookDTO> DeleteByPhone(String phone) {
        return null;
    }
}
