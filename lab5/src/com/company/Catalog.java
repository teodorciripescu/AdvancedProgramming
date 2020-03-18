package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents = new ArrayList<>();

    public Catalog(String name, String path){
        this.name = name;
        this.path = path;
    }

    public void add(Document doc) {
        documents.add(doc);
    }

    public List<Document> getDocuments(){
        return documents;
    }
    //finding a document by Id using stream and filter.
    public Document findById(String id) {
        return documents.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst().orElse(null);
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
