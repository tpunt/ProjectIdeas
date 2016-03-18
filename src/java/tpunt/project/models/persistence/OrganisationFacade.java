package tpunt.project.models.persistence;

import tpunt.project.models.entities.Organisation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Performs database actions on the Organisation domain model object.
 * 
 * This functionality was not implemented in the end due to time constraints
 * 
 * @author tpunt
 */
@Stateless
public class OrganisationFacade extends AbstractFacade<Organisation> {

    @PersistenceContext(unitName = "ProjectsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrganisationFacade() {
        super(Organisation.class);
    }
    
}
