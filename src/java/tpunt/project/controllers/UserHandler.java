package tpunt.project.controllers;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import tpunt.project.models.business.PrivilegeLevel;
import tpunt.project.models.entities.User;

/**
 * Handles the currently logged in user.
 * 
 * This includes performing authentication and authorisation checks.
 * 
 * @author tpunt
 */
@Named(value = "userHandler")
@SessionScoped
public class UserHandler implements Serializable {
    
    /**
     * Holds the logged in user's data
     */
    private User user = null;

    /**
     * Performs a logout by nullifying the user
     */
    public void logout() {
        user = null;
    }
    
    /**
     * Authentication checks if the user is logged in or not
     * 
     * @return where the user is authenticated or not
     */
    public boolean isAuthenticated() {
        return user != null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Checks to see if the logged in user is the same as the user from the project.
     * 
     * This is used to ensure that only users who own a project can modify it.
     * 
     * @param userFromProject
     * 
     * @return whether the two users are the same
     */
    public boolean isLoggedInUser(User userFromProject) {
        if (!isAuthenticated()) {
            return false;
        }

        return userFromProject.equals(user);
    }
    
    /**
     * Checks to see if the user is authorised to perform an action
     * 
     * @param privileges
     * @return whether the user is authorised or not
     */
    public boolean isAuthorised(PrivilegeLevel privileges) {
        if (!isAuthenticated()) {
            return false;
        }

        return user.getPrivilegeLevel().getValue() >= privileges.getValue();
    }
    
    /**
     * A convenience method to check if the user is an admin
     * 
     * @return whether the user is an admin or not
     */
    public boolean isAdmin() {
        return isAuthorised(PrivilegeLevel.ADMIN);
    }
    
    /**
     * Handles the logging out of the user
     * 
     * @return the page to navigate to
     */
    public String doLogout() {
        logout();

        return "/index";
    }
}
