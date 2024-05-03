package controllers;
import java.util.*;

import javax.swing.text.AbstractDocument.Content;

import enitities.Student;
import enitities.Suggestion;

/**
 * The MySuggestionsController class implements the Controller interface
 * and provides functionality to manage suggestions for a specific student.
 * The 'MySuggestionsController' class also implements the interfaces
 * viewMySuggestions, editSuggestion,deleteSuggestion, selectSuggestion.
 */
public class MySuggestionsController implements Controller, viewMySuggestions, editSuggestion,deleteSuggestion, selectSuggestion {

    /**
     * The user's choice input.
     */
    int choice;

    /**
     * The student for whom suggestions are managed.
     */
    Student student;

    /**
     * List of all suggestions in the system.
     */
    ArrayList<Suggestion> suggestionList = new ArrayList<Suggestion>();

    /**
     * List of suggestions specific to the student.
     */
    ArrayList<Suggestion> mySuggestions = new ArrayList<Suggestion>();

    /**
     * The suggestion to be modified or deleted.
     */
    Suggestion suggestionToModify;

    /**
     * Constructs a MySuggestionsController object with the specified student
     * and list of all suggestions.
     *
     * @param student        The student for whom suggestions are managed.
     * @param suggestionList List of all suggestions in the system.
     */
    public MySuggestionsController(Student student, ArrayList<Suggestion> suggestionList) {
        this.student = student;
        this.suggestionList = suggestionList;


        for (Suggestion suggestion : suggestionList) {
            if (suggestion.getSender().equals(student.getNetworkID())) {
                mySuggestions.add(suggestion);
            }
        }


        while (true){
            if (mySuggestions.isEmpty()){
                System.out.println("No suggestions found.");
                break;
            }
            boolean quitStatus = getUserInput();
            if (quitStatus){ 
                break;
            }
        }
        System.out.println("Returning you to main menu...");
    }


    /**
     * Displays the details of suggestions issued
     * specifically by the student as a camp committee.
     */
    public void viewMySuggestions() {
        int i = 1;
        for (Suggestion suggestion : mySuggestions) {
            System.out.print("(" + (i) + ")" + " ");
            i++;
            suggestion.printSuggestionDetails();
        }
    }

    /**
     * Edits the message of a selected suggestion.
     */
    public void editSuggestion() {
        viewMySuggestions();
        selectSuggestion();
        if (suggestionToModify.getStatus().equals("VIEWED") || suggestionToModify.getStatus().equals("APPROVED")) {
            System.out.println("Suggestion is processing, you cannot edit it.");
            return;
        }
        suggestionToModify.setMessage();
    }

    /**
     * Deletes a selected suggestion.
     */
    public void deleteSuggestion() {
        viewMySuggestions();        
        selectSuggestion();
        if (suggestionToModify.getStatus().equals("VIEWED") || suggestionToModify.getStatus().equals("APPROVED")) {
            System.out.println("Suggestion is processing, you cannot delete it.");
            return;
        }

        suggestionList.remove(suggestionToModify);
        mySuggestions.remove(suggestionToModify);
    }

    /**
     * Selects a suggestion from the list to modify or delete.
     */
    public void selectSuggestion(){
        System.out.println("Select the Suggestion you wish to modify");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice> mySuggestions.size() || choice < 1){
            System.out.println("Invalid input.");
            return;
        }
        suggestionToModify = mySuggestions.get(choice - 1);
    }

    /**
     * Displays the menu for managing suggestions.
     */
    public void printMenu(){
        System.out.println("Select function you want to do: "); 
        System.out.println("(1) Edit Suggestion");
        System.out.println("(2) Delete Suggestion");
        System.out.println("(3) View suggestions");
        System.out.println("(0) Return to main menu");
        System.out.print("Your choice: ");

    }

    /**
     * Gets user input to perform a specific action.
     *
     * @return true if the user chooses to return to the main menu, false otherwise.
     */
    public boolean getUserInput(){
        printMenu();
        Scanner ch = new Scanner(System.in);
        int choice = ch.nextInt();
        switch (choice){
            case 1:
                editSuggestion();
                return false;

            case 2:
                deleteSuggestion();
                return false;
            
            case 3:
                viewMySuggestions();
                return false;

            case 0:
                return true;
            default:
                System.out.print("Invalid choice. 0 to return to main menu. ");
                return false;
        }

    }

}




