package com.retrozinndev.jsonutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

public class JSONReader {

    private JSON json;
    private File jsonFile;

    public JSONReader(String jsonDir) {
        if(!jsonDir.endsWith(".json")) {
            jsonDir += ".json";
        }
        this.jsonFile = new File(jsonDir);
    }

    public JSONReader(File jsonFile) {
        if(!jsonFile.toString().endsWith(".json")) {
            jsonFile = new File(jsonFile.toString()+".json");
        }
        this.jsonFile = jsonFile;
    }

    public JSONReader(JSON json) {
        this.jsonFile = json.toFile();
        this.json = json;
    }

    /**
     * Reads the specified JSON file and adds content to a Map.
     * @param json the JSON file to read.
     * 
     * @return
     * A HashMap containing all the keys and their respective 
     * values inside the specified JSON file.
     */
    public Map<String, Object> readMap(JSON json) {
        if(json.checkIfExists(json.toFile())) {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(json.toFile()));
                String line, formattedLine;
                while((line = reader.readLine())!= null) {
                    formattedLine = line.trim();
                    if(formattedLine.equals("{") || formattedLine.equals("}")) 
                        continue;
                    
                    String[] splittedLine = formattedLine.split(":");
                    
                    String key = splittedLine[0].replace('"', ' ').trim();
                    Object value = splittedLine[1].replace(',', ' ').trim();
                    
                    json.toMap().put(key, getValueType(value));
                }
                reader.readLine();
                reader.close();

                return json.toMap();
            } catch(Exception e) { e.printStackTrace(); }
        }
        return null;
    }

    public Object getValueType(Object value) {
        if(value.equals("true") || value.equals("false")) 
            handleBooleanType(value);
        
        try {
            return Integer.parseInt(value.toString());
        } catch(NumberFormatException nfe) {
            return value.toString().replace('"', ' ').trim();
        }
    }


    private final boolean handleBooleanType(Object value) {
        if(value.equals("true")) 
            return true;
        
        return false;
    }
}