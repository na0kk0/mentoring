package com.naokko;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        JsonSerializer serializer = new JsonSerializer();
        Cat cat = new Cat("Meowi", 1, "Orange");

        System.out.println(serializer.serilaize(cat));
        Cat test = serializer.deserialize(Cat.class,"{name:Naokko,age:1,color:Black}");
        System.out.println(serializer.serilaize(test));
    }
}