package troubleShootSearch.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import troubleShootSearch.entities.MyArrayList;
import troubleShootSearch.entities.MyTree;
import troubleShootSearch.entities.Synonyms;

public class UserVisitor implements Visitor {

	private String[] userQuery;
	private Map<String, String> exactMatchedSolutions;
	private ArrayList<String> partialMatchedSolutions;
	private Map<String, String> semanticMatchedSolutions;

	public UserVisitor(String[] userQuery) {
		this.userQuery = userQuery;
		this.exactMatchedSolutions = new HashMap<>();
		this.partialMatchedSolutions = new ArrayList<>();
		this.semanticMatchedSolutions = new HashMap<>();
	}

	public Map<String, String> getExactMatchedSolutions() {
		return exactMatchedSolutions;
	}

	public void setExactMatchedSolutions(Map<String, String> exactMatchedSolutions) {
		this.exactMatchedSolutions = exactMatchedSolutions;
	}

	public ArrayList<String> getPartialMatchedSolutions() {
		return partialMatchedSolutions;
	}

	public void setPartialMatchedSolutions(ArrayList<String> partialMatchedSolutions) {
		this.partialMatchedSolutions = partialMatchedSolutions;
	}



	public Map<String, String> getSemanticMatchedSolutions() {
		return semanticMatchedSolutions;
	}

	public void setSemanticMatchedSolutions(Map<String, String> semanticMatchedSolutions) {
		this.semanticMatchedSolutions = semanticMatchedSolutions;
	}

	@Override
	public void visit(MyArrayList arrayList) {
		// Find exact matched words
		int currCount = 1;
		for (List<String> lineAndLineNo : arrayList.getMyArrayListElement()) {
			boolean isMatched = false;
			String[] lineWords = lineAndLineNo.get(0).toLowerCase().split(" ");
			Map<String, Integer> lineWordsMap = new HashMap<>();
			for (String lWord : lineWords) {
				if (lineWordsMap.containsKey(lWord))
					lineWordsMap.put(lWord, lineWordsMap.get(lWord) + 1);
				else
					lineWordsMap.put(lWord, 1);
			}
			for (String uqWord : userQuery) {
				uqWord = uqWord.toLowerCase();
				if (lineWordsMap.containsKey(uqWord)) {
					isMatched = true;
					continue;
				} else {
					isMatched = false;
					break;
				}
			}
			if (isMatched) {
				this.exactMatchedSolutions.put(Integer.toString(currCount++), lineAndLineNo.get(0));

			}

		}

	}

	@Override
	public void visit(MyTree tree) {
		// Find Partial matched word using substring, larger length word
		// will be considered as string,
		// small will be used to check if this string is a substring of larger one
		Map<String, ArrayList<String>> treeMap = tree.getTree();
		int matchCount = 0;
		// ArrayList<String> lineNumbers = new ArrayList<>();
		String lineNumbers = "";
		for (String word : userQuery) {
			word = word.toLowerCase();
			for (String treeWord : treeMap.keySet()) {
				treeWord = treeWord.toLowerCase();
				int maxLen = 0;
				if (treeWord.length() < word.length())
					maxLen = treeWord.length();
				else
					maxLen = word.length();
				double sliceIndex = 0;
				if (maxLen > 4)
					sliceIndex = Math.ceil(maxLen / 2.0);
				else
					sliceIndex = 3;
				if (treeWord.length() > 3
						&& treeWord.substring(0, (int) sliceIndex).contains(word.substring(0, (int) sliceIndex))
						&& !treeWord.equals(word)) {
					matchCount = matchCount + 1;
					// lineNumbers.add(treeMap.get(treeWord).toString());
					lineNumbers += treeMap.get(treeWord).toString() + " ";
				}
			}
		}
		if (matchCount != 0)
			this.partialMatchedSolutions.add(Integer.toString(matchCount) + " " + lineNumbers);
	}

	@Override
	public void visit(Synonyms synonyms) {
		// Semantic match - replace words in our original query of user and the try to find exact
		// match
		int currCount = 1; 
		Map<String, String> synonymsMap = synonyms.getSynonyms();
		for (List<String> lineAndLineNo : synonyms.getMyArrayListElement()) {
			boolean isMatched = false;
			String[] lineWords = lineAndLineNo.get(0).toLowerCase().split(" ");
			Map<String, Integer> lineWordsMap = new HashMap<>();
			for (String lWord : lineWords) {
				if (lineWordsMap.containsKey(lWord))
					lineWordsMap.put(lWord, lineWordsMap.get(lWord) + 1);
				else
					lineWordsMap.put(lWord, 1);
			}
			for (String uqWord : userQuery) {
				uqWord = uqWord.toLowerCase();
				if (lineWordsMap.containsKey(uqWord)) {
					isMatched = true;
					continue;
				} else {
					if (synonymsMap.containsKey(uqWord)) {
						String syn = synonymsMap.get(uqWord);
						if (lineWordsMap.containsKey(syn)) {
							isMatched = true;
							continue;
						} else {
							isMatched = false;
							break;
						}

					}
					isMatched = false;
					break;
				}
			}
			if (isMatched) {
				if(!this.exactMatchedSolutions.containsKey(lineAndLineNo.get(1)))
					this.semanticMatchedSolutions.put(Integer.toString(currCount++), lineAndLineNo.get(0));

			}
		}
	}

}
