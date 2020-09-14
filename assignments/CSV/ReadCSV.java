package Csv_alt1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;



public class ReadCSV {

	
	// place-holder for file lines
	ArrayList<String[]> linesArray = new ArrayList<String[]>();
    // The columns used in the task
	HashMap<Integer, String[]> columns = new HashMap<Integer, String[]>();	
	
	// Method for reaading the file lines and saving in the ArrayList
	public void readFile(String filePath) {

		File file = new File(filePath);
		
		try {
			Scanner sc = new Scanner(file);
    	    while(sc.hasNextLine() ) {		    	 
		    	 String line = sc.nextLine();		    	 
		    	 linesArray.add( line.split(",")   );	
		    	 
			}
		    		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
				
	
	}
	
	
	// Print the file content
	public void printFile() {
		
		for(String[] line_i: linesArray) {
			System.out.println( Arrays.toString(line_i)  );
		}
	}
	
	
	
	
	// Find duplicates using Set (in  O(n) time), 
	// ref:  https://stackoverflow.com/questions/6895192/find-duplicate-values-in-java-map
	//       https://www.java67.com/2015/10/2-ways-to-find-duplicate-elements-in-java-array.html
	
	public ArrayList<String> findDuplicates(String[] column) {
		
		HashSet<String> columnEntries = new HashSet<String>();		
		ArrayList<String> duplicates = new ArrayList<String>();
				
		for(int row = 0; row <  column.length; ++row) {	
			
			if(!columnEntries.add(column[row]) &&  !column[row].isBlank()){
				System.out.println("Duplicate:  " + column[row]);
				
				duplicates.add(column[row]);
			}
		}

		return duplicates;
	}
	
	
	public void getColumns() {
		
		int[] cols = new int[] {0, 1, 2, 3, 4, 6};
		
		for(int i = 0; i < cols.length; ++i) {
		    
			  String[] col_i = new String[ linesArray.size() ] ;	   
      		  for(int j = 0; j < linesArray.size(); ++j) {		 	      
      			  String[] line_j =  linesArray.get(j);		 	        
      			  col_i[j] = line_j[  cols[i]  ];				
		       }		 
      		
      		  
      		  columns.put(i, col_i );		
		}

		
	}
	
	
	public void findNamesWithChar(CharSequence ch) {
        
		System.out.println( "\nTASK [1]> The number of names that contain [" +  ch + "]:\n");
		
		int count = 0;

		for(int i = 1; i < linesArray.size(); ++i) {
			String[] line_i = linesArray.get(i);        	        	               	
        	for(int j = 1; j < 3; ++j) {        		
        		if( line_i[j].toLowerCase().contains(ch) ){
            		//System.out.println(" >>  " + line_i[j] );
            		++count;
        		}        		
        	}
        }
		
		System.out.println("Result:  " + count );
		System.out.println( "----------------------------------------------------"); 
	}
	
	
	public void timeStampDuplicates() {
		System.out.println( "\nTASK [2] > Students with the same time stamp:\n");
		// we need column with time stamps:
		
		String [] timeStamps = columns.get(0);
		String [] namesColOne = columns.get(1);
		String [] namesColTwo = columns.get(2);
		
		ArrayList<String> duplTimes = this.findDuplicates( timeStamps ); 
		
		for(int i = 0; i < duplTimes.size(); ++i) {
			
			String duplicate_i = duplTimes.get(i);			
			for(int j = 0; j < timeStamps.length; ++j) {
				
				if( Objects.equals(duplicate_i, timeStamps[j]) ) {					
					System.out.println( "[Names #1]:  "  +  namesColOne[j] + "    [Names #2]: " + namesColTwo[j]);					
				}				
			 }			
		 }
		System.out.println( "----------------------------------------------------"); 
	}
	
	
	// Check the column with OS type and count students that want to work on Android 
	public void worksWithAndroid() {

		System.out.println( "\nTASK [3]> The number of students that will work with an Android app:\n");
		
		String [] namesColOne = columns.get(1);
		String [] namesColTwo = columns.get(2);
		String [] osType = columns.get(5);		
		
		int count = 0;
		
		for(int i = 0; i < osType.length; ++i) {
			
			if( Objects.equals(osType[i], "Android App") ) {					
				System.out.println( "[Names #1]:  "  +  namesColOne[i] + "    [Names #2]: " + namesColTwo[i]);	
				
				++count;
			}
			
		}
		
		int total = count * 2;
		
		System.out.println( "\nResult (both columns):  " + total);
		System.out.println( "----------------------------------------------------"); 
		
		
	}
	
    // Matching names with e-mails	
	public void sortNames() {
		
		
		System.out.println( "\nTASK [4]> Match names with e-mails, check for duplicate e-mails: \n");
		
		// Adding all names / e-mails to ArrayLists, avoiding blank lines
		ArrayList<String> allNames = new ArrayList<String>();
		ArrayList<String> allEmails = new ArrayList<String>();
		
		for(int col = 1; col < 3; ++col) {
			
			String[] namesCol = columns.get(col);
			String[] emailsCol = columns.get(col + 2);
			
			// omit the first row with col. names
			for(int row = 1; row < namesCol.length; ++row) {
				
				if(!namesCol[ row ].isBlank()) {
					allNames.add( namesCol[ row ] );					
					allEmails.add( emailsCol[ row ]);
				    } 				
			}						
		}
		
		
		System.out.println("Names, e-mails (both columns): \n");
		
		
		for(int row = 0; row < allNames.size(); ++row) {
			System.out.print( allNames.get(row) + "   " + allEmails.get(row) + "\n");
		}
		
		
		// checking for duplicates
		String[] emails = allEmails.toArray( new String[ allEmails.size() ] );
		ArrayList<String> duplEmails = this.findDuplicates( emails ); 
		

		
		System.out.println("\nStudents with same emails:   \n");
				
		for(int dupl = 0; dupl < duplEmails.size(); ++ dupl) {
			
			String duplicate_i = duplEmails.get(dupl);
			for(int row = 0; row < allEmails.size(); ++ row) {
				
				if(Objects.equals(allEmails.get(row), duplicate_i ) ) {
					System.out.println(allNames.get(row) + " :  " + duplicate_i);
				}
				
			}
			
		}
		
		System.out.println( "----------------------------------------------------");
	}
	
	
	
	public static void main(String[] args) {
		
		
		ReadCSV csv = new ReadCSV();
		// Read the CSV file
		csv.readFile( "sample.csv" );
		csv.getColumns();
		
		//csv.printFile();
		
		
		// Find the number of students with an 'a' in their name
		csv.findNamesWithChar("a");
		//List students with same time stamp
		csv.timeStampDuplicates();
		// The number of students working with Android
		csv.worksWithAndroid();
		
		csv.sortNames();		
		
		
	}

	
}
