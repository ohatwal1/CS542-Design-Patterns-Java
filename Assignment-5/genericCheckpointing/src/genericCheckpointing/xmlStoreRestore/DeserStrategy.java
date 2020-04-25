package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.TransferI;

/**
 * Any class implementing deserialization has to implement this interface
 */
public interface DeserStrategy {
    SerializableObject processOutput(TransferI transfer);
}
