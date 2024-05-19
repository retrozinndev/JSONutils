# JSONutils
A **simple** and **easy to use** JSON Library for Java.

[![](https://jitpack.io/v/retrozinndev/JSONutils.svg)](https://jitpack.io/#retrozinndev/JSONutils)
[![GitHub Release](https://img.shields.io/github/v/release/retrozinndev/JSONutils?include_prereleases&sort=semver&display_name=release&style=flat)](https://github.com/retrozinndev/JSONutils/releases/latest)
[![GitHub License](https://img.shields.io/github/license/retrozinndev/JSONutils?style=flat)](https://choosealicense.com/licenses/gpl-3.0/)
[![GitHub Issues or Pull Requests](https://img.shields.io/github/issues-pr/retrozinndev/JSONutils?style=flat)](https://github.com/retrozinndev/JSONutils/pulls)

> ⚠️ Notice: JSONutils is still under development, currently at the **Beta** stage. Please [help this project](#-Contributing) if you can!

## TODO List
 - Make a class for formatting single-lined JSON files to multi-line.

## How it works
JSONutils Library's focus is the `JSON` class. Make use of the main class for reading and writing JSONs.
Please refer to the [Wiki](https://github.com/retrozinndev/JSONutils/wiki) to learn more how JSONutils work.

### Classes

| Class Name | About the Class |
| - | - |
| **JSON** | JSONutils main class. When creating a new JSON class instance, searches for the mentioned file. If the file exists, then the library reads it, adds variables and values contained in it to a HashMap (`jsonMap`). You can get the Map using the `JSON.toMap()` method |
| **JSONBuilder** | You can use it separately from the `JSON` class or use it in a single line. It's Used to build JSON files. **JSONBuilder** class is useful for  writing new new JSON files |
| **JSONReader** | When the **JSON** class is instantiated, the **JSONReader** class is used for calling the `JSONReader.readMap()` function. This function reads the JSON  file, gets the variables and their values, adds them to a **Map** of **<String, Object>** and returns the used HashMap(`JSON.jsonMap<>`) with all variables in it |
| **Message** | This class is internally used to send console messages, like reading/writing errors, warnings and syntax issues. |

## Usage
Here are some usage examples of JSONutils:
### Directory
```java
 JSON json = new JSON("example.json"); //Initializes the JSON instance with a file directory
 /* Adds example variables to JSON queue */
 json.newVariable("someKeyString", "someStringValue");
 json.newVariable("someKeyBool", true);
 json.write(); // Merges queue into JSON map, writes the JSON map then reads JSON again.

 /* Prints a key and its respective value */
 System.out.println("someKeyString -> " + json.getValue("someKeyString"));
```
### File
Works the same as when [using a directory](#Directory), but using a File class instance instead.
```java
 File fileExample = new File("exampleJSON.json"); // Creates a new File class instance with the given name
 JSON json = new JSON(fileExample); //Initializes the JSON instance with a File class instance
 /* your code here */
```

## ❤️ Contributing
### How to Contribute
You're free to contribute with code, ideas, suggestions, feature requests... and many more! If you just want to suggest a feature, or a change, you can create a [New Issue](https://github.com/retrozinndev/JSONutils/issues/new) and say what you want to be in the JSONutils Library! Your contribution is always welcome!

## 📜 License
This library is licensed under the [GNU General Public License v3](https://choosealicense.com/licenses/gpl-3.0)(and further versions), meaning you can **distribute**, **use the library commercially**, **modify** and **use the library in your personal projects(privately or publicly)**. Feel free to suggest your modified version of the JSONutils Library to the official one! Your contribution is always welcome!
