package org.sid.customerservice.web;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CustomerConfigTestController {
    @Value("${global.param.p1}") // Pour injecter les variables de paramètre existant dans le fichier de configuration
    private  String p1;
    @Value("${global.param.p2}")
    private String p2;

    @Value("${customer.params.x}")
    private String X;

    @Value("${customer.params.y}")
    private String Y;

    /*
     function qui va returner les parameters à partir de fichier de configuration
     globale et le fichier de configuration par defaut de customer-service
     */

    @GetMapping("/params")
    public Map<String,String> params(){
        return Map.of("p1",p1,"p2",p2,"x",X,"y",Y);
    }
}
