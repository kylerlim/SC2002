package controllers;
import java.util.ArrayList;
import java.util.Scanner;

import enitities.Camp;
import enitities.Enquiry;
import enitities.Staff;
import enitities.Student;
import enitities.User;

/**
 * The CampAuthorityController class implements functionality related to viewing and replying to enquiries
 * for staff members with camp authority. It allows staff to view enquiries related to camps they are involved
 * with and reply to those enquiries.
 *
 * The CampAuthorityController controller class implements interfaces viewEnquiries, replyEnquiries,
 * selectEnquiry and Controller.
 */
public class CampAuthorityController implements viewEnquiries, replyEnquiries, selectEnquiry, Controller{

    /**
     * The user associated with this controller.
     */
    User user;
    /**
     * List of camp names associated with the user.
     */
    ArrayList<String> myCamplist = new ArrayList<String>();
    /**
     * List of all enquiries in the system.
     */
    ArrayList<Enquiry> EnquiryList;
    /**
     * The selected enquiry for processing.
     */
    Enquiry selectedEnquiry;
    /**
     * List of enquiries related to the user.
     */
    ArrayList<Enquiry> myEnquiries = new ArrayList<Enquiry>() ;

     /**
     * Constructs a CampAuthorityController object with the specified user, list of camps, and list of enquiries.
     *
     * @param user        The user associated with this controller.
     * @param Camplist    List of camp names associated with the user.
     * @param EnquiryList List of all enquiries in the system.
     */
    public CampAuthorityController(User user,ArrayList<Camp> Camplist,ArrayList<Enquiry> EnquiryList){
        this.user= user;
        this.EnquiryList = EnquiryList;

        if (user instanceof Staff){
            Staff staff = (Staff) user;
            for (Enquiry eq : EnquiryList){
                if (staff.getCreatedCamps().contains(eq.getCampName())){
                    myEnquiries.add(eq);
                }
            }
        }
        else if (user instanceof Student){
            Student student = (Student) user;
            for (Enquiry eq: EnquiryList){
                if (student.getCampCommittee().contains(eq.getCampName())){
                    myEnquiries.add(eq);
                }
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
     * Allows the user to select an enquiry for further processing.
     */
    public void selectEnquiry(){
        System.out.println("Select enquiry to reply to: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice> myEnquiries.size() || choice < 1){
            System.out.println("Invalid input.");
            return;
        }
        selectedEnquiry = myEnquiries.get((choice - 1));
    }

    /**
     * Displays all enquiries related to the user.
     */
    public void viewEnquiries() {
        int i = 1;
        for(Enquiry enquiry: myEnquiries){
            System.out.print("(" + (i) + ")" + " ");
            i++;
            enquiry.printEnquiryDetails();
            enquiry.setStatus("VIEWED");

        }

    }


    /**
     * Allows the user to reply to a selected enquiry.
     */
    public void replyEnquiries() {
        viewEnquiries();
        selectEnquiry();
        if (!selectedEnquiry.getReply().isEmpty()){
            System.out.println("Enquiry has already been replied to.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Reply: ");
        String reply = sc.nextLine();
        selectedEnquiry.setEnquiryResponder(this.user.getNetworkID());
        selectedEnquiry.setReply(reply);

        if (user instanceof Student){
            ((Student)user).addPoints(1);
        }

	}

    /**
     * Displays the menu options for the user.
     */
    public void printMenu(){
        System.out.println("Select what you wish to do: ");
            System.out.println("(1) View Enquiries");
            System.out.println("(2) Reply Enquiries");
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
                viewEnquiries();
                return false;
            case 2:
                replyEnquiries();
                return false;

            case 0:
                return true;
            
            default:
            System.out.print("Invalid choice. 0 to return to main menu. ");
                return false;
        }

    }

}
