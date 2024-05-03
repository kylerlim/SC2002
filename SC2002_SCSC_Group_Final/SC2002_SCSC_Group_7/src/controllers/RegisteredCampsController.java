package controllers;
import java.util.ArrayList;
import java.util.Scanner;

import enitities.Camp;
import enitities.Student;
import enitities.Suggestion;
import controllers.*;
import enitities.*;
import helpers.*;

/**
 * The `RegisteredCampsController` class implements the Controller interface and provides
 * functionality for managing the camps a student has registered for in the CAMs
 * (Camp Administration Management System).
 * The class also implements the interface quitCamp.
 * The class also implements the interface createSuggestion.
 */
public class RegisteredCampsController implements quitCamp,createSuggestion, Controller {

	/**
	 * The student associated with this controller.
	 */
    Student student;
    /**
     * The list of all available camps.
     */
    ArrayList<Camp> allCamps;
    /**
     * The list of all suggestions in the CAMs system.
     */
    ArrayList <Suggestion> allSuggestions;
    /**
     * The list of camps the student is registered for.
     */
    ArrayList<Camp> myCamps = new ArrayList<Camp>();

    /**
     * Constructs a new RegisteredCampsController object.
     *
     * @param student        The student associated with this controller.
     * @param allCamps       The list of all available camps.
     * @param allSuggestions The list of all suggestions in the CAMs system.
     */
    public RegisteredCampsController(Student student, ArrayList <Camp> allCamps, ArrayList <Suggestion> allSuggestions){
        this.student = student;
        this.allCamps = allCamps;
        this.allSuggestions = allSuggestions;
        while (!getUserInput());
        System.out.println("Returning you to main menu...");
        }


    /**
     * Function to allow student to quit a camp.
     */
    public void quitCamp() {
        Scanner sc = new Scanner(System.in);
        CampGenerator cg = new CampGenerator(student, allCamps);
        myCamps  = cg.generateMyAttendeeCamps();
        if (myCamps.isEmpty()){
            System.out.println("You do not have any camps available to quit from");
            return;
        }

        System.out.println("You can only quit from: ");
        int x = 1;
        for (Camp camp: myCamps){
            System.out.print("(" + x + ")");
            camp.printAttributes();
        }
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        Camp campToQuit = myCamps.get(choice - 1);
        String campName = campToQuit.getName();

        campToQuit.removeFromAttendeeList(student.getNetworkID());
        campToQuit.addToBlackList(student.getNetworkID());
        student.removeFromAttendeeCamp(campName);
        student.addToBlackListCamp(campName);

        System.out.println("You are now removed from " + campName + ". You cannot rejoin anymore...");
    }

    /**
     * Allows the student to create a suggestion to a particular camp
     * that he is a camp committee member of.
     */

    public void createSuggestion(){
        Scanner sc = new Scanner(System.in);
        CampGenerator cg = new CampGenerator(student, allCamps);
        myCamps  = cg.generateMyCommitteeCamps();

        if (myCamps.isEmpty()){
            System.out.println("You are not part of any committee ");
            return;
        }

        System.out.println("You can only submit suggestions to: ");
        int x = 1;
        for (Camp camp: myCamps){
            System.out.print("(" + x + ")");
            camp.printAttributes();
        }
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        Camp campToSuggest = myCamps.get(choice - 1);
        String campName = campToSuggest.getName();

        Suggestion sg = new Suggestion();
        sg.setCampName(campName);
        sg.setMessage();
        sg.setSender(student.getNetworkID());
        sg.setStatus("UNVIEWED");
        allSuggestions.add(sg);

    }

    /**
     * Displays the camps the student is participating as a
     * camp committee or camp attendee.
     */
    public void viewRegisteredCamps(){
        CampPrinter cp = new CampPrinter(student, allCamps);

        System.out.println("You are attending the following camps: ");
        if(student.getCampCommittee().size() != 0) System.out.println("=============COMMITTEE================");
        cp.viewMyCommitteeCamps();
        if(student.getCampAttendee().size() != 0)System.out.println("==============ATTENDEE================");
        cp.viewMyAttendeeCamps();
    }

    /**
     * Prints the menu for the RegisteredCampController controller.
     */
    public void printMenu(){
        System.out.println("Select what you want to do: ");
        System.out.println("(1) View camps you are part of.");
        System.out.println("(2) Withdraw from camp.");
        System.out.println("(3) Create a suggestion");
        System.out.println("(0) Return to main menu");
        System.out.print("Your choice: ");

    }

    /**
     * Gets user input and performs specific functions
     * based on the selected menu option.
     *
     * @return True if the user chooses to return to the main menu, false otherwise.
     */
    public boolean getUserInput(){
        printMenu();
        Scanner ch = new Scanner(System.in);
        int choice = ch.nextInt();
        switch (choice){
            case 1:
                viewRegisteredCamps();
                return false;

            case 2:
                quitCamp();
                return false;

            case 3:
                if (!student.getCampCommittee().isEmpty()) createSuggestion();
                else System.out.print("Invalid choice.");
                return false;
        
            case 0:
                return true;
                
            default:
                System.out.print("Invalid choice.");
                return false;
        }

    }


}