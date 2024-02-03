package com.retrozinndev.jsonutils;

import java.io.File;

import com.retrozinndev.jsonutils.Message.Type;

public class JSONBuilder extends JSON {
    private JSONBuilder inst = this;

    /**
     * JSONBuilder class constructor, for creating new JSON files with variables and many things.
     * @param jsonFile
     * The JSON file to be created.
     */
    public JSONBuilder(File jsonFile) {
        super(jsonFile);
    }

    /**
     * JSONBuilder class constructor, for creating new JSON files with variables and many things.
     * @param jsonFile
     * The directory of the JSON file to be created.
     */
    public JSONBuilder(String jsonDir) {
        super(jsonDir);
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
        return inst;
    }

    public JSONBuilder newVariable(String keyString, int value) {
        queuedJSONChanges.put(keyString, value);
        return inst;
    }

    public JSONBuilder newVariable(String keyString, String value) {
        queuedJSONChanges.put(keyString, value);
        return inst;
    }
}
