import java.io.*;
import java.util.*;

public class GetData {
	String file;
	ArrayList<Data> myList = new ArrayList<Data>();
	Map<String, Double> dataMap;
	double threshold = 5.0;
	Map<String, Double> outMap;

	public GetData(String file) throws Exception {
		// TODO Auto-generated constructor stub
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		dataMap = new HashMap<String, Double>();
		Data temp;
		int i = 0;
		while ((line = br.readLine()) != null) {
			temp = new Data(line);
			if (temp.coOccurence >= threshold) {
				myList.add(temp);
				addToMap(temp);
			}
			if (i % 1000000== 0)
//				System.out.println(i / 1000000);
			i++;
		}
		br.close();
	}

	void addToMap(Data line) {
		String key1 = line.word1;
		String key2 = line.word2;
		double score = line.coOccurence;
		double value = 0.0;
		if (dataMap.containsKey(key1))
			value = dataMap.get(key1);
		dataMap.put(key1, value + score);
		if (dataMap.containsKey(key2))
			value = dataMap.get(key2);
		dataMap.put(key2, value + score);
	}

	void printMap() {
		System.out.println(dataMap.toString());
	}

	void printOutMap() {
		for (String entry : outMap.keySet()) {
			double score = outMap.get(entry);
			String write = score + "," + entry;
			System.out.println(write);
		}
	}

	void getSynonyms(String query) {
		outMap = new HashMap<String, Double>();
		int s = myList.size();
		for (int i = 0; i < s; i++) {
			String term1 = myList.get(i).word1;
			String term2 = myList.get(i).word2;
			double coOcc = myList.get(i).coOccurence;
			if (term1.equals(query) || term2.equals(query)) {
				double count1 = dataMap.get(term1);
				double count2 = dataMap.get(term2);
				double score = 2 * coOcc / (count1 + count2);
				if (term1.equals(query)) {
					outMap.put(term2, score);
				} else {
					outMap.put(term1, score);
				}
			}
		}

	}

	void getSecondLevelSynonyms(String query) {
		int s = myList.size();
		Map<String, Double> outMap2 = new HashMap<String, Double>();
		getSynonyms(query);
		for (int i = 0; i < s; i++) {
			String term1 = myList.get(i).word1;
			String term2 = myList.get(i).word2;
			double coOcc = myList.get(i).coOccurence;
			if (!term1.equals(query) && !term2.equals(query)) {
				double count1 = dataMap.get(term1);
				double count2 = dataMap.get(term2);
				double score = 2 * coOcc / (count1 + count2);
				if (outMap.containsKey(term1)) {
					double preScore = outMap.get(term1);
					score = preScore * score;
					outMap2.put(term2, score);
				}
				if (outMap.containsKey(term2)) {
					double preScore = outMap.get(term2);
					score = preScore * score;
					outMap2.put(term1, score);
				}
			}
		}
		for (String entry : outMap2.keySet()) {
			double score = outMap2.get(entry);
			if (outMap.containsKey(entry)) {
				score = score + outMap.get(entry);
			}
			outMap.put(entry, score);
		}
	}

}
