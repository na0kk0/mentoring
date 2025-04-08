package com.naokko;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        JsonSerializer serializer = new JsonSerializer();
        Cat cat = new Cat("Meowi", 1, "Orange");
        Record record = new Record("4Ever", "Axolotes Mexicanos", 2024, true);

        System.out.println(serializer.serilaize(cat));
        System.out.println(serializer.serilaize(record));
        Cat testCat = serializer.deserialize(Cat.class,"{name:Naokko,age:1,color:Black}");
        Record testRecord = serializer.deserialize(Record.class,"{name:Synkronized,artist:Jamiroquai,yearrelease:1999,favorite:true}");
        System.out.println(serializer.serilaize(testCat));
        System.out.println(serializer.serilaize(record));
        Record test2 = serializer.deserialize(Record.class, serializer.serilaize(record));
        System.out.println(test2.getArtist());

    }
}