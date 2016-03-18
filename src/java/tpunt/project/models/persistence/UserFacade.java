package tpunt.project.models.persistence;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import tpunt.project.models.entities.User;

/**
 * Performs database actions on the User domain model object.
 * 
 * This includes persisting it, hydrating it, updating it, and deleting it.
 * 
 * @author tpunt
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "ProjectsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User register(User user) throws EJBTransactionRolledbackException {
        create(user);
        return user;
    }
    
    public User login(User user) throws EJBTransactionRolledbackException {
        Query userQuery = em.createQuery(
            "SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
            User.class
        );
        userQuery.setParameter("username", user.getUsername());
        userQuery.setParameter("password", user.getPassword());
        return (User) userQuery.getSingleResult();
    }
    
}
