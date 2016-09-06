package com.aspigrow.persistence.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.aspigrow.persistence.entities.user.User;

public interface UserDao extends GenericDao<User, Long> {

    /**
     * Saves a user's information.
     * 
     * @param user the object to be saved
     * @return the persisted User object
     * @throws Exception 
     */
    User saveUser(User user) throws Exception;

    /**
     * Retrieves the password in DB for a user
     * 
     * @param userId the user's id
     * @return the password in DB, if the user is already persisted
     * @throws Exception 
     */
    String getUserPassword(Long userId) throws Exception;

    /**
     * Retrieves the user active status
     *
     * @param userId the user's id
     * @return the status in Boolean format
     * @throws Exception
     */
    boolean inActiveUser(Long userId) throws Exception;
    
    /**
     * Delete the user's information and update status
     *
     * @param userId the user's id
     * @return the status in Boolean format
     * @throws Exception
     */
    boolean deleteUser(Long userId) throws Exception;

    /**
     * Gets users information based on login name.
     * 
     * @param username the user's username
     * @return userDetails populated userDetails object
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException thrown when user not
     * found in database
     */
    User loadUserByUsername(String username) throws UsernameNotFoundException;
    
    /**
     * Gets users information based on login name.
     * 
     * @param email the user's email
     * @return userDetails populated userDetails object
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException thrown when user not
     * found in database
     */
    User findByEmail(String email) throws UsernameNotFoundException;

}
