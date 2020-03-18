package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String args[]) {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() {
        try{
        Catalog catalog = new Catalog("Car Resources", "./catalog.ser");
        Document doc = new Document("car1", "Tesla Car 1",
                "https://www.cstatic-images.com/car-pictures/main/USC70TSC024B021001.png");
        //Document doc = new Document("car1", "Tesla Car 1","text.txt"); // file on disk
        doc.addTag("type", "Car");
        doc.addTag("manufacturer", "Tesla");
        doc.addTag("company_ceo", "Elon Musk");
        catalog.add(doc);

        CatalogUtil.save(catalog);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testLoadView() {
        Catalog catalog;
        try {
            catalog = CatalogUtil.load("./catalog.ser");
            Document doc = catalog.findById("car1");
            CatalogUtil.view(doc);
        } catch (FileNotFoundException e) {
            System.err.println("The file is missing!");
        } catch (InvalidCatalogException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println("Failed to browse the item.");
            //e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
