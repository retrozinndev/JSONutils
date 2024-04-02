# JSONutils
A simple JSON Library for java, focused at being easy-to-use.

> ⚠️ Notice: JSONutils is still under development, currently at the **Beta** stage. Please [help this project](#-Contributing) if you can!

[![](https://jitpack.io/v/retrozinndev/JSONutils.svg)](https://jitpack.io/#retrozinndev/JSONutils)
## TODO
 - Make a JSONFormatter class.

## How it works
JSONutils Library's focus is the `JSON` class. Make use of the main class for reading and writing JSONs. 

- **JSON** <br>
JSONutils' main class. When creating a new JSON class instance, searches for the mentioned file. If the file exists, then
the library reads it, adds variables and values contained in it to a HashMap (`jsonMap<>`). You can get the Map using the `JSON.toMap()` method.

- **JSONBuilder** <br>
You can use it separately from the `JSON` class or use it in a single line. It's Used to build JSON files. **JSONBuilder** class is useful for 
writing new new JSON files.

- **JSONReader** <br>
When the **JSON** class is instantiated, the **JSONReader** class is used for calling the `JSONReader.readMap()` function. This function reads the JSON 
file, gets the variables and their values, adds them to a **Map** of **<String, Object>** and returns the used HashMap(`JSON.jsonMap<>`) with all variables in it.

- **Message** <br>
This class is only used internally for sending console messages in a more organized way inside the code.

## Usage
Here is a usage example of the JSON class.
### Using directory
```java
 JSON json = new JSON("example.json"); //Initializes the JSON instance with a file directory
 /* Adds example variables to JSON queue */
 json.newVariable("someKeyString", "someStringValue");
 json.newVariable("someKeyBool", true);
 json.newVariable("someKeyInt", 1234);
 json.write(); // Merges queue into JSON map, writes the JSON map then reads the file again, for preventing problems.

 /* Prints a key and its respective value */
 System.out.println("someKeyString -> " + json.getValue("someKeyString"));
```
### Using a File instance
Works the same as when [using a directory](#Using-directory), but using a File class instance instead.
```java
 JSON json = new JSON(new File("fileExample.json")); //Initializes the JSON instance with a File class instance
 /* your code here */
 ...
```

## ❤️ Contributing
### How to Contribute
You're free to contribute with code, ideas, suggestions, feature requests... and many more! If you just want to suggest a feature, or a change, you can create a [New Issue](repo/issues/new) or a [Start a Discussion](repo/discussions/new) and say what you want to be in the JSONutils Library! Your contribution is always welcome!

## 📜 License
This library is licensed under the [GNU General Public License v3](https://choosealicense.com/licenses/gpl-3.0/)(and further versions), meaning you can **distribute**, **use the library commercially**, **modify** and **use the library in your personal projects(privately or publicly)**. Feel free to suggest your modified version of the JSONutils Library to the official one! Your contribution is always welcome!
