package enitities;
import java.util.ArrayList;

/**
 * The `Student' class represents a student user in the CAMs (Camp Administration Management System).
 * The 'Student' class is a subclass of the `User` class.
 */
public class Student extends User {

    /**
     * Represents the list of camps where the student is participating as a camp committee.
     */
    private ArrayList<String> committeeCamp;
    /**
     * Represents the list of camps where the student is participating as a camp attendee.
     */
    private ArrayList<String> attendeeCamp;
    /**
     *  Represents the list of camps where the student has quit participating.
     */
    private ArrayList<String> blackListCamp;
    /**
     * Represents the number of points the student has accumulated as a camp committee member.
     */
    private int points;

    /**
     * represents the maximum number of camps that student can join as a Camp Committee which is 1.
     */
    public static final int MAX_COMM_PART = 1;

    /**
     * Constructs a new Student object with the specified attributes.
     *
     * @param name          The name of the student.
     * @param email         The email address of the student.
     * @param faculty       The faculty to which the student belongs.
     * @param networkID     The unique network ID of the student.
     * @param password      The password of the student that corresponds to the unique student ID.
     * @param committeeCamp The list of camps where the student is a camp committee.
     * @param attendeeCamp  The list of camps where the student is a camp attendee.
     * @param points        The points collected by student as a camp committee.
     * @param blackListCamp The list of camps that student has quit participating.
     */
    public Student(String name, String email, Faculty faculty, String networkID, Password password, ArrayList<String> committeeCamp, ArrayList<String> attendeeCamp, int points, ArrayList<String> blackListCamp) {
        super(name, email, faculty, networkID, password);
        this.committeeCamp = new ArrayList<>(committeeCamp);
        this.attendeeCamp = new ArrayList<>(attendeeCamp);
        this.blackListCamp = new ArrayList<>(blackListCamp);
        this.points = points;
    }

    /**
     *
     * @return Gets the list of camps names that student is a camp committee member.
     */
    public ArrayList<String> getCampCommittee() {
        return this.committeeCamp;
    }

    /**
     *
     * @return Gets the list of camp names where the student is an attendee.
     */
    public ArrayList<String> getCampAttendee() {
        return this.attendeeCamp;
    }

    /**
     * Sets the list of camp names where the student is an attendee.
     * @param newCommitteeCamp The new list of camp names where the student is a camp committee.
     */
    public void setCampCommittee(ArrayList<String> newCommitteeCamp) {
        this.committeeCamp = new ArrayList<>(newCommitteeCamp);
    }

    /**
     *
     * @param newAttendeeCamp The new list of camp names where the student is a camp committee.
     */
    public void setCampAttendee(ArrayList<String> newAttendeeCamp) {
        this.attendeeCamp = new ArrayList<>(newAttendeeCamp);
    }

    /**
     * Gets the number of points the student has earned as a Camp Committee member.
     * @return Number of points.
     */
    public int getPoints() {
        return this.points;
    }


    /**
     * Adds points to the student's previous tally of points.
     * @param num Number of points to be added.
     */
    public void addPoints(int num) {
        this.points += num;
    }

    // Getter for blackListCamp

    /**
     * Gets the list of camp names the student has quit joining
     * @return The list of camp names the student has quit joining.
     */
    public ArrayList<String> getBlackListCamp() {
        return this.blackListCamp;
    }

    // Setter for blackListCamp

    /**
     * Sets the list of camp names the student has quit participating
     * @param newBlackListCamp The new list of camp names where the student has quit joining
     */
    public void setBlackListCamp(ArrayList<String> newBlackListCamp) {
        this.blackListCamp = new ArrayList<>(newBlackListCamp);
    }

    /**
     * Adds a new camp to the list of camps that student has quit joining.
     * @param campName Name of camp that student has recently quit joining.
     */
    public void addToBlackListCamp(String campName){
        blackListCamp.add(campName);
    }

    /**
     * Removes a camp from the list of camps that student attends as attendee.
     * @param campName The camp name that student no longer participates as an attendee.
     */
    public void removeFromAttendeeCamp(String campName){
        attendeeCamp.remove(campName);
    }

    /**
     * Adds a new camp to the list of camps student joins as an attendee.
     * @param campName The camp name the student is joining as an attendee.
     */
    public void addToAttendeeCamp(String campName){
        attendeeCamp.add(campName);
    }

    /**
     * Adds a new camp to the list of camps student joins as a committee
     * @param campName The new camp name the student is joining as a committee
     */
    public void addToCommitteeCamp(String campName){
        committeeCamp.add(campName);
    }

    /**
     * Gets the max number of camps that student can be a camp committee
     * @return The maximum number of camps that student can participate as committee.
     */
    public static int getMAX(){
        return MAX_COMM_PART;
    }
}
