# JSONutils
A simple JSON Library for java, still in development.

[![](https://jitpack.io/v/retrozinndev/JSONutils.svg)](https://jitpack.io/#retrozinndev/JSONutils)
### TODO:
 - Search for value of variable using a get();
 - Read file for existing variables before writing new ones;
 - Make a JSONFormatter class.

### How it works
JSONutils Library's focus is the `JSON` class.

- **JSON** <br>
When creating a new JSON type instance, searches for the indicated file. If the file exists, then
the library reads it, adds variables and values contained in it to a HashMap (`jsonMap<>`). You can get the Map using the `JSON.toMap()` method.

- **JSONBuilder** <br>
You can use it separately from the `JSON` class or use it in a single line, just like the [Example in Usage section](#Usage).
**JSONBuilder** class is useful for writing new variables on a new JSON file.

- **JSONReader** <br>
When the **JSON** class is instantiated, the **JSONReader** class is used for calling the `JSONReader.readMap()` function. This function reads the JSON 
file, gets the variables and their values, adds them to a **Map** of **<String, Object>** and returns the used HashMap(`JSON.jsonMap<>`) with all variables in it.

- **Message** <br>
This class is only used internally for sending console messages in a more organized way inside the code.

### Usage
Here are two examples of usage of JSONutils:
<!--1. --> 
#### JSONBuilder
- Multi-lined:
 ```java
 File file = new File("example.json"); // Creates an instance of the Java File class
 JSONBuilder builder = new JSONBuilder(file); // JSONBuilder instance, asks for File (A File instance) or String (Directory)
 builder.newVariable("ExampleVar", "example_value"); // Adds the variable to a queue
 builder.makeJSON(); // Makes the JSON file with the given variables
 ```
- Single lined:
 ```java
 // If the file doesn't exists, creates the file with the given variable(newVariable())
 JSONBuilder jsonFile = new JSONBuilder("/directory/file.json").newVariable("IsLibraryComplete", false).makeJSON();
 ```
#### JSON
```java
 JSON json = new JSON("example.json").get("SomeVariableName"); // If exists, returns the value of this variable
```
