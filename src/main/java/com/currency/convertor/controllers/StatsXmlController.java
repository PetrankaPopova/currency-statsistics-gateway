package com.currency.convertor.controllers;

import com.currency.convertor.domain.dto.XmlResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xml_api")
public class StatsXmlController {
    @PostMapping(consumes = "application/xml",produces = "application/xml", value = "/command")
    XmlResponse processCommand(){
        System.out.println("here");
        return new XmlResponse();
    }
}
