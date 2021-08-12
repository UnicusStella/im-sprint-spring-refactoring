package com.codestates.seb.StatesAirlineServer.Repository;

import com.codestates.seb.StatesAirlineServer.Data.BookData;
import com.codestates.seb.StatesAirlineServer.Domain.BookDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryImpl implements BookRepository{

    private final List<BookDTO> bookList = BookData.getInstance().getBookList();

    @Override
    public List<BookDTO> FindAll() {

        return bookList;
    }

    @Override
    public List<BookDTO> FindByUuid(String uuid) {

        return bookList
                .stream()
                .filter(item -> item.getFlight_uuid().equals(uuid))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> FindByPhone(String phone) {

        return bookList
                .stream()
                .filter(item -> item.getPhone().equals(phone))
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO Save(BookDTO data) {

        bookList.add(data);
        return data;
    }

    @Override
    public List<BookDTO> Delete(String phone) {

        return bookList
                .stream()
                .filter(item -> !item.getPhone().equals(phone))
                .collect(Collectors.toList());
    }
}
