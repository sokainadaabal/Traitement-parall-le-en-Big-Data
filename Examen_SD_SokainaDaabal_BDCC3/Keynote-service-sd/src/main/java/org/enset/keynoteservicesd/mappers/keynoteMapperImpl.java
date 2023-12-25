package org.enset.keynoteservicesd.mappers;

import org.enset.keynoteservicesd.dtos.KeynoteDTO;
import org.enset.keynoteservicesd.entities.Keynote;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class keynoteMapperImpl {
    public KeynoteDTO fromKeynote(Keynote keynote){
        KeynoteDTO keynoteDTO = new KeynoteDTO();
        keynoteDTO.setKeynoteId(keynote.getId());
        BeanUtils.copyProperties(keynote,keynoteDTO);
        return  keynoteDTO;
    }
    public Keynote fromKeynoteDTO(KeynoteDTO keynoteDto){
        Keynote keynote = new Keynote();
        BeanUtils.copyProperties(keynoteDto,keynote);
        return  keynote;
    }
}
