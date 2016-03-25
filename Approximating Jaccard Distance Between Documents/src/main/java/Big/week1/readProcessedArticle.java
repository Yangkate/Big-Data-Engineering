package Big.week1;

import java.io.BufferedReader;
import java.io.FileReader;

public class readProcessedArticle {

	public static String[] read(String wordTxtOutputFile, int numbersOfArticles)
			throws Exception {// read wordTxtOutputFile.txt and return a array
								// of numbersOfArticles articles
		// TODO Auto-generated method stub
		int lines = 0;
		String[] processedArticleParts = { "1", "2" };
		String[] processedArticleReturn = new String[2 * numbersOfArticles];
		// read output.txt file

		String line3 = "";
		String cvsSplitBy3 = ":";
		BufferedReader br3 = new BufferedReader(new FileReader(
				wordTxtOutputFile));

		while ((line3 = br3.readLine()) != null) {

			processedArticleParts = line3.split(cvsSplitBy3, 2);
			String midddleValue = processedArticleParts[0];
			processedArticleReturn[lines] = midddleValue;
			midddleValue = processedArticleParts[1];
			processedArticleReturn[lines + 1] = midddleValue;
			lines += 2;
		}
		br3.close();
		for (String s : processedArticleReturn) {
			if (s == null) {
				throw new Error();
			}
		}

		return processedArticleReturn;
	}

}
