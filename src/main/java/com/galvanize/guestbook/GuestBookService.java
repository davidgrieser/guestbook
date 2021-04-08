package com.galvanize.guestbook;

import org.springframework.stereotype.Service;

public class GuestBookService {
    GuestBookRepository guestBookRepository;

    public GuestBookService(GuestBookRepository guestBookRepository) {
        this.guestBookRepository = guestBookRepository;
    }

    public void saveEntry(EntryDto entryDto) {
        guestBookRepository.save(new EntryEntity(entryDto.getName(), entryDto.getComment()));
    }
}
