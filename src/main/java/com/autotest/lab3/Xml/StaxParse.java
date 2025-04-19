package com.autotest.lab3.Xml;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class StaxParse {
    public static void main(String[] args) {
        int depth = 0;
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("game_library.xml"));
            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                switch(event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        System.out.print((qName.equalsIgnoreCase("gamelibrary") ? "" : "\n") + "\t".repeat(depth) + qName + " : ");
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()){
                            Attribute currentAttribute = attributes.next();
                            System.out.print("\n" + "\t".repeat(depth) + currentAttribute.getName() + " : " + currentAttribute.getValue());
                        }
                        depth++;
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (characters.getData().strip() != "") {
                            System.out.print(characters.getData().strip());
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        depth--;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
