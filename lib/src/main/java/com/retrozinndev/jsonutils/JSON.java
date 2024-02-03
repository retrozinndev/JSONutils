package com.retrozinndev.jsonutils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import com.retrozinndev.jsonutils.Message.Type;

public class JSON {
    protected File jsonFile;
    public String tab = "    ";
    
    public Map<String, Object> queuedJSONChanges = new HashMap<>();
    public Map<String, Object> jsonMap = new HashMap<>();

    /**
     * Creates a new JSON instance for the desired JSON file.
     * @param jsonDir
     * The desired json file directory.
     */
    public JSON(String jsonDir) {
        if(!jsonDir.endsWith(".json")) {
            jsonFile = new File(jsonDir.toString()+".json");
        }
    }

    /**
     * Creates a new JSON instance for the desired JSON file.
     * @param jsonFile
     * The desired json file.
     */
    public JSON(File jsonFile) {
        if(!jsonFile.toString().endsWith(".json")) {
            this.jsonFile = new File(jsonFile.toString()+".json");
        }
    }

    /**
     * Gets the value of a key in a JSON.
     * <p>
     * <strong>Note: This function doesn't work yet.(In development)</strong>
     * </p>
     * @param keyString
     * The key used to return it's value.
     * @return
     * The value of the key in the JSON.
    */
    public Object getValue(String keyObject) {
        return ""; //TODO
    }

    /**
     * Checks if json file exists.
     * @return
     * Boolean that confirms if json exists.
     */
    public final boolean checkIfExists(File file) {
        if(jsonFile == null) {
            Message.send(Type.Error, "The object 'json' is not defined. checkIfExists() -> false");
            return false;
        } if(jsonFile.exists()) 
            return jsonFile.exists();
            
        Message.send(Type.Error, "JSON not found in dir \""+jsonFile.toString()+"\".");
        return false;
    }

    /**
     * Creates an empty JSON file with opened block.
     * @param jsonFile
     * The JSON File to be created.
     * @return
     * Instance of JSONBuilder class.
     */
    protected File makeEmptyJSON(File jsonFile) {
        if(!jsonFile.exists()) {
            BufferedWriter writer;
            try {
                writer = new BufferedWriter(new FileWriter(jsonFile));
                writer.write("{\n"+tab+"\n}");
                writer.flush();
                writer.close();
            } catch(IOException e) { e.printStackTrace(); }
        }

        return jsonFile;
    }
    /**
     * Writes all given variables in Queued HashMap using JSONBuilder.newVariable() method.
     * <p>
     * OBS: This method is used internally in the JSONBuilder class, inside the makeJSON() method.
     * @param json
     * The JSON file to write variables.
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
     * Gets the JSON objects as a Map<String, Object>
     * @return
     * A Map<String, Object> containing the JSON objects.
     */
    public Map<String, Object> toMap() { return jsonMap; }

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
}
