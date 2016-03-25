package week3.ApproximatingStreamCardinalities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) throws IOException {
		
		int k = 27;//for HLL
		int m = (int) Math.pow(2, k);
		int kmin=50000;//for k-min-values
		int linek = 0;
		int index = 0;
		double lineNumber2=0;
		Long[] arrayKmin = new Long[kmin];
		// Long[] arrayKmin={1L,2L,3L,4L,5L,6L,7L,9L};
		for (int b = 0; b < kmin; b++) {
			arrayKmin[b] = 1L;
			//System.out.println("arrayKmin[b]" + arrayKmin[b]);
		}
		int[] arrayHLL = new int[m];
		try (BufferedReader r = new BufferedReader(new InputStreamReader(
				System.in))) {
			String line;
			while ((line = r.readLine()) != null) {
				lineNumber2++;
				long numberL = Long.parseLong(line);
				HashFunction h = Hashing.murmur3_128(4548744);
				long numberL2=Math.abs(h.hashLong(numberL).asLong());
				if (linek < kmin) {
					arrayKmin[linek] =numberL2;
					linek++;
				} else {
					Arrays.sort(arrayKmin);
					if (numberL2< arrayKmin[kmin - 1]) {
						arrayKmin[kmin - 1] = numberL2;
						
					}
					
				}
				
				// KMV ends
				// HLL starts
				String numberInBinary = Long.toBinaryString(numberL);
				
				if (numberInBinary.length() > k) {
					index = Integer.parseInt(numberInBinary.substring(
							numberInBinary.length() - k, numberInBinary.length()),
							2);
				} else {
				}
				
				for (int j = numberInBinary.length() - k; j > 0; j--) {
					if (numberInBinary.substring(j - 1, j) == "1"
							&& arrayHLL[index] < numberInBinary.length() - k - j
									+ 1) {
						arrayHLL[index] = numberInBinary.length() - k - j + 1;
					}
				}
				// HLL ends

			}
			
			r.close();
			long cardinalityOfKmin = 0;
			if (arrayKmin[kmin - 1] == 0) {
				System.out.println("arrayKmin[k-1] is 0");
			} else {
				
				cardinalityOfKmin = ((kmin - 1) * 10L*(1000000000000000000L/ arrayKmin[kmin - 1]));
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
			
//			//Determine the cardinality
//			long  DV_est= (long) (am * m * m / sum);
//			System.out.println("DV_est"+DV_est);
//			double jj=(  Math.pow(2, 32)/30 );
//			System.out.println("(( 1/30) * Math.pow(2, 32) )"+jj);
//			long cardinalityOfHLL=0L;
//			if(DV_est < (5/2) * m){
//				int count_of_zero_registers=0;
//				for(int i=0;i<m;i++){if(arrayHLL[i]==0){count_of_zero_registers++;}}
//				int V = count_of_zero_registers;
//				if(V==0){cardinalityOfHLL=DV_est;}else{cardinalityOfHLL=(long) (m*Math.log(m/V));}
//			}else if (DV_est <= (  Math.pow(2, 32)/30 )){cardinalityOfHLL=DV_est;}
//			else if (DV_est >(  Math.pow(2, 32)/30 )){cardinalityOfHLL= (long) (-Math.pow(2, 32) * Math.log( 1 - DV_est/Math.pow(2, 32)));}
//			else{cardinalityOfHLL=DV_est;}
			
			 long cardinalityOfHLL= (long) (am * m * m / sum);
			
			System.out.println("cardinalityOfKmin:" + cardinalityOfKmin);
			System.out.println("cardinalityOfHLL:" + cardinalityOfHLL);
			System.out.println("lineNumber:" + lineNumber2);
			

		}
	}
	}

