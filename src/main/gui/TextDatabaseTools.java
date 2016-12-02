package main.gui;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * To use this class to input a textfile name and from there you can use a variety of methods to make
 * modifying text files easier. 
 * @author Freddie Taylor Pike
 */
public class TextDatabaseTools implements DatabaseTools {
	private String fileName;
	private String[][] arrayData = new String[9001][9001]; // IT"S OVER 9000!!!!!!!!!!!
	
	/** 
	 * A constructor for TextDatabaseTools. 
	 * IMPORTANT:If a file doesn't exist it will create a new one.
	 */
	public TextDatabaseTools(String fileName) {
		fileName += ".txt"; // Must add .txt.
		this.fileName = fileName;
		try {
		    File textFile = new File(fileName);

		    if ( textFile.exists() && !textFile.isDirectory() ) { // If file exists and exists in directory. 
		        System.out.println(fileName + " already Exists"); // If a text file already exists then use that one.
		    }
		    else {
		    	System.out.println(fileName + " is created"); // Create new text file.
		    	PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		    	writer.close();
		    }
		} 
		catch (Exception exception) {
		   System.out.println("There was an error: ");
		   exception.printStackTrace();
		}
	}
		
	/**
	 * Print out the entire contents of the text file.
	 */
	public void printContents() {
		String line = null;
		String[] splitLine = null;
		try {
			// FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            
			// Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	
	        int rowCount = 0; // Keep Row count to help with printing out text file. 
	        
	        // Read through the text document. 
	        while((line = bufferedReader.readLine()) != null) 
	        {
	        	splitLine = line.split("/");
	        	System.out.print("Row = " + rowCount + ":    ");
	        	for (int i = 0; i < splitLine.length; i++) {
	        		System.out.print(splitLine[i] + " ");
	        		// Another way to print out: System.out.print("Column " + i + " value = " + splitLine[i] + " ");
	        	}
	        	System.out.println();
	        	rowCount++;
	        }
	        bufferedReader.close();
		}
		catch(FileNotFoundException exception) {
            System.out.println("Unable to open file '" + fileName + "'");
            exception.printStackTrace();
        }
        catch(IOException exception) {
            System.out.println("Error reading file '" + fileName + "'");
            exception.printStackTrace();
        }
	}
	
	
	/**
	 * Print out the contents of a column of information from the text file.
	 * @param column - The column to print out
	 */
	public void printContentsByColumn(int column) {
		String line = null;
		String[] splitLine = null;
		try {
			// FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            
			// Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	
	        // Read through the text document. 
	        while((line = bufferedReader.readLine()) != null) 
	        {
	        	splitLine = line.split("/"); 
        		System.out.println(splitLine[column]); // Print out the specified column of array 
	        	
	        }
	        bufferedReader.close();
		}
		catch(FileNotFoundException exception) {
            System.out.println("Unable to open file '" + fileName + "'");
            exception.printStackTrace();
        }
        catch(IOException exception) {
            System.out.println("Error reading file '" + fileName + "'");
            exception.printStackTrace();
        }
	}

	/**
	 * Add a line to the text file.
	 * @param line - line to be added at end of text file. MUST ADD "/" between elements to specify 
	 * a new column.
	 */
	public void addRow(String line) {
		try {
	        FileWriter fileWriter = new FileWriter(fileName, true);
	        
	        fileWriter.write(line + "\n");  // Write new line out 
	        fileWriter.close();
		}
		catch(FileNotFoundException exception) {
            System.out.println("Unable to open file '" + fileName + "'");
            exception.printStackTrace();
        }
        catch(IOException exception) {
            System.out.println("Error reading file '" + fileName + "'");
            exception.printStackTrace();
        }
	}
	
