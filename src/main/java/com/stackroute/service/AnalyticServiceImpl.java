package com.stackroute.service;

import com.stackroute.domain.AnalysisResult;
import com.stackroute.domain.NlpResult;
import com.stackroute.nlpService.NlpService;
import com.stackroute.nlpService.NlpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AnalyticServiceImpl implements AnalyticService {
    private String conceptNames[];
    private NlpResultService nlpResultService;

    @Autowired
    public AnalyticServiceImpl(NlpResultService nlpResultService) {
        this.nlpResultService = nlpResultService;
    }

    public String getNounSentence() {
        StringBuilder nounSentence = new StringBuilder();
        ArrayList<String> nouns = new ArrayList<>(nlpResultService.getNlpResult().getNounWords());
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
        ArrayList<String> verbs = new ArrayList<>(nlpResultService.getNlpResult().getVerbWords());
        for (int i = 0; i < verbs.size(); i++) {
            verbSentence.append(verbs.get(i) + " ");
        }
        return verbSentence.toString().trim().toLowerCase();
    }

    public AnalysisResult getAnalysisResult() {
        AnalysisResult analysisResult = new AnalysisResult();
        System.out.println("Analytic Service Result in Analytic Service Impl .java");
        analysisResult.setConfidenceScore(25.5);
        analysisResult.setDocumentId("D001");
        analysisResult.setParagraphId("P001");
        analysisResult.setDomain("spring framework");
        analysisResult.setIntentLevel("knowledge");
        analysisResult.setConcept(getConceptName());
        analysisResult.setParagraphContent(nlpResultService.getNlpResult().getClearedParagraph());
        return analysisResult;
    }

    public String[] getConceptNames() {
        return conceptNames;
    }

    public void setConceptNames(String[] conceptNames) {
        this.conceptNames = conceptNames;
    }
}