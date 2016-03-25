package week3.ApproximatingStreamCardinalities;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class setCalculator {
	public static void getSet(){
		long i = 0;
		Set<Long> a = new HashSet<Long>();
		long startTime = System.currentTimeMillis(); // Get the start Time
		long endTime = 0;

		System.out.println("starttime"+startTime);

		Random rand = new Random(); 
		try{for ( i = 0; i <13449681; i++) {
			
			int value = rand.nextInt(100000000); 
		
			HashFunction hf = Hashing.murmur3_128(value);
			HashCode hashCode = hf.hashString("The string to hash...",
					StandardCharsets.UTF_8);
			long hash = hashCode.asLong();
			a.add(hash);
			System.out.println(hash+"i---"+i);
			
		}}catch(Throwable t){t.printStackTrace();System.out.println("endtime"+System.currentTimeMillis());}
		
		endTime = System.currentTimeMillis(); // Get the end Time
//13449681 run out of number
		System.out.println("endtime"+endTime);
		System.out.println("i="+i);
		System.out.println("Difference in mili seconds: "
				+ (endTime - startTime)); // Print the difference in mili
											// seconds
		System.out.println("Differencce in Seconds: " + (endTime - startTime)
				/ 1000); // Print the difference in seconds
	}

}
