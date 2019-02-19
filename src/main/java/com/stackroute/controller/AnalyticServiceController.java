package com.stackroute.controller;

import com.stackroute.service.ConceptService;
import com.stackroute.service.ParagraphService;
import com.stackroute.domain.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/")
public class AnalyticServiceController {

    @Autowired
    ConceptService conceptService;

    @Autowired
    ParagraphService paragraphService;


    @GetMapping("/concepts")
    public Collection<String> getAllUsers() {
        return conceptService.getConcepts();
    }

    @PostMapping("/paragraph")
    public ResponseEntity<?> setParagraph(@RequestBody Paragraph paragraph) {
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<String>("Track is successfully saved", HttpStatus.CREATED);
        return responseEntity;
    }

}
