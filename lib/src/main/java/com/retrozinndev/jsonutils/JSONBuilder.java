package com.retrozinndev.jsonutils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class JSONBuilder extends JSON {
    private JSONBuilder inst;
    public String tab = "    ";

    public JSONBuilder(File jsonFile) {
        super(jsonFile);
    }

    public JSONBuilder(String jsonDir) {
        super(jsonDir);
    }
    
    /**
     * Creates an empty JSON file with a opened block.
     * @param jsonFile
     * The JSON File to be created.
     * @return
     * Instance of JSONBuilder class.
     */
    public JSONBuilder makeEmptyJSON(File jsonFile) {
        if(!jsonFile.exists()) {
            BufferedWriter writer;
            try {
                writer = new BufferedWriter(new FileWriter(jsonFile));
                writer.write("{\n"+tab+"\n}");
                writer.close();
            } catch(IOException e) { e.printStackTrace(); }
        }
        return inst;
    }

    // public JSONBuilder newBlock() {
    //     return inst;
    // }

    // public JSONBuilder newVariableArray(Object) {
    //     return inst;
    // }

    public JSONBuilder newVariable(Object keyObject, Object valueObject) {
        if(!json.exists()) { makeEmptyJSON(json); }
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(json));
            
        } catch(IOException e) { e.printStackTrace(); }
        return inst;
    }
}