	/**
	 * Remove an entire line from text database. MUST ADD "/" between elements to specify 
	 * a new column.
	 * @param line - The line to be removed from the text file. 
	 */
	public void removeRow(String deleteLine) {
		try {
			// Name of tempfile that the data will be written to without the specified row. 
			File textFile = new File(fileName);
			File tempFile = new File("tempTable.txt");
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
			
			String line; 
			boolean lineNotDeleted = true; // This variable is needed so multiple columns with the same
			// value aren't deleted.
			
			while((line = bufferedReader.readLine()) != null) 
	        {
        		if (line.equals(deleteLine) && lineNotDeleted ) {
        			lineNotDeleted = false;
        			continue; 
        		}
        		bufferedWriter.write(line);
        		bufferedWriter.newLine();
	        }
			
			boolean isFileRenamed = tempFile.renameTo(textFile); // Rename the temp file to the old filename.
			
			// This tests if the file was renamed or not.
			if (isFileRenamed) {
				System.out.println("File Was Renamed");
			} else {
				System.out.println("File Wasn't Renamed");
			}
			
			bufferedWriter.close(); 
			bufferedReader.close(); 
		}
		catch(FileNotFoundException exception) {
            System.out.println("Unable to open file '" + fileName + "'");
            exception.printStackTrace();
        }
        catch(IOException exception) {
            System.out.println("Error reading file '" + fileName + "'");
            exception.printStackTrace();
        }
	}
	
	/**
	 * Remove line from text database. 
	 * @param line - The line to be removed from the text file
	 *
	public void removeRowByStartingColumnValue(String deleteLine) {
		try {
			// Name of temptable.txt 
			File textFile = new File(fileName);
			File tempFile = new File("tempTable.txt");
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
			
			String line; 
			boolean lineNotDeleted = true;
			
			while((line = bufferedReader.readLine()) != null) 
	        {
        		if (line.startsWith(deleteLine) && lineNotDeleted ) {
        			lineNotDeleted = false;
        			continue; 
        		}
        		bufferedWriter.write(line);
        		bufferedWriter.newLine();
	        }
			
			boolean isFileRenamed = tempFile.renameTo(textFile);
			
			if (isFileRenamed) {
				System.out.println("File Was Renamed");
			} else {
				System.out.println("File Wasn't Renamed");
			}
			
			bufferedWriter.close(); 
			bufferedReader.close(); 
		}
		catch(FileNotFoundException exception) {
            System.out.println("Unable to open file '" + fileName + "'");
            exception.printStackTrace();
        }
        catch(IOException exception) {
            System.out.println("Error reading file '" + fileName + "'");
            exception.printStackTrace();
        }
	}
	*/
	
	/**
	 * Remove an entire line from text database.
	 * @param line - The line to be removed from the textfile
	 */
	public void removeRowByColumn(String deleteLine, int column) {
		try {
			// Name of tempfile
			File textFile = new File(fileName);
			File tempFile = new File("tempTable.txt");
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
			
			String line; 
			String splitLine[];
			
			boolean lineNotDeleted = true; // This variable is needed so multiple columns with the same
			// value aren't deleted.
			
			while((line = bufferedReader.readLine()) != null) 
	        {
				splitLine = line.split("/");
        		if (splitLine[column].equals(deleteLine) && lineNotDeleted ) {
        			lineNotDeleted = false;
        			continue; 
        		}
        		bufferedWriter.write(line);
        		bufferedWriter.newLine();
	        }
			
			boolean isFileRenamed = tempFile.renameTo(textFile); // Rename the temp file to the old filename.
			
			// This tests if the file was renamed or not.
			if (isFileRenamed) {
				System.out.println("File Was Renamed");
			} else {
				System.out.println("File Wasn't Renamed");
			}
			
			bufferedWriter.close(); 
			bufferedReader.close(); 
		}
		catch(FileNotFoundException exception) {
            System.out.println("Unable to open file '" + fileName + "'");
            exception.printStackTrace();
        }
        catch(IOException exception) {
            System.out.println("Error reading file '" + fileName + "'");
            exception.printStackTrace();
        }
	}
	
