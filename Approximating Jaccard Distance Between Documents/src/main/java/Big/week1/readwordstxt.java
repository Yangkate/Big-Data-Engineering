package Big.week1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashSet;

public class readwordstxt {
	public static HashSet<String> readwords(String wordTxtFile)
			throws IOException {
		// create hash set to store words from words.txt
		HashSet<String> newset = new HashSet<String>();

		// read words.txt

		// count file.line.length
		LineNumberReader lnr = new LineNumberReader(new FileReader(new File(
				wordTxtFile)));
		lnr.skip(Long.MAX_VALUE);
		int lineNumber = lnr.getLineNumber() + 1;
		// Add 1 because line index
		// starts at 0
		// Finally, the LineNumberReader object should be closed to prevent
		// resource leak
		lnr.close();
		String line = "";
		String cvsSplitBy2 = " ";
		try (BufferedReader br2 = new BufferedReader(
				new FileReader(wordTxtFile))) {
			int k = 0;// read line from 0th to lineNumber-200
			int frequency = 0;

			while ((line = br2.readLine()) != null && k != lineNumber - 200) {
				// process the line
				// use comma as separator
				k++;

				String[] txtParts = line.split(cvsSplitBy2);
				int linePartLength = txtParts.length;
				String wordFrequency = txtParts[linePartLength - 2];
				frequency = Integer.parseInt(wordFrequency);

				String word = txtParts[linePartLength - 1];

				if (frequency > 4) {

					newset.add(word);
				}

			}

			br2.close();
		}

		return newset;

	}
}
