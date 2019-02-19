package com.stackroute.domain;

import lombok.Data;

@Data
public class AnalysisResult {
    String paragraphId;
    String paragraphContent;
    String documentId;
    String domain;
    String concept[];
    String intentLevel;
    double confidenceScore;
}
