package org.enset.keynoteservicesd.dtos;

import lombok.Data;

@Data
public class KeynoteDTO {
    private Long keynoteId;
    private String firstName;
    private String lastName;
    private String function;
}
