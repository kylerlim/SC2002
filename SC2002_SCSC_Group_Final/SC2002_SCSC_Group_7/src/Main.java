import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import controllers.*;
import enitities.*;
import helpers.*;
import boundaries.*;
import boundaries.Authenticator;
import boundaries.StaffDirectory;
import boundaries.StudentDirectory;
import enitities.Camp;
import enitities.Enquiry;
import enitities.Password;
import enitities.Staff;
import enitities.Student;
import enitities.Suggestion;
import helpers.CSVUtils;
import helpers.OffOnline;

/**
 * The main Application Class of the CAMs (Camp Administration Management System) Portal Application.
 */
public class Main {
    /**
     *
     * Main Function, starting point of the CAMs Portal Application
     *
     * @param args Command-line arguments passed to the application*
     * @throws NoSuchAlgorithmException When a particular cryptographic algorithm is requested but is not available in the environment.
     * @throws FileNotFoundException When a file with the specified pathname does not exist.
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException {
        // Instantiate all the CSV files
        ArrayList<Map<String, String>> passwordRecords = CSVUtils.readCSV("Password.csv");
        ArrayList<Map<String, String>> studentListRecords = CSVUtils.readCSV("StudentList.csv");
        ArrayList<Map<String, String>> staffListRecords = CSVUtils.readCSV("StaffList.csv");
        ArrayList<Map<String, String>> campInfoRecords = CSVUtils.readCSV("CampList.csv");
        ArrayList<Map<String, String>> userCampRecords = CSVUtils.readCSV("UserCamp.csv");
        ArrayList<Map<String, String>> suggestRecords = CSVUtils.readCSV("SuggestionList.csv");
        ArrayList<Map<String, String>> enquiryRecords = CSVUtils.readCSV("EnquiryList.csv");
        ArrayList <Camp> allCamps = OffOnline.campsRetriever(userCampRecords,campInfoRecords);
        ArrayList <Student> allStudents = OffOnline.studentsRetriever(studentListRecords,userCampRecords, passwordRecords);
        ArrayList <Staff> allStaffs = OffOnline.staffsRetriever(staffListRecords,userCampRecords,passwordRecords);
        ArrayList <Enquiry> allEnquiries = OffOnline.enquiriesRetriever(enquiryRecords);
        ArrayList <Suggestion> allSuggestions = OffOnline.suggestionsRetriever(suggestRecords);


        System.out.print("Welcome to the CAMS portal. Today is ");
        LocalDate currentDate = LocalDate.now(); // Create a date object
        System.out.println(currentDate); // Display the current date

        Scanner scanner = new Scanner(System.in);
        String defaultPW = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
        String networkID = "";
        String password = "";
        String userType = "";

        
        Authenticator authenticator = new Authenticator(); //instantiating a Authenticator object
        for (int attempt = 0; attempt < 3; attempt++) { //Giving each user three attempts to log in
            // Read User ID
            System.out.print("Enter Network ID: ");
            networkID = scanner.nextLine().toUpperCase();

            // Read password
            System.out.print("Enter Password: ");
            password = scanner.nextLine();

            // Read user type (student or staff)
            System.out.print("Enter user type (e.g.staff/student): ");
            userType = scanner.nextLine().toLowerCase();
            String stringUserType = String.valueOf((userType));

            // Hash the password to authenticate the user
            Password hashedPassword = new Password(Password.hashPassword(password));
            authenticator.setPermitted(networkID, hashedPassword, passwordRecords, stringUserType); //authenticating the user input

            if (authenticator.getPermitted()) { //verifying user input by checking boolean attribute permitted of authenticator object
                System.out.println("Authentication successful!");
                break; // Exit the loop on successful authentication
            } else {
                System.out.println("Failed authentication. Please try again.");
            }
        }

        if (!authenticator.getPermitted()) {
            System.out.println("Maximum attempts reached. Exiting the program.");
            return; // Exit if authentication fails
        }
        else { //login page for user
            if (userType.equals("staff")){
              Staff user = null;
                for (Staff staff : allStaffs){
                    if (staff.getNetworkID().equals(networkID)){
                        user = staff;
                    }
                }
                assert user != null;
                user.printUserAttributes();
                if (user.getEncypted_password().getPW().equals(Password.hashPassword("password"))) {
                    System.out.println("You are logging in for the first time, please change your password.");
                    ChangePasswordController cpc = new ChangePasswordController(user, allStudents, allStaffs);
                }
                StaffDirectory staffDirectory = new StaffDirectory(user, allStudents, allStaffs,allCamps, allEnquiries, allSuggestions);
            }
//
            else if (userType.equals("student")) {
                Student user=null;
                for (Student student : allStudents){
                    if (student.getNetworkID().equals(networkID)){
                        user = student;
                    }
                }
                if (user.getEncypted_password().getPW().equals(Password.hashPassword("password"))) {
                    System.out.println("You are logging in for the first time, please change your password.");
                    ChangePasswordController cpc = new ChangePasswordController(user, allStudents, allStaffs);
                }
                assert user != null;
                user.printUserAttributes();
                StudentDirectory studentDirectory = new StudentDirectory(user,allStudents,allStaffs,allCamps,allEnquiries,allSuggestions);
            }


        }


    ArrayList<Map<String, String>> updatedPasswordRecords = OffOnline.passwordsStorer(allStudents,allStaffs);
    ArrayList<Map<String, String>> updatedStudentListRecords = OffOnline.studentStorer(allStudents);
    ArrayList<Map<String, String>> updatedStaffListRecords =  OffOnline.staffStorer(allStaffs);
    ArrayList<Map<String, String>> updatedCampInfoRecords = OffOnline.campAttributesStorer(allCamps);
    ArrayList<Map<String, String>> updatedUserCampRecords = OffOnline.userCampStorer(allCamps,allStudents,allStaffs);
    ArrayList<Map<String, String>> updatedSuggestRecords = OffOnline.suggestionsStorer(allSuggestions);
    ArrayList<Map<String, String>> updatedEnquiryRecords = OffOnline.enquiriesStorer(allEnquiries);

    CSVUtils.writeCSV("SuggestionList.csv", updatedSuggestRecords);
    CSVUtils.writeCSV("Password.csv", updatedPasswordRecords);
    CSVUtils.writeCSV("EnquiryList.csv", updatedEnquiryRecords);
    CSVUtils.writeCSV("UserCamp.csv", updatedUserCampRecords);
    CSVUtils.writeCSV("StudentList.csv", updatedStudentListRecords);
    CSVUtils.writeCSV("StaffList.csv", updatedStaffListRecords);
    CSVUtils.writeCSV("CampList.csv", updatedCampInfoRecords);


    }
}