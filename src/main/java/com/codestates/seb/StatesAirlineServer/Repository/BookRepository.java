package com.codestates.seb.StatesAirlineServer.Repository;

import com.codestates.seb.StatesAirlineServer.Domain.BookDTO;

import java.util.List;

public interface BookRepository {
    public List<BookDTO> FindAll();
    public List<BookDTO> FindByUuid(String uuid);
    public List<BookDTO> FindByPhone(String phone);
    public BookDTO Save(BookDTO data);
    public List<BookDTO> Delete(String phone);
}
