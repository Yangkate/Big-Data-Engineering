package BigData.week2;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

public class realDistanceBetweenTwoemail {

	public static Map<String, Double> getDistance(String pathToQueryEmail,
			String pathToEmail2) throws IOException {
		// TODO Auto-generated method stub
		Map<String, Double> twoEmailDistance = new HashMap<String, Double>();

		File file1 = new File(pathToQueryEmail);
		String email1 = FileUtils.readFileToString(file1);
		File file2 = new File(pathToEmail2);
		String email2 = FileUtils.readFileToString(file2);

		Map<String, Integer> VectorEmail1 = emailConvert.convert(email1);
		Map<String, Integer> VectorEmail2 = emailConvert.convert(email2);

		double sqOfEmail1 = 0;
		double sqOfEmail2 = 0;

		double sumOfMutiply = 0;
		for (Entry<String, Integer> entry : VectorEmail1.entrySet()) {
			String qk = entry.getKey();
			int qv = entry.getValue();
			sqOfEmail1 += qv * qv;
			if (VectorEmail2.containsKey(qk)) {

				sumOfMutiply += (VectorEmail2.get(qk)) * (qv);
			}
		}

		for (Entry<String, Integer> entry : VectorEmail2.entrySet()) {

			int v = entry.getValue();

			sqOfEmail2 += v * v;
		}

		double middleVarable = sumOfMutiply
				/ (Math.sqrt(sqOfEmail1) * Math.sqrt(sqOfEmail2));
		if (middleVarable > 1) {
			middleVarable = 1;
		}
		double distance = Math.acos(middleVarable);
		twoEmailDistance.put(pathToQueryEmail, distance);
		// System.out.println("---realDistanceBetweenTwoemail----");
		return twoEmailDistance;
	}

	public static Map<String, Double> getDistanceWithEmailName(
			String pathToQueryEmail, String pathToEmail2) throws IOException {
		Map<String, Double> twoEmailDistance = new HashMap<String, Double>();

		File file1 = new File(pathToQueryEmail);
		String email1 = FileUtils.readFileToString(file1);
		File file2 = new File(pathToEmail2);
		String email2 = FileUtils.readFileToString(file2);

		Map<String, Integer> VectorEmail1 = emailConvert.convert(email1);
		Map<String, Integer> VectorEmail2 = emailConvert.convert(email2);

		double sqOfEmail1 = 0;
		double sqOfEmail2 = 0;

		double sumOfMutiply = 0;
		for (Entry<String, Integer> entry : VectorEmail1.entrySet()) {
			String qk = entry.getKey();
			int qv = entry.getValue();
			sqOfEmail1 += qv * qv;
			if (VectorEmail2.containsKey(qk)) {

				sumOfMutiply += (VectorEmail2.get(qk)) * (qv);
			}
		}

		for (Entry<String, Integer> entry : VectorEmail2.entrySet()) {

			int v = entry.getValue();

			sqOfEmail2 += v * v;
		}

		double cosineOfAngle = sumOfMutiply
				/ (Math.sqrt(sqOfEmail1) * Math.sqrt(sqOfEmail2));

		if (cosineOfAngle > 1.001){
			throw new Error();
		}
		if ( cosineOfAngle >= 0.9999999999999999) {
			cosineOfAngle = 1;
		}
		double angle = Math.acos(cosineOfAngle);
		twoEmailDistance.put(pathToEmail2, angle);
		// System.out.println(distance+"pathToEmail2"+pathToEmail2);
		return twoEmailDistance;
	}

}
