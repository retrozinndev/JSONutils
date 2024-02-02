# JSONutils
A simple JSON Library for java, still in development.

### TODO:
 - Add JSON variables to file;
 - Read file for existing variables before writing new ones;
 - Only write when `JSONBuilder.makeJSON()` is called;
 - Make a JSONFormatter class.

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
