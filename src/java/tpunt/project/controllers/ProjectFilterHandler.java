package tpunt.project.controllers;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import tpunt.project.models.entities.Project;
import tpunt.project.models.persistence.ProjectFacade;

/**
 * Handle the filtering of projects on the index page.
 * 
 * @author tpunt
 */
@Named(value = "ProjectFilterHandler")
@RequestScoped
public class ProjectFilterHandler {

    @Inject
    private ProjectFacade projectFacade;

    /**
     * Hold the filtered projects list
     */
    private List<Project> projects;
    
    /**
     * Hold the input title to filter projects by
     */
    private String projectTitle = "";

    /**
     * Hold the input owner to filter projects by
     */
    private String projectOwner = "";

    /**
     * Hold the input status to filter projects by
     */
    private String projectStatus = "";

    /**
     * Load the projects according to the 3 filter fields
     */
    @PostConstruct
    public void loadProjects() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

        projectTitle = request.getParameter("filter:title") == null ? "" : request.getParameter("filter:title");
        projectOwner = request.getParameter("filter:owner") == null ? "" : request.getParameter("filter:owner");
        projectStatus = request.getParameter("filter:status") == null ? "" : request.getParameter("filter:status");
        
        projects = projectFacade.getFilteredProjects(projectTitle, projectOwner, projectStatus);
    }
    
    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        if (projectStatus == null) {
            projectStatus = "";
        }

        this.projectStatus = projectStatus;
    }
}
