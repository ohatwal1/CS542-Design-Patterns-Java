package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.TransferI;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import genericCheckpointing.util.SerStrategy;

public class XMLSerialization implements SerStrategy{

    /**
     * Converts the given object to serialized form and writes it to
     * the TransportI object passed in as object.
     *
     * @param sObject Object to be serialized.
     * @param transport Transport object
     */
    @Override
    public void processInput(SerializableObject sObject, TransferI transfer) {
        StringBuilder output = new StringBuilder();

        Class<?> cls = sObject.getClass();

        appendHeader(output, cls.getName());

        for (Field f : cls.getDeclaredFields()) {
            String fieldName = f.getName();

            try {
                String methodName = "get" + capitalizeFirstLetter(fieldName);
                Method getterMethod = cls.getMethod(methodName);
                Object invokeRet = getterMethod.invoke(sObject);

                if (f.getType() == int.class)
                    output.append(SerializeTypes.serializeInt(fieldName, (int)invokeRet));
                else if (f.getType() == long.class)
                    output.append(SerializeTypes.serializeLong(fieldName, (long) invokeRet));
                else if (f.getType() == String.class)
                    output.append(SerializeTypes.serializeString(fieldName, (String) invokeRet));
                else if (f.getType() == boolean.class)
                    output.append(SerializeTypes.serializeBoolean(fieldName, (boolean) invokeRet));
                else if (f.getType() == double.class)
                    output.append(SerializeTypes.serializeDouble(fieldName, (double) invokeRet));
                else if (f.getType() == float.class)
                    output.append(SerializeTypes.serializeFloat(fieldName, (float) invokeRet));
                else if (f.getType() == char.class)
                    output.append(SerializeTypes.serializeChar(fieldName, (char) invokeRet));
                else if (f.getType() == short.class)
                    output.append(SerializeTypes.serializeShort(fieldName, (short) invokeRet));

            }
            catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        appendFooter(output);

        transfer.write(output.toString());
    }

    /**
     * Add starting tags to the given StringBuilder based on classname.
     *
     * @param output StringBuilder
     * @param className Name of class
     */
    private void appendHeader(StringBuilder output, String className) {
        output.append("<DPSerialization>\n");
        output.append(" <complexType xsi:type=\"");
        output.append(className);
        output.append("\">\n");
    }

    /**
     * Add closing tags to the given StringBuilder.
     *
     * @param output StringBuilder
     */
    private void appendFooter(StringBuilder output) {
        output.append(" </complexType>\n");
        output.append("</DPSerialization>\n");
    }

    /**
     * Capitalizes first letter of the given string.
     *
     * @param input String
     * @return Modified string
     */
    private String capitalizeFirstLetter(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
