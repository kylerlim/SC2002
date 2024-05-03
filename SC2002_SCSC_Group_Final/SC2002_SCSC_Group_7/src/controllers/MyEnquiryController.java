package controllers;
import java.util.ArrayList;
import java.util.Scanner;

import enitities.Enquiry;
import enitities.Student;

/**
 * The MyEnquiryController class implements the Controller interface
 * and provides functionality to manage enquiries for a specific student.
 *
 * The 'MyEnquiryController' class also implements deleteEnquiry, viewMyEnquiries,
 * editEnquiry, selectEnquiry interfaces.
 */
public class MyEnquiryController implements deleteEnquiry, viewMyEnquiries, editEnquiry, selectEnquiry, Controller {

    /**
     * The user's choice input.
     */
    int choice;

    /**
     * The camp attendee student whose enquiries will be managed.
     */
    Student student;

    /**
     * List of all enquiries in the system.
     */
    ArrayList<Enquiry> allEnquiries = new ArrayList<Enquiry>();

    /**
     * List of enquiries specific to the student.
     */
    ArrayList<Enquiry> myEnquiries = new ArrayList<Enquiry>();

    /**
     * The enquiry to be modified or deleted.
     */
    Enquiry enquiryToModify;

    /**
     * Constructs a MyEnquiryController object with the specified student
     * and list of all enquiries.
     *
     * @param student       The student for whom enquiries are managed.
     * @param allEnquiries  List of all enquiries in the system.
     */
    public MyEnquiryController(Student student, ArrayList<Enquiry> allEnquiries){
        this.student = student;
        this.allEnquiries = allEnquiries;

        for (Enquiry enq: allEnquiries){
            if(enq.getSender().equals(student.getNetworkID())){
                myEnquiries.add(enq);
            }
        }
        while (true){
            if (myEnquiries.isEmpty()){
                System.out.println("No enquiries found.");
                break;
        }
            if (getUserInput()){ 
                break;
            }
        }
        System.out.println("Returning you to main menu...");
    }

    /**
     * Displays the details of enquiries specific to the student.
     */
    public void viewMyEnquiries() {
        int i = 1;
        for (Enquiry enquiry : myEnquiries){
            System.out.print("(" + (i) + ")" + " ");
            i++;
            enquiry.printEnquiryDetails();       
         }
    }

    /**
     * Edits the message of a selected enquiry.
     */
    public void editEnquiry(){
        viewMyEnquiries();
        selectEnquiry();
        // if (myEnquiries.isEmpty()){
        //     return;
        // }
        if(enquiryToModify.getStatus().equals("VIEWED")){
            System.out.println("Enquiry has been viewed, you cannot edit it.");
            return;        
        }
        enquiryToModify.setMessage();    
    }

    /**
     * Deletes a selected enquiry.
     */
    public void deleteEnquiry() {
        viewMyEnquiries();
        selectEnquiry();
        if(enquiryToModify.getStatus().equals("VIEWED")){
            System.out.println("Enquiry has been viewed, you cannot delete it.");
            return;        
        }
        allEnquiries.remove(enquiryToModify);
        myEnquiries.remove(enquiryToModify);    
    }

    /**
     * Selects an enquiry by camp attendee from the list to modify or delete.
     */
    public void selectEnquiry(){
        System.out.println("Select the enquiry you wish to modify: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice> myEnquiries.size() || choice < 1){
            System.out.println("Invalid input.");
            return;
        }
        enquiryToModify = myEnquiries.get(choice-1);
    }

    /**
     * Displays the menu for managing enquiries.
     */
    public void printMenu(){
        System.out.println("Select function you want to do: "); 
        System.out.println("(1) Edit Enquiry");
        System.out.println("(2) Delete Enquiry");
        System.out.println("(3) View Enquiries");
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
        choice = ch.nextInt();
        switch (choice){
            case 1:
                editEnquiry();
                return false;

            case 2:
                deleteEnquiry();
                return false;
            
            case 3:
                viewMyEnquiries();
                return false;
            
            case 0:
                return true;
            default:
                System.out.print("Invalid choice. 0 to return to main menu. ");
                return false;
        }

    }
}