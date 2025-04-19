package com.autotest.lab3.Xml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XmlDomParse {
    public static void printRec(Document document) { printRec(document.getRootElement()); }
    public static void printRec(Element parent) { printRec(parent, 0); }
    public static void printRec(Element parent, int depth) {
        System.out.println("\t".repeat(depth) + (depth == 0 ? "Root Element : " : "Current Element : ") + parent.getName());
        List<Attribute> attributes = parent.getAttributes();
        for (Attribute attr : attributes) {
            System.out.println("\t".repeat(depth) + attr.getName() + " : " + attr.getValue());
        }
        depth++;
        List<Element> children = parent.getChildren();
        for (Element child : children) {
            if (child.getChildren().size() > 0) printRec(child, depth);
            else System.out.println("\t".repeat(depth) + child.getName() + " : " + child.getText());
        }
    }
    public static void main(String[] args) {
        try {
            File inputFile = new File("game_library.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            printRec(document);
        } catch(JDOMException e) {
            e.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
