package genericCheckpointing.server;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.MySpecialTypes;

public interface StoreI extends StoreRestoreI {

	void writeObj(MyAllTypesFirst aRecord, String wireFormat);
	void writeObj(MyAllTypesSecond bRecord, String wireFormat);
	void writeObj(MySpecialTypes sRecord, String wireFormat);

}
