import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

public class CopyUtils {
    public static <T> T deepCopy(T copyObject) {
        if (copyObject == null) {
            return null;
        }
        Class<?> clazz = copyObject.getClass();
        List<Field> fields = Arrays.stream(copyObject.getClass().getDeclaredFields()).toList();

        if (isPrimitive(clazz)) {
            return copyPrimitiveObject(copyObject);
        } else if (clazz.isArray()) {
            return copyArray(copyObject);
        } else if (isCollection(clazz)) {
            return copyCollections(copyObject);
        } else {
            return copyObject(copyObject, fields);
        }
    }

    private static <T> T copyPrimitiveObject(T obj) {
        T newObj = null;
        try {
            Class<?> clazz = obj.getClass();
            Field field = clazz.getDeclaredField("value");
            field.trySetAccessible();
            String value = obj.toString();
            Constructor<?> constructor = clazz.getDeclaredConstructor(String.class);
            newObj = (T) constructor.newInstance(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newObj;
    }

    private static <T> T copyArray(T obj) {
        int length = Array.getLength(obj);
        T newObj = (T) Array.newInstance(obj.getClass().getComponentType(), length);
        try {
            Object element = Array.get(obj, 0);

            if (!isPrimitive(element.getClass())) {
                Field[] classFields = element.getClass().getDeclaredFields();
                List<Field> fields = Arrays.stream(classFields).toList();
                for (int i = 0; i < length; i++) {
                    Array.set(newObj, i, deepCopy(Array.get(obj, i)));
                }
            } else {
                for (int i = 0; i < length; i++) {
                    Array.set(newObj, i, Array.get(obj, i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newObj;
    }

    private static <T> T copyObject(T obj, List<Field> fields) {
        T newObj = null;
        try {
            Class<?>[] fieldClasses = new Class[fields.size()];
            for (int i = 0; i < fields.size(); i++) {
                fieldClasses[i] = fields.get(i).getType();
            }

            Constructor<?> parentConstructor;
            try {
                parentConstructor = obj.getClass().getDeclaredConstructor(fieldClasses);
            } catch (Exception e) {
                parentConstructor = obj.getClass().getDeclaredConstructor();
            }
            Object[] fieldValues = new Object[fields.size()];
            for (int i = 0; i < fields.size(); i++) {
                fields.get(i).setAccessible(true);
                fieldValues[i] = deepCopy(fields.get(i).get(obj));
            }
            newObj = (T) parentConstructor.newInstance(fieldValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newObj;
    }

    private static <T> T copyCollections(T copyCollection) {
        T newObj = null;
        try {
            Class<?> clazz = copyCollection.getClass();

            Field fields = Arrays.stream(clazz.getDeclaredFields())
                    .filter(x -> x.getName().equals("a")
                            || x.getName().equals("elementData"))
                    .findFirst()
                    .get();
            fields.setAccessible(true);
            var value = fields.get(copyCollection);
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            newObj = (T) constructor.newInstance();
            for (int i = 0; i < Array.getLength(value); i++) {
                ((List) newObj).add(deepCopy(Array.get(value, i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newObj;
    }

    private static boolean isCollection(Class<?> clazz) {
        return Arrays.asList(clazz.getInterfaces())
                .stream()
                .anyMatch(x -> x.equals(Collection.class)
                        || x.equals(List.class))
                || clazz.getSuperclass().equals(AbstractList.class)
                || clazz.getSuperclass().equals(AbstractCollection.class);
    }

    private static boolean isPrimitive(Class<?> clazz) {
        boolean isContainsValue = Arrays.stream(clazz.getDeclaredFields()).anyMatch(x -> x.getName().equals("value"));
        return isContainsValue
                && clazz.getSuperclass().equals(Number.class)
                || clazz.equals(String.class);
    }
}
