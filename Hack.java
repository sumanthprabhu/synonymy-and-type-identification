
public class Hack {

	public static void main(String[] args) {
		try {
			String fileName = args[0];
			GetData data = new GetData(fileName);
			String query = args[1];
			data.getSecondLevelSynonyms(query);
			data.printOutMap();

		} catch (Exception ex) {
			ex.printStackTrace();
			// System.out.println(ex.getMessage());
		}
	}
}
