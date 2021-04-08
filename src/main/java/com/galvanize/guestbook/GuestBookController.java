package com.galvanize.guestbook;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestBookController {

    @GetMapping("entries")
    public String getEntries() {
        return "[]";
    }

}
