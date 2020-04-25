package genericCheckpointing.util;

import java.util.Objects;

public class MySpecialTypes extends SerializableObject{
    private int myInt1;
    private int myInt2;
    private String myString1;
    private String myString2;
    private double myDoubleT1;
    private double myDoubleT2;


    /**
     * Empty argument constructor
     */
    public MySpecialTypes() {

    }

    /**
     * Parameterized constructor
     * @param myIntIn1 myInt1 field
     * @param myIntIn2 myInt2 field
     * @param myStringIn1 myString1 field
     * @param myStringIn2 myString2 field
     * @param myDoubleTIn1 myDoubleT1 field
     * @param myDoubleTIn1 myDoubleT1 field
     */
    public MySpecialTypes(int myIntIn1, int myIntIn2, String myStringIn1, String myStringIn2, double myDoubleT1,
    		double myDoubleT2) {
        this.myInt1 = myIntIn1;
        this.myInt2 = myIntIn2;
        this.myString1 = myStringIn1;
        this.myString2 = myStringIn2;
        this.myDoubleT1 = myDoubleT1;
        this.myDoubleT2 = myDoubleT2;
        
    }

    public int getMyInt1() {
        return myInt1;
    }

    public void setMyInt1(Integer myInt1) {
        this.myInt1 = myInt1;
    }
    
    public int getMyInt2() {
        return myInt2;
    }

    public void setMyInt2(Integer myInt2) {
        this.myInt2 = myInt2;
    }

    public String getMyString1() {
        return myString1;
    }

    public void setMyString1(String myString1) {
        this.myString1 = myString1;
    }
    
    public String getMyString2() {
        return myString2;
    }

    public void setMyString2(String myString2) {
        this.myString2 = myString2;
    }
    
    
    public double getMyDoubleT1() {
        return myDoubleT1;
    }

    public void setMyDoubleT1(Double myDoubleT1) {
        this.myDoubleT1 = myDoubleT1;
    }
    
    public double getMyDoubleT2() {
        return myDoubleT2;
    }

    public void setMyDoubleT2(Double myDoubleT2) {
        this.myDoubleT2 = myDoubleT2;
    }



    @Override
    public String toString() {
        return "MySpecialTypes{" +
                "myInt1=" + myInt1 +
                "myInt2=" + myInt2 +
                ", myString1='" + myString1 + '\'' +
                ", myString2='" + myString2 + '\'' +
                "myDoubleT1=" + myDoubleT1 +
                "myDoubleT2=" + myDoubleT2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MySpecialTypes that = (MySpecialTypes) o;
        return getMyInt1() == that.getMyInt1() &&
        		getMyInt2() == that.getMyInt2() &&
        		Double.compare(that.getMyDoubleT1(), getMyDoubleT1()) == 0 &&
                Double.compare(that.getMyDoubleT2(), getMyDoubleT2()) == 0 &&
        		Objects.equals(getMyString1(), that.getMyString1()) &&
        		Objects.equals(getMyString2(), that.getMyString2());
                
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMyInt1(), getMyInt2(), getMyString1(), getMyString2(), getMyDoubleT1(), getMyDoubleT2());
    }
}
