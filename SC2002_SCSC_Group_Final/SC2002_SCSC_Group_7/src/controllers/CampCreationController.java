package controllers;
import java.util.Scanner;

import enitities.Camp;
import enitities.Staff;

import java.util.ArrayList;

import enitities.*;
import helpers.*;
import boundaries.*;

/**
 * The CampCreationController class implements functionality for staff members to create new camps.
 * It allows staff to input information about a new camp, such as name, description, location, dates,
 * and visibility, and adds the created camp to the list of all camps. This class implements the createCamp
 * and Controller interfaces.
 */
public class CampCreationController implements createCamp, Controller {

    /**
     * The staff member creating the camp.
     */
    Staff staff;

    /**
     * List of all camps in the system.
     */
    ArrayList<Camp> allCamps;

    /**
     * Constructs a CampCreationController object with the specified list of all camps and staff member.
     *
     * @param allCamps List of all camps in the system.
     * @param staff    The staff member creating the camp.
     */
    public CampCreationController(ArrayList<Camp> allCamps, Staff staff){
        this.staff = staff;
        this.allCamps = allCamps;
        while (!getUserInput())
        System.out.println("Returning you to main menu...");
        }


    /**
     * Allows staff member to create a new camp by inputting details like
     * camp name, start and end dates, location etc.
     */
    public void createCamp(){
        Scanner sc = new Scanner(System.in);
        Camp newCamp = new Camp();
        ArrayList<String> newList = new ArrayList<String>();
        newCamp.setAttendeeList(newList);
        newCamp.setCommitteeList(newList);
        newCamp.setBlackList(newList);
        System.out.print("Enter Camp name: "); 
        String campName = sc.nextLine();
        newCamp.setName(campName);
        newCamp.setCommitteeSlots();
        newCamp.setTotalSlots();
        newCamp.setDescription();
        newCamp.setLocation();
        newCamp.setStartDate();
        newCamp.setEndDate();
        newCamp.setClosingDate();
        newCamp.setCreator(staff.getNetworkID());
        System.out.print("Allow all students outside " + staff.getFaculty() + "? (T/F)");
        Scanner excluScanner = new Scanner(System.in);
        String openString = excluScanner.nextLine();
        if (openString.equals("T")){newCamp.setOpenTo(Faculty.NTU);}
        else {newCamp.setOpenTo(staff.getFaculty());}
        newCamp.setVisibility();
        
        System.out.println(campName + " has been created, edit in the edit camp menu"); // to get the name

        allCamps.add(newCamp);

    }

    /**
     * Displays the menu options for creating a new camp.
     */
    @Override
    public void printMenu() {
        // TODO Auto-generated method stub
        System.out.println("Select function you want to do: ");
        System.out.println("1) Create new camp");
        System.out.println("0) Return to main menu");
        System.out.print("Enter the desired function:");
    }


    /**
     * Obtains user input and directs to the appropriate functionality based on the choice.
     *
     * @return True if the user chooses to return to the main menu, false otherwise.
     */
    @Override
    public boolean getUserInput() {
       Scanner sc = new Scanner(System.in);
       int userInput;
        printMenu();
        userInput = sc.nextInt();

        switch(userInput){
            case 1: 
                createCamp();
                return false;
            case 0:
                return true;

            default:
                System.out.print("Invalid choice. 0 to return to main menu. ");
                return false;
        }   
    }
}

