package com.autotest.lab3.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
public class Xpath {
    public static boolean checkTag(XPath xPath, Document doc, String tag) throws XPathExpressionException {
        String expression = "//" + tag;
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        return nodeList.getLength() > 0;
    }
    public static boolean checkChildren(XPath xPath, Document doc, String tag) throws XPathExpressionException {
        String expression = "//" + tag + "/*";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        NodeList children = nodeList.item(0).getChildNodes();
        return children.getLength() > 0;
    }
    public static void print(XPath xPath, Document doc, String tag) throws XPathExpressionException{
        String expression = "//" + tag;
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            print(nNode, 0);
        }
    }
    public static void print(Node parent, int depth) {
        if (parent.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) parent;
            boolean par = eElement.getChildNodes().getLength() <= 1;
            System.out.println("\t".repeat(depth) + parent.getNodeName() + " : " + (par ? eElement.getTextContent() : ""));
            NamedNodeMap attributes = eElement.getAttributes();
            for (int j = 0; j < attributes.getLength(); j++) {
                Node attr = attributes.item(j);
                System.out.println("\t".repeat(depth) + attr.getNodeName() + " : " + attr.getNodeValue());
            }
            depth++;
            NodeList children = eElement.getChildNodes();
            for (int j = 0; j < children.getLength(); j++) {
                Node child = children.item(j);
                if (child.getNodeType() == Node.ELEMENT_NODE) print(child, depth);
            }
        }
    }
    public static void main(String[] args) {
        try {
            File inputFile = new File("game_library.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            XPath xPath =  XPathFactory.newInstance().newXPath();
            if (checkTag(xPath,doc, "game")) {
                if (checkChildren(xPath, doc, "game")) {
                    print(xPath, doc, "game[@gameId='002']");
                } else {
                    System.out.println("Tag does not have children");
                }
            } else {
                System.out.println("Tag not found");
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
