package com.stackroute.controller;

import com.stackroute.domain.AnalysisResult;
import com.stackroute.service.AnalyticService;
import com.stackroute.service.ConceptSerive;
import com.stackroute.domain.Paragraph;
import com.stackroute.service.ParagraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/")
public class AnalyticServiceController {
    private ConceptSerive conceptSerive;
    private ParagraphService paragraphService;
    private AnalyticService analyticService;

    @Autowired
    public AnalyticServiceController(ConceptSerive conceptSerive, ParagraphService paragraphService, AnalyticService analyticService) {
        this.conceptSerive = conceptSerive;
        this.paragraphService = paragraphService;
        this.analyticService = analyticService;
    }


    @GetMapping("/concepts")
    public Collection<String> getAllUsers() {
        return conceptSerive.getConcepts();
    }

    @PostMapping("/paragraph")
    public ResponseEntity<?> setParagraph(@RequestBody Paragraph paragraph) {
        ResponseEntity responseEntity;
        System.out.println("Hi I am In Paragraph Controller");
        paragraphService.setParagraph(paragraph);
        responseEntity = new ResponseEntity<String>("Track is successfully saved", HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("/analysisResult")
    public AnalysisResult getAnalysisResult() {
        AnalysisResult analysisResult = new AnalysisResult();
        analysisResult = analyticService.getAnalysisResult();
        return analysisResult;
    }
}
