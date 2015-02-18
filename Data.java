public class Data {
	String word1, word2;
	double coOccurence;

	public Data(String w1, String w2, double coOcc) {
		// TODO Auto-generated constructor stub
		word1 = w1;
		word2 = w2;
		coOccurence = coOcc;
	}

	public Data(String line) {
		String[] data = line.split(",");
		word1 = data[0];
		word2 = data[1];
		coOccurence = Double.parseDouble(data[2]);
	}
}
