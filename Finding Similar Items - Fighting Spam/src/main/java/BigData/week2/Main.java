package BigData.week2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) throws IOException {

		// part one starts

		// // read training spam email data
		// String folderPathOfSpamTraning =
		// "/Users/yang/Downloads/study/BigData/week3_test/spamtest2/spam/";
		// // read training ham email data
		// String folderPathOfHamTraning =
		// "/Users/yang/Downloads/study/BigData/week3_test/hamtest2/ham/";
		// // read query email
		// String folderPathofQuery =
		// "/Users/yang/Downloads/study/BigData/week3_test/query2/query/";
		// //part one starts
		// double
		// disQueryAndSpam=calculateDistance.calculateDis(folderPathOfSpamTraning,folderPathofQuery);
		// double
		// disQueryAndHam=calculateDistance.calculateDis(folderPathOfHamTraning,folderPathofQuery);
		//
		// if(disQueryAndHam<disQueryAndSpam){
		// System.out.println("part 1:This query email is most likely to be a ham");
		// }else{
		// System.out.println("part 1:This query email is most likely to be a spam");
		// }
		// System.out.println("-1--disQueryAndSpam ----"+disQueryAndSpam);
		//
		// System.out.println("-1--disQueryAndHam ----"+disQueryAndHam );

		// part one ends

		// part three starts
		// // read training spam email data
		// String folderPathOfSpamTraning3 =
		// "/Users/yang/Downloads/study/BigData/week3_data/spamtest2/";
		// // read training ham email data
		// String folderPathOfHamTraning3 =
		// "/Users/yang/Downloads/study/BigData/week3_data/hamtest2/";
		// // read query email
		// String folderPathofQuery3 =
		// "/Users/yang/Downloads/study/BigData/week3_data/merge100test2/";
		//
		// Map<String,Double> QueryAndRealDistance =
		// realDistance.getRealDistance(
		// folderPathOfSpamTraning3, folderPathOfHamTraning3,
		// folderPathofQuery3);
		// Map<Integer, Float> erroTable = new HashMap<Integer, Float>();
		//
		// for (int h = 0; h < 7; h++) {
		// int hyperpalinAmount3 = (int) Math.pow(2, h);// change seeds here
		// // [1, 2, 4, 8, 16,
		// // 32,64]
		// List<Integer> seedList3 = new ArrayList<Integer>();// 10 seeds for
		// // hyerpain
		// for (int i = 0; i < hyperpalinAmount3; i++) {
		// int randomNum = 62645 + (int) (Math.random() * 423564178);
		// if (!seedList3.contains(randomNum)) {
		// seedList3.add(randomNum);
		// }
		// }
		//
		//
		// Map<String,List<String>> QueryAndClosestDistance =
		// queryWithCloesestEmailSet.hyperplainMaker(
		// folderPathOfSpamTraning3, folderPathOfHamTraning3,
		// folderPathofQuery3, seedList3);
		//
		// Map<String,Double> realDisWithClosestEmailByHyper=new
		// HashMap<String,Double>();
		// for (Entry<String, List<String>> ecd :
		// QueryAndClosestDistance.entrySet())
		// {
		//
		//
		//
		// double haha=0;
		// List<Double> minDis =new ArrayList<Double>();
		// for(int i=0;i<ecd.getValue().size();i++)
		// {
		// haha=realDistanceBetweenTwoemail.getDistance(ecd.getKey(),
		// ecd.getValue().get(i)).get(ecd.getKey());
		// System.out.println("haha:" + haha);
		// minDis.add(haha);
		//
		// }
		// double min=0;
		// if(ecd.getValue().size()==0){
		// min=0;}else{min=Collections.min(minDis);}
		//
		// realDisWithClosestEmailByHyper.put(ecd.getKey(), min);
		//
		// }
		// for (Entry<String, Double> rdceb :
		// realDisWithClosestEmailByHyper.entrySet())
		// {
		// float erroN = (float)
		// Math.abs(rdceb.getValue()-QueryAndRealDistance.get(rdceb.getKey()));
		// erroTable.put(hyperpalinAmount3, erroN);
		// }
		//
		//
		// }
		// for (java.util.Map.Entry<Integer, Float> e : erroTable.entrySet()) {
		// // to get key
		// e.getKey();
		// // and to get value
		// e.getValue();
		//
		// System.out.println(e.getKey() + "," + e.getValue());
		// }
		//
		// // draw a chart
		// final XYSeries series = new XYSeries("error in  n hyperpalins");
		//
		// for (int i = 0; i < erroTable.size(); i++) {
		//
		// series.add((int) Math.pow(2, i),
		// erroTable.get((int) Math.pow(2, i)));
		//
		// final XYSeriesDemo demo = new XYSeriesDemo("error in  n hyperpalins",
		// series);
		// demo.pack();
		// RefineryUtilities.centerFrameOnScreen(demo);
		// demo.setVisible(true);

		// part three ends

		// part four starts
		// read training spam email data
		String folderPathOfSpamTraning3 = "/Users/yang/Downloads/study/BigData/week3_data/spamtest2/";
		// read training ham email data
		String folderPathOfHamTraning3 = "/Users/yang/Downloads/study/BigData/week3_data/hamtest2/";
		// read query email
		String folderPathofQuery3 = "/Users/yang/Downloads/study/BigData/week3_data/merge100test3/";
		int hamMarkedAsHam = 0;
		int hamMarkedAsSpam = 0;
		int spamMarkedAsSpam = 0;
		int spamMarkedAsHam = 0;
		Map<String, Map<String, Double>> judageResult = new HashMap<String, Map<String, Double>>();
		Map<String, List<String>> merge2 = new HashMap<String, List<String>>();
		Map<String, List<String>> merge = new HashMap<String, List<String>>();
		List<String> QueryEmailsCannotFindSameSignature= new ArrayList<String>();

		int j = 0;
		for (int h = 0; h < 1; h++) {
			// System.out.println("----h---"+h);
			int hyperpalinAmount3 = (int) Math.pow(2, 4);// change seed's number
															// here

			List<Integer> seedList3 = new ArrayList<Integer>();

			for (int i = 0; i < hyperpalinAmount3; i++) {
				int randomNum = 62645 + (int) (Math.random() * 423564178);
				if (!seedList3.contains(randomNum)) {
					seedList3.add(randomNum);
				}
			}

			// calculate

			Map<String, List<String>> QueryAndClosestDistance = queryWithCloesestEmailSet
					.hyperplainMaker(folderPathOfSpamTraning3,
							folderPathOfHamTraning3, folderPathofQuery3,
							seedList3);
			
			System.out.println("QueryAndClosestDistance.size()"+QueryAndClosestDistance.size());
			for (Entry<String, List<String>> ecd : QueryAndClosestDistance
					.entrySet()) {
				List<String> mideleList = new ArrayList<String>();
				if (j == 0) {
					merge2.put(ecd.getKey(), mideleList);
				}
				merge2.get(ecd.getKey()).addAll(ecd.getValue());
			}
			j++;
		}
		
		for (Entry<String, List<String>> ecd : merge2.entrySet()) {

			if (ecd.getValue().size() != 0) {
				merge.put(ecd.getKey(), ecd.getValue());
			}else{QueryEmailsCannotFindSameSignature.add(ecd.getKey());}
		}
	
		
		
		
		for (Entry<String, List<String>> m : merge.entrySet()) {
			Map<String, Double> minDis = new HashMap<String, Double>();
			for (int i = 0; i < m.getValue().size(); i++) {
				double distanceBetweenTwo = realDistanceBetweenTwoemail
						.getDistanceWithEmailName(m.getKey(),
								m.getValue().get(i)).get(m.getValue().get(i));
				minDis.put(m.getValue().get(i), distanceBetweenTwo);
			}
			if (m.getValue().size() == 0) {
				minDis.put("ham", Math.PI / 2);
				
			}
			
			judageResult.put(m.getKey(), minDis);
			
			
		}
		
		for (Entry<String, Map<String, Double>> jr : judageResult.entrySet()) {

			String keyWithLowerestvalue = null;

			double lowestDistance = Double.MAX_VALUE;
			for (Entry<String, Double> b : jr.getValue().entrySet()) {
				Double distance = b.getValue();
				if (distance < lowestDistance) {
					lowestDistance = distance;
					keyWithLowerestvalue = b.getKey();
				}
			}

			
			if (jr.getKey().contains("ham")
					&& keyWithLowerestvalue.contains("ham")) {
				hamMarkedAsHam++;
			} else if (jr.getKey().contains("ham")
					&& keyWithLowerestvalue.contains("spam")) {
				hamMarkedAsSpam++;
			} else if (jr.getKey().contains("spam")
					&& keyWithLowerestvalue.contains("spam")) {
				spamMarkedAsSpam++;
			} else if (jr.getKey().contains("spam")
					&& keyWithLowerestvalue.contains("ham")) {
				spamMarkedAsHam++;
			}
		}

		System.out.println("hamMarkedAsHam" + hamMarkedAsHam);
		System.out.println("hamMarkedAsSpam" + hamMarkedAsSpam);
		System.out.println("spamMarkedAsSpam" + spamMarkedAsSpam);
		System.out.println("spamMarkedAsHam" + spamMarkedAsHam);
		System.out.println("someOtherEmailCannotFindAnyEmailWithSameSignature" + QueryEmailsCannotFindSameSignature.size());

		// part four ends

		System.out.println("---main done ----");

	}

}
