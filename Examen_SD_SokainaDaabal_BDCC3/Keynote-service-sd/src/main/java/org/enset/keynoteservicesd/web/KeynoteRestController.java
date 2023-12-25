package org.enset.keynoteservicesd.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.enset.keynoteservicesd.dtos.KeynoteDTO;
import org.enset.keynoteservicesd.services.KeynoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/keynote")
@AllArgsConstructor
@CrossOrigin(value = "*",maxAge = 3600)
@Slf4j // les logs
public class KeynoteRestController {

    private KeynoteService keynoteService;

    @GetMapping("/findAll")
    public List<KeynoteDTO> keynoteDTOS(){
        return keynoteService.getAllKeynoteDto();
    }
    @PostMapping("/add")
    public KeynoteDTO saveKeynote(@RequestBody KeynoteDTO keynoteDTO){
        return keynoteService.saveKeynote(keynoteDTO);
    }
}
