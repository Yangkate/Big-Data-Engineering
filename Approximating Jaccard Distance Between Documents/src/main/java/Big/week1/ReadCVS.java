package Big.week1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class ReadCVS {

	public static void readDataCVS(String csvFile, String OUTPUT_FILE,
			String wordTxtFile, int numbersOfArticles) throws IOException {
		HashSet<String> DicSet = readwordstxt.readwords(wordTxtFile);
		// read dataset from .csv

		BufferedReader br = null;
		String line = "";

		String cvsSplitBy = ",";
		try {

			// write out articles to txt

			try (PrintWriter outWriter = new PrintWriter(new PrintStream(
					OUTPUT_FILE))) {
				//
				Random radomArticle = new Random();// rand for select rodam
													// articles
				int[] articles = new int[numbersOfArticles];// to put 200
															// articles'IDs
				for (int l = 0; l < numbersOfArticles; l++) {
					articles[l] = radomArticle.nextInt(463819);

				}
				Arrays.sort(articles);

				br = new BufferedReader(new FileReader(csvFile));
				int j = 0;
				int lines = 0;

				while ((line = br.readLine()) != null) {
					// to read jth line
					if (lines == articles[j]) {
						String articleFliterStringGramIni = "";
						//
						// use comma as separator
						String[] parts = line.split(cvsSplitBy, 2);
						if (parts.length != 2) {
							throw new Error();
						}
						String articleID = parts[0];
						String articleContent = parts[1];
						// write ID to file

						// System.out.print(articleID + ":");
						outWriter.print(articleID + ":");

						// use regex to remove the punctuation and spaces

						String[] Res = articleContent.toLowerCase().split(
								"[\\p{Punct}\\s]+");

						// compare words from article and words.txt
						int jb2 = 0;
						int cc = 0;
						for (int jb = 0; jb < Res.length; jb++) {
							if (DicSet.contains(Res[jb])) {

								if (jb2 == 0) {
									jb2++;
									cc = jb;
								} else {
									jb2++;
									articleFliterStringGramIni = Res[cc]
											+ Res[jb] + ",";
									// Write the string
									outWriter.print(articleFliterStringGramIni);

									cc = jb;
								}

							}

						}
						outWriter.println(" ");
						outWriter.flush();
						++j;
						if (j == numbersOfArticles) {
							break;
						}
					}
					lines++;
				}
				if (lines < numbersOfArticles) {
					throw new Error();
				}
				if (j < numbersOfArticles) {
					throw new Error();
				}

			} catch (IOException e) {
				e.printStackTrace();

			}

		} finally {
			if (br != null) {
				try {
					br.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}