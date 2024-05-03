package controllers;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import enitities.Camp;
import enitities.Enquiry;
import enitities.Student;
import helpers.CampGenerator;
import helpers.CampPrinter;
import helpers.DateHandler;

/**
 * The CampInterestController class implements functionality for students to express interest
 * in joining camps, either as a camp committee member or camp attendee. It also allows students to
 * submit enquiries for camps. This class implements the registerAsCommittee, registerAsAttendee,createEnquiry
 * and Controller interfaces.
 */
public class CampInterestController implements registerAsCommittee,createEnquiry, registerAsAttendee, Controller {

    /**
     * List of all camps in the CAMs system.
     */
    ArrayList <Camp> allCamps;

    /**
     * List of all enquiries in the system.
     */
    ArrayList <Enquiry> allEnquiries;

    /*
     * List of camps that associated to the user.
     */
    ArrayList <Camp> myCamps = new ArrayList<Camp>();

    /**
     * The student using the camp interest controller.
     */
    Student student;
    /**
     * The camp for which the student is expressing interest.
     */
    Camp campToRegister;

    /**
     * Constructs a CampInterestController object with the specified list of all camps,
     * all enquiries, and the student using the controller.
     *
     * @param allCamps      List of all camps in the system.
     * @param allEnquiries  List of all enquiries in the system.
     * @param student       The student using the camp interest controller.
     */
    public CampInterestController(ArrayList<Camp> allCamps, ArrayList <Enquiry> allEnquiries, Student student){
        this.student = student;
        this.allCamps = allCamps;
        this.allEnquiries = allEnquiries;
        CampPrinter cp = new CampPrinter(student, allCamps);

        while(true){
            CampGenerator cg = new CampGenerator(student, allCamps);
            myCamps = cg.generateMyCamps();
            cp.viewAllCamps();
            System.out.println("(0) Go back");
            cp.setCampToInteract();
            if (cp.getCampToInteract() == null){ 
                break;
            }
            campToRegister = cp.getCampToInteract();
            if (getUserInput()){
                break;
            }    
        
        }
        System.out.println("Returning you to main menu...");
    }



    /**
     * Registers the student as a camp committee member for the selected camp, subject to various conditions.
     */
    public void registerAsCommittee() {
        String campName = campToRegister.getName();
        int currentSize = campToRegister.getCommitteeList().size() + campToRegister.getAttendeeList().size();

        if (student.getCampCommittee().contains(campName)){
            System.out.println("You are already in this camp as a committee.");
            return;
        }

        if (student.getCampAttendee().contains(campName)){
            System.out.println("You are already in this camp as an attendee.");
            return;
        }
		if (student.getCampCommittee().size() == Student.getMAX()){
            System.out.println("You could only register for up to " + Student.getMAX() + " camps as a committee.");
            return;
        }

        if (campToRegister.getCommitteeList().size() > campToRegister.getCommitteeSlots()){
            System.out.println("The camp committee is already full.");
            return;
        }

        if (currentSize > campToRegister.getTotalSlots() || campToRegister.getClosingDate().isBefore(LocalDate.now())){
            System.out.println("Camp is closed for registration");
            return;
        }

        if (student.getBlackListCamp().contains(campName)){
            System.out.println("You are not allowed to register for this camp");
            return;
        }


        
        for (Camp camp: myCamps){
            if(DateHandler.CampClashes(camp, campToRegister)){
                System.out.println(campToRegister.getName() + " clashes with " + camp.getName());
                System.out.println("You cannot register.");
                return;
            }
        }

        campToRegister.addToCommitteeList(student.getNetworkID());
        student.addToCommitteeCamp(campName);
        

        
	}

    /**
     * Registers the student as a regular camp attendee for the selected camp, subject to various conditions.
     */
    public void registerAsAttendee() {
        String campName = campToRegister.getName();
        int currentSize = campToRegister.getCommitteeList().size() + campToRegister.getAttendeeList().size();

        if (student.getCampCommittee().contains(campName)){
            System.out.println("You are already in this camp as a committee.");
            return;
        }

        if (student.getCampAttendee().contains(campName)){
            System.out.println("You are already in this camp as an attendee.");
            return;
        }

        if (student.getBlackListCamp().contains(campName)){
            System.out.println("You are not allowed to register for this camp");
            return;
        }

        if (currentSize > campToRegister.getTotalSlots() || campToRegister.getClosingDate().isBefore(LocalDate.now())){
            System.out.println("Camp is closed for registration");
            return;
        }
       
        for (Camp camp: myCamps){
            if(DateHandler.CampClashes(camp, campToRegister)){
                System.out.println(campToRegister.getName() + " clashes with " + camp.getName());
                System.out.println("You cannot register.");
                return;
            }
        }

        campToRegister.addToAttendee(student.getNetworkID());
        student.addToAttendeeCamp(campName);
    

    }

    /**
     * Creates an enquiry for the selected camp if the student is not already a committee member.
     */
    public void createEnquiry() {
        if (student.getCampCommittee().contains(campToRegister.getName())){
            System.out.println("You cannot send an enquiry to a camp you are committee of.");
            return;
        }
        Enquiry enquiry = new Enquiry();
        enquiry.setSender(student.getNetworkID());
        String campName = campToRegister.getName();
        enquiry.setCampName(campName);
        enquiry.setMessage();
        enquiry.setStatus("UNVIEWED");
        enquiry.setReply("");
        enquiry.setEnquiryResponder("");


        System.out.println("Enquiry has been created");
        allEnquiries.add(enquiry);

    }



    /**
     * Displays the menu options for expressing interest in a camp and submitting enquiries.
     */
    public void printMenu(){
        System.out.println("Select what you wish to do: ");
        System.out.println("(1) Sign up as committee member");
        System.out.println("(2) Sign up as regular member");
        System.out.println("(3) Submit an enquiry");
        System.out.println("(0) Return to main menu");
        System.out.print("Your choice: ");
    }

    /**
     * Obtains user input and directs to the appropriate functionality based on the choice.
     *
     * @return True if the user chooses to return to the main menu, false otherwise.
     */
    public boolean getUserInput(){
        Scanner sc = new Scanner(System.in);
        printMenu();
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                registerAsCommittee();
                return false;
            case 2:
                registerAsAttendee();
                return false;

            case 3:
                createEnquiry();
                return false;
            
            case 0:
                return true;
            default:
                System.out.print("Invalid choice. 0 to return to main menu. ");
                return false;
        }
    }
}