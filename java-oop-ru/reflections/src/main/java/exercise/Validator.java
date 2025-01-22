package exercise;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// BEGIN
class Validator {

    public static ArrayList<String> validate(Object obj) {
        ArrayList<String> listOfFields = new ArrayList<>();
        Class<?> cl = obj.getClass();
        try {
            var l2 = cl.getDeclaredFields();
            for (var field : l2) {
                Annotation nn = field.getAnnotation(NotNull.class);
                field.setAccessible(true); // необходимо , т.к. private
                if (nn != null && null == field.get(obj)) {
                    listOfFields.add((String) field.getName());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return listOfFields;
    }

    public static Map advancedValidate(Object obj) {
        //ArrayList<String> listOfMessages = new ArrayList<>();
        HashMap<String, ArrayList<String>> mapOfWrongFields = new HashMap<>();
        Class<?> cl = obj.getClass();
        try {
            for (var field : cl.getDeclaredFields()) {
                //Integer i = 0;
                ArrayList<String> listOfMessages = new ArrayList<>();
                NotNull notnull = field.getAnnotation(NotNull.class);  // важно не просто тип Annotation,
                                                                        // а конкретную аннотацию Notnull
                MinLength minSize = field.getAnnotation(MinLength.class);
                field.setAccessible(true); // необходимо , т.к. private
                if (notnull != null && null == field.get(obj)) {
                    listOfMessages.add("can not be null");
                    if (minSize != null) {
                        listOfMessages.add("length must be larger than " + minSize.minLength());
                    }
                }
                if (minSize != null && null != field.get(obj) && (field.get(obj).toString().length() < 3)) {
                    listOfMessages.add("length must be larger than " + minSize.minLength());
                }
                if (!listOfMessages.isEmpty()) {
                    mapOfWrongFields.put(field.getName(), listOfMessages);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return mapOfWrongFields;
    }

}
// END
