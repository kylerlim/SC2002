package enitities;
import java.util.List;
import java.util.Scanner;


import helpers.DateHandler;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The `Camp` class represents camps that will be administrated
 * by CAMs (Camp Administration Management System).
 * It implements interfaces CampRoles and CampInformation.
 */
public class Camp implements CampRoles, CampInformation {

    /**
     * The maximum number of committee members allowed for a camp.
     */
    public static final int MAX_COMMITTEE = 10;


    String staffIC;
    // Attributes for CampRoles
    private String creator;
    private ArrayList<String> committeeList;
    private ArrayList<String> attendeeList;
    private ArrayList<String> blackList;

    // Attributes for CampInformation
    private String name;
    private LocalDate startDate;  // Changed to LocalDate
    private LocalDate endDate;    // Changed to LocalDate
    private LocalDate closingDate; // Changed to LocalDate
    private String location;
    private int totalSlots;
    private int committeeSlots;
    private String description;
    private boolean visibility;
    private Faculty openTo;

    /**
     * Default constructor for the `Camp` class.
     */
    public Camp(){
    }
    // Constructor // must remove indexNo

    /**
     * Parameterized constructor for the `Camp` class.
     *
     * @param name          The name of the camp.
     * @param startDate     The start date of the camp.
     * @param endDate       The end date of the camp.
     * @param closingDate   The closing date for camp registration.
     * @param location      The location of the camp.
     * @param totalSlots    The number of slots available for camp attendees.
     * @param committeeSlots The number of slots reserved for committee members.
     * @param description   The description of the camp.
     * @param creator       The creator of the camp.
     * @param attendeeList  The list of camp attendees for the camp.
     * @param committeeList The list of camp committees for the camp.
     * @param openTo        The faculty the camp is open to.
     * @param visibility    The visibility status of the camp.
     * @param blackList     The list of attendees that quit from joining the camp.
     */
    public Camp(String name, String startDate, String endDate, String closingDate,
                String location, int totalSlots, int committeeSlots,
                String description, String creator, ArrayList<String> attendeeList,
                ArrayList<String> committeeList, Faculty openTo, boolean visibility,
                ArrayList<String> blackList) {

        // Assign values from parameters to class attributes

        this.name = name;
        this.startDate = DateHandler.StringToDate(startDate);
        this.endDate = DateHandler.StringToDate(endDate);
        this.closingDate = DateHandler.StringToDate(closingDate);
        this.location = location;
        this.totalSlots = totalSlots;
        this.committeeSlots = committeeSlots;
        this.description = description;
        this.creator = creator;
        this.visibility = visibility;
        this.openTo = openTo;

        // Initialize lists
        this.attendeeList = attendeeList != null ? new ArrayList<>(attendeeList) : new ArrayList<>();
        this.committeeList = committeeList != null ? new ArrayList<>(committeeList) : new ArrayList<>();
        this.blackList = blackList != null ? new ArrayList<>(blackList) : new ArrayList<>();
    }

    // Implementations of CampRoles methods

    /**
     * Gets the Network ID of staff that created the camp.
     * @return Network ID of staff that created the camp.
     */
    @Override
    public String getCreator() {
        return creator;
    }

    /**
     * Sets the creator of the camp using a particular staff's Network ID.
     * @param creator Network ID of staff that created the camp.
     */
    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * Gets the list of camp committees for the camp.
     * @return The list of camp committees for the camp.
     */
    @Override
    public ArrayList<String> getCommitteeList() {
        return committeeList;
    }

    /**
     * Sets the list of camp committees for the camp.
     * @param committeeList The list of camp committees for the camp.
     */
    @Override
    public void setCommitteeList(ArrayList<String> committeeList) {
        this.committeeList = committeeList;
    }

    /**
     * Gets the list of camp attendees for the camp.
     * @return The list of camp attendees for the camp.
     */
    @Override
    public ArrayList<String> getAttendeeList() {
        return attendeeList;
    }

