package genericCheckpointing.util;

import java.util.Objects;
import genericCheckpointing.util.MyAllTypesFirst;

public class MyAllTypesSecond extends MyAllTypesFirst{

    private double myDoubleT;
    private double myOtherDoubleT;
    private float myFloatT;
    private short myShortT;
    private char myCharT;

    /**
     * Empty argument constructor
     */
    public MyAllTypesSecond() {

    }

    /**
     * Parameterized constructor
     *
     * @param myDoubleTIn myDouble
     * @param myOtherDoubleTIn myOtherDouble
     * @param myFloatTIn myFloat
     * @param myShortTIn myShort
     * @param myCharTIn myChar
     */
    public MyAllTypesSecond(double myDoubleTIn, float myFloatTIn, short myShortTIn, char myCharTIn,double myOtherDoubleTIn
                            ) {
        this.myDoubleT = myDoubleTIn;
        this.myFloatT = myFloatTIn;
        this.myShortT = myShortTIn;
        this.myCharT = myCharTIn;
        this.myOtherDoubleT = myOtherDoubleTIn;
    }

    public double getMyDoubleT() {
        return myDoubleT;
    }

    public void setMyDoubleT(Double myDoubleT) {
        this.myDoubleT = myDoubleT;
    }
    
    public double getMyOtherDoubleT() {
        return myOtherDoubleT;
    }

    public void setMyOtherDoubleT(Double myOtherDoubleT) {
        this.myOtherDoubleT = myOtherDoubleT;
    }

    public float getMyFloatT() {
        return myFloatT;
    }

    public void setMyFloatT(Float myFloatT) {
        this.myFloatT = myFloatT;
    }

    public short getMyShortT() {
        return myShortT;
    }

    public void setMyShortT(Short myShortT) {
        this.myShortT = myShortT;
    }

    public char getMyCharT() {
        return myCharT;
    }

    public void setMyCharT(Character myCharT) {
        this.myCharT = myCharT;
    }


    @Override
    public String toString() {
        return "MyAllTypesSecond{" +
                "myDoubleT=" + myDoubleT +
                "myOtherDoubleT=" + myOtherDoubleT +
                ", myFloatT=" + myFloatT +
                ", myShortT=" + myShortT +
                ", myCharT=" + myCharT +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyAllTypesSecond that = (MyAllTypesSecond) o;
        return Double.compare(that.getMyDoubleT(), getMyDoubleT()) == 0 &&
        		Double.compare(that.getMyOtherDoubleT(), getMyOtherDoubleT()) == 0 &&
                Float.compare(that.getMyFloatT(), getMyFloatT()) == 0 &&
                getMyShortT() == that.getMyShortT() &&
                getMyCharT() == that.getMyCharT();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMyDoubleT(), getMyOtherDoubleT(), getMyFloatT(), getMyShortT(), getMyCharT());
    }
}
