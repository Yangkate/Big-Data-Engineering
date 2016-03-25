package Big.week1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Random;
import java.util.Set;

public class errorCalculate {
	public static HashMap<Integer, Float> errorCal(String wordTxtFile,
			String csvFile, String OUTPUT_FILE, String wordTxtOutputFile,
			int numbersOfArticles) throws Exception {

		HashMap<Integer, Float> erroTable = new HashMap<Integer, Float>();// (n,error)
		ReadCVS.readDataCVS(csvFile, OUTPUT_FILE, wordTxtFile,
				numbersOfArticles);
		int number = (int) (numbersOfArticles / 2);
		float[] resultCompareOfPossimbility = new float[number];
		float[] resultCompareofReality = new float[number];

		String[] processedArticles = readProcessedArticle.read(
				wordTxtOutputFile, numbersOfArticles);

		// caculate JD in different methods

		for (int p = 5; p < 81; p += 5) {// [5, 10, 15, 20, 25, 30, 35. 40, …,
											// 80]:

			int n = p;// numbers of permutations

			for (int q = 0; q < number; q++) {

				String article0 = processedArticles[4 * q + 1];
				String article1 = processedArticles[4 * q + 3];
				if (article0 == null || article1 == null) {
					throw new Error();
				}
				// real jd
				resultCompareofReality[q] = jaccardDistance.jc(article0,
						article1);
				// jd with possibility
				Random rand = new Random();// rand for seed

				String[] doc0 = article0.split(",");
				String[] doc1 = article1.split(",");
				Set<String> myDocSet0 = new HashSet<String>(Arrays.asList(doc0));
				Set<String> myDocSet1 = new HashSet<String>(Arrays.asList(doc1));

				int nSameHigh = 0;// numbers of same minHasher
				int seed = rand.nextInt(3 * n) + 1;// random number for seed
				for (int j = 0; j < n; j++) {

					Minhasher test = new Minhasher(seed);
					if (test.hash(myDocSet0) == test.hash(myDocSet1)) {
						nSameHigh++;
					}
				}
				float jdp = 1 - (float) (nSameHigh / n);
				resultCompareOfPossimbility[q] = jdp;
			}
			float err = 0;
			float sum = 0;
			for (int h = 0; h < number; h++) {
				sum = Math
						.abs((resultCompareOfPossimbility[h] - resultCompareofReality[h]));
			}
			err = sum / number;
			erroTable.put(p, err);

		}
		return erroTable;

	}

}
