package main.gui;
/**
 * This interface will classes to inherit database principles in order to make the program extendible. 
 * @author Freddie Pike
 *
 */
interface DatabaseTools {
	/**
	 * Print out entire contents of the database.
	 */
	public void printContents();
	
	/**
	 * Print out the contents of a column of information from the database.
	 * @param column
	 */
	public void printContentsByColumn(int column);

	/**
	 * Add a row to the database.
	 * @param line
	 */
	public void addRow(String line);
	
	/**
	 * Remove a row from text database.
	 * @param line - removes the row containing this value
	 */
	public void removeRow(String line);
}
