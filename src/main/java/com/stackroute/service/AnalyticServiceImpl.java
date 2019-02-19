package com.stackroute.service;

import com.stackroute.nlpService.NlpServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AnalyticServiceImpl {
    String conceptNames[];
    ArrayList<String> nouns;
    ArrayList<String> verbs;
    NlpServiceImpl nlpService;

    public AnalyticServiceImpl(NlpServiceImpl nlpService) {
        this.nlpService = nlpService;
        this.nouns = nlpService.getNouns();
        this.verbs = nlpService.getVerbs();
    }

    public void setConceptNames(String[] concepts) {
        this.conceptNames = concepts;
    }

    public String[] getConceptNames() {
        return conceptNames;
    }

    public ArrayList<String> getNouns() {
        return nouns;
    }

    public ArrayList<String> getVerbs() {
        return verbs;
    }

    public String getNounSentence() {
        StringBuilder nounSentence = new StringBuilder();
        for (int i = 0; i < nouns.size(); i++) {
            nounSentence.append(nouns.get(i) + " ");
        }
        return nounSentence.toString().trim().toLowerCase();
    }

    public String getConceptName() {
        int conceptNameCount[] = new int[conceptNames.length];
        int max = 0;
        int conceptIndex = -1;
        for (int i = 0; i < conceptNames.length; i++) {
            String pattenString = conceptNames[i];
            Pattern pattern = Pattern.compile(pattenString);
            Matcher matcher = pattern.matcher(getNounSentence());
            while (matcher.find()) {
                conceptNameCount[i]++;
            }
        }
        for (int i = 0; i < conceptNameCount.length; i++) {
            if (conceptNameCount[i] > max) {
                max = conceptNameCount[i];
                conceptIndex = i;
            }
        }
        if (conceptIndex == -1) {
            return "No Concept is found for this paragraph";
        } else {
            return conceptNames[conceptIndex];
        }
    }

    public String getVerbSentence() {
        StringBuilder verbSentence = new StringBuilder();
        for (int i = 0; i < verbs.size(); i++) {
            verbSentence.append(verbs.get(i) + " ");
        }
        return verbSentence.toString().trim().toLowerCase();
    }

    public void showAllResults() {
        System.out.println("Get Noun Sentence");
        System.out.println(getNounSentence());

        System.out.println("Get Concept Names");
        System.out.println(getConceptName());

        System.out.println("Get Verb Sentence");
        System.out.println(getVerbSentence());
    }
}