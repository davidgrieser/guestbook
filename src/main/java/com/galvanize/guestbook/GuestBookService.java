package com.galvanize.guestbook;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestBookService {
    GuestBookRepository guestBookRepository;

    public GuestBookService(GuestBookRepository guestBookRepository) {
        this.guestBookRepository = guestBookRepository;
    }

    public void saveEntry(EntryDto entryDto) {
        guestBookRepository.save(new EntryEntity(entryDto.getName(), entryDto.getComment()));
    }

    public List<EntryDto> getEntries() {
        return guestBookRepository.findAll()
                .stream()
                .map(entryEntity -> {
                    return new EntryDto(entryEntity.getName(), entryEntity.getComment());
                })
                .collect(Collectors.toList());
    }
}
