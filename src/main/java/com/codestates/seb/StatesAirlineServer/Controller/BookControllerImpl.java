package com.codestates.seb.StatesAirlineServer.Controller;

import com.codestates.seb.StatesAirlineServer.Domain.BookDTO;
import com.codestates.seb.StatesAirlineServer.Service.BookService;
import com.codestates.seb.StatesAirlineServer.Service.BookServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @GetMapping(value = "/book")
    public List<BookDTO> FindBook(@RequestParam(required = false) String flight_uuid,
                                  @RequestParam(required = false) String phone) {

        if (flight_uuid != null) {
            return bookService.SearchByFlightUuid(flight_uuid);
        } else if (phone != null) {
            return bookService.SearchByPhone(phone);
        } else {
            return bookService.SearchAll();
        }
    }

    @Override
    @PostMapping(value = "/book")
    public BookDTO CreateBook(@RequestBody BookDTO createData) {

        return bookService.SaveBook(createData);
    }

    @Override
    @DeleteMapping(value = "/book")
    public List<BookDTO> DeleteByPhone(@RequestParam String phone) {

        return bookService.DeleteByPhone(phone);
    }
}
