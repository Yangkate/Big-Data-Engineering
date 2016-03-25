package BigData.week2;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class calculateDistance {
	
	public static double calculateDis(String folderPathOfTranningData,String folderPathofQuery) throws IOException{
		// TODO Auto-generated method stub
				
				List<Map<String, Integer>> vectorsOfTraning = readTrainningData
						.readData2(folderPathOfTranningData);

				//System.out.println("---traning done ----");
				
				List<Map<String, Integer>> vectorsOfQuery = readTrainningData
						.readData2(folderPathofQuery);

				// System.out.println("---------------------------------- query email of qure----------------------");
				// caculate distance
				final Map<String, Double> emailOfSmallDistancemap = new HashMap<String, Double>();
				File folder = new File(folderPathOfTranningData);
				File[] listOfFiles = folder.listFiles();

				double sqOfQury;
				double sqOfTraning;
				double distance = 0;
				double distance2 = 90;
				int sum;
				for (int i = 0; i < vectorsOfTraning.size(); i++) {
					//System.out.println("----------------------------------tranning email of "+ i);

					sqOfQury = 0;
					sqOfTraning = 0;

					sum = 0;

					// vectorsOfQuery.get(0).forEach((qk, qv)
					// ->System.out.println("Key : " + qk + " Value : " + qv));
					for (Entry<String, Integer> entry : vectorsOfQuery.get(0)
							.entrySet()) {
						String qk = entry.getKey();
						int qv = entry.getValue();
						sqOfQury += qv * qv;
						if (vectorsOfTraning.get(i).containsKey(qk)) {

							sum += (vectorsOfTraning.get(i).get(qk)) * (qv);
						}
					}

					// calculate sqrt of traning email
					for (Entry<String, Integer> entry : vectorsOfTraning.get(i)
							.entrySet()) {

						int v = entry.getValue();

						sqOfTraning += v * v;
					}
					// calculate sqrt of traning email ends
                    double middleVarable=sum/ (Math.sqrt(sqOfTraning) * Math.sqrt(sqOfQury));
					if(middleVarable >1){middleVarable=1;}
					distance = Math.acos(middleVarable);
//					System.out.println("---multiply----" + (sum
//							/ (Math.sqrt(sqOfTraning) * Math.sqrt(sqOfQury))));
//					System.out.println("---66 middleVarable----"+middleVarable);
//					System.out.println("---66 Math.sqrt(sqOfQury)---"+Math.sqrt(sqOfQury));
//					System.out.println("---66 Math.sqrt(sqOfTraning)---"+Math.sqrt(sqOfTraning));
					
					if (distance <= distance2) {
						distance2 = distance;

						File file = listOfFiles[i];

						emailOfSmallDistancemap.put(file.getAbsolutePath(), distance);

					}
					//System.out.println("Original map: " + emailOfSmallDistancemap);
					//System.out.println("---distance----" + distance);

				}

				//System.out.println("---query done ----");

				// give outh the name the smallest distance email file name

				//System.out.println("------------------listOfFiles.length-----"+ listOfFiles.length);

				double min = Collections.min(emailOfSmallDistancemap.values());
				//System.out.println("Original map: " + emailOfSmallDistancemap);

//				for (Entry<String, Double> entry : emailOfSmallDistancemap.entrySet()) {
//					if (entry.getValue().equals(min)) {
//						System.out.println("keys : " + entry.getKey() + " value :  "
//								+ entry.getValue());
//						System.out.println("----------------file name-----------"
//								+ entry.getKey());
//					}
//				}
				//System.out.println("---calculate distance done ----");
				return min;
		
	}

	

}
