package BigData.week2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class errorCalculation {

	static double CalculateError(String folderPathOfSpamTraning,
			String folderPathOfHamTraning, String folderPathofQuery,
			List<Integer> seedList) throws IOException {
		// TODO Auto-generated method stub
		List<Double> ErrorWithSpamList=new ArrayList<Double>();//to return
		List<Double> ErrorWithHamList=new ArrayList<Double>();//to return
		List<Map<String, Integer>> vectorsOfHam = readTrainningData
				.readData(folderPathOfHamTraning);
		List<Map<String, Integer>> vectorsOfSpam = readTrainningData
				.readData(folderPathOfSpamTraning);

		List<Map<String, Integer>> vectorsOfQuery = readTrainningData
				.readData(folderPathofQuery);

		// System.out.println("---------------------------------- query email of qure----------------------");

		List<List<Integer>> allHamVectorInHyperplain = new ArrayList<List<Integer>>();
		List<List<Integer>> allSpamVectorInHyperplain = new ArrayList<List<Integer>>();
		List<List<Integer>> queryVectorInHyperplain  = new ArrayList<List<Integer>>();
		

		 //read Ham
		final Map<String, Double> emailOfSmallDistancemap = new HashMap<String, Double>();
		File folder = new File(folderPathOfHamTraning);
		File[] listOfFilesHam = folder.listFiles();
		
		long sumOfHashham = 0;
		
        
		for (int i = 0; i < vectorsOfHam.size(); i++) {
			
			List<Integer> oneVectorInHyperplain = new ArrayList<Integer>();
			for (int seedi = 0; seedi < seedList.size(); seedi++) {

				HashFunction h = Hashing.murmur3_128(seedList.get(seedi));

				// hyperplain for training emails
				for (Entry<String, Integer> entry : vectorsOfHam.get(i)
						.entrySet()) {
					String k = entry.getKey();
					int v = entry.getValue();

					HashCode hvofEmali = h
							.hashString(k, StandardCharsets.UTF_8);

					sumOfHashham += hvofEmali.asLong() * v;
					

				}

				
				if (sumOfHashham < 0) {
					oneVectorInHyperplain.add(0);
				
				} else if (sumOfHashham > 0) {
					oneVectorInHyperplain.add(1);
					
				} else {
					oneVectorInHyperplain.add(0);
				}

			}
			allHamVectorInHyperplain.add(oneVectorInHyperplain);

			

			}
		//read Spam
		File[] listOfFilesSpam = folder.listFiles();

		
		long sumOfHashSpam = 0;
		
		for (int i = 0; i < vectorsOfSpam.size(); i++) {
			
			List<Integer> oneVectorInHyperplain = new ArrayList<Integer>();
			for (int seedi = 0; seedi < seedList.size();seedi++) {

				HashFunction h = Hashing.murmur3_128(seedList.get(seedi));

				// hyperplain for training emails
				for (Entry<String, Integer> entry : vectorsOfSpam.get(i)
						.entrySet()) {
					String k = entry.getKey();
					int v = entry.getValue();

					HashCode hvofEmali = h
							.hashString(k, StandardCharsets.UTF_8);

					sumOfHashSpam += hvofEmali.asLong() * v;
					

				}

				
				if (sumOfHashSpam < 0) {
					oneVectorInHyperplain.add(0);
					
				} else if (sumOfHashSpam > 0) {
					oneVectorInHyperplain.add(1);
					
				} else {
					oneVectorInHyperplain.add(0);
				}

			}
			allSpamVectorInHyperplain.add(oneVectorInHyperplain);

			

			}
		
		//read query emai
		long sumOfHashQuery = 0;
		
		
			// System.out.println("--------sumOfHashQuery---:"+ sumOfHashQuery);
			for(int i = 0; i < vectorsOfQuery.size(); i++){
				List<Integer> oneVectorInHyperplain = new ArrayList<Integer>();
				for (int seedi = 0; seedi<seedList.size(); seedi++) {

					HashFunction h = Hashing.murmur3_128(seedList.get(seedi));

					
					for (Entry<String, Integer> entry : vectorsOfQuery.get(0)
							.entrySet()) {
						String qk = entry.getKey();
						int qv = entry.getValue();

						HashCode hv = h.hashString(qk, StandardCharsets.UTF_8);
						// System.out.println("------------hv-------hasscode value:"
						// + hv.asLong());
						sumOfHashQuery += hv.asLong() * qv;
					}
					// System.out.println("-----sumOfHashQuery ----////-----::"+sumOfHashQuery
					// );
					if (sumOfHashQuery < 0) {
						oneVectorInHyperplain.add(0);
						// System.out.println("-----0----quer--");
					} else if (sumOfHashQuery > 0) {
						oneVectorInHyperplain.add(1);
						// System.out.println("-----1----query--");
					} else {
						oneVectorInHyperplain.add(0);
					}
				
				}
				queryVectorInHyperplain.add(oneVectorInHyperplain);
			}
			
			//distance between query and Ham
			for(int f=0;f<queryVectorInHyperplain.size();f++){
			double distance = 0;
			double min = 20;
				for (int j = 0; j < allHamVectorInHyperplain.size(); j++) {
					// System.out.println("-------------------------------------------------------------allVectorInHyperplain.size()--"+allVectorInHyperplain.size());

					// System.out.println("-------------------------------------------------------------mail--"+j);
					long sum1 = 0;
					long sum2 = 0;
					long sum3 = 0;
					for (int k = 0; k <seedList.size(); k++) {
//						System.out
//								.println("-----------------------oneVectorInHyperplain.get(k) ----::"
//										+ queryVectorInHyperplain.get(k));
						// System.out.println("---allVectorInHyperplain.get(j).get(k)----::"+allVectorInHyperplain.get(j).get(k));
						sum1 += queryVectorInHyperplain.get(f).get(k)
								* allHamVectorInHyperplain.get(j).get(k);
						sum2 += queryVectorInHyperplain.get(f).get(k)
								* queryVectorInHyperplain.get(f).get(k);
						sum3 += allHamVectorInHyperplain.get(j).get(k)
								* allHamVectorInHyperplain.get(j).get(k);
					}
//					System.out.println("---sum1 ----" + sum1);
//					System.out.println("---sum2----"+sum2);
//					 System.out.println("---sum3----"+sum3);
					distance = Math
							.acos(sum1 / (Math.sqrt(sum2) * Math.sqrt(sum3)));
					double distance3 = Math
							.acos(1);
//					System.out.println("---(sum1 / (Math.sqrt(sum2) * Math.sqrt(sum3)))---" + (sum1 / (Math.sqrt(sum2) * Math.sqrt(sum3))));
//					System.out.println("---distance3----" + distance3);
//					 System.out.println("---distance1----" + distance);
					if (distance <= min) {
						min = distance;

						//File file = listOfFilesHam[j];

						//emailOfSmallDistancemap.put(file.getName(), distance);

					}
					// System.out.println("---min----" + min);

				}
				//System.out.println("--min--:"+min);
				ErrorWithHamList.add(min);
	}
				//
				//distance between query and Spam
			for(int f=0;f<queryVectorInHyperplain.size();f++){
				double min2=20;
				double distance2=0;
				for (int j = 0; j < allSpamVectorInHyperplain.size(); j++) {
					// System.out.println("-------------------------------------------------------------allVectorInHyperplain.size()--"+allVectorInHyperplain.size());

					// System.out.println("-------------------------------------------------------------mail--"+j);
					long sum1 = 0;
					long sum2 = 0;
					long sum3 = 0;
					for (int k = 0; k < seedList.size(); k++) {
						//System.out.println("-----------------------oneVectorInHyperplain.get(k) ----::"+ queryVectorInHyperplain.get(k));
						//System.out.println("---allSpamVectorInHyperplain.get(j).get(k)----::"+allSpamVectorInHyperplain.get(j).get(k));
						sum1 += queryVectorInHyperplain.get(f).get(k)
								* allSpamVectorInHyperplain.get(j).get(k);
						sum2 += queryVectorInHyperplain.get(f).get(k)
								* queryVectorInHyperplain.get(f).get(k);
						sum3 += allSpamVectorInHyperplain.get(j).get(k)
								* allSpamVectorInHyperplain.get(j).get(k);
					}
//					System.out.println("---sum1 ----" + sum1);
//					 System.out.println("---sum2----"+sum2);
//					System.out.println("---sum3----"+sum3);
					distance2 = Math
							.acos(sum1 / (Math.sqrt(sum2) * Math.sqrt(sum3)));
					//System.out.println("---distance2----" + distance2);
					if (distance2 < min2) {
						min2 = distance2;
						//System.out.println("---mi2min2 ----" + min2);
						//File file = listOfFilesSpam[j];

						//emailOfSmallDistancemap.put(file.getName(), distance2);

					}
					
					

				
		}
				//System.out.println("--min2--:"+min2);
				ErrorWithSpamList.add(min2);		
	}
			
		double sumError=0;
			for(int i = 0; i < vectorsOfQuery.size(); i++){
				sumError += Math.abs(ErrorWithSpamList.get(i)-ErrorWithHamList.get(i));
					
				}
			  System.out.println("--sumError/100--:"+sumError/100);
				return sumError/100;

		

	}
	}


