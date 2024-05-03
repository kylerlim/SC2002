package enitities;
import java.util.ArrayList;

/**
 * The `Staff` class represents staff members in the CAMs system.
 * The 'Staff' class is a subclass of the `User` class.
 * Staff members have the ability to create and be in charge of camps.
 */
public class Staff extends User {
    /**
     *  The list of camps created by the particular staff member.
     */
    private ArrayList<String> createdCamps;

    /**
     * Parameterised constructor for the 'Staff' Class.
     * Constructs a new staff member with the specified attributes.
     *
     * @param name           The name of the staff member.
     * @param email          The email address of the staff member.
     * @param faculty        The faculty to which the staff member belongs.
     * @param networkID      The unique network ID of the staff member.
     * @param password       The password associated with the staff member's account.
     * @param createdCamps   The list of camps created by the staff member.
     */
    public Staff(String name, String email, Faculty faculty, String networkID, Password password, ArrayList<String> createdCamps) {
        super(name, email, faculty, networkID, password);
        this.createdCamps = new ArrayList<>(createdCamps); 
    }
    /**
     * Default constructor for the `Staff` class.
     */
    public Staff(){

    }

    /**
     * Gets the list of camps created by the staff member.
     *
     * @return The list of created camps.
     */
    public ArrayList<String> getCreatedCamps() {
        return this.createdCamps;
    }

    /**
     * Sets the list of camps created by the staff member.
     *
     * @param newCreatedCamps The new list of created camps.
     */
    public void setCreatedCamps(ArrayList<String> newCreatedCamps) {
        this.createdCamps = new ArrayList<>(newCreatedCamps);
    }


    /**
     * Adds a new camp to the list of camps created by the staff member.
     *
     * @param campName The name of the camp to be added.
     */
    public void addToCreatedCamps(String campName){
        createdCamps.add(createdCamps.size(), campName);
    }
}
