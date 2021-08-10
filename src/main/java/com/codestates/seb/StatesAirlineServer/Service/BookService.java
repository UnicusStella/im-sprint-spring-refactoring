package com.codestates.seb.StatesAirlineServer.Service;

import com.codestates.seb.StatesAirlineServer.Domain.BookDTO;

import java.util.List;

public interface BookService {
    public List<BookDTO> SearchAll();
    public List<BookDTO> SearchByFlightUuid(String uuid);
    public List<BookDTO> SearchByPhone(String phone);
    public BookDTO SaveBook(BookDTO data);
    public List<BookDTO> DeleteByPhone(String phone);
}
