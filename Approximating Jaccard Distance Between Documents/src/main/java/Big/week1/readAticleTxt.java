package Big.week1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class readAticleTxt {
	public static float readAticle() throws IOException {
		float JD = 0;
		// read output.txt file
		String wordTxtOutputFile = "/Users/yang/Downloads/processedArticle.txt";
		String line3 = "";
		String cvsSplitBy3 = ":";
		// count file.line.length
		LineNumberReader lnr2 = new LineNumberReader(new FileReader(new File(
				wordTxtOutputFile)));
		lnr2.skip(Long.MAX_VALUE);
		int lineNumber2 = lnr2.getLineNumber();

		// Finally, the LineNumberReader object should be closed to prevent
		// resource leak
		lnr2.close();

		BufferedReader br3 = new BufferedReader(new FileReader(
				wordTxtOutputFile));
		int linei = 0;
		String[] processedArticleParts0 = { "ini", "jj" };// to store article 1
		String[] processedArticleParts1 = { "nini2", "haha" };// to store
																// article 2

		while ((line3 = br3.readLine()) != null) {
			for (int i = 0; i < processedArticleParts1.length; i++) {
				String value = processedArticleParts1[i];
				processedArticleParts0[i] = value;
			}
			processedArticleParts1 = line3.split(cvsSplitBy3, 2);
			if (linei != 0) {

				JD = jaccardDistance.jc(processedArticleParts0[1].toString(),
						processedArticleParts1[1].toString());
			}
			linei++;

		}
		br3.close();

		return JD;

	}

}