	/**
	 * Return the text file as a 2D array.
	 */
	public String[][] returnTextFileAsArray() {
		String line = null;
		String[] splitLine = null;
		try {
			// FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            
			// Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	
	        int rowCount = 0; // To help with putting the data into the array.
	        
	        // Read through the text document. 
	        while((line = bufferedReader.readLine()) != null) 
	        {
	        	splitLine = line.split("/");
	        	// System.out.print("Row = " + rowCount + " ");
	        	for (int i = 0; i < splitLine.length; i++) {
	        		arrayData[rowCount][i] = splitLine[i];
	        		// System.out.println(arrayData[rowCount][i]);// Another way to print: System.out.print("Column " + i + " value = " + splitLine[i] + " ");
	        	}
	        	rowCount++;
	        }
	        // Always close files.
	        bufferedReader.close();
	        return arrayData;
		}
		catch(FileNotFoundException exception) {
            System.out.println("Unable to open file '" + fileName + "'");
            exception.printStackTrace();
            return arrayData;
        }
        catch(IOException exception) {
            System.out.println("Error reading file '" + fileName + "'");
            exception.printStackTrace();
            return arrayData;
        }
	}
	
	/**
	 * Update the text file using a 2D array.
	 * @param updateArray - 
	 */
	public void updateTextFileUsingArray(String[][] updateArray) {
		try {
			// 
			File textFile = new File(fileName);
			File tempFile = new File("tempTable.txt");
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
			
			// Nested for loop that writes each element from the array to the text file. 
			for (int i = 0; i < updateArray.length; i++) {
	    		for (int j = 0; j < updateArray[0].length; j++) {
	    			if (updateArray[i][j] == null) break;
	    			bufferedWriter.write(updateArray[i][j] + "/");
	    		}
	    		if (updateArray[i][0] == null) break;
	    		bufferedWriter.newLine();
	    	}	
			
			boolean isFileRenamed = tempFile.renameTo(textFile);
			
			if (isFileRenamed) {
				System.out.println("File Was Renamed");
			} else {
				System.out.println("File Wasn't Renamed");
			}
			
			bufferedWriter.close(); 
			bufferedReader.close(); 
		}
		catch(FileNotFoundException exception) {
            System.out.println("Unable to open file '" + fileName + "'");
            exception.printStackTrace();
        }
        catch(IOException exception) {
            System.out.println("Error reading file '" + fileName + "'");
            exception.printStackTrace();
        }
		
	}
	
	/**
	 * Return a row as an arrayList.
	 * @param row - row that will grabbed from text file.
	 * @return
	 */
	public ArrayList<String> grabRowDataAsArrayList(int row) {
		String line = null;
		String[] splitLine = null;
		ArrayList<String> tempList = new ArrayList();
		try {
			// FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            
			// Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	
	        int rowTemp = 0;
	        
	        // Read through the text document. 
	        while((line = bufferedReader.readLine()) != null) 
	        {
	        	if (rowTemp == row) {
	        		splitLine = line.split("/");
	        		for (int i = 0; i < splitLine.length; i++) {
	        			tempList.add(splitLine[i]);
	        		}
	        		break;
	        	}
	        	rowTemp++;
	        }
	        // Always close files.
	        bufferedReader.close();
	        return tempList;
		}
		catch(FileNotFoundException exception) {
            System.out.println("Unable to open file '" + fileName + "'");
            exception.printStackTrace();
            return tempList;
        }
        catch(IOException exception) {
            System.out.println("Error reading file '" + fileName + "'");
            exception.printStackTrace();
            return tempList;
        }
	}
	
	/**
	 * Return a column as an arrayList.
	 * @param column - column that will grabbed from text file.
	 * @return
	 */
	public ArrayList<String> grabColumnDataAsArrayList(int column) {
		String line = null;
		String[] splitLine = null;
		ArrayList<String> tempList = new ArrayList();
		try {
			// FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            
			// Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	
	        int rowTemp = 0;
	        
	        // Read through the text document. 
	        while((line = bufferedReader.readLine()) != null) 
	        {
	        	splitLine = line.split("/");
	        	tempList.add(splitLine[column]);
	        	// System.out.println("Column " + column + " " + splitLine[column]);
	        }
	        // Always close files.
	        bufferedReader.close();
	        return tempList;
		}
		catch(FileNotFoundException exception) {
            System.out.println("Unable to open file '" + fileName + "'");
            exception.printStackTrace();
            return tempList;
        }
        catch(IOException exception) {
            System.out.println("Error reading file '" + fileName + "'");
            exception.printStackTrace();
            return tempList;
        }
	}
	
