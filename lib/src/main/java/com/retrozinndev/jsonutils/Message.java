package com.retrozinndev.jsonutils;

public abstract class Message {

    public static enum Type {
        Error,
        Alert,
        Normal,
        Tip
    }

    public static void send(Type type, String message) {
        if(type.equals(Type.Alert)) {
            System.out.println("JSONutils: Alert: " + message);
        } else if(type.equals(Type.Error)) {
            System.err.println("JSONutils: Error: " + message);
        } else if(type.equals(Type.Tip)) {
            System.err.println("JSONutils: Tip: " + message);
        } else {
            System.out.println("JSONutils: " + message);
        }
    }
}