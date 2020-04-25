package genericCheckpointing.util;


import java.util.Objects;

public class MyAllTypesFirst extends SerializableObject{
    private int myInt;
    private long myLong;
    private long myOtherLong;
    private String myString;
    private boolean myBool;
    private int myOtherInt;

    /**
     * Empty argument constructor
     */
    public MyAllTypesFirst() {

    }

    /**
     * Parameterized constructor
     * @param myIntIn myInt field
     * @param myLongIn myLong field
     * @param myOtherLongIn myLong field
     * @param myStringIn myString field
     * @param myBoolIn myBool field
     * @param myOtherIntIn myOtherInt field
     */
    public MyAllTypesFirst(int myIntIn, long myLongIn, long myOtherLongIn, String myStringIn, boolean myBoolIn, int myOtherIntIn
                           ) {
        this.myInt = myIntIn;
        this.myLong = myLongIn;
        this.myOtherLong = myOtherLongIn;
        this.myString = myStringIn;
        this.myBool = myBoolIn;
        this.myOtherInt = myOtherIntIn;
    }

    public int getMyInt() {
        return myInt;
    }

    public void setMyInt(Integer myInt) {
        this.myInt = myInt;
    }

    public long getMyLong() {
        return myLong;
    }

    public void setMyLong(Long myLong) {
        this.myLong = myLong;
    }
    
    public long getMyOtherLong() {
        return myOtherLong;
    }

    public void setMyOtherLong(Long myOtherLong) {
        this.myOtherLong = myOtherLong;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public boolean getMyBool() {
        return myBool;
    }

    public void setMyBool(Boolean myBool) {
        this.myBool = myBool;
    }

    public int getMyOtherInt() {
        return myOtherInt;
    }

    public void setMyOtherInt(Integer myOtherInt) {
        this.myOtherInt = myOtherInt;
    }



    @Override
    public String toString() {
        return "MyAllTypesFirst{" +
                "myInt=" + myInt +
                ", myLong=" + myLong +
                ", myOtherLong=" + myOtherLong +
                ", myString='" + myString + '\'' +
                ", myBool=" + myBool +
                ", myOtherInt=" + myOtherInt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyAllTypesFirst that = (MyAllTypesFirst) o;
        return getMyInt() == that.getMyInt() &&
                getMyLong() == that.getMyLong() &&
                getMyOtherLong() == that.getMyOtherLong() &&
                getMyBool() == that.getMyBool() &&
                getMyOtherInt() == that.getMyOtherInt() &&
                Objects.equals(getMyString(), that.getMyString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMyInt(), getMyLong(), getMyOtherLong(), getMyString(), getMyBool(), getMyOtherInt());
    }
}
