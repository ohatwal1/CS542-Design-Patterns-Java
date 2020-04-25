package troubleShootSearch.entities;

import java.util.ArrayList;

public class Node {
	private String word;
	Node left;
	Node right;

	// Line numbers of the lines in the input file in which the word was present.
	// Feel free to use a collection other than List.
	private ArrayList<String> lineNumbersFoundIn;

	public Node(String x, Node l, Node r, String lineNo) {
		left = l;
		right = r;
		word = x;
		lineNumbersFoundIn = new ArrayList<String>();
		lineNumbersFoundIn.add(lineNo);
	}

	public Node() {

	}

	public ArrayList<String> getLineNumbersFoundIn() {
		return lineNumbersFoundIn;
	}

	public void setLineNumbersFoundIn(ArrayList<String> lineNumbersFoundIn) {
		this.lineNumbersFoundIn = lineNumbersFoundIn;
	}

	public String getWord() {
		return word;
	}

	/**
	 * @param word
	 *            the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	public void addLineNo(String lineNo) {
		this.lineNumbersFoundIn.add(lineNo);

	}
}
