package org.enset.keynoteservicesd.services;

import org.enset.keynoteservicesd.dtos.KeynoteDTO;
import org.enset.keynoteservicesd.excpetions.keynoteNotExiste;

import java.util.List;

public interface KeynoteService {

    // creation Keynote
    KeynoteDTO saveKeynote(KeynoteDTO keynoteDTO);
    // recherché à partir son nom ou fonction
    List<KeynoteDTO> searchKeynote(String keyword);
    // recuperer la liste des keynote
    List<KeynoteDTO> getAllKeynoteDto();
    // recuperer keynote a partir de ID
      KeynoteDTO getKeynote(Long keynoteId) throws keynoteNotExiste;
    // modifier un keynote
      KeynoteDTO updateKeynote(KeynoteDTO keynoteDTO) throws keynoteNotExiste  ;
    // delete keynote
      void deleteKeyNote(Long keynoteId);

}
