package com.galvanize.guestbook;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class GuestBookController {

    GuestBookService guestBookService;

    public GuestBookController(GuestBookService guestBookService) {
        this.guestBookService = guestBookService;
    }

    @GetMapping("entries")
    public String getEntries() {
        return guestBookService.getEntries();
    }

    @PostMapping("entries")
    @ResponseStatus(HttpStatus.CREATED)
    public EntryDto postEntries(@RequestBody EntryDto entry) {
        guestBookService.saveEntry(entry);
        return entry;
    }

}
