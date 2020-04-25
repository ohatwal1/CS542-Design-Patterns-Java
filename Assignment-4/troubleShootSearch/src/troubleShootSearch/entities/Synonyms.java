package troubleShootSearch.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import troubleShootSearch.visitor.Visitor;

public class Synonyms implements Visitables{

	private Map<String, String> synonyms = new HashMap<>();
	private ArrayList<List<String>> myArrayListElement = new ArrayList<>();

	public Synonyms(Map<String, String> synonyms, ArrayList<List<String>> myArrayListElement) {
		this.synonyms = synonyms;
		this.myArrayListElement = myArrayListElement;
	}

	public ArrayList<List<String>> getMyArrayListElement() {
		return myArrayListElement;
	}

	public void setMyArrayListElement(ArrayList<List<String>> myArrayListElement) {
		this.myArrayListElement = myArrayListElement;
	}

	public Map<String, String> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(Map<String, String> synonyms) {
		this.synonyms = synonyms;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		
	}

}
