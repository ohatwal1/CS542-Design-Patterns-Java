package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.TransferI;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLDeserialization implements DeserStrategy {

    /**
     * Reads a serialized object from transport object and deserializes
     * it into an Object of type SerializableObject using reflections.
     *
     * @param transport Transport object to read from
     * @return Deserialized object
     */
    @Override
    public SerializableObject processOutput(TransferI transfer) {
        String serializedString = transfer.read();

        if (serializedString.length() <= 0) {
        	return null;
        }

        // Strip down the DPSerialization tags
        serializedString = stripDPSerializationTags(serializedString);

        // Get the object name and strip down complexType tags
        String objectName = getObjectName(serializedString);
        serializedString = stripComplexTypeTags(serializedString);

        // Create object
        Class<?> cls;
        SerializableObject obj;
        try {
            cls = Class.forName(objectName);
            obj = (SerializableObject)cls.newInstance();
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }

        // populate fields
        populateFields(cls, obj, serializedString);

        return obj;
    }

    /**
     * Populates the fields of given SerializableObject based on content
     * in serialized string using reflections.
     *
     * @param cls Class of which the object is a type
     * @param obj Object to be populated
     * @param serializedString Serialized version of object's fields
     */
    private void populateFields(Class<?> cls, SerializableObject obj, String serializedString) {

        // Get the field names, their types, and values
        Pattern p = Pattern.compile("<(\\w+) xsi:type=\"xsd:(\\w+)\">(.*?)</(\\w+)>");
        Matcher m = p.matcher(serializedString);

        while (m.find()) {
            String fieldName = m.group(1);
            String fieldType = m.group(2);
            String fieldValue = m.group(3);

            Object[] params = new Object[1];
            Class[] signature = new Class[1];

            switch (fieldType) {
                case "int":
                    params[0] = DeserializeTypes.deserializeInt(fieldValue);
                    signature[0] = Integer.class;
                    break;

                case "long":
                    params[0] = DeserializeTypes.deserializeLong(fieldValue);
                    signature[0] = Long.class;
                    break;

                case "string":
                    params[0] = fieldValue;
                    signature[0] = String.class;
                    break;

                case "boolean":
                    params[0] = DeserializeTypes.deserializeBoolean(fieldValue);
                    signature[0] = Boolean.class;
                    break;

                case "double":
                    params[0] = DeserializeTypes.deserializeDouble(fieldValue);
                    signature[0] = Double.class;
                    break;

                case "float":
                    params[0] = DeserializeTypes.deserializeFloat(fieldValue);
                    signature[0] = Float.class;
                    break;

                case "short":
                    params[0] = DeserializeTypes.deserializeShort(fieldValue);
                    signature[0] = Short.class;
                    break;

                case "char":
                    params[0] = DeserializeTypes.deserializeChar(fieldValue);
                    signature[0] = Character.class;
                    break;
            }

            try {
                String methodName = "set" + capitalizeFirstLetter(fieldName);
                Method setterMethod = cls.getMethod(methodName, signature);
                setterMethod.invoke(obj, params);
            }
            catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Removes unnecessary starting and ending tags
     *
     * @param input String
     * @return String with tags removed.
     */
    private String stripDPSerializationTags(String input) {
        return input.replace("<DPSerialization>", "").replace("</DPSerialization>", "");
    }

    /**
     * Gets an object name of serialized object from string
     *
     * @param input String
     * @return Object name
     */
    private String getObjectName(String input) {
        Pattern p = Pattern.compile("<complexType xsi:type=\"(.*)\">");
        Matcher m = p.matcher(input);
        if (m.find())
            return m.group(1);
        return null;
    }

    /**
     * Removes tags of type complexType from the given string
     *
     * @param input String
     * @return modified string
     */
    private String stripComplexTypeTags(String input) {
        String output = null;

        // modify complexType tags
        Pattern p = Pattern.compile("<complexType xsi:type=\"(.*)\">");
        Matcher m = p.matcher(input);
        if (m.find())
            output = m.replaceAll("");

        if (output != null)
            return output.replace("</complexType>", "");
        return null;
    }

    /**
     * Capitalizes first letter of string
     *
     * @param input String
     * @return Modified string
     */
    private String capitalizeFirstLetter(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
