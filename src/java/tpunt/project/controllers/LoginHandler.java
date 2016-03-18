package tpunt.project.controllers;

import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import tpunt.project.models.entities.exceptions.InvalidInputException;
import tpunt.project.models.entities.User;
import tpunt.project.models.persistence.UserFacade;
/**
 * Handles the logging in of the user on the user interface.
 * 
 * @author tpunt
 */
@Named(value = "loginHandler")
@RequestScoped
public class LoginHandler {
    
    @Inject
    private UserFacade userFacade;
    
    @Inject
    private UserHandler userHandler;

    /**
     * Hold ephemeral user data
     */
    private final User user = new User();
    
    /**
     * Hold the error if login fails
     */
    private String error = "";

    public User getUser() {
        return user;
    }

    public String getError() {
        return error;
    }

    /**
     * The action to be performed upon user login.
     * 
     * @return the page to navigate to
     */
    public String doLogin() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

        try {
            user.setUsername(request.getParameter("login:username"));
            user.setPassword(request.getParameter("login:password"));
            String passwordHash = user.getPassword(); // hash here
            user.setPassword(passwordHash);
            userHandler.setUser(userFacade.login(user));
        } catch (InvalidInputException | EJBTransactionRolledbackException e) {
            error = "Login failed.";
        }
        
        return "";
    }
}
