package tpunt.project.controllers;

import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import tpunt.project.models.entities.User;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import tpunt.project.models.entities.exceptions.InvalidInputException;
import tpunt.project.models.persistence.UserFacade;

/**
 * Handles the user log in.
 *
 * @author tpunt
 */
@Named(value = "registerHandler")
@RequestScoped
public class RegisterHandler {

    @Inject
    private UserFacade userFacade;

    @Inject
    private UserHandler userHandler;

    /**
     * Holds ephemeral user data
     */
    private final User user = new User();
    
    /**
     * Store the errors being generated from a bad form data submission
     */
    private String usernameError = "";
    private String passwordError = "";
    private String emailError = "";
    private String firstNameError = "";
    private String lastNameError = "";
    private String phoneNumberError = "";
    private boolean hasError = false;
    private String error = "";

    public String getError() {
        return error;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getEmailError() {
        return emailError;
    }

    public String getFirstNameError() {
        return firstNameError;
    }

    public String getLastNameError() {
        return lastNameError;
    }

    public String getPhoneNumberError() {
        return phoneNumberError;
    }

    public User getUser() {
        return user;

    }

    /**
     * Handles the action of registering a new user.
     * 
     * @return the page to navigate to
     */
    public String doRegister() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

        try {
            user.setUsername(request.getParameter("register:username"));
        } catch (InvalidInputException iie) {
            usernameError = iie.getMessage();
            hasError = true;
        }
        
        try {
            user.setPassword(request.getParameter("register:password"));
            String passwordHash = user.getPassword(); // hash here
            user.setPassword(passwordHash);
        } catch (InvalidInputException iie) {
            passwordError += iie.getMessage();
            hasError = true;
        }
        
        try {
            user.setEmail(request.getParameter("register:email"));
        } catch (InvalidInputException iie) {
            emailError += iie.getMessage();
            hasError = true;
        }
        
        try {
            user.setPhoneNumber(request.getParameter("register:phoneNumber"));
        } catch (InvalidInputException iie) {
            phoneNumberError += iie.getMessage();
            hasError = true;
        }
        
        try {
            user.setFirstName(request.getParameter("register:firstName"));
        } catch (InvalidInputException iie) {
            firstNameError += iie.getMessage();
            hasError = true;
        }
        
        try {
            user.setLastName(request.getParameter("register:lastName"));
        } catch (InvalidInputException iie) {
            lastNameError += iie.getMessage();
            hasError = true;
        }
        
        if (hasError) {
            return "";
        }

        try {
            userHandler.setUser(userFacade.register(user));
        } catch (EJBTransactionRolledbackException nre) {
//            error = nre.getMessage();
        }
        
        return "/index";
    }

    /**
     * Handles the navigation to the register.xhtml page.
     * 
     * @return the page to navigate to
     */
    public String registerAction() {
        if (userHandler.isAuthenticated()) {
            error = "You are already logged in!";
            return "/index";
        }

        return "/register";
    }
}
