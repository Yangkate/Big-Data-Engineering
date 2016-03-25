package BigData.week2;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class hyperplainVector {

	public static Map<String, Vector<Integer>> gethyperVectors(
			Map<String, Map<String, Integer>> vectorsOfHam,
			List<Integer> seedList) {

		Map<String, Vector<Integer>> allHamVectorInHyperplain = new HashMap<String, Vector<Integer>>();
		// Map<String,Vector<Integer>> allSpamVectorInHyperplain = new
		// HashMap<String,Vector<Integer>>();
		// Map<String,Vector<Integer>> allQueryVectorInHyperplain = new
		// HashMap<String,Vector<Integer>>();
		// List<Map<String,Vector<Integer>>> allListVectorInHyperplain = new
		// ArrayList<Map<String,Vector<Integer>>>();

		// long sumOfHashspam = 0;
		// long sumOfHashqueryam = 0;
		//System.out.println("seedList " + seedList);
		for (Entry<String, Map<String, Integer>> e : vectorsOfHam.entrySet()) {

			Vector<Integer> oneVectorInHyperplain = new Vector<Integer>(
					seedList.size());

			for (int seedi = 0; seedi < seedList.size(); seedi++) {

				HashFunction h = Hashing.murmur3_128(seedList.get(seedi));
				double sumOfHashham = 0;
				
				// hyperplain for training emails
				for (Entry<String, Integer> entry : e.getValue().entrySet()) {
					String k = entry.getKey();
					int v = entry.getValue();

					HashCode hvofEmali = h
							.hashString(k, StandardCharsets.UTF_8);

					sumOfHashham += ((double)hvofEmali.asLong()) * v;

				}

				if (sumOfHashham < 0) {
					oneVectorInHyperplain.add(0);

				} else if (sumOfHashham > 0) {
					oneVectorInHyperplain.add(1);

				} else {
					oneVectorInHyperplain.add(0);
				}

			}
			allHamVectorInHyperplain.put(e.getKey(), oneVectorInHyperplain);
		}

//		System.out.println("    allVectorInHyperplain"
//				+ allHamVectorInHyperplain);
		return allHamVectorInHyperplain;
	}

}
