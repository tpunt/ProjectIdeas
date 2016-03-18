package tpunt.project.models.persistence;

import tpunt.project.models.entities.Address;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Performs database actions on the Address domain model object.
 * 
 * This functionality was not implemented in the end due to time constraints
 * 
 * @author tpunt
 */
@Stateless
public class AddressFacade extends AbstractFacade<Address> {

    @PersistenceContext(unitName = "ProjectsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AddressFacade() {
        super(Address.class);
    }
    
}
