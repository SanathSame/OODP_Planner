package P1;
/**
 * Interface used to display respective menus to the user. Implemented by studentMenuUI and adminMenuUI classes
 * 
 */
public interface menuUI {	
	/**
	 * Public method called from starsPlannerUI to display menu.
	 * 
	 * @param Username : username of the user who logged in to the application
	 */
	public void displayMenu(String Username);
}
