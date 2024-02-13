package com.retrozinndev.jsonutils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.retrozinndev.jsonutils.Message.Type;

public class JSONBuilder {
    public Map<String, Object> queuedJSONChanges = new HashMap<>();
    private File jsonFile;

    /**
     * JSONBuilder class constructor, for creating new JSON files with variables and many things.
     * @param jsonFile
     * The JSON file to be created.
     */
    public JSONBuilder(File jsonFile) {
        if(!jsonFile.toString().endsWith(".json")) {
            jsonFile = new File(jsonFile.toString()+".json");
        }
        this.jsonFile = jsonFile;
    }

    /**
     * JSONBuilder class constructor, for creating new JSON files with variables and many things.
     * @param jsonFile
     * The directory of the JSON file to be created.
     */
    public JSONBuilder(String jsonDir) {
        if(!jsonDir.endsWith(".json")) {
            jsonDir += ".json";
        }
        this.jsonFile = new File(jsonDir);
    }

    /**
     * Writes all given variables in Queued HashMap using JSONBuilder.newVariable() method.
     * <p>
     * OBS: This method is used internally in the JSONBuilder class, inside the makeJSON() method.
     * </p>
     * @param json
     * The JSON file to write the modifications on.
     * @return
     * The JSON instance.
     */
    public JSON writeModifications(File json) {
        jsonMap.putAll(queuedJSONChanges);
        queuedJSONChanges.clear();
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(json));
            writer.write("{\n");
            for(int i = 0; i < jsonMap.keySet().size(); i++) {
                String lineKey = jsonMap.keySet().toArray()[i].toString();
                Object lineValue = jsonMap.get(lineKey);
                String jsonLine = "\""+lineKey+"\": "+lineValue;
                if(lineValue instanceof String) 
                    jsonLine = "\""+lineKey+"\": \""+lineValue.toString()+"\"";

                
                if(i != jsonMap.size() - 1) { jsonLine+=", \n"; }
                writer.write(tab+jsonLine);
            }

            writer.write("\n}");
            writer.close();
        } catch(IOException e) { e.printStackTrace(); }

        return new JSON(json);
    }

    /**
     * Writes the final JSON with the given objects in the given directory.
     */
    public JSON makeJSON() {
        if(!jsonFile.exists()) { makeEmptyJSON(jsonFile); }
        boolean hasModifications = queuedJSONChanges.size() > 0;
        if(hasModifications) 
            writeModifications(jsonFile);
        else 
            Message.send(Type.Normal, "No modifications were found for 'json' object, nothing has changed.");
        
        return new JSON(jsonFile);
    }

    public JSONBuilder newVariable(String keyString, boolean value) {
        queuedJSONChanges.put(keyString, value);
        return this;
    }

    public JSONBuilder newVariable(String keyString, int value) {
        queuedJSONChanges.put(keyString, value);
        return this;
    }

    public JSONBuilder newVariable(String keyString, String value) {
        queuedJSONChanges.put(keyString, value);
        return this;
    }

     /**
     * Gets a Map of queued changes in the JSON instance.
     * <p>
     * OBS: when new variables are added, they are queued in this Map until you call makeJSON() on them.
     * </p>
     * @return
     * Map<String, Object> containing queued changes.
     * 
     */
    public Map<String, Object> getQueuedChanges() { return queuedJSONChanges; }

    public void get
}
