package com.codestates.seb.StatesAirlineServer.Service;

import com.codestates.seb.StatesAirlineServer.Data.BookData;
import com.codestates.seb.StatesAirlineServer.Domain.BookDTO;
import com.codestates.seb.StatesAirlineServer.Repository.BookRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepositoryImpl bookRepositoryImpl;

    public BookServiceImpl(BookRepositoryImpl bookRepositoryImpl) {
        this.bookRepositoryImpl = bookRepositoryImpl;
    }

    @Override
    public List<BookDTO> SearchAll() {

        return bookRepositoryImpl.FindAll();
    }

    @Override
    public List<BookDTO> SearchByFlightUuid(String uuid) {

        return bookRepositoryImpl.FindByUuid(uuid);
    }

    @Override
    public List<BookDTO> SearchByPhone(String phone) {

        return bookRepositoryImpl.FindByPhone(phone);
    }

    @Override
    public BookDTO SaveBook(BookDTO data) {

        return bookRepositoryImpl.Save(data);
    }

    @Override
    public List<BookDTO> DeleteByPhone(String phone) {

        return bookRepositoryImpl.Delete(phone);
    }
}
