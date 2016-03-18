package tpunt.project.models.persistence;

import java.util.List;
import tpunt.project.models.entities.Project;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import tpunt.project.models.entities.User;

/**
 * Performs database actions on the Project domain model object.
 * 
 * This includes persisting it, hydrating it, updating it, and deleting it.
 * 
 * @author tpunt
 */
@Stateless
public class ProjectFacade extends AbstractFacade<Project> {

//    @Inject
    @PersistenceContext(unitName = "ProjectsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Project createProject(Project project) {
        create(project);
        em.flush();
        em.refresh(project);
        return project;
    }

    public ProjectFacade() {
        super(Project.class);
    }

    public List<Project> getAllProjects() {
        Query projectsQuery = em.createQuery(
            "SELECT p FROM Project p JOIN FETCH p.user",
            Project.class
        );
        return projectsQuery.getResultList();
    }

    public List<Project> getFilteredProjects(String title, String owner, String status) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Project> cq = cb.createQuery(Project.class);
        Root<Project> project = cq.from(Project.class);
        Join<Project, User> user = project.join("user");
        
        Predicate t = cb.like(cb.upper(project.get("title")), "%" + title.toUpperCase() + "%");
        Predicate o = cb.like(cb.upper(user.get("username")), "%" + owner.toUpperCase() + "%");
        Predicate s = cb.equal(project.get("status"), status);

        if (!"".equals(status)) {
            cq.where(t, o, s);
        } else {
            cq.where(t, o);
        }

        TypedQuery<Project> typedQuery = em.createQuery(cq);
        return typedQuery.getResultList();
    }

    public Project getProject(Long id) {
        Query projectQuery = em.createQuery("SELECT p FROM Project p WHERE p.id = :id", Project.class);
        projectQuery.setParameter("id", id);
        return (Project) projectQuery.getSingleResult();
    }

    public Project updateProject(Project project) {
        Query updateProject = em.createQuery(
                "UPDATE Project p"
                + " SET p.aimsAndObjectives = :aims"
                + ", p.deliverables = :del"
                + ", p.lastUpdated = CURRENT_DATE"
                + ", p.openQuestions = :openqs"
                + ", p.status = :status"
                + ", p.title = :title"
                + " WHERE p.id = :id",
                Project.class
        );

        updateProject.setParameter("aims", project.getAimsAndObjectives());
        updateProject.setParameter("del", project.getDeliverables());
        updateProject.setParameter("openqs", project.getOpenQuestions());
        updateProject.setParameter("status", project.getStatus());
        updateProject.setParameter("title", project.getTitle());
        updateProject.setParameter("id", project.getId());
        updateProject.executeUpdate();

        return project;
    }

    public void deleteProject(Project project) {
        Query deleteProject = em.createQuery("DELETE FROM Project p WHERE p.id = :id");
        deleteProject.setParameter("id", project.getId());
        deleteProject.executeUpdate();
    }
}
