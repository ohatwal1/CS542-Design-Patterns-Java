package genericCheckpointing.util;


/**
 * This interface is implemented by all classes which perform
 * serialized/deserialized XML.
 * The classes can write to file.
 */

public interface TransferI {
	void setInFileName(String inFileName);
	void setOutFileName(String outFileName);
    void close();
    void write(String input);
    String read();

}
