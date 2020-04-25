package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.TransferI;

/**
 * Any class implementing serialization has to implement this interface
 */
public interface SerStrategy {
    void processInput(SerializableObject sObject, TransferI transfer);
}
