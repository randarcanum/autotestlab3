package com.autotest.lab3.Xml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XmlDomQuery {
    public static Element query(Document document, String attrName, String attrVal) {
        return query(document.getRootElement(), attrName, attrVal);
    }
    public static Element query(Element root, String attrName, String attrVal) {
        Attribute attr = root.getAttribute(attrName);
        if (attr != null && attr.getValue().equals(attrVal)) return root;
        List<Element> children = root.getChildren();
        for (Element child : children) {
            Element result = query(child, attrName, attrVal);
            if (result != null) return result;
        }
        return null;
    }
    public static void main(String[] args) {
        try {
            File inputFile = new File("game_library.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            Element library = document.getRootElement();
            Element game = query(library, "gameId", "004");
            if (game != null) XmlDomParse.printRec(game);
            else System.out.println("Game not found");
        } catch(JDOMException e) {
            e.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
