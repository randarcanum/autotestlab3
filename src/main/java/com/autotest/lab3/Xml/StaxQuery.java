package com.autotest.lab3.Xml;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
public class StaxQuery {
    public static void main(String[] args) {
        int depth = 0;
        boolean isRequestId = false;
        boolean root = true;
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("game_library.xml"));
            String requestedAttr = "gameId";
            String requestedId = "005";
            boolean isFound = false;
            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                switch(event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String name = startElement.getName().getLocalPart();
                        if (name.equalsIgnoreCase("game")) {
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()) {
                                Attribute currentAttribute = attributes.next();
                                if (currentAttribute.getName().getLocalPart().equals(requestedAttr) && currentAttribute.getValue().equals(requestedId)) {
                                    isRequestId = true;
                                    isFound = true;
                                    break;
                                }
                            }
                        }
                        if (isRequestId) {
                            System.out.print((root ? "" : "\n") + "\t".repeat(depth) + name + " : ");
                            root = false;
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()){
                                Attribute currentAttribute = attributes.next();
                                System.out.print("\n" + "\t".repeat(depth) + currentAttribute.getName() + " : " + currentAttribute.getValue());
                            }
                            depth++;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (isRequestId && characters.getData().strip() != "") {
                            System.out.print(characters.getData().strip());
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        if (isRequestId) {
                            depth--;
                            if (endElement.getName().getLocalPart().equalsIgnoreCase("game")) {
                                isRequestId = false;
                            }
                        }
                        break;
                }
            }
            if (!isFound) {
                System.out.println("Game not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
