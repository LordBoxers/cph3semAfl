/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mibsen
 */
public class DBfacade {
    private static EntityManagerFactory emf;
    private static DBfacade instance;

    private DBfacade() {}

    public static DBfacade getBookFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DBfacade();
        }
        return instance;
    }
    
    public Customer addCustomer(String firstName, String lastName){
        Customer cust = new Customer(firstName, lastName);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        }finally {
            em.close();
        }
    }
    
    public Customer findByID(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Customer cust = em.find(Customer.class,id);
            return cust;
        }finally {
            em.close();
        }
    }
    public List<Customer> allCustomers(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = em.createQuery("Select cust from Customer cust",Customer.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    public List<Customer> findByLastName(String name){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT e FROM Customer e WHERE e.firstName = :name", Customer.class);
            query.setParameter("name", name);
            
            
            List<Customer> cl = new ArrayList<>();
            for (Customer e : query.getResultList()) {
                cl.add(e);
            }
            return cl;
        } finally {
            em.close();
        }
    }
    
    public int getNumberOfCustomers() {
        
        int res = allCustomers().size();
        return res;
        
    }

}
