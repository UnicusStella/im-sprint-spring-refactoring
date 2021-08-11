package com.codestates.seb.StatesAirlineServer.Controller;

import com.codestates.seb.StatesAirlineServer.Domain.BookDTO;
import com.codestates.seb.StatesAirlineServer.Service.BookServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookControllerImpl implements BookController {

    private final BookServiceImpl bookServiceImpl;

    public BookControllerImpl(BookServiceImpl bookServiceImpl) {

        this.bookServiceImpl = bookServiceImpl;
    }

    @Override
    @GetMapping(value = "/book")
    public List<BookDTO> FindBook(@RequestParam(required = false) String flight_uuid,
                                  @RequestParam(required = false) String phone) {

        if (flight_uuid != null) {
            return bookServiceImpl.SearchByFlightUuid(flight_uuid);
        } else if (phone != null) {
            return bookServiceImpl.SearchByPhone(phone);
        } else {
            return bookServiceImpl.SearchAll();
        }
    }

    @Override
    @PostMapping(value = "/book")
    public BookDTO CreateBook(@RequestBody BookDTO createData) {

        return bookServiceImpl.SaveBook(createData);
    }

    @Override
    @DeleteMapping(value = "/book")
    public List<BookDTO> DeleteByPhone(@RequestParam String phone) {

        return bookServiceImpl.DeleteByPhone(phone);
    }
}
