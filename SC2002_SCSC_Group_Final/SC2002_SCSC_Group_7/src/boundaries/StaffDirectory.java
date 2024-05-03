package boundaries;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import controllers.CampCreationController;
import controllers.CampSuggestionsController;
import controllers.ChangePasswordController;
import controllers.ReportGeneratorController;
import controllers.StaffCampsModificationController;
import controllers.CampAuthorityController;
import enitities.Camp;
import enitities.Enquiry;
import enitities.Staff;
import enitities.Student;
import enitities.Suggestion;
import helpers.CampPrinter;

/**
 * The StaffDirectory class is responsible for managing the functionality and navigation
 * available to staff members. It provides a menu for staff to interact with various
 * features such as viewing camps, creating camps, modifying camps,
 * generating reports, changing passwords, and handling enquiries and suggestions.
 */
public class StaffDirectory {

	/**
	 * The staff member associated with this StaffDirectory.
	 */
	Staff staff;
	/**
	 * List containing all student records in the system.
	 */
	ArrayList<Student> allStudents;
	/**
	 * List containing all staff records in the system.
	 */
	ArrayList<Staff> allStaffs;
	/**
	 * List containing all camp records in the system.
	 */
	ArrayList<Camp> allCamps;
	/**
	 * List containing all enquiry records in the system.
	 */
	ArrayList<Enquiry> allEnquiries;
	/**
	 * List containing all suggestion records in the system.
	 */
	ArrayList<Suggestion> allSuggestions;

	/**
	 * Constructs a StaffDirectory object with the specified staff member and lists of
	 * students, staffs, camps, enquiries, and suggestions.
	 *
	 * @param staff        The staff member associated with this StaffDirectory.
	 * @param allStudents  List containing all student records in the system.
	 * @param allStaffs    List containing all staff records in the system.
	 * @param allCamps     List containing all camp records in the system.
	 * @param allEnquiries List containing all enquiry records in the system.
	 * @param allSuggestions List containing all suggestion records in the system.
	 * @throws NoSuchAlgorithmException If an algorithm is requested but is not available in the environment.
	 * @throws FileNotFoundException If the file containing the password records is not found.
	 */
	public StaffDirectory(Staff staff, ArrayList<Student> allStudents, ArrayList<Staff> allStaffs, ArrayList<Camp> allCamps, ArrayList<Enquiry> allEnquiries, ArrayList<Suggestion> allSuggestions) throws NoSuchAlgorithmException, FileNotFoundException {
		this.staff = staff;
		this.allCamps = allCamps;
		this.allStudents = allStudents;
		this.allStaffs = allStaffs;
		this.allEnquiries = allEnquiries;
		this.allSuggestions = allSuggestions;

		while (true){
			if (directToController()) break;
		}
		System.out.println("Logging out...");
	}

	/**
	 * Displays the main menu options for staff members.
	 */
	public void printMenu() {
    // TODO - implement StaffDirectory.printMenu
    
	System.out.println("*******************************************");
	System.out.println("*              	MAIN MENU            	  *");
	System.out.println("*******************************************");
	System.out.println("--------------Password Manager-------------");
	System.out.println("* 1. Change my password                   *");
	System.out.println("*                                         *");
	System.out.println("----------------Camps Manager--------------");
	System.out.println("* 2. View all camps                       *");
	System.out.println("* 3. Create camp                          *");
	System.out.println("* 4. View and modify my camps             *");
	System.out.println("* 5. Generate report of my camps          *");
	System.out.println("*                                         *");
	System.out.println("--------------Enquiries matters------------");
	System.out.println("* 6. View/reply enquiries sent to my camps*");
	System.out.println("*                                         *");
	System.out.println("-------------Suggestions matters-----------");
	System.out.println("* 7. View/approve suggestions to my camps *");
	System.out.println("*                                         *");
	
	
	System.out.println("*******************************************");
	System.out.println("* 0. Logout                   	          *");
	System.out.print("Your choice: ");

    System.out.println("");
    // throw new UnsupportedOperationException();
  }

	/**
	 * Directs the staff member to the appropriate controller based on their menu choice.
	 *
	 * @return True if the user chooses to logout, false otherwise.
	 * @throws NoSuchAlgorithmException If an algorithm is requested but is not available in the environment.
	 * @throws FileNotFoundException If the file containing the password records is not found.
	 */
	public boolean directToController() throws NoSuchAlgorithmException, FileNotFoundException {
	Scanner sc = new Scanner(System.in);
	printMenu();
	int choice = sc.nextInt();

	switch (choice) {

		case 1:
			ChangePasswordController cpc = new ChangePasswordController(staff,allStudents,allStaffs);
			return false;
		case 2:
			CampPrinter cp = new CampPrinter(staff, allCamps);
			cp.viewAllCamps();
			return false;

		case 3:
			CampCreationController ccc = new CampCreationController(allCamps, staff); // change the _____ after creation
			return false;

		case 4:
			StaffCampsModificationController scmc = new StaffCampsModificationController(allCamps, staff);
			return false;
		case 5:
			ReportGeneratorController rgc = new ReportGeneratorController(staff,allCamps,allStudents,allStaffs);
			return false;


		case 6:
			CampAuthorityController cac = new CampAuthorityController(staff,allCamps,allEnquiries); ///synchronise show message for case 5
			return false;

		case 7:
			CampSuggestionsController csc = new CampSuggestionsController(staff, allSuggestions, allStudents); ///synchronise show message for case 4
			return false;

		case 0:
			return true;
			
		default:
			System.out.print("Invalid choice. 0 to return to main menu. ");
			return false;
	}


  }

}