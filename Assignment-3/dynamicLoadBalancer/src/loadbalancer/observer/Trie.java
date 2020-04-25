package loadbalancer.observer;

import java.util.HashMap;
import java.util.Map;
//

class TrieNode {
	char node_val;
	Map<Character, TrieNode> children;
	boolean isEnd;
	ServiceManager sm;

	public TrieNode(char ch) {
		this.node_val = ch;
		this.children = new HashMap<>();
		this.isEnd = false;
		this.sm = null;
	}

	public Map<Character, TrieNode> getChildren() {
		return this.children;
	}

	public void setEnd(boolean val) {
		this.isEnd = val;
	}

	public boolean getIsEnd() {
		return this.isEnd;
	}

	public ServiceManager getSMInstance() {
		return this.sm;
	}

	public void setSMInstance(ServiceManager currSM) {
		this.sm = currSM;
	}
}

class Trie {
	private TrieNode root;

	public Trie() {
		this.root = new TrieNode((char) '0');
	}

	public void insert(String s, ServiceManager sm) {
		TrieNode currNode = this.root;
		for (int idx = 0; idx < s.length(); idx++) {
			Map<Character, TrieNode> children = currNode.getChildren();
			if (children.containsKey(s.charAt(idx))) {
				currNode = children.get(s.charAt(idx));
			} else {
				TrieNode newNode = new TrieNode(s.charAt(idx));
				children.put(s.charAt(idx), newNode);
				currNode = newNode;
			}
		}
		currNode.setEnd(true);
		currNode.setSMInstance(sm);
	}

	public ServiceManager search(String s) {
		TrieNode currNode = this.root;
		for (int idx = 0; idx < s.length(); idx++) {
			Map<Character, TrieNode> children = currNode.getChildren();
			if (children.containsKey(s.charAt(idx))) {
				currNode = children.get(s.charAt(idx));
			} else {
				return null;
			}
		}
		if (currNode.getIsEnd()) {
			return currNode.getSMInstance();
		} else {
			return null;
		}
	}

	private boolean findAndDelete(String serviceName, TrieNode currNode, int currIndex) {
		if (currNode == null) {
			return false;
		}
		if (currNode.getIsEnd()) {
			return true;
		}
		Map<Character, TrieNode> children = currNode.getChildren();
		if (children.containsKey(serviceName.charAt(currIndex))) {
			// currNode = children.get(serviceName.charAt(currIndex));
			boolean re = this.findAndDelete(serviceName, children.get(serviceName.charAt(currIndex)), currIndex + 1);
			if (re) {
				if (currNode == this.root) {
					return true;
				}
				TrieNode delNode = children.get(serviceName.charAt(currIndex));
				Map<Character, TrieNode> grandChildren = delNode.getChildren();
				if (grandChildren.size() < 1)
					children.remove(serviceName.charAt(currIndex));
				return true;
			}
			return re;
		} else {
			return false;
		}
	}

	public boolean remove(String serviceName) {

		TrieNode currNode = this.root;
		// traverse till leaf
		return this.findAndDelete(serviceName, currNode, (int) 0);

	}
}