    /**
     * Sets the list of camp attendees for the camp.
     * @param attendeeList The list of camp atttendees for the camp.
     */
    @Override
    public void setAttendeeList(ArrayList<String> attendeeList) {
        this.attendeeList = attendeeList;
    }

    /**
     * Gets the list of students who have quit from joining the camp.
     * @return the list of students that have quit from the camp.
     */
    @Override
    public ArrayList<String> getBlackList() {
        return blackList;
    }

    /**
     * Sets the list of students that have quit from the camp.
     * @param blackList The list of students who have quit from the camp.
     */
    @Override
    public void setBlackList(ArrayList<String> blackList) {
        this.blackList = blackList;
    }

    // Implementations of CampInformation methods

    /**
     * Gets the name of camp.
     * @return The name of the camp.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of camp.
     * @param name The name of the camp.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the starting date of the camp.
     * @return The starting date of the camp.
     */
    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the starting date of the camp
     */
    @Override
    public void setStartDate() {
        LocalDate temp;
        LocalDate earliestDate = LocalDate.parse("2024-01-01");
        do{
        System.out.print("Set start date after "+ earliestDate +" only (YYYYMMDD): ");
        Scanner dateScanner = new Scanner(System.in);
        String dateString = dateScanner.nextLine();
        temp = DateHandler.StringToDate(dateString);
    }while (temp.isBefore(earliestDate));

        startDate = temp;
    }

    /**
     * Gets the end date of the camp.
     * @return The end date of the camp.
     */
    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the camp.
     */
    @Override
    public void setEndDate() {
        LocalDate temp;
        do{
        System.out.print("Set end date after " + startDate + " (YYYYMMDD):");
        Scanner dateScanner = new Scanner(System.in);
        String dateString = dateScanner.nextLine();
        temp = DateHandler.StringToDate(dateString);
    }while (temp.isBefore(startDate));

        endDate = temp;
    }

    /**
     * Gets the closing date for registration of the camp.
     * @return The closing date for registration of the camp.
     */
    @Override
    public LocalDate getClosingDate() {
        return closingDate;
    }

    /**
     * Sets the closing date for registration of the camp.
     */
    @Override
    public void setClosingDate() {
        LocalDate temp;
        do{
        System.out.print("Set registration closing date before " + startDate + " (YYYYMMDD):");
        Scanner dateScanner = new Scanner(System.in);
        String dateString = dateScanner.nextLine();
        temp = DateHandler.StringToDate(dateString);
    }while (temp.isAfter(startDate));

        closingDate = temp;
    }

    /**
     * Gets the location of the camp.
     * @return the location of the camp.
     */
    @Override
    public String getLocation() {
        return location;
    }


    /**
     * Sets the location of the camp.
     */
    @Override
    public void setLocation() {
        Scanner locScanner = new Scanner(System.in);
        System.out.print("Location: ");
        this.location = locScanner.nextLine();
        // locScanner.close();
    }

    /**
     * Gets the total slots of the camp for camp attendees.
     * @return Number of slots for camp attendees.
     */
    @Override
    public int getTotalSlots() {
        return totalSlots;
    }

    /**
     * Sets the total slots of the camp for camp attendees.
     */
    @Override
    public void setTotalSlots() {
        Scanner sc = new Scanner(System.in);
        boolean updated = false;
        int num;
        do{ 
            System.out.print("Total Slots: ");
            num = sc.nextInt();

            if (num < totalSlots){
                System.out.println("You cannot set it less than " + committeeSlots);
                continue;
            }

            else if (num < 0){
                System.out.println("You cannot set negative!");
                continue;
            }
            else if (num < committeeList.size()){
                System.out.println("Cannot set to under " + (attendeeList.size() +committeeList.size()) + " as committee members will be kicked out.");
                continue;
            }

            else if (num < committeeSlots){
                System.out.println("Cannot set it to lower than "+ committeeSlots);
                continue;
            }
            
            updated = true;
        }while(!updated);
        
        totalSlots = num;
    }

