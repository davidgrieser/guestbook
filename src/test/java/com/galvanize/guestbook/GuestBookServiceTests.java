package com.galvanize.guestbook;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    void getEntries() {
        EntryEntity firstEntryEntity = new EntryEntity("GetName1", "GetComment1");
        EntryEntity secondEntryEntity = new EntryEntity("GetName2", "GetComment2");

        when(mockGuestBookRepository.findAll()).thenReturn(
                List.of(firstEntryEntity, secondEntryEntity)
        );

        List<EntryDto> actual = service.getEntries();

        assertThat(actual).isEqualTo(
                List.of(
                         new EntryDto("GetName1", "GetComment1"),
                         new EntryDto("GetName2", "GetComment2")
                )
        );

    }
}
