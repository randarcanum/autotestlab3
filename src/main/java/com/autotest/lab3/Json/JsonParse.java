package com.autotest.lab3.Json;

import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParse {
    public static void printRec(JSONObject parent) { printRec(parent, -1); }
    @SuppressWarnings("unchecked")
    public static void printRec(JSONObject parent, int depth) {
        Object attributes = parent.get("attributes");
        if (attributes != null) {
            Set<String> attributeList = ((JSONObject) attributes).keySet();
            for (String attribute : attributeList) {
                System.out.println("\t".repeat(depth) + attribute + " : " + ((JSONObject) attributes).get(attribute));
            }
        }
        depth++;
        Set<String> children = ((JSONObject) parent).keySet();
        children.remove("attributes");
        for (String child : children) {
            if (parent.get(child) instanceof JSONArray) {
                if (child.equals("features")) System.out.println("\t".repeat(depth) + child + " : ");
                JSONArray array = (JSONArray) parent.get(child);
                for (int i = 0; i < array.size(); i++) {
                    if (!child.equals("features") && !child.equals("attributes")) System.out.println("\t".repeat(depth) + child + " : ");
                    if (array.get(i) instanceof JSONObject) {
                        printRec((JSONObject) array.get(i), depth);
                    } else {
                        System.out.println("\t".repeat(depth+1) + array.get(i));
                    }
                }
            } else if (parent.get(child) instanceof JSONObject) {
                printRec((JSONObject) parent.get(child), depth);
            } else {
                System.out.println("\t".repeat(depth) + child + " : " + parent.get(child));
            }
        }
    }
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject document = (JSONObject)parser.parse(new FileReader("game_library.json"));
            printRec(document);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
