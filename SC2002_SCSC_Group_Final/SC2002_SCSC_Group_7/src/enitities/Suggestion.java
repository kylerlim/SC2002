package enitities;
import java.util.Scanner;

/**
 * The `Suggestion` class represents the suggestions made by camp committee members
 * for a specific camp they oversee and need to be handled by the specific camp creator.
 */
public class Suggestion {


	/**
	 * Network ID of sender of suggestion.
	 */
	private String sender;
	/**
	 * The name of the camp associated with the suggestion.
	 */
	private String campName;
	/**
	 * The content of the suggestion message.
	 */
	private String message;
	/**
	 * The status of the suggestion.
	 */
	private String status;

	private static Scanner scanner = new Scanner(System.in);

	/**
	 * Constructs a new suggestion with the specified attributes.
	 *
	 * @param sender    The sender of the suggestion.
	 * @param campName  The camp name associated with the suggestion.
	 * @param message   The content of the suggestion message.
	 * @param status    The status of the suggestion.
	 */
	public Suggestion(String sender, String campName, String message, String status) {
		this.sender = sender;
		this.campName = campName;
		this.message = message;
		this.status = status;

	}

	/**
	 * Default constructor for the `Suggestion` class.
	 */
	public Suggestion(){}

	/**
	 * Sets the sender of the suggestion.
	 *
	 * @param sender The sender of the suggestion.
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}


	/**
	 * Sets the name of the camp associated with the suggestion.
	 *
	 * @param campName The name of the camp associated with the suggestion.
	 */
	public void setCampName(String campName) {
		this.campName = campName;
	}

	/**
	 * Sets the content of the suggestion message.
	 */
	public void setMessage() {
		System.out.println("Enter message:");
		this.message = scanner.nextLine();
	}

	/**
	 * Sets the status of the suggestion.
	 *
	 * @param status The status of the suggestion.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the sender of the suggestion.
	 *
	 * @return The sender of the suggestion.
	 */
	public String getSender() {
		return this.sender;
	}

	/**
	 * Gets the name of the camp associated with the suggestion.
	 *
	 * @return The name of the camp associated with the suggestion.
	 */
	public String getCampName() {
		return this.campName;
	}

	/**
	 * Gets the content of the suggestion message.
	 *
	 * @return The content of the suggestion message.
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Gets the status of the suggestion.
	 *
	 * @return The status of the suggestion.
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Prints the details of the suggestion.
	 */
	public void printSuggestionDetails() {
		System.out.println(sender + ", " + campName + ", " + message + ", status:{" + status + "}");

	}


	// ... [Any other methods or code you have in your class]
}