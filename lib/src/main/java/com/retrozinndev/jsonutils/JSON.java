package com.retrozinndev.jsonutils;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

import com.retrozinndev.jsonutils.Message.Type;

public class JSON {
    public Map<String, Object> queuedJSONChanges = new HashMap<>();
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
     * <strong>Note:</strong> This method searches for the key in the JSON Map, then, if the key exists, the function returns its value.
     * </p>
     * @param keyString
     * The key you want the value from.
     * @return
     * The value of the key in the JSON.
    */
    public Object getValue(String keyString) {
        Object value = null;
        if(jsonMap.containsKey(keyString)) 
            value = jsonMap.get(keyString);
        else {
            Message.send(Type.Error, "Key \""+keyString+"\" not found in the JSON Map.");
            Message.send(Type.Tip, "Try forcing the read of the JSON file before calling getValue(): ");
            Message.send(Type.Tip, "{jsonInstance}.read().getValue(\""+keyString+"\");");
        }
        return value;
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
     * Gets the JSONReader instance from the JSON class.
     * @return
     * A JSONReader instance.
     */
    public JSONReader getReader() {
        if(jReader == null && jsonFile != null) 
            jReader = new JSONReader(this);
        
        return jReader;
    }

    /**
     * Gets the JSONBuilder instance from the JSON class.
     * @return
     * A JSONBuilder instance.
     */
    public JSONBuilder getBuilder() {
        if(jBuilder == null) 
            jBuilder = new JSONBuilder(this);

        return jBuilder;
    }

    /**
     * Adds the given key and value in the JSON queue map. Is written when calling the write() function.
     * @param name The variable name/key
     * @param value The variable's value
     * @return This JSON instance.
     */
    public JSON newVariable(String name, Object value) {
        queuedJSONChanges.put(name, value);
        return this;
    }

    /**
     * Gets a Map of queued changes in the JSON instance.
     * <p>
     * OBS: when new variables are added, they are queued in this Map until you build it.
     * </p>
     * @return
     * Map<String, Object> containing queued changes inside this instance.
     * 
     */
    public Map<String, Object> getQueuedChanges() { return queuedJSONChanges; }
    /**
     * Reads the JSON file. Can be used to prevent problems when trying to get a recently added value to JSON.
     */
    public JSON read() {
        getReader().readMap(this);
        return this;
    }

    /**
     * Writes the JSON file. With all variables inside the JSON Map and queued variable and re-reads the JSON.
     * @return this JSON instance.
     */
    public JSON write() {
        getBuilder().writeJSON(this); 
        read();
        return this;
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
