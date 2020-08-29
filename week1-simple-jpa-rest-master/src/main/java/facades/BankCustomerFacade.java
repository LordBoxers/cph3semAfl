package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BankCustomerFacade {

    private static BankCustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private BankCustomerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BankCustomerFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankCustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    private CustomerDTO getCustomerByID(long id) {
        EntityManager em = getEntityManager();
        try {
            CustomerDTO c = em.find(CustomerDTO.class, id);
            return c;
        } finally {
            em.close();
        }
    }
    private List<CustomerDTO> getCustomerByName(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<BankCustomer> qry = em.createQuery("SELECT e FROM BankCustomer e WHERE e.firstName = :name", BankCustomer.class);
            qry.setParameter("name", name);
            List<CustomerDTO> cl = new ArrayList<CustomerDTO>();
            for (BankCustomer e : qry.getResultList()) {
                CustomerDTO tempcust = new CustomerDTO(qry.getSingleResult());
                cl.add(tempcust);
            }
            return cl;
        } finally {
            em.close();
        }
    }
    private BankCustomer addCustomer(BankCustomer cust) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }
    }
    private List<BankCustomer> getAllBankCustomers() {
        EntityManager em = getEntityManager();
        try {
            Query qry = em.createQuery("SELECT e.firstName FROM BANKCUSTOMER");
            List<BankCustomer> cl = qry.getResultList();
            return cl;
        } finally {
            em.close();
        }
    }
    
}
