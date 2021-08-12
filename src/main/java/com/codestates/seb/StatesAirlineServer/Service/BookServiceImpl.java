package com.codestates.seb.StatesAirlineServer.Service;

import com.codestates.seb.StatesAirlineServer.Domain.BookDTO;
import com.codestates.seb.StatesAirlineServer.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> SearchAll() {

        return bookRepository.FindAll();
    }

    @Override
    public List<BookDTO> SearchByFlightUuid(String uuid) {

        return bookRepository.FindByUuid(uuid);
    }

    @Override
    public List<BookDTO> SearchByPhone(String phone) {

        return bookRepository.FindByPhone(phone);
    }

    @Override
    public BookDTO SaveBook(BookDTO data) {

        return bookRepository.Save(data);
    }

    @Override
    public List<BookDTO> DeleteByPhone(String phone) {

        return bookRepository.Delete(phone);
    }
}
