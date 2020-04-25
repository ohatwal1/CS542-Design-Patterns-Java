package genericCheckpointing.xmlStoreRestore;

/**
 * Exposes static utility methods to serialize all primitive data types
 */
class SerializeTypes {

    static String serializeInt(String fieldName, int value) {
        return "  <" + fieldName + " xsi:type=\"xsd:int\">" + value + "</" + fieldName + ">\n";
    }

    static String serializeLong(String fieldName, long value) {
        return "  <" + fieldName + " xsi:type=\"xsd:long\">" + value + "</" + fieldName + ">\n";
    }

    static String serializeString(String fieldName, String value) {
        return "  <" + fieldName + " xsi:type=\"xsd:string\">" + value + "</" + fieldName + ">\n";
    }

    static String serializeBoolean(String fieldName, boolean value) {
        return "  <" + fieldName + " xsi:type=\"xsd:boolean\">" + value + "</" + fieldName + ">\n";
    }

    static String serializeDouble(String fieldName, double value) {
        return "  <" + fieldName + " xsi:type=\"xsd:double\">" + value + "</" + fieldName + ">\n";
    }

    static String serializeFloat(String fieldName, float value) {
        return "  <" + fieldName + " xsi:type=\"xsd:float\">" + value + "</" + fieldName + ">\n";
    }

    static String serializeShort(String fieldName, short value) {
        return "  <" + fieldName + " xsi:type=\"xsd:short\">" + value + "</" + fieldName + ">\n";
    }

    static String serializeChar(String fieldName, char value) {
        return "  <" + fieldName + " xsi:type=\"xsd:char\">" + value + "</" + fieldName + ">\n";
    }
}
