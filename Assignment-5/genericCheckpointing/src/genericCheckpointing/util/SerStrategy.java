package genericCheckpointing.util;

public interface SerStrategy {
	
	void processInput(SerializableObject sObject, TransferI transfer);

}
