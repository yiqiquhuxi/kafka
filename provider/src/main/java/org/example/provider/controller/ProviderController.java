package org.example.provider.controller;

import jakarta.validation.Valid;
import org.example.provider.domain.User;
import org.example.provider.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("get")
    public String get(@RequestBody @Valid User user){
        return "get";
    }




}
