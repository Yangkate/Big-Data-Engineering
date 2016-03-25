package week3.ApproximatingStreamCardinalities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;


import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class cardinalityEst {
	// public static long getDistinctValue(long number,int k){
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		
		
		String dataPath = "/Users/yang/Downloads/study/BigData/week4/test1/data106.txt";// path
																						// to
																						// data
		// String
		// dataPath="/Users/yang/Downloads/study/BigData/week4/laoda/data1000000";
		int k = 10;//for HLL
		int m = (int) Math.pow(2, k);
		int kmin=11;//for k-min-values
		int linek = 0;
		int index = 0;
		
		Long[] arrayKmin = new Long[kmin];
		// Long[] arrayKmin={1L,2L,3L,4L,5L,6L,7L,9L};
		for (int b = 0; b < kmin; b++) {
			arrayKmin[b] = 1L;
			System.out.println("arrayKmin[b]" + arrayKmin[b]);
		}
		int[] arrayHLL = new int[m];
		// File folder = new File(folderPath);
		// File[] listOfFiles = folder.listFiles();
		// for (int i = 0; i < listOfFiles.length; i++) {
		// File file = listOfFiles[i];
		// if (file.isFile() && file.getName().endsWith(".txt")) {

		LineNumberReader lnr2 = new LineNumberReader(new FileReader(new File(
				dataPath)));
		lnr2.skip(Long.MAX_VALUE);
		int lineNumber2 = lnr2.getLineNumber();

		// Finally, the LineNumberReader object should be closed to prevent
		// resource leak
		lnr2.close();

		BufferedReader br3 = new BufferedReader(new FileReader(dataPath));
		String line3 = "";
		while ((line3 = br3.readLine()) != null) {
			// System.out.println("----------------file name-----------"+file.getName());

			// String longInString = FileUtils.readFileToString(file);
			// long numberL=Long.parseLong(FileUtils.readFileToString(file));
			long numberL = Long.parseLong(line3);
			// System.out.println("line:"+line3);
			// System.out.println("numberL"+numberL);
			/* do somthing with content */
			// KMV starts
			HashFunction h = Hashing.murmur3_128(4548744);
			long numberL2=Math.abs(h.hashLong(numberL).asLong());
			if (linek < kmin) {
				arrayKmin[linek] =numberL2;
				linek++;
			} else {
				Arrays.sort(arrayKmin);
				if (numberL2< arrayKmin[kmin - 1]) {
					arrayKmin[kmin - 1] = numberL2;
					//System.out.println("numberL2"+numberL2);
				}
				// Arrays.sort(arrayKmin);
			}
			// for(int b=0;b<k;b++){
			// System.out.println("arrayKmin[b]:"+arrayKmin[b]);}
			// KMV ends
			// HLL starts
			String numberInBinary = Long.toBinaryString(numberL);
			// System.out.println("numberL"+numberL);
			// System.out.println("numberInBinary"+numberInBinary);
			if (numberInBinary.length() > k) {
				index = Integer.parseInt(numberInBinary.substring(
						numberInBinary.length() - k, numberInBinary.length()),
						2);
			} else {
			}
			// System.out.println("numberInBinary.length()-k:"+(numberInBinary.length()-k));
			// System.out.println("numberInBinary.length()"+numberInBinary.length());
			for (int j = numberInBinary.length() - k; j > 0; j--) {
				if (numberInBinary.substring(j - 1, j) == "1"
						&& arrayHLL[index] < numberInBinary.length() - k - j
								+ 1) {
					arrayHLL[index] = numberInBinary.length() - k - j + 1;
				}
			}
			// HLL ends

		}
		// }
		br3.close();

//		for (int b = 0; b < k; b++) {
//			System.out.println("arrayKmin[b]--:" + arrayKmin[b]);
//		}
		long cardinalityOfKmin = 0;
		if (arrayKmin[kmin - 1] == 0) {
			System.out.println("arrayKmin[k-1] is 0");
		} else {
			//System.out.println("max:" + max);
			cardinalityOfKmin = ((kmin - 1) * 10L*(1000000000000000000L/ arrayKmin[kmin - 1]));
//			System.out.println((1000000000000000000L/ arrayKmin[kmin - 1]));
//			System.out.println(arrayKmin[kmin - 1]);
		}
		double sum = 0;
		for (int n = 0; n < arrayHLL.length; n++) {
			sum += Math.pow(2, -arrayHLL[n]);
		}

		double am = 0;
		if (m == 16) {
			am = 0.673;
		} else if (m == 32) {
			am = 0.72134;
		} else if (m >= 64) {
			am = 0.7213;
		}
		long cardinalityOfHLL = (long) (am * m * m / sum);
		System.out.println("cardinalityOfKmin:" + cardinalityOfKmin);
		System.out.println("cardinalityOfHLL:" + cardinalityOfHLL);
		System.out.println("lineNumber:" + lineNumber2);

	}

	static void test() {
		HashFunction h = Hashing.murmur3_128(2345678);
		HashCode v = h.hashLong(467328);
		v.asLong();
	}

}
