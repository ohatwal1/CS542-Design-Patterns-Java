package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessorUtil;
import genericCheckpointing.util.SerStrategy;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.TransferI;

public class StoreRestoreHandler implements InvocationHandler {

	private TransferI transfer ;
	public StoreRestoreHandler() {
		transfer = new FileProcessorUtil();
    }
	
	/**
     * Allows setting of file name to read/write objects from.
     * Delegates call to the composed TransportI object
     *
     * @param fileNameIn String filename
     */
	
	
    public void setCheckpointInFileName(String inFileName) {
    	transfer.setInFileName(inFileName);
    }
    
    public void setCheckpointOutFileName(String outFileName) {
    	transfer.setOutFileName(outFileName);
    }
	
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        if (method.getName().equals("writeObj")) {

            assert (args[2].equals("XML"));

            serializeData((SerializableObject)args[0], new XMLSerialization());

            return null;
        }
        else if (method.getName().equals("readObj")) {

            assert (args[0].equals("XML"));

            return deserializeData(new XMLDeserialization());
        }

        return null;
    }

	public void serializeData(SerializableObject sObject, SerStrategy sStrategy) {
		sStrategy.processInput(sObject,transfer);
	}
	
    private SerializableObject deserializeData(DeserStrategy dStrategy) {
        return dStrategy.processOutput(transfer);
    }

}
