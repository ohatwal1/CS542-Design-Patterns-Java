package troubleShootSearch.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import troubleShootSearch.entities.MyArrayList;
import troubleShootSearch.entities.MyTree;
import troubleShootSearch.entities.Synonyms;
import troubleShootSearch.entities.Visitables;

/**
 * @author Omkar
 *
 */
/**
 * @author Omkar
 *
 */
public class FileProcessor {

	static String outputFilePath = "";

	/**
	 * @param filePath
	 *            Read Technical info and store it to arrayList
	 * @return
	 */
	public MyTree storeData(String[] filePath) {
		Scanner sc = null;

		try {
			File file = new File(filePath[0]);
			sc = new Scanner(file);

			outputFilePath = filePath[3];
			ArrayList<List<String>> myArrayListElement = new ArrayList<>();
			String lineNo = "1";
			int line_number = 1;
			while (sc.hasNextLine()) {
				String[] details = sc.nextLine().split(" ");
				for (String detail : details) {
					ArrayList<String> innerList = new ArrayList<>();
					innerList.add(detail);
					innerList.add(lineNo);
					myArrayListElement.add(innerList);
				}
				line_number++;
				lineNo = Integer.toString(line_number);
			}

			MyTree myTree = storeDataInTreeStructure(myArrayListElement);

			MyLogger.writeMessage("Tree Structure: " + myTree.getTree().toString(), MyLogger.DebugLevel.INPUT_READ);

			return myTree;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			sc.close();
		}
		return null;

	}

	public MyArrayList storeDataIntoArray(String[] filePath) {
		Scanner sc = null;

		try {
			File file = new File(filePath[0]);
			sc = new Scanner(file);

			ArrayList<List<String>> myArrayListElement = new ArrayList<>();
			String lineNo = "1";
			int line_number = 1;
			while (sc.hasNextLine()) {
				ArrayList<String> innerList = new ArrayList<>();
				innerList.add(sc.nextLine());
				innerList.add(lineNo);
				myArrayListElement.add(innerList);

				line_number++;
				lineNo = Integer.toString(line_number);
			}
			MyArrayList myArrayList = storeDataInArrayList(myArrayListElement);
			MyLogger.writeMessage("Array List: " + myArrayList.getMyArrayListElement().toString(),
					MyLogger.DebugLevel.INPUT_READ);

			return myArrayList;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			sc.close();
		}
		return null;

	}

	public Synonyms addSynonyms(String[] filePath, ArrayList<List<String>> myArrayListElement) {
		Scanner sc = null;
		Map<String, String> inputData = new HashMap<>();

		try {
			File file = new File(filePath[2]);
			sc = new Scanner(file);

			while (sc.hasNextLine()) {
				String[] details = sc.nextLine().split("=");
				inputData.put(details[0].toLowerCase(), details[1].toLowerCase());
				inputData.put(details[1].toLowerCase(), details[0].toLowerCase());
			}
			Synonyms synonyms = new Synonyms(inputData, myArrayListElement);
			MyLogger.writeMessage("Synonyms : " + synonyms.getSynonyms().toString(), MyLogger.DebugLevel.INPUT_READ);

			return synonyms;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		return null;
	}

	public MyArrayList storeDataInArrayList(ArrayList<List<String>> myArrayListElement) {
		MyArrayList myArrayList = new MyArrayList(myArrayListElement);
		return myArrayList;
	}

	public MyTree storeDataInTreeStructure(ArrayList<List<String>> myArrayListElement) {
		MyTree myTree = new MyTree(myArrayListElement);
		return myTree;
	}

	public ArrayList<String> readDataFromUser(String[] filePath) {
		Scanner sc = null;
		try {
			File file = new File(filePath[1]);
			sc = new Scanner(file);
			ArrayList<String> userQueries = new ArrayList<>();
			while (sc.hasNextLine()) {
				String userQuery = sc.nextLine();
				userQueries.add(userQuery);
			}
			return userQueries;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		return null;

	}

	public ArrayList<Visitables> getVisitables(String[] args) {
		ArrayList<Visitables> dataMatching = new ArrayList<>();
		dataMatching.add(this.storeData(args));
		MyArrayList arrayList = this.storeDataIntoArray(args);
		dataMatching.add(this.storeDataIntoArray(args));
		dataMatching.add(this.addSynonyms(args, arrayList.getMyArrayListElement()));
		return dataMatching;
	}
	
	public void sendDataToProcessor(StringBuilder stringData, String FilePath)
	{
		Results result = new Results();
		result.writeOutput(stringData, FilePath);
	}

}
