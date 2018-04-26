package org.alex323glo.its_simulator.service;

import org.alex323glo.its_simulator.exception.AppException;
import org.alex323glo.its_simulator.model.User;
import org.alex323glo.its_simulator.model.UserExtension;
import org.alex323glo.its_simulator.model.UserGameProfile;

import java.util.List;

/**
 * General User Service.
 *
 * @author Alexey_O
 * @version 0.1
 */
public interface UserService {

    /**
     * Registers new User to System.
     *
     * @param username unique not-null username.
     * @param password not-null password.
     * @param email unique not-null email.
     * @return registered User.
     * @throws AppException if System can't register User in some reasons. Possible reasons:
     *  - User with such username and/or email was registered to system before (UserDuplicationException)
     *  - username, password, or email didn't pass validation via Validator class logic (ValidationException)
     *
     * @see org.alex323glo.its_simulator.util.Validator
     */
    User registerUser(String username, String password, String email) throws AppException;

    /**
     * Searches for User record by its username.
     *
     * WARNING! This method doesn't give guarantees that additional
     * userExtension and userGameProfile fields will be properly initialized.
     * If you want to get User object with all fully initialized fields (and without loop references),
     * please, use findUser() method (referenced below).
     *
     * @param username unique not-null username.
     * @return needed User, if it was registered to System before, or null, if it wasn't.
     * @throws AppException if System can't search for User in some reasons.
     *
     * @see UserService#findUser(String)
     */
    User findLightUser(String username) throws AppException;

    /**
     * Searches for User record by its username.
     *
     * WARNING! This method (as result) gives you User object with all
     * fully initialized fields (and without loop references). If you want to get light-weight
     * User object (with not initialized userExtension and userGameProfile fields),
     * please, use findLightUser() method (referenced below).
     *
     * @param username unique not-null username.
     * @return needed User, if it was registered to System before, or null, if it wasn't.
     * @throws AppException if System can't search for User in some reasons.
     *
     * @see UserService#findUser(String)
     */
    User findUser(String username) throws AppException;

    /**
     * Removes User from System.
     * WARNING! This operation will delete all records, dependent on this User (cascade operation)!!!
     *
     * @param user User, which will be removed.
     * @return removed User.
     * @throws AppException if System can't delete User in some reasons.
     */
    User deleteUser(User user) throws AppException;   // DANGER (will delete in cascade) !!!



    /**
     * Changes UserExtension of needed User
     * (will simply replace old extension with new one).
     *
     * @param username unique not-null username.
     * @param newUserExtension new instance of User extension.
     * @return replaced (old) UserExtension.
     * @throws AppException if System can't change User's Extension in some reasons.
     */
    UserExtension changeUserExtension(String username, UserExtension newUserExtension) throws AppException;

    /**
     * Searches for UserExtension record by User's username.
     *
     * @param username unique not-null username.
     * @return needed UserExtension, if User with such username was registered to System,
     * or null, if it wasn't.
     * @throws AppException if System can't find UserExtension in some reasons. Possible reasons: <br>
     *  - username didn't pass validation via Validator class logic (ValidationException) <br>
     */
    UserExtension findUserExtension(String username) throws AppException;

    /**
     * TODO finish doc (UserService.findUserGameProfile())
     * @param username
     * @return
     * @throws AppException
     */
    UserGameProfile findUserGameProfile(String username) throws AppException;



    /**
     * Lists all Users.
     *
     * @return List of all Users.
     * @throws AppException if System can't list all Users in some reasons.
     */
    List<User> findAllUsers() throws AppException;
}
