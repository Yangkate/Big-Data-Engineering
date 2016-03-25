package Big.week1;

import java.util.HashMap;

import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RefineryUtilities;

public class main {
	public static void main(String[] args) throws Exception {

		String wordTxtFile = "/Users/yang/Downloads/words.txt";// read words.txt
																// param:@wordTxtFile
		String csvFile = "/Users/yang/Downloads/wikipedia_utf8_filtered_20pageviews.csv";// read
																							// CVS
																							// of
																							// random
																							// articles
																							// param:@numbersOfArticles
																							// @OUTPUT_FILE
		String OUTPUT_FILE = "/Users/yang/Downloads/processedArticle1.txt";
		String wordTxtOutputFile = "/Users/yang/Downloads/processedArticle1.txt";// read
																					// AB
																					// articles
																					// para:@wordTxtOutputFile
																					// @numbersOfArticles
		int numbersOfArticles = 20;// how many articles you want
									// ?@numbersOfArticles

		HashMap<Integer, Float> erroTable = new HashMap<Integer, Float>();

		erroTable = errorCalculate.errorCal(wordTxtFile, csvFile, OUTPUT_FILE,
				wordTxtOutputFile, numbersOfArticles);

		System.out.println("n" + ",erro");
		for (java.util.Map.Entry<Integer, Float> e : erroTable.entrySet()) {
			// to get key
			e.getKey();
			// and to get value
			e.getValue();

			System.out.println(e.getKey() + "," + e.getValue());
		}

		// draw a chart
		final XYSeries series = new XYSeries("error in function of n");

		for (int i = 0; i < erroTable.size(); i++) {

			series.add(5 * i + 5, erroTable.get(5 * i + 5));

		}
		final XYSeriesDemo demo = new XYSeriesDemo("error in function of n",
				series);
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

		// System.out.println("---whole project done---");

	}

}
