package controllers;

/**
 * The {@code Controller} interface defines methods for getting user input and printing a menu.
 */
public interface Controller {

	/**
	 * Gets user input based on the implemented functionality.
	 *
	 * @return {@code true} if the user input indicates a request to return to the main menu, {@code false} otherwise.
	 */
	boolean getUserInput();

	/**
	 * Prints the menu for the implemented functionality.
	 */
	void printMenu();
}
