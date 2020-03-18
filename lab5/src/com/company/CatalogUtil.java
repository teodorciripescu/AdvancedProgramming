package com.company;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class CatalogUtil {

    public static void save(Catalog catalog) throws IOException {
        try (var oos = new ObjectOutputStream( new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
        catch (IOException e){ // handling writeObject exception
            e.printStackTrace();
        }
    }

    public static Catalog load(String path) throws InvalidCatalogException, IOException {
        Catalog c;
        try(FileInputStream fis = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fis)){
            c = (Catalog) in.readObject();
            //if the catalog is null or if it has no documents throw an Invalid Catalog Exception
            if(c.getDocuments().isEmpty() || c.equals(null)) {
                throw new InvalidCatalogException(new Exception());
            }
            return c;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void view(Document doc) throws IOException, URISyntaxException {
        Desktop desktop = Desktop.getDesktop();
        File file = new File(doc.getLocation());
        if(file.isFile()) {
            desktop.open(file);
        }
        else {
            desktop.browse(new URI(doc.getLocation()));
        }
    }

}
