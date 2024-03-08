package com.retrozinndev.jsonutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

import com.retrozinndev.jsonutils.Message.Type;

public class JSONReader {

    private JSON json;
    private File jsonFile;

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
                int lineNumber = 0;
                while((line = reader.readLine()) != null) {
                    lineNumber++;
                    formattedLine = line.trim();
                    if(formattedLine.startsWith("{") || formattedLine.startsWith("}")) 
                        continue;
                    
                    String[] splittedLine = null;
                    if(formattedLine.contains(":")) {
                        splittedLine = formattedLine.split(":");
                    } else {
                        Message.send(Type.Error, "Couldn't read variable at \""+json.toFile().toString()+"\":");
                        Message.send(Type.Error, "An error occurred when trying to read the variable value: ");
                        Message.send(Type.Tip, "At: \""+formattedLine+"\", line: " + lineNumber);
                        continue;
                    }
                    String key = splittedLine[0].trim();
                    Object value = splittedLine[1].trim();
                    if(value.toString().contains(",")) 
                        value = value.toString().replace(",", " ").trim();

                    if(key.contains("\"")) {
                        key = splittedLine[0].replace('"', ' ').trim();
                    } else if (value.toString().contains(",")) {
                        value = splittedLine[1].replace(',', ' ').trim();
                    }
                    
                    json.toMap().put(key, getValueType(value));
                }
                reader.readLine();
                reader.close();

                return json.toMap();
            } catch(Exception e) { 
                Message.send(Type.Error, "Couldn't read \""+json.toFile().toString()+"\":");
                e.printStackTrace();
            }
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