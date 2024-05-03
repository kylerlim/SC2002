
package enitities;

/**
 * The `User` class represents a generic user in the system with basic attributes such as name,
 * email, faculty, network ID, and encrypted password.
 */
public class User {
    /**
     * Name of the user.
     */
    private String name;
    /**
     * Email of the user.
     */
    private String email;
    /**
     * Faculty of the user.
     */
    private Faculty faculty;
    /**
     * Network ID of the user.
     */
    private String networkID;
    /**
     * Encrypted password belonging to the user.
     */
    private Password encypted_password;


    /**
     * Constructs a new `User` object with the specified attributes.
     *
     * @param name            The name of the user.
     * @param email           The email address of the user.
     * @param faculty         The faculty to which the user belongs.
     * @param NetworkID       The unique network ID of the user.
     * @param password The encrypted password associated with the user.
     */
    public User(String name, String email, Faculty faculty, String NetworkID, Password password) {
        this.name = name;
        this.email = email;
        this.faculty = faculty;
        this.networkID = NetworkID;
        this.encypted_password = password;
    }

    /**
     * Default constructor for the `User` class.
     */
    public User() {

    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets the faculty to which the user belongs.
     *
     * @return The faculty of the user.
     */
    public Faculty getFaculty() {
        return this.faculty;
    }

    /**
     * Gets the unique network ID of the user.
     *
     * @return The network ID of the user.
     */
    public String getNetworkID() {return this.networkID;}

    /**
     * Prints the attributes of the user.
     */
    public void printUserAttributes(){
        System.out.print("Name: " + getName());
        System.out.print("\tFaculty: " + getFaculty());
        System.out.println("\tNetwork ID: " + getNetworkID());
    }

    /**
     * Gets the encrypted password associated with the user.
     *
     * @return The encrypted password of the user.
     */
    public Password getEncypted_password(){
        return this.encypted_password;
    }

}
