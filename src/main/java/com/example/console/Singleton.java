package com.example.console;

public class Singleton {
    private static Singleton instance = null;

    private Singleton() {

    }

    //lasy initialization
    public static Singleton getInstance() {
        if(instance == null) {
            synchronized (Singleton.class) {
                if(instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }

    //lasy initialization
    public static synchronized Singleton getInstance1() {
        if(instance == null)
            instance = new Singleton();
        return instance;
    }

    //eager initialization
    private static Singleton instance1 = new Singleton();
    public static Singleton getInstance3() {
        return instance1;
    }

    //static block initialization for exception handling
    static{
        try{
            instance = new Singleton();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }
    public static Singleton getInstance4() {
        return instance1;
    }
}
