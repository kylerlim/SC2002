package controllers;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import enitities.Staff;
import enitities.Student;
import enitities.User;

/**
 * The ChangePasswordController class implements the changePassword interface
 * and provides functionality to change the password for a given user (Student or Staff).
 */
public class ChangePasswordController implements changePassword {

    /**
     * List of all students in the system.
     */
    ArrayList<Student> studentList;

    /**
     * List of all staff members in the system.
     */
    ArrayList <Staff> staffList;

    /**
     * The user for whom the password is to be changed.
     */
    User user;

    /**
     * Constructs a ChangePasswordController object with the specified user,
     * list of all students, and list of all staff members.
     *
     * @param user          The user for whom the password is to be changed.
     * @param allStudents   List of all students in the system.
     * @param allStaffs     List of all staff members in the system.
     * @throws NoSuchAlgorithmException if the hashing algorithm is not available.
     */
    public ChangePasswordController(User user,ArrayList<Student> allStudents, ArrayList <Staff> allStaffs) throws NoSuchAlgorithmException {
        this.staffList = allStaffs;
        this.studentList = allStudents;
        this.user = user;
        changePassword(user);
    }

    /**
     * Changes the password for the specified user.
     *
     * @param user The user for whom the password is to be changed.
     * @throws NoSuchAlgorithmException if the hashing algorithm is not available.
     */
    public void changePassword(User user) throws NoSuchAlgorithmException {
        if (user instanceof Student){
            for(Student student: studentList){
                if (user.getNetworkID().equals(student.getNetworkID())){
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter your new password");
                    String password;
                    password = sc.next();
                    student.getEncypted_password().setPW(password);
                    return;
                }
            }

        }
        if (user instanceof Staff){
            for(Staff staff: staffList){
                if (user.getNetworkID().equals(staff.getNetworkID())){
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter your new password");
                    String password;
                    password = sc.next();
                    staff.getEncypted_password().setPW(password);
                    return;
                }
            }
        }

    }

}
