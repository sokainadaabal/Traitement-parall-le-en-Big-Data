package org.sid.springcloudstream.services;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import org.sid.springcloudstream.entities.PageEvent;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class PageEventService {
    /*
     un service Consumer kafka
     */
    @Bean
    public Consumer<PageEvent> pageEventCustomer(){
        return (input) -> {
            System.out.println("***********************************");
            System.out.println(input.toString());
            System.out.println("***********************************");
        };
    }
    /*
    un service Supplier kafka
    */

    // pour chaque second, il produit un evenement
    // spring cloud spring configure le timer automatique.
    @Bean
    public Supplier<PageEvent> pageEventSupplier(){
        return()-> new PageEvent(
                Math.random()>0.5?"Page1":"Page2",
                Math.random()>0.5?"U1":"U2",
                new Date(),
                new Random().nextInt(9000));
    }
     /*
     un service de data Analytics Real time Stream Process avec kafka stream
     */
    @Bean
    public Function<PageEvent,PageEvent> pageEventPageFunction(){
        return (input)->{
          input.setName("Page Event");
          input.setUser("user_Sokaina");
          return input;
        };
    }
    /*
    envoie de resultat en temps reel
    */
    @Bean
    public Function<KStream<String,PageEvent>,KStream<String,Long>> kStreamFunction(){
        return (input)->{
           return input
                   .filter((k,v)->v.getDuration()>100)
                   .map((k,v)->new KeyValue<>(v.getName(),0L))
                   .groupBy((k,v)->k,Grouped.with(Serdes.String(),Serdes.Long()))
                   .windowedBy(TimeWindows.of(Duration.ofMillis(5000)))
                   .count(Materialized.as("page-count"))
                   .toStream()
                   .map((k,v)->new  KeyValue<>("=>"+k.window().startTime()+k.window().endTime()+k.key(),v));
        };
    }
}
