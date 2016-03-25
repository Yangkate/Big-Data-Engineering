package Big.week1;

import java.util.ArrayList;
import java.util.List;

public class jaccardDistance {

	public static float jc(String s, String t) {
		String[] sSplit = s.split(",");

		String[] tSplit = t.split(",");

		// calculate intersection
		List<String> intersection = new ArrayList<String>();
		for (int i = 0; i < sSplit.length; i++) {
			for (int k = 0; k < tSplit.length; k++) {
				if (!intersection.contains(sSplit[i])) // no duplicate
					if (sSplit[i].equals(tSplit[k])) // has intersection
					{
						intersection.add(sSplit[i]);
						break;
					}

			}
		}

		// calculate union
		List<String> union = new ArrayList<String>();
		if (sSplit.length > tSplit.length) // calculate big tupple first
		{
			for (int i = 0; i < sSplit.length; i++)
				if (!union.contains(sSplit[i]))
					union.add(sSplit[i]);
			for (int i = 0; i < tSplit.length; i++)
				if (!union.contains(tSplit[i]))
					union.add(tSplit[i]);
		} else {
			for (int i = 0; i < tSplit.length; i++)
				if (!union.contains(tSplit[i]))
					union.add(tSplit[i]);
			for (int i = 0; i < sSplit.length; i++)
				if (!union.contains(sSplit[i]))
					union.add(sSplit[i]);

		}

		return (1 - ((float) intersection.size()) / ((float) union.size()));
	}
}