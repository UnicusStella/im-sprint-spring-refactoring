package com.codestates.seb.StatesAirlineServer.Repository;

import com.codestates.seb.StatesAirlineServer.Domain.BookDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository{


    @Override
    public List<BookDTO> FindAll() {
        return null;
    }

    @Override
    public List<BookDTO> FindByUuid(String uuid) {
        return null;
    }

    @Override
    public List<BookDTO> FindByPhone(String phone) {
        return null;
    }

    @Override
    public BookDTO Save(BookDTO data) {
        return null;
    }

    @Override
    public List<BookDTO> Delete(String phone) {
        return null;
    }
}
