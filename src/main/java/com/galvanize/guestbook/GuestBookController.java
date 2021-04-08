package com.galvanize.guestbook;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestBookController {

    @GetMapping("entry")
    public String getEntries() {
        return "[]";
    }

}
