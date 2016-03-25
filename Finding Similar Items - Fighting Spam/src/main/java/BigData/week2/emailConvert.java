package BigData.week2;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class emailConvert {
	
	public static Map<String, Integer> convert(String email){
	//store a vector
		
		final Map<String, Integer> oneVector = new HashMap<String, Integer>();
			String cvsSplitBy3 = " ";
			//email = email.substring(8); //get rid of the 'Subject:' part
			 String[] emailPart = email.split(cvsSplitBy3);
			 //int emailSize=emailPart.length;
			 for(int s=0;s<emailPart.length;s++){
				// int j=emailSize-s-1;
				     if(oneVector.keySet().contains(emailPart[s]))
				     {
				    	 oneVector.put(emailPart[s], oneVector.get(emailPart[s])+1);

				     }else
				     {
				    	 oneVector.put(emailPart[s], 1);
				     }
				 
					 }
			 
			return oneVector;
}
	}
	

