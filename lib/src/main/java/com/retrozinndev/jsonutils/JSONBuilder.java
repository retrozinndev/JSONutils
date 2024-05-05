package com.retrozinndev.jsonutils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.retrozinndev.jsonutils.Message.Type;

public class JSONBuilder {
    public Map<String, Object> jsonMap = new HashMap<>();
    public Map<String, Object> queuedJSONChanges = new HashMap<>();
    private File jsonFile;
    public String tab = "    ";

    /**
     * JSONBuilder class constructor, for creating new JSON files with variables and many things.
     * <p>
     * <strong>Recommended:</strong> Use the JSON class getBuilder() method instead.
     * </p><br>
     * <p>
     * JSON json = new JSON(new File("example.json")).getBuilder().{any function you want to call}();
     * </p>
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
     * <p>
     * <strong>Recommended:</strong> Use the JSON class getBuilder() method instead.
     * </p><br>
     * <p>
     * JSON json = new JSON("some/dir/example.json").getBuilder().anyFunction();
     * </p>
     * @param jsonDir
     * The directory of the JSON file to be created.
     */
    public JSONBuilder(String jsonDir) {
        if(!jsonDir.endsWith(".json")) {
            jsonDir += ".json";
        }
        this.jsonFile = new File(jsonDir);
    }

    /**
     * JSONBuilder class constructor, for creating new JSON file, with variables and many things.
     * <p>
     * The best and recommended way to create a JSONBuilder instance. Disables the Only Build mode for this instance.
     * </p>
     * @param json The JSON instance you want to use.
     */
    public JSONBuilder(JSON json) {
        if(!json.jsonFile.toString().endsWith(".json")) {
            json.jsonFile = new File(json.jsonFile.toString()+".json");
        }
        this.jsonFile = json.toFile();
    }

    /**
     * Writes all given variables in Queued HashMap using JSONBuilder.newVariable() method. Clears the queue.
     * <p>
     * Note: This method is used for building new files, not re-building an existing one. For that, use writeJSON() instead.
     * </p>
     * @return
     * A new JSON instance of the built file.
     */
    public JSON writeFile() {
        getMap().putAll(getQueuedChanges());
        queuedJSONChanges.clear();
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(jsonFile));
            writer.write("{\n");
            for(int i = 0; i < getMap().keySet().size(); i++) {
                String lineKey = getMap().keySet().toArray()[i].toString();
                Object lineValue = getMap().get(lineKey);
                String jsonLine = "\""+lineKey+"\": "+lineValue;
                if(lineValue instanceof String) 
                    jsonLine = "\""+lineKey+"\": \""+lineValue.toString()+"\"";

                if(i != getMap().size() - 1) { jsonLine+=", \n"; }
                writer.write(tab+jsonLine);
            }

            writer.write("\n}");
            writer.close();
        } catch(IOException e) { e.printStackTrace(); }

        return new JSON(jsonFile);
    }

    /**
     * Writes all given variables in Queued HashMap using JSONBuilder.newVariable() method.
     * <p>
     * Note: This method is used internally in the JSON class and should not be called by you.
     * </p>
     * @param json
     * The JSON to write the modifications on.
     * @return
     * The JSON instance.
     */
    public File writeJSON(File outputFile) {
        getMap().putAll(getQueuedChanges());
        queuedJSONChanges.clear();
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write("{\n");
            for(int i = 0; i < getMap().keySet().size(); i++) {
                String lineKey = getMap().keySet().toArray()[i].toString();
                Object lineValue = getMap().get(lineKey);
                String jsonLine = "\""+lineKey+"\": "+lineValue;
                if(lineValue instanceof String) 
                    jsonLine = "\""+lineKey+"\": \""+lineValue.toString()+"\"";

                if(i != getMap().size() - 1) { jsonLine+=", \n"; }
                writer.write(tab+jsonLine);
            }

            writer.write("\n}");
            writer.close();
        } catch(IOException e) { e.printStackTrace(); }

        return outputFile;
    }

    /**
     * Creates an empty JSON file with an opened block.
     * @param jsonFile
     * The JSON File to be created.
     * @return
     * An empty JSON File.
     */
    protected File makeEmptyJSON(File jsonFile) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(jsonFile));
            writer.write("{\n"+tab+"\n}");
            writer.flush();
            writer.close();
        } catch(IOException e) { e.printStackTrace(); }
        
        return jsonFile;
    }

    /**
     * Writes the final JSON with the given objects in the JSON directory.
     * @return
     * A JSON representation of the wrote file containing the data.
     */
    public JSON makeJSON() {
        if(!jsonFile.exists()) { makeEmptyJSON(jsonFile); }
        boolean hasModifications = queuedJSONChanges.size() > 0;
        if(hasModifications) 
            writeJSON(jsonFile);
        else 
            Message.send(Type.Alert, "No modifications were found for the json object. Skipping write.");
        
        return null;
    }

    public JSONBuilder newVariable(String keyString, boolean value) {
        queuedJSONChanges.put(keyString, value);
        return this;
    }

    public JSONBuilder newVariable(String keyString, int value) {
        queuedJSONChanges.put(keyString, value);
        return this;
    }

    public Map<String, Object> getMap() {
        return jsonMap;
    }

    public JSONBuilder newVariable(String keyString, String value) {
        queuedJSONChanges.put(keyString, value);
        return this;
    }

    /**
     * Gets a Map of queued changes in the JSON instance.
     * <p>
     * Note: when new variables are added, they are queued in this Map until you call makeJSON() on them.
     * </p>
     * @return
     * A Map of String and Object containing the queued changes.
     */
    public Map<String, Object> getQueuedChanges() { return queuedJSONChanges; }
}