	/**
	 * This method updates a column of a textfile using an ArrayList.
	 * @param updateArray - The array that updates the column
	 * @param column - update this column of the fille .
	 */
	public void updateColumnUsingArrayList(ArrayList<String> updateArray, int column) {
		try {
			// Name of tempfile
			File textFile = new File(fileName);
			File tempFile = new File("tempTable.txt");
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
			
			String line; 
			String splitLine[];
			
			int arrayCounter = 0; // Go through the parameter array with this value
			
			while((line = bufferedReader.readLine()) != null) 
	        {
				splitLine = line.split("/");
				for (int i = 0; i < splitLine.length; i++) {
					if (i == column) {
						bufferedWriter.write(updateArray.get(arrayCounter) + "/");
						arrayCounter++;
						continue;
					}
					bufferedWriter.write(splitLine[i] + "/");
					System.out.print(splitLine[i] + "/");
				}
				System.out.println();
        		bufferedWriter.newLine();
	        }
			
			boolean isFileRenamed = tempFile.renameTo(textFile);
			
			if (isFileRenamed) {
				System.out.println("File Was Renamed");
			} else {
				System.out.println("File Wasn't Renamed");
			}
			
			bufferedWriter.close(); 
			bufferedReader.close(); 
		}
		catch(FileNotFoundException exception) {
            System.out.println("Unable to open file '" + fileName + "'");
            exception.printStackTrace();
        }
        catch(IOException exception) {
            System.out.println("Error reading file '" + fileName + "'");
            exception.printStackTrace();
        }
	}
	
	/**
	 * Return a row as an arrayList. ***NOT FINISHED
	 * @param row - row that will grabbed from text file.
	 * @return
	 */
	public void updateRowUsingArrayList(ArrayList<String> updateArray, int row) {
		try {
			// Name of tempfile
			File textFile = new File(fileName);
			File tempFile = new File("tempTable.txt");
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
			
			String line; 
			String splitLine[];
			
			int arrayCounter = 0; // Go through the parameter array with this value
			
			while((line = bufferedReader.readLine()) != null) 
	        {
				splitLine = line.split("/");
				for (int i = 0; i < splitLine.length; i++) {
					if (i == row) {
						for (int j = 0; j < updateArray.size(); j++) {
							bufferedWriter.write(updateArray.get(j) + "/");
						}
						continue;
					}
					bufferedWriter.write(splitLine[i] + "/");
					System.out.print(splitLine[i] + "/");
				}
				System.out.println();
        		bufferedWriter.newLine();
	        }
			
			boolean isFileRenamed = tempFile.renameTo(textFile);
			
			if (isFileRenamed) {
				System.out.println("File Was Renamed");
			} else {
				System.out.println("File Wasn't Renamed");
			}
			
			bufferedWriter.close(); 
			bufferedReader.close(); 
		}
		catch(FileNotFoundException exception) {
            System.out.println("Unable to open file '" + fileName + "'");
            exception.printStackTrace();
        }
        catch(IOException exception) {
            System.out.println("Error reading file '" + fileName + "'");
            exception.printStackTrace();
        }
	}
	
	/**
	 * A method that sorts the text file. TO BE IMPLEMENETED IN 2ND ITERATION. 
	 */
	public void sort() {
		
	}
	
	/**
	 * A method that sorts the text file by a specified column. TO BE IMPLEMENETED IN 2ND ITERATION. 
	 * @param - column - an int value that specifies what column the text file is going to be sorted by.
	 */
	public void sortByColumn(int column) {
		
	}
	
	/**
	 * Print out all the class name and the name of the textfile.
	 */
	@Override
	public String toString() {
		return "TextDatabaseManager [fileName=" + fileName + "]";
	}
}
