package facades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import entities.Employee;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeExample() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Employee getEmployeeByID(long id) {
        EntityManager em = getEntityManager();
        try {
            Employee c = em.find(Employee.class, id);
            return c;
        } finally {
            em.close();
        }
    }
    public List<Employee> getEmployeeByName(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> qry = em.createQuery("SELECT e FROM Employee e WHERE e.firstName = :name", Employee.class);
            qry.setParameter("name", name);
            return qry.getResultList();
        } finally {
            em.close();
        }
    }
    public List<Employee> getAllEmployee() {
        EntityManager em = getEntityManager();
        try {
            Query qry = em.createQuery("SELECT e FROM Employee e");
            List<Employee> cl = qry.getResultList();
            return cl;
        } finally {
            em.close();
        }
    }
    public Employee highestSalary() {
        EntityManager em = getEntityManager();
        try {
            Query qry = em.createQuery("SELECT MAX(e.salary) FROM Employee e");
            Employee e = (Employee) qry.getSingleResult();
            return e;
        } finally {
            em.close();
        }
    }
    public Employee addCustomer(Employee e) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            return e;
        } finally {
            em.close();
        }
    }
    
    

}
