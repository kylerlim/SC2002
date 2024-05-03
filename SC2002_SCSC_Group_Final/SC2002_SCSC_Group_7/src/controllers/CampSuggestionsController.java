package controllers;

import java.util.ArrayList;
import java.util.Scanner;

import enitities.Staff;
import enitities.Student;
import enitities.Suggestion;
import controllers.*;
import enitities.*;
import helpers.*;

/**
 * The CampSuggestionsController class implements functionality for staff members
 * to view and approve suggestions submitted by students for camps they have created.
 * This class implements the viewSuggestions, approveSuggestions, and Controller interfaces.
 */
public class CampSuggestionsController implements viewSuggestions, approveSuggestions, Controller {

    /**
     * The staff member using the camp suggestions controller.
     */
    Staff staff;

    /**
     * List of all suggestions in the system.
     */
    ArrayList<Suggestion> allSuggestions;

    /**
     * List of all students in the system.
     */
    ArrayList<Student> allStudents;

    /**
     * List of suggestions related to camps created by the staff member.
     */
    ArrayList<Suggestion> myCampSuggestions = new ArrayList<Suggestion>();

    /**
     * The suggestion to be interacted with.
     */
    Suggestion sgToInteract;

    /**
     * Constructs a CampSuggestionsController object with the specified staff member,
     * list of all suggestions, and list of all students.
     *
     * @param staff           The staff member using the camp suggestions controller.
     * @param allSuggestions  List of all suggestions in the system.
     * @param allStudents     List of all students in the system.
     */
    public CampSuggestionsController(Staff staff, ArrayList <Suggestion> allSuggestions, ArrayList<Student> allStudents){
        this.staff = staff;
        this.allSuggestions = allSuggestions;
        this.allStudents = allStudents;
        int x = 1;
        for (Suggestion sg : allSuggestions){
            if (staff.getCreatedCamps().contains(sg.getCampName())){
                myCampSuggestions.add(sg);
                System.out.print("(" + x +")");
                sg.printSuggestionDetails();
                if (sg.getStatus().equals("UNVIEWED")) sg.setStatus("VIEWED");
                x++;
            }
        }


        while(true){
            if (myCampSuggestions.isEmpty()){
                System.out.println("No suggestions found.");
                break;
            }
            if (getUserInput()){ 
                break;
            }
        }
        System.out.println("Returning you to main menu...");
	}

    /**
     * Displays all suggestions related to camps created by the staff member.
     */
    public void viewSuggestions() {
        int x  = 1;
        for (Suggestion sg : myCampSuggestions){
                System.out.print("(" + x +")");
                sg.printSuggestionDetails();
                x++;
            }

}

    /**
     * Selects a suggestion to be approved based on user input.
     */
    public void selectSuggestion(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the suggestion you wish to approve: ");
        int sgChoice = sc.nextInt();
        if (sgChoice> myCampSuggestions.size() || sgChoice < 1){
            System.out.println("Invalid input.");
            return;
        }
        sgToInteract = myCampSuggestions.get((sgChoice - 1));
    }


    /**
     * Approves a selected suggestion and updates the status accordingly.
     * Also awards points to the camp committee who submitted the suggestion.
     */
    public void approveSuggestions(){
        viewSuggestions();
        selectSuggestion();
        if (sgToInteract.getStatus().equals("APPROVED")){
            System.out.println("Suggestion has already been approved.");
            return;
        }

        sgToInteract.setStatus("APPROVED");
        String studentName = sgToInteract.getSender();
        for (Student std: allStudents){
            if(std.getNetworkID().equals(studentName)){
                std.addPoints(1);
            }
        }

        System.out.println("Suggestion has been approved.");
        System.out.println("Make necessary amendments in the modifications menu.");
    }

    /**
     * Displays the menu options for viewing and approving suggestions.
     */
    public void printMenu(){
        System.out.println("Select function you want to do: ");
        System.out.println("(1) View all suggestions.");
        System.out.println("(2) Approve a suggestion.");
        System.out.println("(0) Return to main menu");
        System.out.print("Enter the desired function:");
    }

    /**
     * Obtains user input and directs to the appropriate functionality based on the choice.
     *
     * @return True if the user chooses to return to the main menu, false otherwise.
     */
    public boolean getUserInput(){
        printMenu();
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                viewSuggestions();
                return false;

            case 2:
                approveSuggestions();
                return false;

            case 0:
                return true;

            default:
                System.out.print("Invalid choice. 0 to return to main menu. ");
                return false;

            
        }
    }
}

