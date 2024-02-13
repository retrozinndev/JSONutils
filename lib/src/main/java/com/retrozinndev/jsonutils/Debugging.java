package com.retrozinndev.jsonutils;

public class Debugging {
    public static void main(String[] args) {
        JSON json = new JSONBuilder("debug.json").newVariable("test", 1).makeJSON();
    }
}
