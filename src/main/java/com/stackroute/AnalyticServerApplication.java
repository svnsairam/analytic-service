package com.stackroute;

import com.stackroute.service.AnalyticServiceImpl;
import com.stackroute.nlpService.NlpServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnalyticServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyticServerApplication.class, args);
        NlpServiceImpl nlpService = new NlpServiceImpl();
        nlpService.setParagraph("Spring Boot is a brand new framework from the team at Pivotal, designed to simplify the bootstrapping and development of a new Spring application. The framework takes an opinionated approach to configuration, freeing developers from the need to define boilerplate configuration");
        nlpService.showAllResults();
        AnalyticServiceImpl analyticService = new AnalyticServiceImpl(nlpService);
        String concepts[] = {"spring core", "spring datajpa", "spring data jpa", "spring aop", "spring security", "spring cloud", "spring reactive","spring framework"};
        analyticService.setConceptNames(concepts);
        analyticService.showAllResults();
    }
}