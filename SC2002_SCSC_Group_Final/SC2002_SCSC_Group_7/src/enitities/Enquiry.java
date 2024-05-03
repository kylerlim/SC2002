package enitities;
import java.util.Scanner;

/**
 * The `Enquiry` class represents an enquiry made by a user regarding a specific camp.
 * It includes information such as the sender, camp name, message, status, reply,
 * and the responder's information.
 */
public class Enquiry {

	/**
	 * Network ID of sender.
	 */
	private String sender;
	/**
	 * Name of the camp.
	 */
	private String campName;
	/**
	 * Enquiry message about the camp.
	 */
	private String message;
	/**
	 * Status of the enquiry message.
	 */
	private String status;
	/**
	 * Reply to enquiry message.
	 */
	private String reply;
	/**
	 * Responder's information
	 */
	private String enquiryResponder;

	private static Scanner scanner = new Scanner(System.in);

	/**
	 * Constructs an `Enquiry` object with the specified details.
	 *
	 * @param sender            The sender of the enquiry.
	 * @param campName          The name of the camp related to the enquiry.
	 * @param message           The message content of the enquiry.
	 * @param status            The status of the enquiry.
	 * @param reply             The reply to the enquiry.
	 * @param enquiryResponder The responder's information for the enquiry.
	 */
	public Enquiry(String sender, String campName, String message, String status, String reply, String enquiryResponder){
		this.sender = sender;
		this.campName = campName;
		this.message = message;
		this.status = status;
		this.reply = reply;
		this.enquiryResponder = enquiryResponder;
	}

	/**
	 * Default constructor for the `Enquiry` class.
	 */
	public Enquiry(){

	}

	/**
	 * Sets the sender of the enquiry.
	 *
	 * @param sender The sender of the enquiry.
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * Sets the camp name related to the enquiry.
	 *
	 * @param campName The name of the camp.
	 */
	public void setCampName(String campName) {

		this.campName = campName;

	}

	/**
	 * Sets the message content of the enquiry.
	 */
	public void setMessage() {
		System.out.println("Enter new message:");
		this.message = scanner.nextLine();
	}

	/**
	 * Sets the status of the enquiry.
	 *
	 * @param status The status of the enquiry.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Sets the reply to the enquiry.
	 *
	 * @param text The text reply to the enquiry.
	 */
	public void setReply(String text) {
		this.reply = text;
	}

	/**
	 * Gets the sender of the enquiry.
	 *
	 * @return The sender of the enquiry.
	 */
	public String getSender() {
		return this.sender;
	}

	/**
	 * Gets the camp name related to the enquiry.
	 *
	 * @return The name of the camp.
	 */
	public String getCampName() {
		return this.campName;
	}

	/**
	 * Gets the message content of the enquiry.
	 *
	 * @return The message content of the enquiry..
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Gets the status of the enquiry.
	 *
	 * @return The status of the enquiry.
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Gets the reply to the enquiry.
	 *
	 * @return The reply to the enquiry.
	 */
	public String getReply() {
		return this.reply;
	}

	/**
	 * Retrieves the responder's information for the enquiry.
	 *
	 * @return The responder's information.
	 */
	public String getEnquiryResponder() {
		return this.enquiryResponder;
	}

	/**
	 * Sets the responder's information for the enquiry.
	 *
	 * @param enquiryResponder The responder's information.
	 */
	public void setEnquiryResponder(String enquiryResponder) {
		this.enquiryResponder = enquiryResponder;
	}

	/**
	 * Prints the details of the enquiry.
	 */
	public void printEnquiryDetails() {
		System.out.print(sender + ", " + campName + ", " + message + ", status:{" + status + "}");

		if (!getEnquiryResponder().isEmpty()) {
			System.out.println(getEnquiryResponder() + ", " + reply);
		}

		System.out.print("\n");
	}

	// Method to close the scanner when done


	// ... [Any other methods you may have]
}
