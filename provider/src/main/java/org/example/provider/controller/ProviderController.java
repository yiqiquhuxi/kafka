package org.example.provider.controller;

import org.example.provider.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class ProviderController {


    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("done")
    public String done(){
        kafkaProducerService.send("done");
        return "done";
    }



}
