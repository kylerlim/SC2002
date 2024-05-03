package boundaries;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

// import controllers.ChangePasswordController;
// import controllers.MyEnquiryController;
// import controllers.MySuggestionsController;
import controllers.*;
import enitities.*;
import helpers.*;

// import ReportGeneratorController;
// import controllers.CampAuthorityController;
// import controllers.CampInterestController;
// import enitities.Camp;
// import enitities.Enquiry;
// import enitities.Staff;
// import enitities.Student;
// import enitities.Suggestion;
// import helpers.CampPrinter;

/**
 * The StudentDirectory class represents the
 * main menu and navigation panel for a student user.
 */
public class StudentDirectory {

	/**
	 * Represents the current student using the system.
	 */
	Student student;
	/**
	 * Represents a list of all students in the system.
	 */
	ArrayList<Student> allStudents;
	/**
	 * Represents a list of all staff members in the system.
	 */
	ArrayList<Staff> allStaffs;
	/**
	 * Represents a list of all camps in the system.
	 */
	ArrayList<Camp> allCamps;
	/**
	 * Represents a list of all enquiries in the system.
	 */
	ArrayList<Enquiry> allEnquiries;
	/**
	 * Represents a list of all suggestions in the system.
	 */
	ArrayList<Suggestion> allSuggestions;


	/**
	 * Initializes a new instance of the StudentDirectory class.
	 *
	 * @param student       The current student user.
	 * @param allStudents   List of all student users.
	 * @param allStaffs     List of all staff users.
	 * @param allCamps      List of all camps.
	 * @param allEnquiries  List of all enquiries.
	 * @param allSuggestions List of all suggestions.
	 * @throws FileNotFoundException If a file is not found.
	 * @throws NoSuchAlgorithmException If a required cryptographic algorithm is not available.
	 */
	public StudentDirectory(Student student, ArrayList<Student> allStudents, ArrayList<Staff> allStaffs, ArrayList<Camp> allCamps, ArrayList<Enquiry> allEnquiries, ArrayList<Suggestion> allSuggestions) throws FileNotFoundException, NoSuchAlgorithmException {
		Scanner qs = new Scanner(System.in);
		this.student = student;
		this.allCamps = allCamps;
		this.allStaffs = allStaffs;
		this.allStudents = allStudents;
		this.allEnquiries = allEnquiries;
		this.allSuggestions = allSuggestions;

		while (true) {
			if (directToController()) break;
		}
		System.out.println("Logging out...");
	}

	/**
	 * Prints the main menu interface and its options.
	 */
	public void printMenu() {
		System.out.println("***********************************");
        System.out.println("*            MAIN MENU            *");
        System.out.println("***********************************");
		System.out.println("----------Password Manager---------");
        System.out.println("* 1. Change my password           *");
		System.out.println("***********************************");
		System.out.println("------------Camps Manager-----------");
        System.out.println("* 2. View all eligible camps      *");
        System.out.println("* 3. Join camp/send enquiry       *");
        System.out.println("* 4. View/manage my camps         *");
		if (!student.getCampCommittee().isEmpty())
		System.out.println("* /create suggestions for camps.    *");
        System.out.println("*                                 *");
		System.out.println("------------ My Mailbox -----------");
        System.out.println("* 5. View/edit/delete enquiries   *");
		if (!student.getCampCommittee().isEmpty()){
			System.out.println("* 6. View/edit/delete suggestions *");
			System.out.println("*                                 *");
			System.out.println("-------Committee functions--------");
			System.out.println("* 7. Generate report of my camps   *");
			System.out.println("* 8. View/reply enquiries sent to my camps*");
		}
        System.out.println("***********************************");
		System.out.println("* 0. Logout                       *");
		System.out.print("Your choice: ");
	}

	/**
	 * Directs the user to the appropriate controller based on their menu choice.
	 *
	 * @return True if the user chooses to logout, false otherwise.
	 * @throws FileNotFoundException If a file is not found.
	 * @throws NoSuchAlgorithmException If a required cryptographic algorithm is not available.
	 */
	public boolean directToController() throws FileNotFoundException, NoSuchAlgorithmException {
		Scanner sc = new Scanner(System.in);
		printMenu();
		int choice = sc.nextInt();

		switch (choice) {

			case 1:
				ChangePasswordController cpc = new ChangePasswordController(student, allStudents, allStaffs);
				return false;

			case 2:
				CampPrinter cp = new CampPrinter(student, allCamps);
				cp.viewAllCamps();
				return false;

			case 3:
				CampInterestController cic = new CampInterestController(allCamps, allEnquiries, student);
				return false;


			case 4:
				RegisteredCampsController rcc = new RegisteredCampsController(student, allCamps, allSuggestions);
				return false;


			case 5:
				MyEnquiryController mec = new MyEnquiryController(student, allEnquiries);
				return false;

			case 6:
				if (student.getCampCommittee().isEmpty()) {
					System.out.println("Invalid choice.");
					return false;
				}
				MySuggestionsController msc = new MySuggestionsController(student, allSuggestions);
				return false;

			case 7:
				if (student.getCampCommittee().isEmpty()) {
					System.out.println("Invalid choice.");
					return false;
				}
				ReportGeneratorController rgc = new ReportGeneratorController(student, allCamps, allStudents, allStaffs);
				return false;


			case 8:
				if (student.getCampCommittee().isEmpty()) {
					System.out.println("Invalid choice.");
					return false;
				}
				CampAuthorityController cac = new CampAuthorityController(student, allCamps, allEnquiries);
				return false;
			case 0:
				return true;

			default:
				System.out.println("Invalid choice.");
				return false;
		}
	}
}