    /**
     * Gets the total slots of the camp for camp committee.
     * @return Number of slots for camp committee.
     */
    @Override
    public int getCommitteeSlots() {
        return committeeSlots;
    }

    /**
     * Sets the total slots of the camp for camp committee.
     */
    @Override
    public void setCommitteeSlots() {
        Scanner sc = new Scanner(System.in);
        boolean updated = false;
        int num;
        do{ 
            System.out.print("Committee Slots: ");
            num = sc.nextInt();

            if (num > MAX_COMMITTEE){
                System.out.println("Maximum is 10");
                continue;
            }

            else if (num < 0){
                System.out.println("You cannot set negative!");
                continue;
            }

            else if (num < committeeList.size()){
                System.out.println("Cannot set to under " + committeeList.size() + " as committee members will be kicked out.");
                continue;
            } 
            updated = true;
        }while(!updated);
        
        committeeSlots = num;
    }


    /**
     * Gets the description of the camp.
     * @return The string description of the camp.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the camp.
     */
    @Override
    public void setDescription() {
        Scanner descScanner = new Scanner(System.in);
        System.out.print("Description: ");
        this.description = descScanner.nextLine();
        // descScanner.close();
    }

    /**
     * Gets the visibility boolean of the camp.
     * @return the visibility boolean of the camp. True if visible, False if not visibile.
     */
    @Override
    public boolean getVisibility() {
        return visibility;
    }

    /**
     * Sets the visibility boolean to True or False.
     */
    @Override
    public void setVisibility() {
        String visiString;
        Scanner visiScanner = new Scanner(System.in);
        System.out.print("Open to signups (T/F): ");  
        visiString = visiScanner.nextLine();
        int currentSize = committeeList.size() + attendeeList.size();

        if (currentSize > 0){
            System.out.println("Students have already signed up, cannot change visibility");
            visibility = true;
            return;
        }

        else if (visiString.equals("T")) visibility = true;

        else if (visiString.equals("F") && currentSize == 0){
            visibility = false;
            return;
        }
    }


    /**
     * Prints all the attributes and status of the camp.
     */
    @Override
    public void printAttributes() {

        String reg_cap = String.format("[%d / %d]", (committeeList.size() + attendeeList.size()), totalSlots);
        String regStatus;
        if (closingDate.isAfter(LocalDate.now())){
            regStatus  = "OPENED";
        }
        else
            regStatus = "CLOSED";
        System.out.println("{" + name + "} " + description + " @" + location + "," + "from " + getStartDate()+ " to " + getEndDate() + " {" + regStatus +"} " + " {" +getOpenTo() + "} " + reg_cap + " Open: " + getVisibility() );

    }

    /**
     * Gets the faculty that the camp is open to.
     * @return The faculty that the camp is open to.
     */
    public Faculty getOpenTo(){
            return this.openTo;
        }

    /**
     * Sets which faculty will the camp be open to.
     * @param faculty Faculty that the camp be open to.
     */
    public void setOpenTo(Faculty faculty){
        openTo = faculty;
    }

    /**
     * Adds a student to the camp committee list of the camp.
     * @param studentName Name of student joining as a camp committee.
     */
    public void addToCommitteeList(String studentName) {
        committeeList.add(studentName);
    }

    /**
     * Adds a student to the camp attendee list of the camp.
     * @param studentName Name of student joining as a camp attendee.
     */
    public void addToAttendee(String studentName) {
        attendeeList.add(studentName);
    }

    /**
     * Adds a student to the camp black list of the camp,
     * which indicates student that quit attending the camp.
     * @param studentName Name of student that quit attending the camp.
     */public void addToBlackList(String studentName) {
        blackList.add(studentName);
    }

    /**
     * Removes a student from the attendee list of the camp.
     * @param studentName Name of student that quit attending the camp.
     */
    public void removeFromAttendeeList(String studentName) {
        attendeeList.remove(studentName);
    }
    
}


