package com.galvanize.guestbook;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GuestBookServiceTests {

    @Mock
    GuestBookRepository mockGuestBookRepository;

    @InjectMocks
    GuestBookService service;

    @Test
    void saveEntry() {
        EntryDto entryDto = new EntryDto("Name1", "Comment1");
        service.saveEntry(entryDto);
        verify(mockGuestBookRepository).save(
                new EntryEntity("Name1", "Comment1")
        );
    }
}
