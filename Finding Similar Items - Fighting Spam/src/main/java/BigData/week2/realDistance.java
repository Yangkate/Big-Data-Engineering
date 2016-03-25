package BigData.week2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class realDistance {
	
	public static Map<String, Double> getRealDistance(String folderPathOfSpamTraning,
			String folderPathOfHamTraning, String folderPathofQuery) throws IOException{
		// TODO Auto-generated method stub
		 Map<String,Double> QueryAndRealDistance = new HashMap<String,Double>();
		 Map<String,Map<String, Integer>> vectorsOfHamTraning = readTrainningData
						.readData3(folderPathOfHamTraning);
		 Map<String,Map<String, Integer>> vectorsOfSpamTraning = readTrainningData
						.readData3(folderPathOfSpamTraning);
				
				
		 Map<String,Map<String, Integer>> vectorsOfQuery = readTrainningData
						.readData3(folderPathofQuery);
				
				
				 
				//System.out.println(" -----------vectorsOfHamTraning:" +vectorsOfHamTraning.size());
				//System.out.println(" ------------ vectorsOfSpamTraning:" +vectorsOfSpamTraning.size());
				//System.out.println(" ------------folderPathofQuery:" + vectorsOfQuery.size());
				// caculate distance
				final List<Double> emailOfSmallDistancemap = new ArrayList<Double>();
				
				
				
				//for(int l=0;l<vectorsOfQuery.size();l++){
				for (Entry<String, Map<String, Integer>> eq : vectorsOfQuery.entrySet()) {
					
					
					double sqOfQury;
					double sqOfTraning;
					double distance = 0;
					double distance2 = 90;
					int sum;
					for (Entry<String, Map<String, Integer>> eh : vectorsOfHamTraning.entrySet()) {
						sqOfQury = 0;
						sqOfTraning = 0;
						sum = 0;
						// vectorsOfQuery.get(0).forEach((qk, qv)
						// ->System.out.println("Key : " + qk + " Value : " + qv));
						for (Entry<String, Integer> entry : eq.getValue()
								.entrySet()) {
							String qk = entry.getKey();
							int qv = entry.getValue();
							sqOfQury += qv * qv;
							if (eh.getValue().containsKey(qk)) {

								sum += (eh.getValue().get(qk)) * (qv);
							}
						}

						// calculate sqrt of traning email
						for (Entry<String, Integer> entry2 : eh.getValue()
								.entrySet()) {

							int v = entry2.getValue();

							sqOfTraning += v * v;
						}
						// calculate sqrt of traning email ends
						
	                    double middleVarable=sum/ (Math.sqrt(sqOfTraning) * Math.sqrt(sqOfQury));
						if(middleVarable >1){middleVarable=1;}
						distance = Math.acos(middleVarable);				
						if (distance <= distance2) {
							distance2 = distance;
							//File fileHam = listOfHamFiles[i];
							emailOfSmallDistancemap.add(distance2);
						}
				}
               //distance with spam
					
					double sqOfQury11;
					double sqOfTraning11;
					double distance11= 0;
					double distance211= 90;
					int sum11 = 0;
					for (Entry<String, Map<String, Integer>> es : vectorsOfSpamTraning.entrySet()) {
						
						sqOfQury11 = 0;
						sqOfTraning11 = 0;
						sum11 = 0;
						// vectorsOfQuery.get(0).forEach((qk, qv)
						// ->System.out.println("Key : " + qk + " Value : " + qv));
						for (Entry<String, Integer> entry : eq.getValue()
								.entrySet()) {
							String qk = entry.getKey();
							int qv = entry.getValue();
							sqOfQury11 += qv * qv;
							if (es.getValue().containsKey(qk)) {

								sum11 += (es.getValue().get(qk)) * (qv);
							}
						}

						// calculate sqrt of traning email
						for (Entry<String, Integer> entry : es.getValue()
								.entrySet()) {

							int v = entry.getValue();

							sqOfTraning11 += v * v;
						}
						// calculate sqrt of traning email ends
						
	                    double middleVarable2=sum11/ (Math.sqrt(sqOfTraning11) * Math.sqrt(sqOfQury11));
						if(middleVarable2 >1){middleVarable2=1;}
						distance11 = Math.acos(middleVarable2);				
						if (distance11 <= distance211) {
							distance211 = distance11;
							
							emailOfSmallDistancemap.add(distance211);
						}
				}
					
					double min = Collections.min(emailOfSmallDistancemap);
					
					QueryAndRealDistance.put(eq.getKey(),min);
					
				}

//				for (Entry<String, Double> entry : QueryAndRealDistance.entrySet()) {
//					
//						System.out.println( " value :  "
//								+ entry.getValue()+"----QueryAndRealDistance-----keys : " + entry.getKey() );
//						
//					
//				}
				
				
				//System.out.println("---QueryAndRealDistance ----");
				return QueryAndRealDistance;
				
		
	}
				
				
			
					
					
}


