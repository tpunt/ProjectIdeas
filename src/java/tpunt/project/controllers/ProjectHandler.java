package tpunt.project.controllers;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import tpunt.project.models.entities.Project;
import java.util.List;
import java.util.Map;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import tpunt.project.models.business.PrivilegeLevel;
import tpunt.project.models.entities.exceptions.InvalidInputException;
import tpunt.project.models.persistence.ProjectFacade;

/**
 * Handle the management of projects from the user interface.
 * 
 * @author tpunt
 */
@Named(value = "projectHandler")
@RequestScoped
public class ProjectHandler implements Serializable {

    @Inject
    private ProjectFacade projectFacade;

    @Inject
    private UserHandler userHandler;

    /**
     * Hold ephemeral project data
     */
    private Project project = new Project();
    
    /**
     * Hold related errors to project form submission
     */
    private String titleError = "";
    private String aimsAndObjectivesError = "";
    private String openQuestionsError = "";
    private String deliverablesError = "";
    private String statusError = "";
    private boolean hasError = false;
    private String error = "";

    public String getTitleError() {
        return titleError;
    }

    public String getAimsAndObjectivesError() {
        return aimsAndObjectivesError;
    }

    public String getOpenQuestionsError() {
        return openQuestionsError;
    }

    public String getDeliverablesError() {
        return deliverablesError;
    }

    public String getStatusError() {
        return statusError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Pulls the form data into the ephemeral project field to be persisted.
     * 
     * @param formType 
     */
    private void populateProjectFields(String formType) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        try {
            project.setTitle(request.getParameter(formType + ":title"));
        } catch (InvalidInputException iie) {
            titleError = iie.getMessage();
            hasError = true;
        }

        try {
            project.setAimsAndObjectives(request.getParameter(formType + ":aimsAndObjectives"));
        } catch (InvalidInputException iie) {
            aimsAndObjectivesError = iie.getMessage();
            hasError = true;
        }

        try {
            project.setOpenQuestions(request.getParameter(formType + ":openQuestions"));
        } catch (InvalidInputException iie) {
            openQuestionsError = iie.getMessage();
            hasError = true;
        }

        try {
            project.setDeliverables(request.getParameter(formType + ":deliverables"));
        } catch (InvalidInputException iie) {
            deliverablesError = iie.getMessage();
            hasError = true;
        }

        if (userHandler.isAdmin()) {
            try {
                project.setStatus(request.getParameter(formType + ":status"));
            } catch (InvalidInputException iie) {
                statusError = iie.getMessage();
                hasError = true;
            }
        }
    }

    /**
     * Handles the action of creating new projects via the user interface.
     * 
     * @return the page to navigate to
     */
    public String doCreateProject() {
        if (!userHandler.isAuthorised(PrivilegeLevel.STUDENT)) {
            error = "Authentication error: You must be logged in to create new projects!";
            return "";
        }

        populateProjectFields("createproject");
        
        if (hasError) {
            return "";
        }
        
        try {
            project.setUser(userHandler.getUser());
            project = projectFacade.createProject(project);
        } catch (EJBTransactionRolledbackException nre) {
//            error = nre.getMessage();
        }

        return "/project";
    }

    /**
     * Handles the action of updating projects via the user interface.
     * 
     * @return the page to navigate to
     */
    public String doUpdateProject() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        Long projectId = Long.parseLong(params.get("id"));
        Project p = projectFacade.getProject(projectId);
        if (!userHandler.isLoggedInUser(p.getUser()) || !userHandler.isAuthorised(PrivilegeLevel.ADMIN)) {
            error = "Authentication error: This is not your project to update!";
            project = p;
            return "";
        }

        populateProjectFields("editproject");

        if (hasError) {
            project = p;
            return "";
        }

        try {
            project.setId(projectId);
            project = projectFacade.updateProject(project);
            project = doFetchProject(projectId);
        } catch (EJBTransactionRolledbackException nre) {
//            error = nre.getMessage();
        }

        return "/project";
    }

    /**
     * Handles the action of deleting projects via the user interface.
     * 
     * @return the page to navigate to
     */
    public String doDeleteProject() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        Long projectId = Long.parseLong(params.get("id"));
        Project p = projectFacade.getProject(projectId);

        if (!userHandler.isLoggedInUser(p.getUser()) || !userHandler.isAuthorised(PrivilegeLevel.ADMIN)) {
            error = "Authentication error: This is not your project to delete!";
            return "";
        }

        project.setId(projectId);
        projectFacade.deleteProject(project);

        return "/index";
    }

    public List<Project> doFetchAllProjects() {
        return projectFacade.getAllProjects();
    }

    public Project doFetchProject(Long projectId) {
        return projectFacade.getProject(projectId);
    }

    /**
     * Load the project when landing on a project.xhtml page to view a specific project.
     * 
     * @return the page to navigate to
     */
    public String loadProject() {
        if (project.getId() != null) {
            project = projectFacade.getProject(project.getId());
            return "/project";
        }
        
        return "/index";
    }

    /**
     * Handles the navigation of project.xhtml to editproject.xhtml.
     * 
     * It checks the authentication and authorisation of the user.
     * 
     * @return the page to navigate to
     */
    public String editAction() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        project = doFetchProject(Long.parseLong(params.get("id")));

        if (!userHandler.isLoggedInUser(project.getUser()) || !userHandler.isAuthorised(PrivilegeLevel.ADMIN)) {
            error = "Authentication error: This is not your project to update!";
            return "";
        }

        return "/editproject";
    }

    /**
     * Handles the navigation of index.xhtml to project.xhtml.
     * 
     * @return the page to navigate to
     */
    public String viewAction() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        project = doFetchProject(Long.parseLong(params.get("id")));

        return "/project";
    }

    /**
     * Handles the navigation to createproject.xhtml.
     * 
     * It checks to see if the user is authenticated.
     * 
     * @return the page to navigate to
     */
    public String newAction() {
        if (!userHandler.isAuthenticated()) {
            error = "Authentication error: You must be logged in to create new projects!";
            return "";
        }

        return "/createproject";
    }
}
