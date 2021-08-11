package com.codestates.seb.StatesAirlineServer.Controller;

import com.codestates.seb.StatesAirlineServer.Domain.BookDTO;
import com.codestates.seb.StatesAirlineServer.Repository.BookRepositoryImpl;
import com.codestates.seb.StatesAirlineServer.Service.BookServiceImpl;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.List;

@RestController
public class BookControllerImpl implements BookController{

    private final BookServiceImpl bookServiceImpl;

    public BookControllerImpl(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

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
