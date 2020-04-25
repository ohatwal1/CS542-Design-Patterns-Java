package troubleShootSearch.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import troubleShootSearch.entities.Node;
import troubleShootSearch.visitor.Visitor;

public class MyTree implements Visitables {

	// Root of Binary Tree
	Node root;
	Node left;
	Node right;

	// Constructors
	// MyTree(ArrayList<List<String>> myArrayListElement) {
	// root = new Node();
	// root.setWord(myArrayListElement.get(0).get(0));
	// }

	MyTree() {
		this.root = null;
	}

	public MyTree(ArrayList<List<String>> myArrayListElement) {
		this.root = buildTree(myArrayListElement);
	}

	private Node buildTree(ArrayList<List<String>> myArrayListElement) {
		Node root = null;
		for (List<String> element : myArrayListElement) {
			root = insertNode(root, element.get(0), element.get(1));
		}
		return root;

	}

	/**
	 * Binary Search tree insert
	 * 
	 * @param root
	 * @param s
	 * @return
	 */
	public Node insertNode(Node root, String s, String lineNo) { // Binary Search tree insert
		if (root == null) {
			root = new Node(s, null, null, lineNo);
			return root;
		} else {
			if (s.compareTo((String) (root.getWord())) == 0) {
				root.addLineNo(lineNo);
				return root; /* duplicate word found - do nothing */
			} else if (s.compareTo((String) (root.getWord())) < 0)
				root.left = insertNode(root.left, s, lineNo);
			else
				root.right = insertNode(root.right, s, lineNo);
			return root;
		}
	}

	public Map<String, ArrayList<String>> getTree() {
		Map<String, ArrayList<String>> tree = new HashMap<>();
		tree = this.inorderTraversal(this.root, tree);
		return tree;
	}

	public Map<String, ArrayList<String>> inorderTraversal(Node root, Map<String, ArrayList<String>> tree) {
		if (root != null) {
			tree = inorderTraversal(root.left, tree);
			tree.put(root.getWord(), root.getLineNumbersFoundIn());
			tree = inorderTraversal(root.right, tree);
		}
		return tree;
	}

	@Override
	public String toString() {
		return "MyTree [root=" + root + "]";
	}

	@Override
	public void accept(Visitor visitor) {

		visitor.visit(this);
	}
}
