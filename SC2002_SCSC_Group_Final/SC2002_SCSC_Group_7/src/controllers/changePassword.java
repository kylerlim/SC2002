package controllers;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import enitities.User;

/**
 * The {@code changePassword} interface provides a method for changing the password of a user.
 */
public interface changePassword {

    /**
     * Changes the password of the specified user.
     *
     * @param user The user for whom the password needs to be changed.
     * @throws NoSuchAlgorithmException If the required cryptographic algorithm is not available.
     */
    void changePassword(User user) throws NoSuchAlgorithmException;
}

