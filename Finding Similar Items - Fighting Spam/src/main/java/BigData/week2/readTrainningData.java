package BigData.week2;


import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Multiset;

public class readTrainningData {
	public static List<Map<String, Integer>> readData2(String folderPath) throws IOException {
		
		//read trainning data
		    
			File folder = new File(folderPath);
			File[] listOfFiles = folder.listFiles();
	        //store all tranning email vectors
			final List<Map<String, Integer>> allvectors = new ArrayList<Map<String, Integer>>();
			
			
			for (int i = 0; i < listOfFiles.length; i++) {
				File file = listOfFiles[i];
				
				if (file.isFile() && file.getName().endsWith(".txt")) {
					//System.out.println("----------------file name-----------"+file.getName());
					
					String email = FileUtils.readFileToString(file);
					 /* do somthing with content */
					//function convert (string email) 
					allvectors.add(emailConvert.convert(email));			 
				}
			}
		   
			//System.out.println("--readtranningData done---");
			return allvectors;
		}
	
	public static List<Map<String, Integer>> readData(String folderPath) throws IOException {
	
	//read trainning data
		//store all tranning email vectors
				final List<Map<String, Integer>> allvectors = new ArrayList<Map<String, Integer>>();
	    //--
				int j;
			    String[] directories; 
			    
			    	
			    
			    	 File file = new File(folderPath);
			            //
			    	  directories = file.list(new FilenameFilter() {
			    		  public boolean accept(File current, String name) {
			    		    return new File(current, name).isDirectory();
			    		  }
			    		});

			    		 for( j=0;j<directories.length;j++){
			        		 File file2 = new File(folderPath+directories[j]);
			        		  File[] fList = file2.listFiles();
			        		 for (File file3 : fList) {
	       	        if (file3.isFile() && file3.getName().endsWith(".txt")) {
	        	            System.out.println(file3.getAbsolutePath());
		
				//System.out.println("----------------file name-----------"+file3.getName());
				
				String email = FileUtils.readFileToString(file3);
				 /* do somthing with content */
				//function convert (string email) 
				allvectors.add(emailConvert.convert(email));			 
			}
		}
	   
		System.out.println("--readtranningData done---");
	
	}
	    	 
			return allvectors;
			}
	public static Map<String,Map<String, Integer>> readData3(String folderPath) throws IOException {
		
		//read trainning data
			//store all tranning email vectors
					final Map<String,Map<String, Integer>> allvectors = new HashMap<String,Map<String, Integer>>();
		    //--
					int j;
				    String[] directories; 
				    
				    	
				    
				    	 File file = new File(folderPath);
				            //
				    	  directories = file.list(new FilenameFilter() {
				    		  public boolean accept(File current, String name) {
				    		    return new File(current, name).isDirectory();
				    		  }
				    		});

				    		 for( j=0;j<directories.length;j++){
				        		 File file2 = new File(folderPath+directories[j]);
				        		  File[] fList = file2.listFiles();
				        		 for (File file3 : fList) {
		       	        if (file3.isFile() && file3.getName().endsWith(".txt")) {
		        	            //System.out.println(file3.getAbsolutePath());
			
					//System.out.println("----------------file name-----------"+file3.getName());
					
					String email = FileUtils.readFileToString(file3);
					 /* do somthing with content */
					//function convert (string email) 
					allvectors.put(file3.getAbsolutePath(),emailConvert.convert(email));			 
				}
			}
		   
			//System.out.println("--readtranningData done---");
		
		}
		    	 
				return allvectors;}
	}
