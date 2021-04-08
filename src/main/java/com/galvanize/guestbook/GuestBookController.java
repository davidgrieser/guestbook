package com.galvanize.guestbook;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GuestBookController {

    GuestBookService guestBookService;

    public GuestBookController(GuestBookService guestBookService) {
        this.guestBookService = guestBookService;
    }

    @GetMapping("entries")
    public List<EntryDto> getEntries() {
        return guestBookService.getEntries();
    }

    @PostMapping("entries")
    @ResponseStatus(HttpStatus.CREATED)
    public EntryDto postEntries(@RequestBody EntryDto entry) {
        guestBookService.saveEntry(entry);
        return entry;
    }

}
