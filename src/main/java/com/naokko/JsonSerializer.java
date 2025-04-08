package com.naokko;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class JsonSerializer {
    public static <T> String serilaize(T source) throws IllegalAccessException, InvocationTargetException {
        String json = "{";
        Method[] methods = source.getClass().getDeclaredMethods();
        for(Method method : methods)
        {
            if(method.getName().startsWith("get") || method.getName().startsWith("is"))
            {
                String field = method.getName().startsWith("get") ? method.getName().substring(3) : method.getName().substring(2);
                field = field.substring(0, 1).toLowerCase() + field.substring(1);
                json += field+":"+method.invoke(source) + ",";
            }
        }
        json = json.substring(0, json.length() - 1) + "}";
        return json;
    }
    public static <T> T deserialize(Class<T> target, String json){
        try{
            Method[] methods = target.getDeclaredMethods();
            T object = target.getDeclaredConstructor().newInstance();
            json = json.substring(1, json.length() - 1); //Remove the "{" and "}"
            String[] values = json.split(","); //Store the fields and values as ["field1:value1","field2:value2",...]
            HashMap<String, String> fieldValueMap = new HashMap<>(); //I use a Hash map to store "the name of the field" -> "the value of the field"
            for (String value : values) {
                String field = value.substring(0, value.indexOf(":"));
                value = value.substring(value.indexOf(":")+1);
                fieldValueMap.put(field.toLowerCase(), value);
            }
            for(Method method : methods){
                if(method.getName().startsWith("set"))
                {
                    String field =method.getName().substring(3).toLowerCase();
                    String type = method.getParameterTypes()[0].getName();
                    switch(type) // I could use a function to check this outside the deserialize function
                    {
                        case "java.lang.String":
                            method.invoke(object, fieldValueMap.get(field));
                            break;
                        case "int":
                            method.invoke(object, Integer.parseInt(fieldValueMap.get(field.toLowerCase())));
                            break;
                        case "boolean":
                            method.invoke(object, Boolean.parseBoolean(fieldValueMap.get(field.toLowerCase())));
                        //Here I should add more cases for the data types... By now It works for the Cat class
                    }
                }
            }
            return object;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}