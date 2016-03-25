package BigData.week2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

public class queryWithCloesestEmailSet {

	static Map<String, List<String>> hyperplainMaker(
			String folderPathOfSpamTraning, String folderPathOfHamTraning,
			String folderPathofQuery, List<Integer> seedList)
			throws IOException {
		// TODO Auto-generated method stub

		Map<String, List<String>> QueryAndClosestEmailWithName = new HashMap<String, List<String>>();

		Map<String, Map<String, Integer>> vectorsOfHam = readTrainningData
				.readData3(folderPathOfHamTraning);
		Map<String, Map<String, Integer>> vectorsOfSpam = readTrainningData
				.readData3(folderPathOfSpamTraning);

		Map<String, Map<String, Integer>> vectorsOfQuery = readTrainningData
				.readData3(folderPathofQuery);

		// System.out.println("---------------------------------- query email of qure----------------------");

		Map<String, Vector<Integer>> allHamVectorInHyperplain = new HashMap<String, Vector<Integer>>();
		Map<String, Vector<Integer>> allSpamVectorInHyperplain = new HashMap<String, Vector<Integer>>();
		Map<String, Vector<Integer>> queryVectorInHyperplain = new HashMap<String, Vector<Integer>>();

		// read query
		queryVectorInHyperplain = hyperplainVector.gethyperVectors(
				vectorsOfQuery, seedList);


		// read spam
		allSpamVectorInHyperplain = hyperplainVector.gethyperVectors(
				vectorsOfSpam, seedList);
		// read Ham
		allHamVectorInHyperplain = hyperplainVector.gethyperVectors(
				vectorsOfHam, seedList);

		// System.out.println("allHamVectorInHyperplain--"+allHamVectorInHyperplain);
		// System.out.println("allSpamVectorInHyperplain--"+allSpamVectorInHyperplain);
		// System.out.println("queryVectorInHyperplain--"+queryVectorInHyperplain);

		// compare query with email in hyperplain
		for (Entry<String, Vector<Integer>> eq : queryVectorInHyperplain
				.entrySet()) {
			List<String> oneEmailSetClosedToQuery = new ArrayList<String>();

			for (Entry<String, Vector<Integer>> eh : allHamVectorInHyperplain
					.entrySet()) {
//				System.out.println("eq.getValue()" + eq.getValue());
//				System.out.println("eh.getValue()" + eh.getValue());
//				System.out.println("caonima eq" + eq.getKey());
//				System.out.println("caonima eh" + eh.getKey());
				if (eq.getValue().equals(eh.getValue())) {
					oneEmailSetClosedToQuery.add(eh.getKey());
					// System.out.println("caonima eq--"+eq.getKey());
					// System.out.println("caonima eh--"+eh.getKey());
				}
			}

			for (Entry<String, Vector<Integer>> es : allSpamVectorInHyperplain
					.entrySet()) {
//				System.out.println("eq.getValue()--" + eq.getValue());
//				System.out.println("es.getValue()--" + es.getValue());
//				System.out.println("caonima eq--" + eq.getKey());
//				System.out.println("caonima es--" + es.getKey());
				if (eq.getValue().equals(es.getValue())) {
					oneEmailSetClosedToQuery.add(es.getKey());
					// System.out.println("caonima es--"+es.getKey());
					// System.out.println("caonima eq--"+eq.getKey());
				}
			}
			// System.out.println("---jj----"+oneEmailSetClosedToQuery.size());
			QueryAndClosestEmailWithName.put(eq.getKey(),
					oneEmailSetClosedToQuery);
		}

//		System.out.println("---queryWithCloesestEmailSet----"
//				+ QueryAndClosestEmailWithName);

		return QueryAndClosestEmailWithName;

	}

}
