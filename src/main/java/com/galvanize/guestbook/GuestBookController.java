package com.galvanize.guestbook;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class GuestBookController {

    @GetMapping("entries")
    public String getEntries() {
        return "[]";
    }

    @PostMapping("entries")
    @ResponseStatus(HttpStatus.CREATED)
    public EntryDto postEntries(@RequestBody EntryDto entry) {
        return entry;
    }

}
