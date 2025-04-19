package com.autotest.lab3.Xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XmlDomCreate {
    public static void main(String[] args) {
        Element library = new Element("gameLibrary");
        Document doc = new Document(library);
        Element genre = new Element("genre");
        genre.setAttribute(new Attribute("category", "Sandbox"));
        genre.setAttribute(new Attribute("genreId", "4"));
        Element game = new Element("game");
        game.setAttribute(new Attribute("gameId", "007"));
        Element title = new Element("title");
        title.setText("Grand Theft Auto V");
        game.addContent(title);
        Element developer = new Element("developer");
        developer.setText("Rockstar North");
        game.addContent(developer);
        Element releaseDate = new Element("releaseDate");
        releaseDate.setText("2013-09-17");
        game.addContent(releaseDate);
        Element subgenre = new Element("subgenre");
        subgenre.setText("Open world");
        game.addContent(subgenre);
        Element features = new Element("features");
        features.addContent(new Element("feature").setText("Open world"));
        features.addContent(new Element("feature").setText("Action-adventure"));
        features.addContent(new Element("feature").setText("Multiplayer"));
        game.addContent(features);
        Element price = new Element("price");
        price.setText("59");
        game.addContent(price);
        genre.addContent(game);
        doc.getRootElement().addContent(genre);
        try (FileOutputStream out = new FileOutputStream(new File("game_library2.xml"))) {
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, out);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
