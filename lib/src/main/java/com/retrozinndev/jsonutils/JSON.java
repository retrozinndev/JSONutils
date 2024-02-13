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
    private JSONReader jReader = null;
    private JSONBuilder jBuilder = null;
    public String tab = "    ";
    
    
    public Map<String, Object> jsonMap = new HashMap<>();

    /**
     * Creates a new JSON instance for the desired JSON file.
     * @param jsonDir
     * The desired json file directory.
     */
    public JSON(String jsonDir) {
        if(!jsonDir.endsWith(".json")) {
            jsonDir += ".json";
        }
        jsonFile = new File(jsonDir);
        if(jsonFile.exists()) getReader().readMap(this);
    }

    /**
     * Creates a new JSON instance for the desired JSON file.
     * @param jsonFile
     * The desired json file.
     */
    public JSON(File jsonFile) {
        if(!jsonFile.toString().endsWith(".json")) {
            jsonFile = new File(jsonFile.toString()+".json");
        }
        this.jsonFile = jsonFile;
        if(jsonFile.exists()) getReader().readMap(this);
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
        return jsonMap.get(keyObject);
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
     * Gets the JSONReader instance from the JSON class.
     * @return
     * A JSONReader instance.
     */
    public JSONReader getReader() {
        if(jReader == null && jsonFile != null) 
            jReader = new JSONReader(jsonFile);
        
        return jReader;
    }

    /**
     * Gets the JSONBuilder instance from the JSON class.
     * @return
     * A JSONBuilder instance.
     */
    public JSONBuilder getBuilder() {
        if(jBuilder == null) 
            jBuilder = new JSONBuilder(jsonFile);

        return jBuilder;
    }

    /**
     * Gets the JSON objects as a Map<String, Object>
     * @return
     * A Map<String, Object> containing the JSON objects.
     */
    public Map<String, Object> toMap() { return jsonMap; }

    /**
     * Returns this json as a file.
     * @return
     * A File containing the JSON.
     */
    public File toFile() { return jsonFile; }
}
