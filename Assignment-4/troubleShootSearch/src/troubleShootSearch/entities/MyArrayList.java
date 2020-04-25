package troubleShootSearch.entities;

import java.util.ArrayList;
import java.util.List;

import troubleShootSearch.visitor.Visitor;

public class MyArrayList implements Visitables {

	private ArrayList<List<String>> myArrayListElement = new ArrayList<>();

	public MyArrayList(ArrayList<List<String>> myArrayListElement) {
		this.myArrayListElement = myArrayListElement;
	}

	public ArrayList<List<String>> getMyArrayListElement() {
		return myArrayListElement;
	}

	public void setMyArrayListElement(ArrayList<List<String>> myArrayListElement) {
		this.myArrayListElement = myArrayListElement;
	}

	@Override
	public String toString() {
		return "MyArrayList [myArrayListElement=" + myArrayListElement + "]";
	}

	@Override
	public void accept(Visitor visitor) {

		visitor.visit(this);
	}

}
