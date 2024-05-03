package controllers;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import enitities.Camp;
import controllers.*;
import enitities.*;
import helpers.*;
import enitities.Staff;

import java.time.LocalDate;

/**
 * The `StaffCampsModificationController` class implements the editCamp, Controller, and deleteCamp interfaces,
 * providing functionality for staff members to modify and manage camps that they created in CAMs
 * (Camp Administration Management System).
 */
public class StaffCampsModificationController implements editCamp, Controller, deleteCamp {

	/**
     * The user input received from the menu.
     */
    int userInput = 0;
    /**
     * The staff member associated with this controller.
     */
    Staff staff;
    /**
     * The list of all available camps.
     */
    ArrayList <Camp> allCamps = new ArrayList<Camp>();

    /**
     * The camp to interact with during modification or deletion.
     */
    Camp campToInteract;

    /**
     * Constructs a new StaffCampsModificationController object.
     *
     * @param allCamps The list of all available camps.
     * @param staff    The staff member associated with this controller.
     */
    public StaffCampsModificationController(ArrayList <Camp> allCamps, Staff staff){
        this.staff = staff;
        this.allCamps = allCamps;

        while (true){
            this.allCamps = allCamps;
            CampPrinter cp = new CampPrinter(staff, allCamps);
            cp.viewMyCamps();
            if (cp.getCampsAvailable().size() == 0){
                System.out.println("No camps found.");
                break;
            }
            cp.setCampToInteract();
            campToInteract = cp.getCampToInteract();

            if (getUserInput()){ 
                break;
            }
        }
        System.out.println("Returning you to main menu...");
    }

     
	/**
	 *
	 */

    /**
     * Allows staff to edit the selected camp based on various attributes.
     */
    public void editCamp() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Select what you want to edit:");
        System.out.println("(1) Camp description");
        System.out.println("(2) Camp dates");
        System.out.println("(3) Camp location");
        System.out.println("(4) Committee slots");
        System.out.println("(5) Total slots");
        System.out.println("(6) Faculty open to");
        System.out.println("(7) Visibility");
        System.out.println("(0) Abandon");
        System.out.print("Your choice: "); int choice = sc.nextInt();


        switch (choice) {
            case 1:
                campToInteract.setDescription();
                break;
            case 2:
                campToInteract.setStartDate(); 
                campToInteract.setEndDate();
                campToInteract.setClosingDate();
                break;
            case 3:
                campToInteract.setLocation();
                break;

            case 4:
                campToInteract.setCommitteeSlots();
                break;
            case 5:
            campToInteract.setTotalSlots();
                break;
            case 6:
                System.out.println("Allow all students outside " + staff.getFaculty() + "? (T/F)");
                Scanner openS = new Scanner(System.in);
                String openString = openS.nextLine();

                if (openString.equals("T")){campToInteract.setOpenTo(Faculty.NTU);}
                else {campToInteract.setOpenTo(staff.getFaculty());}

                break;
            case 7:
                campToInteract.setVisibility();
                break;
            default:
                break;
        }
        return;
	}

    /**
     * Allows staff to delete the selected camp.
     */
    public void deleteCamp() {
        for (int i = 0; i< allCamps.size(); i ++){
            if(allCamps.get(i).equals(campToInteract)) allCamps.remove(i);
        }
    }

    /**
     * Displays the menu options for the StaffCampsModificationController.
     */
    public void printMenu(){
        System.out.println("Select function you want to do: ");
        System.out.println("1) Edit camp");
        System.out.println("2) Delete camp");
        System.out.println("0) Return to main menu");
        System.out.print("Your choice: ");
    }

    /**
     * Function to get user input and perform specific functions based on the selected menu option.
     *
     * @return True if the user chooses to return to the main menu, false otherwise.
     */
    public boolean getUserInput(){
        Scanner sc = new Scanner(System.in);
        printMenu();
        userInput = sc.nextInt();
        switch(userInput){

            case 1: 
                editCamp();
                return false;

            case 2:
                deleteCamp();
                return false;
                
            case 0:
                return true;
            default:
                System.out.print("Invalid choice. 0 to return to main menu. ");
                return false;


        }

    }

}