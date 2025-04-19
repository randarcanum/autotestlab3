package com.autotest.lab3.Json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.json.simple.JSONObject;

public class JsonCreate {
    public static String printRec(Document document) {
        return JSONObject.toJSONString(printRec(document.getRootElement()));
    }
    @SuppressWarnings("unchecked")
    public static HashMap<String, Object> printRec(Element parent) {
        HashMap<String, Object> map = new LinkedHashMap<>();
        if (parent.getTextTrim() != "") {
            map.put(parent.getName(), parent.getText());
        }
        List<Attribute> attributes = parent.getAttributes();
        if (attributes.size() > 0) {
            HashMap<String, String> attrMap = new LinkedHashMap<>();
            for (Attribute attr : attributes) {
                attrMap.put(attr.getName(), attr.getValue());
            }
        map.put("attributes", attrMap);
        }
        List<Element> children = parent.getChildren();
        for (Element child : children) {
            Object subChild = printRec(child);
            if (((HashMap<String, Object>) subChild).size() == 1) {
                Object first = ((HashMap<String, Object>) subChild).entrySet().iterator().next().getValue();
                if (first instanceof String || first instanceof LinkedList) {
                    subChild = first;
                }
            }
            if (map.containsKey(child.getName())) {
                if (map.get(child.getName()) instanceof List) {
                    ((List<Object>) map.get(child.getName())).add(subChild);
                } else {
                    List<Object> list = new LinkedList<>();
                    list.add(map.get(child.getName()));
                    list.add(subChild);
                    map.put(child.getName(), list);
                }
            } else {
                map.put(child.getName(), subChild);
            }
        }
        return map;
    }
    public static void main(String[] args) {
        try (FileWriter outputFile = new FileWriter("game_library.json")) {
            File inputFile = new File("game_library.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            String json = printRec(document);
            outputFile.write(json);
            outputFile.flush();
        } catch(JDOMException e) {
            e.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
