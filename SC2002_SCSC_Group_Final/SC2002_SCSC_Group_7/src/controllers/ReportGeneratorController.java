package controllers;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import enitities.Camp;
import enitities.Staff;
import enitities.Student;
import enitities.User;
import controllers.*;
import enitities.*;
import helpers.*;

/**
 * The `RegisteredCampsController` class implements the Controller interface and provides
 * functionality for managing the camps a student has registered for in the CAMs
 * (Camp Administration Management System).
 * The `RegisteredCampsController`  controller class also implements generalCampReport, CommitteePerforamnceReport Interfaces
 */
public class ReportGeneratorController implements generalCampReport, CommitteePerformanceReport{

    /**
     * The user associated with this controller.
     */
    User user;

    /**
     * The list of all available camps.
     */
    ArrayList<Camp> allCamps;

    /**
     * The list of all students in the CAMs system.
     */
    ArrayList<Student> allStudents;

    /**
     * The list of all staff in the CAMs system.
     */
    ArrayList<Staff> allStaffs;

    /**
     * Constructs a new ReportGeneratorController object.
     *
     * @param user        The user associated with this controller.
     * @param allCamps    The list of all available camps.
     * @param allStudents The list of all students in the CAMs system.
     * @param allStaffs   The list of all staff in the CAMs system.
     * @throws FileNotFoundException If the file is inaccessible.
     */
    public ReportGeneratorController(User user, ArrayList<Camp> allCamps, ArrayList<Student> allStudents, ArrayList<Staff> allStaffs) throws FileNotFoundException{
        this.user = user;
        this.allCamps = allCamps;
        this.allStudents = allStudents;
        this.allStaffs = allStaffs;
        while (!getUserInput());
        System.out.println("Returning you to main menu...");
    }

    /**
     * Generates a general camp report based on user and camp selection.
     *
     * @throws FileNotFoundException If the specified file is not found.
     */
    public void generalCampReport() throws FileNotFoundException {
        CampPrinter cp = new CampPrinter(user, allCamps);
        if (user instanceof Staff) cp.viewMyCamps();
        else if (user instanceof Student) cp.viewMyCommitteeCamps();

        if (cp.getCampsAvailable().isEmpty()){
            System.out.println("You do not have any camps. ");
            return;
        }
        cp.setCampToInteract();

        Camp camp = cp.getCampToInteract();
        ArrayList<String> filters = filterFunction();

        String fileName = String.format("%s_%s_%s.txt", camp.getName(), user.getName(), LocalDate.now());
        PrintStream originalOut = System.out; // Store the original System.out
        PrintStream out = new PrintStream(fileName);
        System.setOut(out);

        System.out.println("------------------" + camp.getName() + "---------------");
        for (String filter : filters) {
            switch (filter) {
                case "description":
                    System.out.println("Description: " + camp.getDescription());
                    break;
                case "startDate":
                    System.out.println("Start Date: " + camp.getStartDate());
                    break;
                case "endDate":
                    System.out.println("End Date: " + camp.getEndDate());
                    break;
                case "closingDate":
                    System.out.println("Closing Date: " + camp.getClosingDate());
                    break;

                case "location":
                    System.out.println("Location: " + camp.getLocation());
                    break;

                case "totalSlots":
                    System.out.println("Total Slots: " + camp.getTotalSlots());
                    break;

                case "staffIC":
                    for(Staff staff: allStaffs){
                        if (staff.getNetworkID().equals(camp.getCreator())){
                            System.out.println("Staff IC: " + staff.getName());
                        }
                    }
                    break;

                case "openTo":
                    System.out.println("Open To: " + camp.getOpenTo());
                    break;
                case "visibility":
                    System.out.println("Visibility: " + camp.getVisibility());
                    break;
                case "Committeeslot":
                    System.out.println("Committee Slots: " + camp.getCommitteeSlots());
                    break;
                // Add more cases for other filters as needed
                case "committeeList":
                    System.out.println("Camp Committee members: ");
                    for(Student student : allStudents){
                        if (student.getCampCommittee().contains(camp.getName())){
                            System.out.println(student.getName()+ "\t" + student.getFaculty());
                        }
                    }
                    break;
                case "attendeeList":
                    System.out.println("");
                    System.out.println("Camp Attendees: ");
                    for(Student student : allStudents){
                        if (student.getCampAttendee().contains(camp.getName())){
                            System.out.println(student.getName()+ "\t" + student.getFaculty());
                        }
                    }
                    break;
            }
            }
            out.close();
        System.setOut(originalOut); // Restore the original System.out

        System.out.println("REPORT HAS BEEN GENERATED.");
    }


    /**
     * Generates a committee performance report based on user and camp selection.
     *
     * @throws FileNotFoundException If the specified file is not found.
     */
    public void CommitteePerformanceReport() throws FileNotFoundException {
        CampPrinter cp = new CampPrinter(user, allCamps);
        cp.viewMyCamps();
        if (cp.getCampsAvailable().isEmpty()){
            System.out.println("You do not have any camps. ");
            return;
        }
        cp.setCampToInteract();
        Camp camp = cp.getCampToInteract();
        PrintStream originalOut = System.out; // Store the original System.out
        String fileName = String.format("%s_COMMITTEE_PERFORMANCE_%s_%s.txt", camp.getName(), user.getName(), LocalDate.now());
        PrintStream out = new PrintStream(fileName);
        ArrayList<String> allCommittees = null;
        System.setOut(out);
        System.out.println("--------" + camp.getName()+"----------");
        allCommittees =  camp.getCommitteeList();
        for (Student student: allStudents){
            if(allCommittees.contains(student.getNetworkID())){
                    System.out.println(student.getName() + " has a total points of " + student.getPoints());
            }
        }

        out.close();
        System.setOut(originalOut); // Restore the original System.out
        System.out.println("PERFORMANCE REPORT HAS BEEN GENERATED.");
    }


    /**
     * Function to specify filters for generating a camp report.
     *
     * @return ArrayList of selected filters.
     */
    public ArrayList<String> filterFunction() {
        ArrayList<String> filters = new ArrayList<String>(); // Initialize the ArrayList

        System.out.println("Please select the information to include in the report:");
        filters.add("CampName");
        Scanner sc = new Scanner(System.in);
        String input;
        String[] additionalFilters = {
                "Committeeslot",
                "startDate",
                "endDate",
                "closingDate",
                "location",
                "totalSlots",
                "description",
                "staffIC",
                "openTo",
                "visibility",
                "committeeList",
                "attendeeList"

        };

        for (String filter : additionalFilters) {
            System.out.print(filter + " [Y/N]: ");
            input = sc.next();
            if (input.equalsIgnoreCase("Y")) {
                filters.add(filter);
            }
        }

        // sc.close(); // Close the scanner when done

        return filters; // Return the ArrayList
    }

    /**
     * Displays the menu options for the ReportGeneratorController.
     */
    public void printMenu(){
        System.out.println("(1) Generate camp report. ");
        if (user instanceof Staff) System.out.println("(2) Generate performance report. ");
        System.out.println("(0) Return to main menu.");
        System.out.print("Enter your choice: ");
    }

    /**
     * Function to get user input and perform specific functions based on the selected menu option.
     *
     * @return True if the user chooses to return to the main menu, false otherwise.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public boolean getUserInput() throws FileNotFoundException {
        printMenu();
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch(choice){

            case 1:
                generalCampReport();
                return false;

            case 2:
                if (user instanceof Staff) CommitteePerformanceReport();
                else System.out.print("Invalid choice.");
                return false;
            
            case 0:
                return true;
            
            default:
                return false;
        }

    }
}
