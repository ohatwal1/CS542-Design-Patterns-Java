package genericCheckpointing.xmlStoreRestore;

/**
 * Exposes static utility methods to deserialize various data types
 */
class DeserializeTypes {

    static Integer deserializeInt(String input) {
        return Integer.valueOf(input);
    }

    static Long deserializeLong(String input) {
        return Long.valueOf(input);
    }

    static Boolean deserializeBoolean(String input) {
        return Boolean.valueOf(input);
    }

    static Double deserializeDouble(String input) {
        return Double.valueOf(input);
    }

    static Float deserializeFloat(String input) {
        return Float.valueOf(input);
    }

    static Short deserializeShort(String input) {
        return Short.valueOf(input);
    }

    static Character deserializeChar(String input) {
        return input.charAt(0);
    }
}
