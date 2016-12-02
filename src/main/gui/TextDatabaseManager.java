package main.gui;
import java.io.File;

public class TextDatabaseManager implements DatabaseManager {
	
	public TextDatabaseManager() { }
	
	public void createFile(String fileName) {
		
	}
	
	/**
	 * Remove a file in the directory
	 * @param fileName The name of the file. ".txt" must be added at the end of file (ex "example.txt").
	 */
	public void deleteFile(String fileName) {
		try {
			File file = new File(fileName);

    		if (file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation has failed.");
    		}
    	} catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
