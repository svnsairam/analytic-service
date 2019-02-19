package com.stackroute.domain;

import lombok.Data;

@Data
public class Paragraph {

    private String paragraphId;
    private String paragraphContent;
    private String documentId;

    public Paragraph(String paragraphId, String paragraphContent, String documentId) {
        this.paragraphId = paragraphId;
        this.paragraphContent = paragraphContent;
        this.documentId = documentId;
    }
}
