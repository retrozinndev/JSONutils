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
<!--
- **JSONReader** <br>
When the **JSON** class is instantiated, the **JSONReader** class is used for calling the `JSONReader.readMap()` function. This function reads the JSON 
file, gets the variables and their values, adds them to a **Map** of **<String, Object>** and returns the used HashMap(`JSON.jsonMap<>`) with all variables in it.
-->
- **Message** <br>
This class is only used internally for sending console messages in a more organized way inside the code.

### Usage
Here's an example usage of JSONutils:
```java
JSON jsonFile = new JSONBuilder("/directory/file.json").newVariable("IsLibraryComplete", false).makeJSON();
```
**Explanation**:
 - `jsonFile` is an instance of the JSON class;
 - `jsonFile` is instantiated using JSONBuilder for adding variables and building the JSON using `makeJSON()`;
 - `JSONBuilder` has(not yet) many methods to build a JSON file, such as `newVariable(String key, Object value)`, and `makeJSON()`;
 - `JSONBuilder` is extended from the `JSON` class;
 - `makeJSON()` has to be used in the end of the line;
 - `newVariable()` adds the variable to a HashMap, which in `makeJSON()` will be written to a file, just like `Writer.flush()`.
