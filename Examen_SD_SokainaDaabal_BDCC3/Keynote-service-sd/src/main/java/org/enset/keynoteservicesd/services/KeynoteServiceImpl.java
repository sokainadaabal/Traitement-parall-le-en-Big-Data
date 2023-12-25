package org.enset.keynoteservicesd.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.enset.keynoteservicesd.dtos.KeynoteDTO;
import org.enset.keynoteservicesd.entities.Keynote;
import org.enset.keynoteservicesd.excpetions.keynoteNotExiste;
import org.enset.keynoteservicesd.mappers.keynoteMapperImpl;
import org.enset.keynoteservicesd.repositories.KeynoteRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j  // system de journalisation  pour logger vos message
public class KeynoteServiceImpl implements KeynoteService{

    private KeynoteRepository keynoteRepository;
    private keynoteMapperImpl keynoteMapperImppl;
    @Override
    public KeynoteDTO saveKeynote(KeynoteDTO keynoteDTO) {
        log.info("Saving new Keynote");
        Keynote keynote = keynoteMapperImppl.fromKeynoteDTO(keynoteDTO);
        Keynote saveKeynote= keynoteRepository.save(keynote);
        return keynoteMapperImppl.fromKeynote(saveKeynote);
    }

    @Override
    public List<KeynoteDTO> searchKeynote(String keyword) {
        return null;
    }

    @Override
    public List<KeynoteDTO> getAllKeynoteDto() {
        List<Keynote> keynotes= keynoteRepository.findAll();
        return keynotes.stream().map(customer -> keynoteMapperImppl.fromKeynote(customer)).collect(Collectors.toList());
    }

    @Override
    public KeynoteDTO getKeynote(Long keynoteId) throws keynoteNotExiste {
        return null;
    }

    @Override
    public KeynoteDTO updateKeynote(KeynoteDTO keynoteDTO) throws keynoteNotExiste {
        return null;
    }

    @Override
    public void deleteKeyNote(Long keynoteId) {

    }
}
