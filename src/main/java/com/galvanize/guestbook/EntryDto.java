package com.galvanize.guestbook;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntryDto {
    String name;
    String comment;
}
